package negocio;
import modelo.Ciudadano;
import java.util.ArrayList;
import java.util.List;

public class GestorCiudadanos {
    private List<Ciudadano> ciudadanos;

    public GestorCiudadanos(){
        ciudadanos = new ArrayList<>();
    }

    public List<Ciudadano> getCiudadanos(){
        return ciudadanos;
    }

    private boolean existeCedula(String cedula){
        for(Ciudadano ci : ciudadanos){
            if(ci.getCedula().equals(cedula)){
                return true;
            }
        }
        return false;
    }

    private boolean existeNombreUsuario(String nombreUsuario){
        for(Ciudadano ci : ciudadanos){
            if(ci.getNombreUsuario().equals(nombreUsuario)){
                return true;
            }
        }
        return false;
    }

    public Ciudadano buscarPorCedula(String cedula){
        for(Ciudadano ci : ciudadanos){
            if(ci.getCedula().equals(cedula)){
                return ci;
            }
        }
        return null;
    }

    public String registrarCiudadano(String nombreCompleto, String cedula, String correo, String telefono,
                                     String direccion, String nombreUsuario, String contrasena){
        if(existeCedula(cedula)){
            return "Error: La cédula ya existe en el sistema.";
        }
        if(existeNombreUsuario(nombreUsuario)){
            return "Error: El nombre de usuario ya existe en el sistema.";
        }
        Ciudadano nuevo = new Ciudadano(nombreCompleto, cedula, correo,
                telefono, direccion, nombreUsuario, contrasena);
        ciudadanos.add(nuevo);
        return "Registro exitoso. Bienvenido al programa de reciclaje.";
    }

    public Ciudadano iniciarSesion(String nombreUsuario, String contrasena){
        for(Ciudadano ci : ciudadanos){
            if(ci.estaActivo() && ci.iniciarSesion(nombreUsuario, contrasena)){
                return ci;
            }
        }
        return null;
    }

    public String consultarPerfil(String cedula){
        Ciudadano ci = buscarPorCedula(cedula);
        if(ci != null){
            return ci.toString();
        }
        return "Error: Ciudadano no encontrado.";
    }

    public String darDeBaja(String cedula){
        Ciudadano ci = buscarPorCedula(cedula);
        if(ci != null){
            if(ci.estaActivo()){
                ci.setEstado("INACTIVO");
                return "El ciudadano ha sido dado de baja del programa.";
            } else {
                return "Error: El ciudadano ya se encuentra inactivo.";
            }
        }
        return "Error: Ciudadano no encontrado.";
    }

    public String listarCiudadanos(){
        if(ciudadanos.isEmpty()){
            return "No hay ciudadanos registrados.";
        }
        String lista = "===CIUDADANOS REGISTRADOS===\n";
        for(Ciudadano ci : ciudadanos){
            lista += ci.toString() + "\n-----------\n";
        }
        return lista;
    }

}
