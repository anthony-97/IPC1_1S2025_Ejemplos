package view;

import controller.Controlador;
import javax.swing.JOptionPane;

public class Vista extends javax.swing.JFrame {
    private Login ventanaLogin;
    private InicioAdmin ventanaInicio;
    private RegistroEstudiante ventanaRegistroEstudiante;
    private RegistroCurso ventanaRegistroCurso;
    private AsignacionCurso ventanaAsignacion;
    private VistaEstudiantes ventanaEstudiantes;
    private HistorialTransacciones ventanaHistorial;
    private Controlador controlador;

    public Vista() { //Por herencia
        //Vista sin nada
    }
    
    public Login getVentanaLogin() {
        return ventanaLogin;
    }

    public void setVentanaLogin(Login ventanaLogin) {
        this.ventanaLogin = ventanaLogin;
    }

    public InicioAdmin getVentanaInicio() {
        return ventanaInicio;
    }

    public void setVentanaInicio(InicioAdmin ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
    }
    
    public RegistroEstudiante getVentanaRegistroEstudiante() {
        return ventanaRegistroEstudiante;
    }

    public void setVentanaRegistroEstudiante(RegistroEstudiante ventanaRegistroEstudiante) {
        this.ventanaRegistroEstudiante = ventanaRegistroEstudiante;
    }

    public RegistroCurso getVentanaRegistroCurso() {
        return ventanaRegistroCurso;
    }

    public void setVentanaRegistroCurso(RegistroCurso ventanaRegistroCurso) {
        this.ventanaRegistroCurso = ventanaRegistroCurso;
    }

    public AsignacionCurso getVentanaAsignacion() {
        return ventanaAsignacion;
    }

    public void setVentanaAsignacion(AsignacionCurso ventanaAsignacion) {
        this.ventanaAsignacion = ventanaAsignacion;
    }

    public VistaEstudiantes getVentanaEstudiantes() {
        return ventanaEstudiantes;
    }

    public void setVentanaEstudiantes(VistaEstudiantes ventanaEstudiantes) {
        this.ventanaEstudiantes = ventanaEstudiantes;
    }

    public HistorialTransacciones getVentanaHistorial() {
        return ventanaHistorial;
    }

    public void setVentanaHistorial(HistorialTransacciones ventanaHistorial) {
        this.ventanaHistorial = ventanaHistorial;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "IPC1 - Sección A", JOptionPane.WARNING_MESSAGE);
    }
    
}
