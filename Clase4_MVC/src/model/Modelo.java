package model;

import java.util.ArrayList;

public class Modelo {
    private ArrayList<Usuario> usuarios;

    public Modelo() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
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
}
