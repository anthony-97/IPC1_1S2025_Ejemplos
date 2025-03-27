/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Arrays;
import model.Modelo;
import model.Pokemon;
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
        //Se crea el controlador de los ordenamientos
        OrdenadorControlador ordenador = new OrdenadorControlador(velocidad, tipo);
        ordenador.setModelo(this.modelo);
        ordenador.setVista(this.vista);
        Thread hiloOrdenador = new Thread(ordenador); //Hilo que ordena el array
        hiloOrdenador.start(); //Comienza el ordenamiento
    }
    
    public void llenarOriginal() { //Llenar el panel con la lista original
        GraficadorControlador graficadorOriginal = new GraficadorControlador();
        graficadorOriginal.setModelo(this.modelo);
        graficadorOriginal.setVista(this.vista);
        this.vista.getApp().llenarDesordenada(graficadorOriginal.generarGraficaInicial()); //Le paso un ChartPanel
    }
    
    //Para llenar las listas desplegables de los algoritmos y la velocidad
    public void llenarListas() {
        this.vista.getEleccion().llenarListas();
    }
    
    public void desordenarPokemones() { //Desordena la lista de pokemones
        this.modelo.desordenarPokemones();
    }

    public void cargarPokemones(Pokemon[] pokemones) {
        System.out.println(Arrays.toString(pokemones));
        this.modelo.setPokemones(pokemones);
        this.modelo.copiarPokemones(); //Copiar la lista original a la lista que se va a ordenar
        this.llenarOriginal(); //Crea la grafica inicial
    }
    
}