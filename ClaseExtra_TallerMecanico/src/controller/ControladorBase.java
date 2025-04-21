package controller;

import model.Modelo;
import view.Vista;

public abstract class ControladorBase {
    protected Modelo modelo;
    protected Vista vista;

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
    
    public void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}