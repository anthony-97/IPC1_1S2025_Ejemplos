/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author polares
 */
public class Pokemon {
    String nombre;
    int ataque;

    public Pokemon(String nombre, int ataque) {
        this.nombre = nombre;
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    @Override
    public String toString() {
        return "\n{ nombre=" + nombre + ", ataque=" + ataque + " }";
        //[
        //{nombre=Pikachu, ataque=90},
        //{nombre=Pikachu, ataque=90}
        //]
    }
}
