package negocio;
import modelo.Ciudadano;
import modelo.Recompensa;
import java.util.ArrayList;
import java.util.List;

public class GestorRecompensas {
    private List<Recompensa> recompensas;

    public GestorRecompensas(){
        recompensas = new ArrayList<>();
    }

    public List<Recompensa> getRecompensas(){
        return recompensas;
    }

    public Recompensa buscarPorId(int idRecompensa){
        for(Recompensa r : recompensas){
            if(r.getIdRecompensa() == idRecompensa){
                return r;
            }
        }
        return null;
    }

    public String registrarRecompensa(String nombre, String descripcion,
                                      String categoria, int puntosRequeridos,
                                      int stock){
        if(puntosRequeridos <= 0){
            return "Error: Los puntos requeridos deben ser mayores a 0.";
        }
        if(stock <= 0){
            return "Error: El stock debe ser mayor a 0.";
        }
        Recompensa nueva = new Recompensa(nombre, descripcion, categoria,
                puntosRequeridos, stock);
        recompensas.add(nueva);
        return "Recompensa agregada exitosamente al catálogo.";
    }

    public String actualizarRecompensa(int idRecompensa, String descripcion,
                                       int puntosRequeridos, int stock){
        Recompensa r = buscarPorId(idRecompensa);
        if(r == null){
            return "Error: Recompensa no encontrada.";
        }
        if(descripcion != null && !descripcion.isBlank()){
            r.setDescripcion(descripcion);
        }
        if(puntosRequeridos > 0){
            r.setPuntosRequeridos(puntosRequeridos);
        }
        if(stock >= 0){
            r.setStock(stock);
        }
        return "Recompensa actualizada correctamente.";
    }

    public String consultarCatalogo(){
        if(recompensas.isEmpty()){
            return "No hay recompensas registradas en el catálogo.";
        }
        String catalogo = "===CATÁLOGO DE RECOMPENSAS===\n";
        for(Recompensa r : recompensas){
            catalogo += r.toString() + "\n-----------\n";
        }
        return catalogo;
    }

    public String verificarElegibilidad(Ciudadano ciudadano, int idRecompensa){
        if(ciudadano == null){
            return "Error: Ciudadano no encontrado.";
        }
        Recompensa r = buscarPorId(idRecompensa);
        if(r == null){
            return "Error: Recompensa no encontrada.";
        }
        if(!r.verificarStock()){
            return "Recompensa agotada temporalmente.";
        }
        if(ciudadano.getPuntos() < r.getPuntosRequeridos()){
            int puntosFaltantes = r.getPuntosRequeridos() - ciudadano.getPuntos();
            return "Puntos insuficientes. Te faltan " +
                    puntosFaltantes + " puntos para esta recompensa.";
        }
        return "ELEGIBLE";
    }
}
