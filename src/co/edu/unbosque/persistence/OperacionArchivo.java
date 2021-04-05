package co.edu.unbosque.persistence;

import co.edu.unbosque.model.Usuario;

import java.io.*;
import java.util.ArrayList;

public class OperacionArchivo {
    private ObjectInputStream recuperar;
    private ObjectOutputStream escribir;

    public OperacionArchivo(){

    }

    public ArrayList<Usuario> obtener(){
        try  {
            recuperar = new ObjectInputStream(new FileInputStream("./Data/Ciudadanos.dat"));
            ArrayList<Usuario> aux = (ArrayList<Usuario>) recuperar.readObject();
            recuperar.close();
            return aux;
        }catch (EOFException e) {
            return null;
        }catch(IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void escribir(ArrayList<Usuario> a) {
        try {
            ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("./Data/Ciudadanos.dat"));
            escribir.writeObject(a);
            escribir.close();

        } catch (Exception e) {
            System.out.println("Ocurrio una excepcion");
            e.printStackTrace();
            // TODO: handle exception
        }
    }

}
