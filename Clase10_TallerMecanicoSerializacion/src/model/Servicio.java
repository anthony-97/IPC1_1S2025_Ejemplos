/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author polares
 */
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String marca;
    private String modelo;
    private Vector<Repuesto> listaRepuestos;
    private double precioManoDeObra;
    private double precioTotal;

    public Servicio(String id, String nombre, String marca, String modelo, double precioManoDeObra) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.listaRepuestos = new Vector<Repuesto>();
        this.precioManoDeObra = precioManoDeObra;
        this.precioTotal = this.calcularPrecioTotal();
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

    public Vector<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(Vector<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public double getPrecioManoDeObra() {
        return precioManoDeObra;
    }

    public void setPrecioManoDeObra(double precioManoDeObra) {
        this.precioManoDeObra = precioManoDeObra;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    private double calcularPrecioTotal() {
        double total = 0;
        for(Repuesto rep: this.listaRepuestos) {
            total += rep.getPrecio();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", modelo=" + modelo + ", listaRepuestos=" + listaRepuestos + ", precioManoDeObra=" + precioManoDeObra + ", precioTotal=" + precioTotal + '}';
    }
        
}