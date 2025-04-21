/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo1proyecto2;

import controller.Controlador;
import model.Modelo;
import view.Vista;

/**
 *
 * @author polares
 */
public class Ejemplo1Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creacion del modelo y de la vista
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        
        //Creacion del controlador, pasandole el modelo y la vista
        Controlador controlador = new Controlador(vista, modelo);
        
        //Seteando el controlador al modelo
        modelo.setControlador(controlador);
        
        //Se crean las ventanas de la aplicacion
        controlador.crearVentanas();
        vista.getLogin().setVisible(true);
        
        //Se crea al administrador y a los mecanicos por defecto
        controlador.crearAdminYMecanicos(); 
    }
    
}
