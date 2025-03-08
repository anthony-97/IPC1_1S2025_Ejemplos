package model;

import java.util.ArrayList;

public class Curso {
    private static int contador = 1000;
    private String id;
    private String nombre;
    private char seccion;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Transaccion> listaTransacciones;

    public Curso(String nombre, char seccion) {
        contador++;
        this.id = Integer.toString(contador);
        this.nombre = nombre;
        this.seccion = seccion;
        this.listaEstudiantes = new ArrayList<Estudiante>();
        this.listaTransacciones = new ArrayList<Transaccion>();
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

    public char getSeccion() {
        return seccion;
    }

    public void setSeccion(char seccion) {
        this.seccion = seccion;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
    
}
