package controller;

import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import model.Cliente;
import model.OrdenTrabajo;
import model.Mecanico;

public class FlujoControlador extends ControladorBase {
    
    private boolean terminado;
    private JProgressBar barraEspera;
    private JProgressBar barraServicio;
    private JProgressBar barraListos;
    private JLabel labelEspera;
    private JLabel labelServicio;
    private JLabel labelListos;
    
    private final int TIEMPO_ESPERA = 15000;
    private final int TIEMPO_SERVICIO = 10000;
    private final int TIEMPO_ENTREGA = 4000;
    private boolean iniciado;

    public FlujoControlador() {
        this.terminado = false;
        this.iniciado = false;
    }
    
    public void iniciarFlujo() {
        //Se crea un objeto POC cada vez que se inicie un flujo
        ProcesoOrdenControlador poc = new ProcesoOrdenControlador();
        
        //Usa el mismo modelo y la vista
        poc.setModelo(this.modelo);
        poc.setVista(this.vista);
        poc.setClienteActual(this.clienteActual);
        
        //El metodo que verifica el espacio en las colas para poner las ordenes en el lugar correcto
        poc.verificarEspacio(this.clienteActual);
        
        //Se le muestra al admin el estado de las colas y al cliente sus carros listos
        poc.mostrarEstadoActual();
        
        //Si nunca se ha iniciado o ya termino de procesar ordenes, se comienza
        if(!this.iniciado || this.terminado) {
            this.iniciado = true;
            this.terminado = false;
            //Hilos que van a iniciar los procesos de las colas
            new Thread(this::procesarColaEspera).start();
            new Thread(this::procesarColaServicio).start();
            new Thread(this::procesarColaEntrega).start();
        }
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
                
                //Se obtiene al cliente de la orden
                Cliente clienteOrden = this.obtenerCliente(orden.getCliente());
                //Se obtiene la barra y el label de espera de la ventana del cliente de la orden obtenida
                this.obtenerElementosEspera(clienteOrden); 
                
                System.out.println("Cliente orden espera " + clienteOrden.getNombreUsuario());
                
                //Procesando la orden en la cola de espera
                this.setearTextoLabel(this.labelEspera, orden.getPlaca() + " - " + orden.getId());
                procesarBarraProgreso(TIEMPO_ESPERA, this.barraEspera);
                
                this.intentarDarServicio(); //Se intenta pasar de espera a servicio
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
                
                //Se obtiene al cliente de la orden
                Cliente clienteOrden = this.obtenerCliente(orden.getCliente());
                //Se obtiene la barra y el label de servicio de la ventana del cliente de la orden obtenida
                this.obtenerElementosServicio(clienteOrden); 
                System.out.println("Cliente orden servicio " + clienteOrden.getNombreUsuario());

                //Si ya está en proceso, este cliente la ignora
                if (!orden.marcarEnProceso()) {
                    dormir(300);
                    continue;
                }

                //Procesando la orden en la cola de servicio
                this.setearTextoLabel(this.labelServicio, orden.getPlaca() + " - " + orden.getId());
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
                
                //Se obtiene al cliente de la orden
                Cliente clienteOrden = this.obtenerCliente(orden.getCliente());
                System.out.println("Cliente orden listos " + clienteOrden.getNombreUsuario());

                //Se obtiene la barra y el label de listos de la ventana del cliente de la orden obtenida
                this.obtenerElementosListos(clienteOrden); 
                
                //Procesando la orden en la cola de listos
                this.setearTextoLabel(this.labelListos, orden.getPlaca() + " - " + orden.getId());
                procesarBarraProgreso(TIEMPO_ENTREGA, this.barraListos);
                
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
    
    private void obtenerElementosEspera(Cliente clienteOrden) {
        this.barraEspera = clienteOrden.getVentanaProgreso().getEsperaBarra();
        this.labelEspera = clienteOrden.getVentanaProgreso().getLabelEspera();
    }
    
    private void obtenerElementosServicio(Cliente clienteOrden) {
        this.barraServicio = clienteOrden.getVentanaProgreso().getServicioBarra();
        this.labelServicio = clienteOrden.getVentanaProgreso().getLabelServicio();
    }
    
    private void obtenerElementosListos(Cliente clienteOrden) {
        this.barraListos = clienteOrden.getVentanaProgreso().getListosBarra();
        this.labelListos = clienteOrden.getVentanaProgreso().getLabelListos();
    }
    
    private void setearTextoLabel(JLabel label, String texto) {
        SwingUtilities.invokeLater(() ->
            label.setText(texto)
        );
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