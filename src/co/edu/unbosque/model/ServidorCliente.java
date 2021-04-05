package co.edu.unbosque.model;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServidorCliente implements Serializable, Runnable{

    public ServidorCliente(){

    }

    public void iniciarServidor(){
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public void mensaje(String texto, Usuario usuarioEnviar ){
        try{
            Socket misocket = new  Socket("192.168.0.6",9999);
            ObjectOutputStream datoEnviar = new ObjectOutputStream(misocket.getOutputStream());
            datoEnviar.writeObject(usuarioEnviar);
            datoEnviar.close();
            misocket.close();

        }catch (Exception e){
            System.out.println("No hay nadie conectado");
        }
    }



    @Override
    public void run() {

        try{
            //Puerto 1
            ServerSocket servidorCliente = new ServerSocket(9090);
            Socket cliente;
            Usuario usuarioRecibido;
            while(true){
                cliente = servidorCliente.accept();
                ObjectInputStream entradaUsuario = new ObjectInputStream(cliente.getInputStream());
                usuarioRecibido = (Usuario) entradaUsuario.readObject();
                System.out.println("\n"+usuarioRecibido.getUsuario()+": "+usuarioRecibido.getMensaje());

            }

        }catch(Exception e){

        }
    }


   /* public static void main(String[] args) throws UnknownHostException {
        Usuario aux = new Usuario("peamolon","123","192.168.0.6");
        //ServidorCliente a = new ServidorCliente();
        aux.enviarMensaje("Hola que tal");
    }*/


}
