package controller;
import model.Modelo;
import model.Usuario;
import view.Vista;

public class Controlador {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public void mostrarLogin() {
        this.vista.getVentanaLogin().setVisible(true); //Hacer visible la ventana
        this.vista.getVentanaInicio().setVisible(false); //Que no se muestre el inicio
        this.vista.getVentanaLogin().limpiarFields();
    }
    
    public void mostrarInicio(String usuario, String edad) {
        this.vista.getVentanaLogin().setVisible(false);
        this.vista.getVentanaInicio().setVisible(true);
        this.vista.getVentanaInicio().setearDatos(usuario, edad);
    }
    
    public void hacerLogin(String usuario, String contrasena) {
        String[] respuesta = this.modelo.iniciarSesion(usuario, contrasena);
        if(respuesta[0].equals("true")) {
            this.mostrarInicio(respuesta[1], respuesta[2]);
        } else {
            this.vista.getVentanaLogin().mostrarError("Ocurrio un error, revisa tus credenciales");
        }
    }
    
}
