package ejemploclasemvc;

import controller.Controlador;
import model.Modelo;
import model.Usuario;
import view.TipoVentana;
import view.Vista;

/**
 *
 * @author polares
 */
public class EjemploClaseDTT {

    public static void main(String[] args) {
        // TODO code application logic here
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.crearVentanas(); //Crear las ventanas y a cada ventana le va a setear ese controlador
        Usuario admin = new Usuario("admin", "1234", 18); //Creando un usuario
        modelo.getUsuarios().add(admin);
        controlador.mostrarVentana(TipoVentana.Ventana.LOGIN);
    }
    
}
