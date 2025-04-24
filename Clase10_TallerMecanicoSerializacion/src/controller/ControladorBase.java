package controller;

import model.Cliente;
import model.Modelo;
import model.Usuario;
import view.Vista;

public abstract class ControladorBase {
    protected Modelo modelo;
    protected Vista vista;
    protected Cliente clienteActual;

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }
    
    public Cliente obtenerCliente(String dpi) {
        for(Usuario u:this.modelo.getListaUsuarios()) {
            if(u instanceof Cliente) {
                if(u.getDpi().equals(dpi)) {
                    return (Cliente) u;
                }
            }
        }
        return null;
    }
    
    public void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}