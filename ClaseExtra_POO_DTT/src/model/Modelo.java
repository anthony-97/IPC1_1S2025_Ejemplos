package model;

import java.util.ArrayList;
import javax.swing.JTextField;

public class Modelo {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Estudiante> listaEstudiantes; //Analogo a Lista de clientes
    private ArrayList<Curso> listaCursos; //Analogo a lista de cuentas
    
    public Modelo() {
        this.usuarios = new ArrayList<Usuario>();
        this.listaEstudiantes = new ArrayList<Estudiante>();
        this.listaCursos = new ArrayList<Curso>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
   
    public String[] iniciarSesion(String usuario, String contrasena) {
        for (Usuario usr : this.getUsuarios()) {
            if (usr.getNombre().equals(usuario)) {
                if (usr.getContrasena().equals(contrasena)) {
                    return new String[] { "true", usr.getNombre(), Integer.toString(usr.getEdad()) };
                }
            }
        }
        return new String[] { "false" };
    }
    
    public Estudiante buscarPorCUI(String cui) {
        for (Estudiante estudiante : this.getListaEstudiantes()) {
            if (estudiante.getCUI().equals(cui)) {
                return estudiante;
            }
        }
        return null;
    }
    
    public void asignarStudent(String cursoID, String estudianteCUI) {
        Estudiante est = buscarPorCUI(estudianteCUI);
        if (est != null) {
            for (Curso curso : this.getListaCursos()) {
                if (curso.getId().equals(cursoID)) {
                    est.getListaCursos().add(curso); //Se le asigna al estudiante el curso
                    curso.getListaEstudiantes().add(est); //Se le asigna al curso el estudiante
                    System.out.println("Estudiante agregado al curso: " + curso.getNombre());
                    break;
                }
            }
        } else {
            System.out.println("No se encontró el estudiante con CUI: " + estudianteCUI);
        }

    }

    public ArrayList<Curso> getCursosAsignados(String CUI) {
        for(Estudiante estudiante:this.getListaEstudiantes()) {
            if(estudiante.getCUI().equals(CUI)) {
                return estudiante.getListaCursos();
            }
        }
        return null;
    }

    public void crearTransaccion(String idCurso, String fecha, String detalle) {
        for(Curso curso: this.listaCursos) {
            if(curso.getId().equals(idCurso)) {
                curso.getListaTransacciones().add(new Transaccion(fecha, detalle, curso.getListaEstudiantes().size()));
                break;
            }
        }
    }

    public ArrayList<Transaccion> obtenerTransacciones(String idCurso) {
        for(Curso curso: this.listaCursos) {
            if(curso.getId().equals(idCurso)) {
                return curso.getListaTransacciones();
            }
        }
        return null;
    }
}