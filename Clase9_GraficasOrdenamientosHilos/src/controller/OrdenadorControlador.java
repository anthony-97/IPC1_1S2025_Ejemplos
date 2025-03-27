package controller;

import model.Pokemon;
import model.TipoOrdenamiento;

public class OrdenadorControlador extends ControladorBase implements Runnable {
    private int velocidad;
    private TipoOrdenamiento.Ordenamiento tipo;
    private GraficadorControlador graficador;

    public OrdenadorControlador(int velocidad, TipoOrdenamiento.Ordenamiento tipo) {
        this.velocidad = velocidad;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        //Aca se crea el controlador de la grafica
        GraficadorControlador graficador = new GraficadorControlador();
        graficador.setModelo(this.modelo);
        graficador.setVista(this.vista);
        //Se crea el hilo que controlara el tiempo
        TimerControlador timer = new TimerControlador(graficador); //Controlar el tiempo y actualizaciones
        //El modelo y la vista se asignaron al Ordenador en el Controlador general
        //por lo que se obtienen y se les setean al controlador del tiempo
        timer.setModelo(this.getModelo());
        timer.setVista(this.getVista());
        Thread timerHilo = new Thread(timer); //Hilo paralelo que controla el tiempo y actualizaciones
        timerHilo.start(); //Hilo paralelo comienza
        switch (tipo.getIndex()) {
            case 1: //Burbuja                            
                ordenarBurbujaConVelocidad(velocidad);
                break;
            case 2: //Quicksort
                ordenarQuickSortConVelocidad(velocidad);
                break;
        }
        //Se detiene el temporizador una vez completado el ordenamiento
        timer.stopTimer();
        try {
            //Esperamos a que el temporizador finalice su ejecución
            timerHilo.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    }

    private void ordenarBurbujaConVelocidad(int delay) { //Ordenamiento
        int n = this.modelo.getPokemonesDesordenados().length;
        for (int i = 0; i < n - 1; i++) { //Obtiene la solucion en n-1 iteraciones
            for (int j = 0; j < n - i - 1; j++) { 
                if (this.modelo.getPokemonesDesordenados()[j].getAtaque() > this.modelo.getPokemonesDesordenados()[j + 1].getAtaque()) {
                    intercambiar(j, j + 1);
                    try {
                        Thread.sleep(delay); //Se queda dormido la cantidad de ms del delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void ordenarQuickSortConVelocidad(int delay) {
        quickSort(0, this.modelo.getPokemonesDesordenados().length - 1, delay);
    }

    private void quickSort(int inicio, int fin, int delay) {
        if (inicio < fin) { //Caso base, cuando la lista tenga 1 o 0 elementos, no va a entrar al if
            int pivoteIndex = particion(inicio, fin, delay);
            quickSort(inicio, pivoteIndex - 1, delay);
            quickSort(pivoteIndex + 1, fin, delay);
        }
    }

    private int particion(int inicio, int fin, int delay) {
        int pivote = this.modelo.getPokemonesDesordenados()[fin].getAtaque();
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (this.modelo.getPokemonesDesordenados()[j].getAtaque() <= pivote) {
                i++;
                intercambiar(i, j);
                try {
                    Thread.sleep(delay); //Agrega el retardo según la velocidad seleccionada
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        intercambiar(i + 1, fin);
        return i + 1;
    }
    
    //Método auxiliar para intercambiar dos elementos en el arreglo
    private void intercambiar(int i, int j) {
        Pokemon temp = this.modelo.getPokemonesDesordenados()[i];
        this.modelo.getPokemonesDesordenados()[i] = this.modelo.getPokemonesDesordenados()[j];
        this.modelo.getPokemonesDesordenados()[j] = temp;
    }

}