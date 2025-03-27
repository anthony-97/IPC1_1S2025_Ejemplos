package view;

import controller.Controlador;
import javax.swing.JFrame;

public abstract class VentanaBase extends JFrame {
    protected Controlador controlador;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }
}