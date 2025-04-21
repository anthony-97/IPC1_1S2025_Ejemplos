package controller;

import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import model.Cliente;
import model.OrdenTrabajo;
import model.Mecanico;

public class FlujoControlador extends ControladorBase implements Runnable {
    
    private boolean terminado;
    private Cliente clienteFlujo;
    private JProgressBar barraEspera;
    private JProgressBar barraServicio;
    private JProgressBar barraListos;
    private JLabel labelEspera;
    private JLabel labelServicio;
    private JLabel labelListos;
    
    private final int TIEMPO_ESPERA = 15000;
    private final int TIEMPO_SERVICIO = 10000;
    private final int TIEMPO_ENTREGA = 4000;

    public FlujoControlador(Cliente cliente) {
        this.clienteFlujo = cliente;
        this.terminado = false;
    }
    
    public void obtenerElementosVista() {
        this.barraEspera = this.clienteFlujo.getVentanaProgreso().getEsperaBarra();
        this.barraServicio = this.clienteFlujo.getVentanaProgreso().getServicioBarra();
        this.barraListos = this.clienteFlujo.getVentanaProgreso().getListosBarra();
        this.labelEspera = this.clienteFlujo.getVentanaProgreso().getLabelEspera();
        this.labelServicio = this.clienteFlujo.getVentanaProgreso().getLabelServicio();
        this.labelListos = this.clienteFlujo.getVentanaProgreso().getLabelListos();
    }
    
    public void iniciarFlujo() {
        this.obtenerElementosVista(); //Para obtener los elementos de la ventana
        ProcesoOrdenControlador poc = new ProcesoOrdenControlador(); //El verificador
        //Usa el mismo modelo y la vista
        poc.setModelo(this.modelo);
        poc.setVista(this.vista);
        poc.verificarEspacio(this.clienteFlujo); 
        //Se le muestra al admin el estado de las colas
        poc.mostrarEstadoActual();
        
        //Hilos que van los procesos de las colas
        new Thread(this::procesarColaEspera).start();
        new Thread(this::procesarColaServicio).start();
        new Thread(this::procesarColaEntrega).start();
    }

    @Override
    public void run() {
        //Cada proceso usa un hilo
    }

    private void intentarDarServicio() {
        boolean asignado = false;
        for(Mecanico m: this.modelo.getListaMecanicos()) {
            if(!m.isOcupado()) {
                OrdenTrabajo orden = this.modelo.getColaEspera().remove(0); //Se desencola
                orden.setEstado(2); //Estado de cola de servicio en flujo
                orden.setMecanico(m.getNombre());
                m.setOcupado(true);
                this.modelo.getColaServicio().add(orden); //A servicio
                asignado = true;
                break; //Ya asigno al mecanico para que no asigne doble
            }
        }
        if(!asignado) {
            return; //Retorna nada, o sea, no se pudo pasar de espera a servicio
        }
    }
     
    private void procesarColaEspera() {
        while (!terminado) {
            if(this.noHayMas()) {
                terminado = true;
                break;
            }
            
            if (!this.modelo.getColaEspera().isEmpty()) {
                OrdenTrabajo orden = this.modelo.getColaEspera().get(0); //Primer elemento de la cola de espera
                orden.setEstado(1);
                SwingUtilities.invokeLater(() ->
                    this.labelEspera.setText(orden.getPlaca())
                );
                procesarBarraProgreso(TIEMPO_ESPERA, this.barraEspera);
                this.intentarDarServicio(); //Intentar pasar de espera a servicio
            } else {
                dormir(300);
            }
        }
    }

    private void procesarColaServicio() {
        while (!terminado) {
            if (this.noHayMas()) {
                terminado = true;
                break;
            }
            
            if (!this.modelo.getColaServicio().isEmpty()) {
                OrdenTrabajo orden = this.modelo.getColaServicio().get(0);

                //Si ya está en proceso, este cliente la ignora
                if (!orden.marcarEnProceso()) {
                    dormir(300);
                    continue;
                }
                orden.setEstado(2); //Estado en servicio
                if (this.clienteFlujo.esSuAuto(orden.getPlaca())) {
                    SwingUtilities.invokeLater(() ->
                        this.labelServicio.setText(orden.getPlaca())
                    );
                }

                procesarBarraProgreso(TIEMPO_SERVICIO, this.barraServicio);
                synchronized (this.modelo.getColaServicio()) {
                    this.modelo.getColaServicio().remove(orden);
                }
                this.modelo.getColaListos().add(orden);

                //Despues del servicio, se libera al mecanico
                String nombreMecanico = orden.getMecanico();
                for (Mecanico m : this.modelo.getListaMecanicos()) {
                    if (m.getNombre().equals(nombreMecanico)) {
                        m.setOcupado(false);
                        break;
                    }
                }
            } else {
                dormir(300);
            }
        }
    }

    private void procesarColaEntrega() {
        while (!terminado) {
            if(this.noHayMas()) {
                terminado = true;
                break;
            }
            if (!this.modelo.getColaListos().isEmpty()) {
                OrdenTrabajo orden = this.modelo.getColaListos().get(0);
                orden.setEstado(3);
                if(this.clienteFlujo.esSuAuto(orden.getPlaca())) {
                    SwingUtilities.invokeLater(() ->
                        this.labelListos.setText(orden.getPlaca())
                    );
                }
                procesarBarraProgreso(TIEMPO_ENTREGA, this.barraListos);
                this.clienteFlujo.getVentanaProgreso().llenarCarrosListos(clienteFlujo);
                this.modelo.getColaListos().remove(0);
                this.modelo.getColaPorPagar().add(orden);
            } else {
                dormir(300);
            }
        }
    }

    private void procesarBarraProgreso(int duracion, JProgressBar barra) {
        for (int i = 0; i <= 100; i++) {
            final int progreso = i;
            SwingUtilities.invokeLater(() -> barra.setValue(progreso));
            dormir(duracion / 100);
        }
    }

    private boolean noHayMas() {
        return this.modelo.getColaServicio().isEmpty()
            && this.modelo.getColaListos().isEmpty()
            && this.modelo.getColaPorPagar().isEmpty()
            && !hayOrdenesEnCurso();
    }

    private boolean hayOrdenesEnCurso() {
        //Se recorren todas las órdenes para ver si hay alguna en proceso
        for (OrdenTrabajo o : this.modelo.getColaServicio()) {
            if (o.estaEnProceso()) return true;
        }
        for (OrdenTrabajo o : this.modelo.getColaListos()) {
            if (o.estaEnProceso()) return true;
        }
        return false;
    }
}