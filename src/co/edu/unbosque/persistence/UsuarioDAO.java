package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {
    private OperacionArchivo operacion;
    private ArrayList<Usuario> usuarios;
    public UsuarioDAO() {
        operacion = new OperacionArchivo();
        usuarios = new ArrayList<Usuario>();
    }

    public void agregarUsuario(Usuario c){
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        usuarios.add(c);
        operacion.escribir(usuarios);
    }

    public void cerrarSesion(Usuario a){
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        for(Usuario c:usuarios){
            if(c.getUsuario().equals(a.getUsuario())){
                c.setInicioSesion(false);
                System.out.println("Se cerró la sesión con exito");
                break;
            }
        }
        operacion.escribir(usuarios);
    }
    public Usuario devolverUsuario(String usuario){
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        for(Usuario c:usuarios){
            if(c.getUsuario().equals(usuario)){
                return c;
            }
        }
        return null;
    }

    public void iniciarSesion(Usuario a){
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        for(Usuario c:usuarios){
            if(c.getUsuario().equals(a.getUsuario())){
                c.setInicioSesion(true);
                System.out.println("Se inicio la sesión con exito");
                break;
            }
        }
        operacion.escribir(usuarios);
    }

    public Usuario sesionIniciada(){
        boolean bandera = false;
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        for(Usuario c:usuarios){
            if(c.isInicioSesion()){
                return c;
            }
        }
        return null;
    }

    public boolean comprobarInicioSesionCiudadano(String usuario, String contra){
        boolean bandera = false;
        if(operacion.obtener()!=null){
            usuarios = operacion.obtener();
        }
        for(Usuario c:usuarios){
            if(c.getUsuario().equals(usuario)&&c.getContrasenia().equals(contra)){
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    public OperacionArchivo getOperacion() {
        return operacion;
    }

    public void setOperacion(OperacionArchivo operacion) {
        this.operacion = operacion;
    }
}
