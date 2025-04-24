/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo3proyecto2;

import controller.Controlador;
import controller.SerializadorControlador;
import java.io.File;
import model.Modelo;
import view.Vista;

/**
 *
 * @author polares
 */
public class Ejemplo2Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Controlador de la Serializacion
        SerializadorControlador serializador = new SerializadorControlador();
       
        //Declaracion del modelo
        Modelo modelo;
        
        //Se abre el archivo binario del modelo
        File f = new File("modelo.dat");

        if (f.exists()) { //Si el archivo existe
            modelo = serializador.deserializar();
        } else { //Si no existe, se crea el modelo
            modelo = new Modelo();
        }

        //Se crea la vista
        Vista vista = new Vista();
       
        //Se crea el controlador y se le setea la vista y el modelo
        Controlador controlador = new Controlador(vista, modelo);
        modelo.setControlador(controlador);

        //Se crean las ventanas de la aplicacion
        controlador.crearVentanas();
        vista.getLogin().setVisible(true);
        
        //Se crea al administrador y a los mecanicos por defecto
        controlador.crearAdminYMecanicos();
        
        //Se les crea la ventana de progreso a todos los clientes por la deserializacion
        controlador.crearVentanaProgresoClientes();
        
        //Para verificar la deserializacion, se imprimen los repuestos y servicios
        controlador.imprimirSerializados();
        
        //Se serializa al cerrar
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SerializadorControlador serializadorCierre = new SerializadorControlador();
            serializadorCierre.serializar(modelo); 
            System.out.println("Aplicación cerrada. Datos serializados.");
        }));
    }
    
}
