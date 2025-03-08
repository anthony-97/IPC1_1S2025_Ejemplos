package model;

import java.util.ArrayList;

public class Estudiante {
    private String CUI;
    private String nombre;
    private int edad;
    private ArrayList<Curso> listaCursos;

    public Estudiante(String CUI, String nombre, int edad) {
        this.CUI = CUI;
        this.nombre = nombre;
        this.edad = edad;
        this.listaCursos = new ArrayList<Curso>(); //Se va a crear una lista de cursos cada vez que se cree un estudiante
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
    
}
