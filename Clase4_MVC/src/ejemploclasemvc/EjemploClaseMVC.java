package ejemploclasemvc;

import controller.Controlador;
import model.Modelo;
import model.Usuario;
import view.Vista;

/**
 *
 * @author polares
 */
public class EjemploClaseMVC {

    public static void main(String[] args) {
        // TODO code application logic here
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        Usuario admin = new Usuario("admin", "1234", 18); //Creando un usuario
        modelo.getUsuarios().add(admin);
        controlador.mostrarLogin();
    }
    
}
