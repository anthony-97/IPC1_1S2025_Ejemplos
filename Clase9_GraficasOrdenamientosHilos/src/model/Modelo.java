package model;

import controller.Controlador;
import java.util.Arrays;

public class Modelo {
    private Pokemon[] pokemones; //Original
    private Pokemon[] pokemonesDesordenados; //Desordenada a ordenar
    private Controlador controlador;
    
    public Modelo() {
    }

    public Pokemon[] getPokemones() {
        return pokemones;
    }

    public void setPokemones(Pokemon[] pokemones) {
        this.pokemones = pokemones;
    }
    
    public Pokemon[] getPokemonesDesordenados() {
        return pokemonesDesordenados;
    }

    public void setPokemonesDesordenados(Pokemon[] pokemonesDesordenados) {
        this.pokemonesDesordenados = pokemonesDesordenados;
    }
    
    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    
    //Crea una copia de la lista original de carga
    public void copiarPokemones() {
        this.pokemonesDesordenados = Arrays.copyOf(this.pokemones, this.pokemones.length); //A ordenar
    }
    
    public void desordenarPokemones() {
        this.pokemonesDesordenados = Arrays.copyOf(this.pokemones, this.pokemones.length); //Para que queden otra vez desordenados
    }
    
}
