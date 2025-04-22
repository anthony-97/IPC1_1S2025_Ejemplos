/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Vector;
import model.*;
import view.*;

/**
 *
 * @author polares
 */
public class Controlador {
    private Vista vista;
    private Modelo modelo;
    private String usuarioActual; //El usuario que esta logueado
    private FlujoControlador flujoControlador; //El controlador que procesa las colas, se cambió a que solo un flujo las procese

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

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void crearVentanas() { //Metodo que crea las ventanas y les setea el controlador
        this.vista.setLogin(new Login());
        this.vista.getLogin().setControlador(this); //Este objeto Controlador
        this.vista.setRegistro(new Registro());
        this.vista.getRegistro().setControlador(this);
        this.vista.setInicioAdmin(new InicioAdmin());
        this.vista.getInicioAdmin().setControlador(this);
        this.vista.setInicioCliente(new InicioCliente());
        this.vista.getInicioCliente().setControlador(this);
        this.vista.setVentanaProgresoAdmin(new VerProgresoAdmin());
        this.vista.getVentanaProgresoAdmin().setControlador(this);
    }
    
    public void mostrarVentana(TipoVentana.Ventana tipoVentana) {
        javax.swing.JFrame[] ventanas = {
            this.vista.getLogin(),
            this.vista.getRegistro(),
            this.vista.getInicioAdmin(),
            this.vista.getInicioCliente(),
            this.vista.getVentanaProgresoAdmin()
        };

        //Se ocultan todas las ventanas
        for (javax.swing.JFrame ventana : ventanas) {
            ventana.setVisible(false);
        }
        
        switch (tipoVentana.getIndex()) {
            case 1 -> this.vista.getLogin().setVisible(true);
            case 2 -> this.vista.getRegistro().setVisible(true);
            case 3 -> this.vista.getInicioAdmin().setVisible(true);
            case 4 -> this.vista.getInicioCliente().setVisible(true);
            case 5 -> this.vista.getVentanaProgresoAdmin().setVisible(true);
        }
    }
    
    public String[] verificarCredenciales(String usuario, String contrasena) {
        String[] respuesta = new String[] { "", "" };
        for(Usuario usr: this.modelo.getListaUsuarios()) {
            if(usr.getNombreUsuario().equals(usuario)) {
                if(usr.getContrasena().equals(contrasena)) {
                    //Credenciales correctas
                    System.out.println(usr.getTipoUsuario());
                    respuesta[0] = Integer.toString(usr.getTipoUsuario()); //Tipo del usuario
                    respuesta[1] = usr.getDpi();
                    return respuesta;
                }
            }
        }
        return respuesta;
    }
    
    public void hacerLogin(String usuario, String contrasena) {
        String[] respuesta = this.verificarCredenciales(usuario, contrasena);
        if(respuesta[0].equals("1")) {
            //Es un admin
            this.mostrarVentana(TipoVentana.Ventana.INICIO_ADMIN);
            this.usuarioActual = respuesta[1];            
        } else if(respuesta[0].equals("2")) {
            //Es un cliente
            this.mostrarVentana(TipoVentana.Ventana.INICIO_CLIENTE);
            this.usuarioActual = respuesta[1];
        } else {
            //Credenciales incorrectas
            this.vista.getLogin().mostrarError("Ocurrio un error, revisa tus credenciales");
        }
    }
    
    public boolean buscarClientePorDPI(String dpi) {
        for(Usuario usr: this.modelo.getListaUsuarios()) {
            if(usr.getDpi().equals(dpi)) {
                return true;
            }
        }
        return false;
    }
    
    public Cliente obtenerCliente(String dpi) {
        for(Usuario u:this.modelo.getListaUsuarios()) {
            if(u instanceof Cliente) {
                if(u.getDpi().equals(dpi)) {
                    return (Cliente) u;
                }
            }
        }
        return null;
    }
    
