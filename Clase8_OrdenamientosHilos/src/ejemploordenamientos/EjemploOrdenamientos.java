/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploordenamientos;

import controller.Controlador;
import model.Modelo;
import view.Vista;

/**
 *
 * @author polares
 */
public class EjemploOrdenamientos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Modelo modelo = new Modelo();
        modelo.generarPokemones(); //Se generan los pokemones
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        modelo.setControlador(controlador);
        controlador.crearVentanas(); //Se crean las ventanas de la aplicacion
        controlador.llenarOriginal(); //Se llena la lista de pokemones original
        vista.getApp().setVisible(true); //Ventana de inicio es la de la APP
    }
    
}
