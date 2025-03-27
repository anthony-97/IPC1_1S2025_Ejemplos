package model;

import controller.Controlador;
import java.util.Arrays;

public class TimerThread implements Runnable { //Llevar el control del tiempo del ordenamiento
    private volatile boolean running = true;
    private long startTime;
    private Controlador controlador;
   
    public TimerThread(Controlador controlador) {
        this.startTime = System.currentTimeMillis();
        this.controlador = controlador;
    } 

    //Permite detener el temporizador desde otro hilo
    public void stopTimer() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            long elapsed = System.currentTimeMillis() - startTime;
            long minutes = (elapsed / 1000) / 60; //Para que quede en minutos
            long seconds = (elapsed / 1000) % 60; //Para que quede en segundos
            long millis = elapsed % 1000;
            this.controlador.llenarTiempo("Tiempo transcurrido: " + minutes + ":" + seconds + ":" + millis);
            this.controlador.llenarListaOrdenada(); //Actualizar el estado de las listas
            //this.controlador.llenarGraficasOrdenadas();
            try {
                Thread.sleep(100); //Actualiza cada 100 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
