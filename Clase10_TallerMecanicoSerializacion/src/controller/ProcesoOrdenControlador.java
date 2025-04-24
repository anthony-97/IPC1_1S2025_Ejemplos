/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Vector;
import model.Cliente;
import model.Mecanico;
import model.OrdenTrabajo;

/**
 *
 * @author polares
 */
public class ProcesoOrdenControlador extends ControladorBase {
   
    public boolean terminado = false;
            
    //Verifica el espacio de las colas y asigna inicialmente las ordenes de trabajo a donde corresponde
    public void verificarEspacio(Cliente clienteActual) {
        Vector<OrdenTrabajo> ordenes = clienteActual.obtenerOrdenesSinAsignar();
        for(OrdenTrabajo orden: ordenes) {
            System.out.println("Cliente " + orden.getCliente() + "actual " + clienteActual.getNombre());
            
            if(this.modelo.getColaEspera().isEmpty()) { //La cola de espera esta vacia
                boolean asignado = false;
                for(Mecanico m: this.modelo.getListaMecanicos()) {
                    if(!m.isOcupado()) {
                        orden.setEstado(20); //Estado de cola de servicio inicial
                        orden.setMecanico(m.getNombre());
                        m.setOcupado(true);
                        asignado = true;
                        this.modelo.getColaServicio().add(orden); //De una a servicio
                        break; //Ya asigno al mecanico para que no asigne doble
                    }
                }
                if(!asignado) { //No hay mecanicos disponibles
                    this.encolarEnEspera(clienteActual, orden);
                }
            } else { //Ya hay cola de espera
                this.encolarEnEspera(clienteActual, orden);
            }
        }            
    }
    
    public void encolarEnEspera(Cliente c, OrdenTrabajo orden) {
        orden.setEstado(1); //Estado de cola de espera
        if(c.getTipoCliente().equals("oro")) {
            this.modelo.getColaEspera().add(0, orden); //Se agrega al inicio de la cola
        } else {
            this.modelo.getColaEspera().add(orden);
        }
    }
    
    
    public void mostrarEstadoActual() {
        new Thread(() -> {
            while (!this.terminado) {
                this.actualizarTablasColas(); //Actualiza las tablas de las colas al admin
                //Se le van actualizando los carros listos al cliente
                this.clienteActual.getVentanaProgreso().llenarCarrosListos(this.clienteActual);
                dormir(3000);  //Esperamos 3 segundos antes de actualizar el estado
            }
        }).start();
    }
    
    private void actualizarTablasColas() {
        this.vista.getVentanaProgresoAdmin().llenarTablas();
    }
    
}