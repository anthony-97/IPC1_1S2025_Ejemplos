/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author polares
 */

public class OrdenTrabajo {
    private static int contador = 5000;
    private String id;
    private String placa;
    private String cliente;
    private String servicio;
    private String mecanico;
    private volatile boolean enProceso = false; //Verifica que una orden de trabajo ya esta tomada
    private int estado; //0 sin asignar, 1 en espera, 20 en servicio inicial, 2 en servicio flujo, 3 listo

    public OrdenTrabajo(String placa, String cliente, String servicio, int estado) {
        this.id = Integer.toString(contador++);
        this.placa = placa;
        this.cliente = cliente;
        this.servicio = servicio;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //Marcar que un cliente esta tomando la orden, es decir que esta agarrando en su flujo la orden
    public synchronized boolean marcarEnProceso() {
        if (!enProceso) {
            enProceso = true;
            return true;
        }
        return false;
    }

    public boolean estaEnProceso() {
        return enProceso;
    }

    public void limpiarEnProceso() {
        enProceso = false;
    }
    
}
