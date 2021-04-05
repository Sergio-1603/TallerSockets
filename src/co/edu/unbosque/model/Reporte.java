package co.edu.unbosque.model;

public class Reporte {
    private String especie;
    private String tamanio;
    private String localidad;
    private String direccion;
    private String nombreReporte;
    private String telefono;
    private String email;
    private String comentarios;

    public Reporte(String especie, String tamanio, String localidad, String direccion, String nombreReporte, String telefono, String email, String comentarios) {
        this.especie = especie;
        this.tamanio = tamanio;
        this.localidad = localidad;
        this.direccion = direccion;
        this.nombreReporte = nombreReporte;
        this.telefono = telefono;
        this.email = email;
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Reporte hecho por" + nombreReporte+"\n"+
                "Especie='" + especie + "\n" +
                "Tamanio='" + tamanio + "\n" +
                "Localidad='" + localidad + "\n" +
                "Direccion='" + direccion + "\n" +
                "Telefono='" + telefono + "\n" +
                "Email='" + email + "\n" +
                "Comentarios='" + comentarios + "\n";
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
