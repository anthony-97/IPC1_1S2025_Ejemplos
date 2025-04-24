/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ProcesoOrdenControlador;
import java.util.Vector;
import view.VerProgreso;

/**
 *
 * @author polares
 */
public class Cliente extends Usuario {
    private Vector<Automovil> listaAutomoviles; 
    private String tipoCliente; //Oro o normal
    private transient VerProgreso ventanaProgreso; //Cada cliente debe tener su propia ventana de barras de progreso
    private Vector<OrdenTrabajo> listaOrdenes; //Cada cliente va a tener su propia lista de ordenes de trabajo

    public Cliente(String dpi, String nombre, String nombreUsuario, String contrasena, String tipoCliente) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipoUsuario = 2;
        this.listaAutomoviles = new Vector<Automovil>();
        this.tipoCliente = tipoCliente; //Por defecto todos los clientes son normales
        this.listaOrdenes = new Vector<OrdenTrabajo>();
    }

    public Vector<Automovil> getListaAutomoviles() {
        return listaAutomoviles;
    }

    public void setListaAutomoviles(Vector<Automovil> listaAutomoviles) {
        this.listaAutomoviles = listaAutomoviles;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }    

    public VerProgreso getVentanaProgreso() {
        return ventanaProgreso;
    }

    public void setVentanaProgreso(VerProgreso VentanaProgreso) {
        this.ventanaProgreso = VentanaProgreso;
    }

    public Vector<OrdenTrabajo> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(Vector<OrdenTrabajo> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    
    public void llenarListasProgreso() {
        this.ventanaProgreso.llenarListas();
    }
    
    public void vaciarTabla() {
        this.ventanaProgreso.vaciarTabla();
    }
    
    public Vector<OrdenTrabajo> obtenerOrdenesSinAsignar() {
        Vector<OrdenTrabajo> ordenesSinAsignar = new Vector<OrdenTrabajo>();
        for(OrdenTrabajo orden: this.listaOrdenes) {
            if(orden.getEstado() == 0) {
                ordenesSinAsignar.add(orden);
            }
        }
        return ordenesSinAsignar;
    }
    
    public boolean esSuAuto(String placa) {
        for(Automovil auto: this.listaAutomoviles) {
            if(auto.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "dpi=" + dpi + ", nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena + ", tipoUsuario=" + tipoUsuario + ", tipoCliente=" + tipoCliente + "}\n";
    }
  
}