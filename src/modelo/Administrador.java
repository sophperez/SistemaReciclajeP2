package modelo;

public class Administrador extends Usuario {
    public Administrador(String nombreCompleto, String cedula, String correo, String telefono, String direccion,
                         String nombreUsuario, String contrasena){
        super(nombreCompleto, cedula, correo, telefono, direccion,
                nombreUsuario, contrasena);
    }

    @Override
    public String toString(){
        return "===ADMINISTRADOR===\n" + super.toString();
    }
}
