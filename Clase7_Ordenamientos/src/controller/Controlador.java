/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Arrays;
import model.Modelo;
import view.App;
import view.Vista;

/**
 *
 * @author polares
 */
public class Controlador {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
     public String ordenarBurbuja() {
        this.modelo.ordenarBurbuja();
        return Arrays.toString(this.modelo.getPokemones());
    }

    public String ordenarQuicksort() {
        this.modelo.ordenarQuickSort();
        return Arrays.toString(this.modelo.getPokemones());
    }
    
    public void crearVentanas() { //Metodo que crea las ventanas y les setea el controlador
        this.vista.setApp(new App());
        this.vista.getApp().setControlador(this); //Este objeto Controlador
    }
    
    public void llenarOriginal() {
        this.vista.getApp().llenarLista(Arrays.toString(this.modelo.getPokemones()));
    }
}
