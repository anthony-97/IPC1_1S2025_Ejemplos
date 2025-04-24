/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Modelo;
import model.*;

/**
 *
 * @author polares
 */
public class SerializadorControlador extends ControladorBase {
    private static final String ARCHIVO_MODELO = "modelo.dat";
    private static final String ARCHIVO_CONTADORES = "contadores.dat";

    public void serializar(Modelo modelo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_MODELO))) {
            out.writeObject(modelo);
            System.out.println("Modelo serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Se guardan los contadores de OrdenTrabajo y Repuesto
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ARCHIVO_CONTADORES))) {
            dos.writeInt(OrdenTrabajo.getContador());
            dos.writeInt(Repuesto.getContador());
            System.out.println("Contadores guardados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Modelo deserializar() {
        Modelo modelo = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_MODELO))) {
            modelo = (Modelo) in.readObject();
            
            System.out.println("Modelo deserializado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Se leen los contadores
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ARCHIVO_CONTADORES))) {
            int contadorOrden = dis.readInt();
            int contadorRepuesto = dis.readInt();
            OrdenTrabajo.setContador(contadorOrden);
            Repuesto.setContador(contadorRepuesto);
            System.out.println("Contadores restaurados.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return modelo;
    }
  
}
