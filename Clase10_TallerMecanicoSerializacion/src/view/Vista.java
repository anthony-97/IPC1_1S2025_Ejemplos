/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 
 * @author polares
 */
public class Vista {
    private Login login;
    private Registro registro;
    private InicioAdmin inicioAdmin;
    private InicioCliente inicioCliente;
    private VerProgresoAdmin ventanaProgresoAdmin;
    private VentanaReportes ventanaReportes;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public InicioAdmin getInicioAdmin() {
        return inicioAdmin;
    }

    public void setInicioAdmin(InicioAdmin inicioAdmin) {
        this.inicioAdmin = inicioAdmin;
    }

    public InicioCliente getInicioCliente() {
        return inicioCliente;
    }

    public void setInicioCliente(InicioCliente inicioCliente) {
        this.inicioCliente = inicioCliente;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public VerProgresoAdmin getVentanaProgresoAdmin() {
        return ventanaProgresoAdmin;
    }

    public void setVentanaProgresoAdmin(VerProgresoAdmin ventanaProgresoAdmin) {
        this.ventanaProgresoAdmin = ventanaProgresoAdmin;
    }

    public VentanaReportes getVentanaReportes() {
        return ventanaReportes;
    }

    public void setVentanaReportes(VentanaReportes ventanaReportes) {
        this.ventanaReportes = ventanaReportes;
    }
    
}
