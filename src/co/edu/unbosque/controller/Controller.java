package co.edu.unbosque.controller;

import co.edu.unbosque.model.ServidorCliente;
import co.edu.unbosque.model.ServidorPrincipal;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.persistence.UsuarioDAO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    UsuarioDAO usuarioDAO;
    Scanner entrada = new Scanner(System.in);
    ServidorCliente client;
    public Controller() throws UnknownHostException {
        usuarioDAO = new UsuarioDAO();
        funcionar();


    }

    public void funcionar() throws UnknownHostException {

        if(usuarioDAO.sesionIniciada()==null){

           menuInicio();
        }else if(usuarioDAO.sesionIniciada().isCiudadano()){
            usuarioDAO.sesionIniciada().iniciarServidor();
            menuCiudadano(usuarioDAO.sesionIniciada());
        }else if(usuarioDAO.sesionIniciada().isAgente()){
            menuAgente(usuarioDAO.sesionIniciada());
        }

    }

    public void menuInicio() throws UnknownHostException {
        String usuario = "";
        String contra = "";
        int opcion1 = 0;
        do{
            System.out.println("BIENVENIDO A EL SERVICIO AL CLIENTE DE CIUDADANO DE 4 PATAS");
            System.out.println("1. Iniciar Sesion:\n2 Registrarse: ");
            opcion1 = entrada.nextInt();
            if(opcion1==1){
                System.out.println("Ingrese el usuario: ");
                usuario = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese la contrasenia: ");
                contra = entrada.next();
                if(!usuarioDAO.comprobarInicioSesionCiudadano(usuario,contra)){
                    System.out.println("No se encontro el usuario");
                    funcionar();
                }else{
                    usuarioDAO.iniciarSesion(usuarioDAO.devolverUsuario(usuario));
                    funcionar();
                }
            }else if(opcion1==2){
                int decision=0;
                do{
                    System.out.println("Registrarse como:\n1. Ciudadano\n2. Agente");
                    decision = entrada.nextInt();
                    if(decision==1){

                        System.out.println("Ingrese el usuario: ");
                        usuario = entrada.next();
                        entrada.nextLine();
                        System.out.println("Ingrese la contrasenia: ");
                        contra = entrada.next();
                        InetAddress address = InetAddress.getLocalHost();
                        Usuario aux = new Usuario(usuario,contra);
                        aux.setInicioSesion(true);
                        aux.setCiudadano(true);
                        usuarioDAO.agregarUsuario(aux);
                        menuCiudadano(aux);
                    }else if(decision==2){
                        System.out.println("Ingrese el usuario: ");
                        usuario = entrada.next();
                        entrada.nextLine();
                        System.out.println("Ingrese la contrasenia: ");
                        contra = entrada.next();

                        Usuario aux = new Usuario(usuario,contra);
                        aux.setInicioSesion(true);
                        aux.setCiudadano(false);
                        aux.setAgente(true);
                        usuarioDAO.agregarUsuario(aux);
                        menuAgente(aux);
                    }
                }while(decision<0||decision>2);

            }

        }while (opcion1<0||opcion1>2);

    }

    public void menuAgente(Usuario c) throws UnknownHostException {
        int decision = 0;
        do{
            System.out.println("---BIENVENIDO AGENTE "+usuarioDAO.sesionIniciada().getUsuario()+" AL CHAT DE CIUDADANO DE 4 PATAS-----");
            System.out.println("1. Buscar ciudadano: \n2. Cerrar sesion");
            decision = entrada.nextInt();
            if(decision==1){
                String ip = "";
                System.out.println("Ingrese la ip del destinatario");
                ip = entrada.next();
                entrada.nextLine();
                int controlador = 0;
                String aux = "";
                Usuario usuario =usuarioDAO.sesionIniciada();
                usuario.setIp(ip);
                usuario.iniciarServidor();
                String mensajeEnviar = "";
                do{
                    //System.out.println(usuario.getUsuario()+":");
                    //aux = entrada.nextLine();
                    mensajeEnviar = entrada.nextLine();
                    if(mensajeEnviar.equals("salir")){
                        controlador++;
                        menuCiudadano(c);
                    }else{
                        usuario.enviarMensaje(mensajeEnviar);
                    }
                    mensajeEnviar="";
                }while(controlador==0);

            }else if(decision==2){
                Usuario usuario =usuarioDAO.sesionIniciada();
                usuarioDAO.cerrarSesion(usuario);
                funcionar();
            }

        }while(decision<0||decision>2);



    }

    public void menuCiudadano(Usuario c) throws UnknownHostException {
        int opcion1 = 0;
         do{
            System.out.println("-------Bienvenido ciudadano "+c.getUsuario()+" al chat CIUDADANO DE 4 PATAS--------");
            System.out.println("1. Crear caso\n2. Hablar con un agente\n3. Cerrar sesion\nSeleccion; ");
            opcion1 = entrada.nextInt();
            if(opcion1==1){
                int decision = 0;
                do{
                    System.out.println("Digite el numero que corresponde al caso que quiere ");
                    System.out.println("1. Perdida\n2. Robo\n3. Abandono\n4. Animal Peligroso\n5. Manejo indebido en via publica");
                    decision = entrada.nextInt();
                }while(decision<0||decision>5);
                System.out.println("A continuación necesitaremos los siguientes datos: ");
                String especie, tamanio, localidad, direccion, nombrePersona, telefono, email, comentarios;
                System.out.println("Ingrese la especie:");
                especie = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese el tamanio");
                tamanio = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese la localidad del reporte");
                localidad = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese la direccion del reporte");
                direccion = entrada.next();
                entrada.nextLine();
                nombrePersona = usuarioDAO.sesionIniciada().getUsuario();
                System.out.println("Ingrese el telefono");
                telefono = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese su email");
                email = entrada.next();
                entrada.nextLine();
                System.out.println("Ingrese los comentarios");
                comentarios = entrada.next();
                Reporte reporte = new Reporte(especie,tamanio,localidad,direccion,nombrePersona,telefono,email,comentarios);
                System.out.println("Su caso ha sido creado con exito");
                menuCiudadano(c);
            }else if(opcion1==2){
                String ip = "";
                System.out.println("Para salir del chat en cualquier momento ingrese 'salir'");
                System.out.println("Ingrese la ip del destinatario");
                ip = entrada.next();
                entrada.nextLine();
                int controlador = 0;
                String aux = "";
                Usuario usuario =usuarioDAO.sesionIniciada();
                usuario.setIp(ip);
                usuario.iniciarServidor();
                String mensajeEnviar = "";
                do{
                    mensajeEnviar = entrada.nextLine();
                    if(mensajeEnviar.equals("salir")){
                        controlador++;
                        menuCiudadano(c);
                    }else{
                        usuario.enviarMensaje(mensajeEnviar);
                    }
                    mensajeEnviar="";
                }while(controlador==0);
            }else if(opcion1==3){
                Usuario usuario =usuarioDAO.sesionIniciada();
                usuarioDAO.cerrarSesion(usuario);
                funcionar();
            }

        }while(opcion1<0||opcion1>2);

    }


}
