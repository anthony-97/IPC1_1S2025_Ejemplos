/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author polares
 */
public class Modelo {
    private Pokemon[] pokemones;

    public Modelo() {
    }

    public Pokemon[] getPokemones() {
        return pokemones;
    }

    public void setPokemones(Pokemon[] pokemones) {
        this.pokemones = pokemones;
    }
    
    public void generarPokemones() {
        Pokemon pk1 = new Pokemon("Pikachu", 10);
        Pokemon pk2 = new Pokemon("Bulbassur", 40);
        Pokemon pk3 = new Pokemon("Charmander", 5);
        Pokemon pk4 = new Pokemon("Swellow", 2);
        Pokemon pk5 = new Pokemon("Abra", 17);
        
        this.pokemones = new Pokemon[] { pk1, pk2, pk3, pk4, pk5 };
    }
    
    public void ordenarBurbuja() {
        int n = pokemones.length; //Cantidad de elementos del array
        for (int i = 0; i < n - 1; i++) { //El algoritmo ordena en n-1 iteraciones
            for (int j = 0; j < n - i - 1; j++) { //Recorre varias veces la lista sin los burbujeados
                if (pokemones[j].getAtaque() > pokemones[j + 1].getAtaque()) {
                    intercambiar(j, j + 1);
                }
            }
        }
    }
    
    //Método para ordenar por QuickSort
    public void ordenarQuickSort() {
        quickSort(0, pokemones.length - 1); //Le pasamos los indices
    }

    private void quickSort(int inicio, int fin) { 
        if (inicio < fin) { //Si la longitud de la lista que esta ordenando es 0 o 1 no entra al if 
            int pivoteIndex = particion(inicio, fin); //Particionando inicialmente, guardando el indice del pivote
            quickSort(inicio, pivoteIndex - 1); //Recursividad mandandole la lista de menores
            quickSort(pivoteIndex + 1, fin);  //Recursividad mandandole la lista de mayores
        }
    }

    private int particion(int inicio, int fin) {
        int pivote = pokemones[fin].getAtaque(); //el valor del ataque del pivote
        int i = inicio - 1; //Guarda los indices de los menores

        for (int j = inicio; j < fin; j++) {
            if (pokemones[j].getAtaque() <= pivote) {
                i++;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, fin);
        return i + 1; //Se retorna el indice del pivote
    }
    
    //Método auxiliar para intercambiar dos elementos en el arreglo
    private void intercambiar(int i, int j) {
        Pokemon temp = pokemones[i];
        pokemones[i] = pokemones[j];
        pokemones[j] = temp;
    }
    
}
