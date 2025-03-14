/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo_claseordenamientos;

import controller.Controlador;
import model.Modelo;
import view.Vista;

/**
 *
 * @author polares
 */
public class Ejemplo_ClaseOrdenamientos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Modelo modelo = new Modelo();
        modelo.generarPokemones(); //Lista de pokemones
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.crearVentanas();
        controlador.llenarOriginal();
        vista.getApp().setVisible(true);
    }
    
}
