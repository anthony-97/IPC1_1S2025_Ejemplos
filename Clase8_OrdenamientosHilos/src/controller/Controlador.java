/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Arrays;
import model.Modelo;
import model.TipoOrdenamiento;
import view.App;
import view.EleccionOrdenamiento;
import view.TipoVentana;
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
    
    
    public void crearVentanas() { //Metodo que crea las ventanas y les setea el controlador
        this.vista.setApp(new App());
        this.vista.getApp().setControlador(this); //Este objeto Controlador
        this.vista.setEleccion(new EleccionOrdenamiento());
        this.vista.getEleccion().setControlador(this);
    }
    
    public void mostrarVentana(TipoVentana.Ventana tipoVentana) {
        javax.swing.JFrame[] ventanas = {
            this.vista.getApp(),
            this.vista.getEleccion()
        };

        //Se ocultan todas las ventanas
        for (javax.swing.JFrame ventana : ventanas) {
            ventana.setVisible(false);
        }
        
        switch (tipoVentana.getIndex()) {
            case 1 -> this.vista.getApp().setVisible(true);
            case 2 -> this.vista.getEleccion().setVisible(true);
        }
    }

    public void ordenar(int velocidad, TipoOrdenamiento.Ordenamiento tipo) { //Ordenar la lista
        this.modelo.ordenarConHilo(velocidad, tipo);
    }
    
    public void llenarOriginal() { //Llenar el panel con la lista original
        this.vista.getApp().llenarLista(Arrays.toString(this.modelo.getPokemones()));
    }
    
    //Este metodo se ira llamando en el hilo que controla el tiempo y las actualizaciones
    public void llenarListaOrdenada() { //Llenar el panel con la lista ordenada
        this.vista.getApp().llenarOrdenada(Arrays.toString(this.modelo.getPokemonesDesordenados()));
    }
    
    //Este metodo se ira llamando en el hilo que controla el tiempo y las actualizaciones
    public void llenarTiempo(String tiempo) {
        this.vista.getApp().llenarLabelTiempo(tiempo);
    }
    
    //Para llenar las listas desplegables de los algoritmos y la velocidad
    public void llenarListas() {
        this.vista.getEleccion().llenarListas();
    }
    
    public void desordenarPokemones() { //Desordena la lista de pokemones
        this.modelo.desordenarPokemones();
    }
    
}