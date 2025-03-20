package model;

public class Ordenador implements Runnable {
    private Modelo modelo;
    private int velocidad;
    private TipoOrdenamiento.Ordenamiento tipo;

    public Ordenador(Modelo modelo, int velocidad, TipoOrdenamiento.Ordenamiento tipo) {
        this.modelo = modelo;
        this.velocidad = velocidad;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        TimerThread timer = new TimerThread(this.modelo.getControlador()); //Controlar el tiempo y actualizaciones
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
        int n = modelo.getPokemonesDesordenados().length;
        for (int i = 0; i < n - 1; i++) { //Obtiene la solucion en n-1 iteraciones
            for (int j = 0; j < n - i - 1; j++) { 
                if (modelo.getPokemonesDesordenados()[j].getAtaque() > modelo.getPokemonesDesordenados()[j + 1].getAtaque()) {
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
        quickSort(0, modelo.getPokemonesDesordenados().length - 1, delay);
    }

    private void quickSort(int inicio, int fin, int delay) {
        if (inicio < fin) { //Caso base, cuando la lista tenga 1 o 0 elementos, no va a entrar al if
            int pivoteIndex = particion(inicio, fin, delay);
            quickSort(inicio, pivoteIndex - 1, delay);
            quickSort(pivoteIndex + 1, fin, delay);
        }
    }

    private int particion(int inicio, int fin, int delay) {
        int pivote = modelo.getPokemonesDesordenados()[fin].getAtaque();
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (modelo.getPokemonesDesordenados()[j].getAtaque() <= pivote) {
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
        Pokemon temp = modelo.getPokemonesDesordenados()[i];
        modelo.getPokemonesDesordenados()[i] = modelo.getPokemonesDesordenados()[j];
        modelo.getPokemonesDesordenados()[j] = temp;
    }

}