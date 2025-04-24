/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Controlador;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author polares
 */
public class Modelo implements Serializable {
    private Vector<Usuario> listaUsuarios; //Guardo a clientes y admin
    private transient Controlador controlador; //No se serializa al controlador
    private transient Vector<Mecanico> listaMecanicos; //No se serializan a los mecanicos
    private Vector<Repuesto> listaRepuestos;
    private Vector<Servicio> listaServicios;
    private Vector<OrdenTrabajo> colaEspera;
    private Vector<OrdenTrabajo> colaServicio;
    private Vector<OrdenTrabajo> colaListos;
    private Vector<OrdenTrabajo> colaPorPagar;

    public Modelo() {
        this.listaUsuarios = new Vector<Usuario>();
        this.controlador = controlador;
        this.listaMecanicos = new Vector<Mecanico>();
        this.listaRepuestos = new Vector<Repuesto>();
        this.listaServicios = new Vector<Servicio>();
        this.colaEspera = new Vector<OrdenTrabajo>();
        this.colaServicio = new Vector<OrdenTrabajo>();
        this.colaListos = new Vector<OrdenTrabajo>();        
        this.colaPorPagar = new Vector<OrdenTrabajo>();
    }
    
    public Vector<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(Vector<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Vector<Mecanico> getListaMecanicos() {
        return listaMecanicos;
    }

    public void setListaMecanicos(Vector<Mecanico> listaMecanicos) {
        this.listaMecanicos = listaMecanicos;
    }
    
    public Vector<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(Vector<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public Vector<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(Vector<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }
    
    public Vector<OrdenTrabajo> getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(Vector<OrdenTrabajo> colaEspera) {
        this.colaEspera = colaEspera;
    }

    public Vector<OrdenTrabajo> getColaServicio() {
        return colaServicio;
    }

    public void setColaServicio(Vector<OrdenTrabajo> colaServicio) {
        this.colaServicio = colaServicio;
    }

    public Vector<OrdenTrabajo> getColaListos() {
        return colaListos;
    }

    public void setColaListos(Vector<OrdenTrabajo> colaListos) {
        this.colaListos = colaListos;
    }
    
    public Vector<OrdenTrabajo> getColaPorPagar() {
        return colaPorPagar;
    }

    public void setColaPorPagar(Vector<OrdenTrabajo> colaPorPagar) {
        this.colaPorPagar = colaPorPagar;
    }
    
    public void imprimirRepuestos() {
        System.out.println("Rep");
        for(Repuesto rep: this.listaRepuestos) {
            System.out.println(rep);
        }
    }
    
    public void imprimirServicios() {
        System.out.println("Serv");
        for(Servicio serv: this.listaServicios) {
            System.out.println(serv);
        }
    }
    
}
