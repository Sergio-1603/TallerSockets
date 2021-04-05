package co.edu.unbosque.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPrincipal implements Runnable{

    public ServidorPrincipal(){
        Thread hilo = new Thread(this);
        hilo.start();
    }



    @Override
    public void run() {
        try{
            ServerSocket servidor = new ServerSocket(9999);
            System.out.println("servidor corriendo");
            String usuario, ip,mensaje;
            Usuario usuarioRecibido;
            while(true) {
                Socket socketServidor = servidor.accept();
                safePrintln(socketServidor.toString());
                ObjectInputStream datoRecibir = new ObjectInputStream(socketServidor.getInputStream());
                usuarioRecibido = (Usuario) datoRecibir.readObject();
                System.out.println("\n"+usuarioRecibido.getUsuario()+": "+usuarioRecibido.getMensaje());

                //Puerto 1
                Socket enviaSocket = new Socket(usuarioRecibido.getIp(),9090);
                ObjectOutputStream enviador = new ObjectOutputStream(enviaSocket.getOutputStream());
                enviador.writeObject(usuarioRecibido);
                enviaSocket.close();
                socketServidor.close();



            }

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        ServidorPrincipal s = new ServidorPrincipal();
    }

}