package view;

import controller.Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author polares
 */
public class Vista extends javax.swing.JFrame {
    private App app;
    private EleccionOrdenamiento eleccion;
    private Controlador controlador;

    public Vista() {
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public EleccionOrdenamiento getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionOrdenamiento eleccion) {
        this.eleccion = eleccion;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    
}
