package controller;

import org.jfree.chart.ChartPanel;

public class TimerControlador extends ControladorBase implements Runnable { //Llevar el control del tiempo del ordenamiento
    private volatile boolean running = true;
    private long startTime;
    private GraficadorControlador graficador;
    
    public TimerControlador(GraficadorControlador graficador) {
        this.startTime = System.currentTimeMillis();
        this.graficador = graficador;
    } 

    //Permite detener el temporizador desde otro hilo
    public void stopTimer() {
        running = false;
    }
    
    //Este metodo se ira llamando en el hilo que controla el tiempo y las actualizaciones
    public void llenarTiempo(String tiempo) {
        this.vista.getApp().llenarLabelTiempo(tiempo);
    }
    
    public void llenarOrdenada(ChartPanel chartPanel) {
        this.vista.getApp().llenarOrdenada(chartPanel);
    }

    @Override
    public void run() {
        while (running) {
            long elapsed = System.currentTimeMillis() - startTime;
            long minutes = (elapsed / 1000) / 60; //Para que quede en minutos
            long seconds = (elapsed / 1000) % 60; //Para que quede en segundos
            long millis = elapsed % 1000;
            this.llenarTiempo("Tiempo transcurrido: " + minutes + ":" + seconds + ":" + millis);
            this.llenarOrdenada(this.graficador.generarGrafica()); //Va generando y actualizando la grafica
            try {
                Thread.sleep(100); //Actualiza cada 100 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}