    public boolean buscarServicio(String id) {
        for(Servicio serv: this.modelo.getListaServicios()) {
            if(serv.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public void crearAdminYMecanicos() {
        Administrador admin = new Administrador("3056670410301", "Admin", "admin", "1234");
        Mecanico mecanico1 = new Mecanico("Carlos");
        this.modelo.getListaMecanicos().add(mecanico1);
        Mecanico mecanico2 = new Mecanico("Pedro");
        this.modelo.getListaMecanicos().add(mecanico2);
        this.modelo.getListaUsuarios().add(admin);
    }
    
    public boolean crearCliente(String dpi, String nombre, String usuario, String contrasena, String tipo) {
        boolean encontrado = this.buscarClientePorDPI(dpi);
        if(!encontrado) { //No existe el cliente y por lo tanto se crea
            Cliente cliente = new Cliente(dpi, nombre, usuario, contrasena, tipo);
            //Se le crea la ventana de progreso al cliente
            cliente.setVentanaProgreso(new VerProgreso());
            cliente.getVentanaProgreso().setControlador(this);
            //Se agrega al cliente a la lista de usuarios
            this.modelo.getListaUsuarios().add(cliente);
            return true; //Creado exitosamente
        } else {
            return false; //No fue creado
        }
    }

    public void cargarObjetos(Vector<Object> carga, int tipo) {
        if(tipo == 1) {
            //Se hace el casteo del vector de objetos ya que se sabe que vienen clientes que son usuarios
            Vector<Usuario> usuarios = (Vector<Usuario>) (Vector<?>) carga;
            this.modelo.getListaUsuarios().addAll(usuarios);
        } else if(tipo == 3) {
            Vector<Servicio> servicios = (Vector<Servicio>) (Vector<?>) carga;
            this.modelo.getListaServicios().addAll(servicios);
        }
    }

    public void cargarRepuesto(Repuesto rep) {
        this.modelo.getListaRepuestos().add(rep);
    }

    public Repuesto buscarRepuesto(String idRepuesto) {
        for(Repuesto rep: this.modelo.getListaRepuestos()) {
            if(rep.getId().equals(idRepuesto)) {
                return rep;
            }
        }
        return null;
    }

    public Vector<Automovil> obtenerAutos(String usuarioActual) {
        for(Usuario u: this.modelo.getListaUsuarios()) {
            if(u.getDpi().equals(usuarioActual)) {
                Cliente c = (Cliente) u;
                return c.getListaAutomoviles();
            }
        }
        return null;
    }

    public Automovil buscarPorPlaca(String placa) {
       for(Usuario u: this.modelo.getListaUsuarios()) {
            if(u.getDpi().equals(usuarioActual)) {
                Cliente c = (Cliente) u;
                for(Automovil a: c.getListaAutomoviles()) {
                    if(a.getPlaca().equals(placa)) {
                        return a;
                    }
                }
            }
        }
        return null;
    }
    
    public Cliente crearOrdenTrabajo(String carro, String servicio) {
        Automovil auto = this.buscarPorPlaca(carro);
        Cliente c = this.obtenerCliente(this.usuarioActual);
        System.out.println("USUARIO ACTUAL " + this.usuarioActual);
        OrdenTrabajo orden = new OrdenTrabajo(auto.getPlaca(), this.usuarioActual, servicio, 0);
        c.getListaOrdenes().add(orden);
        return c;
    }
    
    public void iniciarFlujo() {
        Cliente clienteActual = this.obtenerCliente(this.usuarioActual);
        //Si no se ha creado un FlujoControlador, se crea y se le setea el modelo y la vista
        if(this.flujoControlador == null) {
            this.flujoControlador = new FlujoControlador();
            this.flujoControlador.setModelo(this.modelo);
            this.flujoControlador.setVista(this.vista);
        }
        //Independientemente de lo anterior, se inicia el FlujoControlador
        this.flujoControlador.setClienteActual(clienteActual);
        this.flujoControlador.iniciarFlujo();
    }

    public void llenarTablasColas() {
        this.vista.getVentanaProgresoAdmin().llenarTablas();
    }
    
}