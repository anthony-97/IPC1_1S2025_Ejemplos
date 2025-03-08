package controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Curso;
import model.Estudiante;
import model.Modelo;
import model.Usuario;
import view.AsignacionCurso;
import view.HistorialTransacciones;
import view.InicioAdmin;
import view.Login;
import view.RegistroCurso;
import view.RegistroEstudiante;
import view.TipoVentana;
import view.Vista;
import view.VistaEstudiantes;

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
    
    public void crearVentanas() { //Metodo que crea las ventanas y les setea el controlador
        this.vista.setVentanaLogin(new Login());
        this.vista.getVentanaLogin().setControlador(this); //Este objeto Controlador
        this.vista.setVentanaInicio(new InicioAdmin());
        this.vista.getVentanaInicio().setControlador(this);
        this.vista.setVentanaRegistroEstudiante(new RegistroEstudiante());
        this.vista.getVentanaRegistroEstudiante().setControlador(this);
        this.vista.setVentanaRegistroCurso(new RegistroCurso());
        this.vista.getVentanaRegistroCurso().setControlador(this);
        this.vista.setVentanaAsignacion(new AsignacionCurso());
        this.vista.getVentanaAsignacion().setControlador(this);
        this.vista.setVentanaEstudiantes(new VistaEstudiantes());
        this.vista.getVentanaEstudiantes().setControlador(this);
        this.vista.setVentanaHistorial(new HistorialTransacciones());
        this.vista.getVentanaHistorial().setControlador(this);
    }
    
    public void mostrarVentana(TipoVentana.Ventana tipoVentana) {
        javax.swing.JFrame[] ventanas = {
            this.vista.getVentanaLogin(),
            this.vista.getVentanaInicio(),
            this.vista.getVentanaRegistroEstudiante(),
            this.vista.getVentanaRegistroCurso(),
            this.vista.getVentanaAsignacion(),
            this.vista.getVentanaEstudiantes(),
            this.vista.getVentanaHistorial()
        };

        //Se ocultan todas las ventanas
        for (javax.swing.JFrame ventana : ventanas) {
            ventana.setVisible(false);
        }
        
        switch (tipoVentana.getIndex()) {
            case 1 -> this.vista.getVentanaLogin().setVisible(true);
            case 2 -> this.vista.getVentanaInicio().setVisible(true);
            case 3 -> this.vista.getVentanaRegistroEstudiante().setVisible(true);
            case 4 -> this.vista.getVentanaRegistroCurso().setVisible(true);
            case 5 -> this.vista.getVentanaAsignacion().setVisible(true);
            case 6 -> this.vista.getVentanaEstudiantes().setVisible(true);
            case 7 -> this.vista.getVentanaHistorial().setVisible(true);
        }
        if (tipoVentana.getIndex() == 1) { //Limpiar los campos del login
            this.vista.getVentanaLogin().limpiarFields();
        }
    }
    
    public void hacerLogin(String usuario, String contrasena) {
        String[] respuesta = this.modelo.iniciarSesion(usuario, contrasena);
        if(respuesta[0].equals("true")) {
            this.mostrarVentana(TipoVentana.Ventana.INICIO);
        } else {
            this.vista.getVentanaLogin().mostrarError("Ocurrio un error, revisa tus credenciales");
        }
    }

    public void crearEstudiante(String cui, String nombre, int edad) {
        Estudiante est = this.modelo.buscarPorCUI(cui); //Para no crear dos estudiantes con el mismo CUI
        if(est == null) { //No lo encontro
            this.modelo.getListaEstudiantes().add(new Estudiante(cui, nombre, edad));
        } else {
            this.vista.getVentanaRegistroEstudiante().mostrarError("Cui duplicado, no se creo al estudiante");
        }
    }

    public void crearCurso(String nombre, char seccion) {
        this.modelo.getListaCursos().add(new Curso(nombre, seccion));
    }

    public void llenarListas() {
        this.vista.getVentanaAsignacion().llenarListas();
    }

    public void asignarEstudiante(String cursoID, String estudianteCUI) {
        this.modelo.asignarStudent(cursoID, estudianteCUI);
    }
    
    public void llenarTablaEstudiantes() {
        this.vista.getVentanaEstudiantes().llenarTablaEstudiantes();
    }
    
    public void crearTransaccion(String idCurso, String detalle) {
        LocalDateTime ahora = LocalDateTime.now(); //Se obtiene el tiempo actual

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHoraString = ahora.format(formato);

        this.modelo.crearTransaccion(idCurso, fechaHoraString, detalle);
    }
    
}
