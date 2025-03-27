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
    
    public void generarPokemones() {
        Pokemon pk1 = new Pokemon("Pikachu", 10);
        Pokemon pk2 = new Pokemon("Bulbassur", 40);
        Pokemon pk3 = new Pokemon("Charmander", 5);
        Pokemon pk4 = new Pokemon("Swellow", 2);
        Pokemon pk5 = new Pokemon("Abra", 17);
        
        this.pokemones = new Pokemon[] {pk1, pk2, pk3, pk4, pk5 };//Original
        //Creando una copia de la lista desordenada
        this.pokemonesDesordenados = Arrays.copyOf(this.pokemones, this.pokemones.length); //A ordenar
    }
    
    public void desordenarPokemones() {
        this.pokemonesDesordenados = Arrays.copyOf(this.pokemones, this.pokemones.length); 
    }
    
    public void ordenarConHilo(int velocidad, TipoOrdenamiento.Ordenamiento tipo) {
        Thread hiloOrdenador = new Thread(new Ordenador(this, velocidad, tipo));
        hiloOrdenador.start();
    }
    
}
