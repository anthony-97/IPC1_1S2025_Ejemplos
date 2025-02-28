package view;

import controller.Controlador;

public class Vista {
    private Login ventanaLogin;
    private Inicio ventanaInicio;
    private Controlador controlador;

    public Vista() {
        this.ventanaLogin = new Login();
        this.ventanaInicio = new Inicio();
    }

    public Login getVentanaLogin() {
        return ventanaLogin;
    }

    public void setVentanaLogin(Login ventanaLogin) {
        this.ventanaLogin = ventanaLogin;
    }

    public Inicio getVentanaInicio() {
        return ventanaInicio;
    }

    public void setVentanaInicio(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
        this.getVentanaInicio().setControlador(controlador);
        this.getVentanaLogin().setControlador(controlador);
    }
    
}
