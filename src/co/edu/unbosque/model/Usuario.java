package co.edu.unbosque.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;
    private String contrasenia;
    private boolean inicioSesion;
    private String ip;
    private ServidorCliente servidor;
    private String mensaje;
    private boolean ciudadano;
    private boolean agente;

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        inicioSesion = false;
        ip = "";
        servidor = new ServidorCliente();
        mensaje = "";
        ciudadano =false;
        agente = false;
        servidor = new ServidorCliente();

    }

    public void iniciarServidor(){
        servidor.iniciarServidor();
    }

    public void enviarMensaje(String mensaje){
        setMensaje(mensaje);
        servidor.mensaje(mensaje, this);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ServidorCliente getServidor() {
        return servidor;
    }

    public void setServidor(ServidorCliente servidor) {
        this.servidor = servidor;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(boolean inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

    public boolean isCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(boolean ciudadano) {
        this.ciudadano = ciudadano;
    }

    public boolean isAgente() {
        return agente;
    }

    public void setAgente(boolean agente) {
        this.agente = agente;
    }
}
