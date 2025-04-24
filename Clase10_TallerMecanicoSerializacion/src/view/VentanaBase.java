package view;

import controller.Controlador;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class VentanaBase extends JFrame {
    protected Controlador controlador;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }
    
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Taller Mecanico USAC", JOptionPane.WARNING_MESSAGE);
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Taller Mecanico USAC", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cerrarSesion() {
        this.controlador.mostrarVentana(TipoVentana.Ventana.LOGIN);
    }
}