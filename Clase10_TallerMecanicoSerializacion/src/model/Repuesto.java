/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author polares
 */
public class Repuesto implements Serializable {
    private static final long serialVersionUID = 1L; //Para la serializacion
    public static int contador = 1;
    private String id;
    private String nombre;
    private String marca;
    private String modelo;
    private int existencias;
    private double precio;

    public Repuesto(String nombre, String marca, String modelo, int existencias, double precio) {
        this.id = Integer.toString(contador++); //Para el correlativo, se asigna el id y se aumenta el contador
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.existencias = existencias;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int nuevoContador) {
        contador = nuevoContador;
    }

    @Override
    public String toString() {
        return "Repuesto{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", modelo=" + modelo + ", existencias=" + existencias + ", precio=" + precio + '}';
    }
    
}
