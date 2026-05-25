package negocio;
import modelo.Ciudadano;
import modelo.Entrega;
import modelo.Material;
import java.util.ArrayList;
import java.util.List;

public class GestorEntregas {
    private List<Entrega> entregas;

    public GestorEntregas(){
        entregas = new ArrayList<>();
    }

    public List<Entrega> getEntregas(){
        return entregas;
    }

    public String registrarEntrega(Ciudadano ciudadano, Material material, double pesoKg){
        if(ciudadano == null){
            return "Error: Ciudadano no encontrado.";
        }
        if(!ciudadano.estaActivo()){
            return "Error: El ciudadano se encuentra inactivo.";
        }
        if(material == null){
            return "Error: Material no válido.";
        }
        if(pesoKg <= 0){
            return "Error: El peso debe ser mayor a 0.";
        }
        Entrega nueva = new Entrega(ciudadano, material, pesoKg);
        entregas.add(nueva);
        ciudadano.sumarPuntos(nueva.getPuntosObtenidos());
        return "Entrega registrada. Se han sumado " +
                nueva.getPuntosObtenidos() + " puntos a tu cuenta.";
    }

    public String consultarHistorial(Ciudadano ciudadano){
        if(ciudadano == null){
            return "Error: Ciudadano no encontrado.";
        }
        String historial = "===HISTORIAL DE ENTREGAS===\n";
        boolean tieneEntregas = false;
        double totalKg = 0;
        int totalPuntos = 0;

        for(Entrega e : entregas){
            if(e.getCiudadano().getCedula().equals(ciudadano.getCedula())){
                historial += e.toString() + "\n-----------\n";
                totalKg += e.getPesoKg();
                totalPuntos += e.getPuntosObtenidos();
                tieneEntregas = true;
            }
        }
        if(!tieneEntregas){
            return "No hay entregas registradas para este ciudadano.";
        }
        historial += "Total kg reciclados: " + totalKg +
                "\nTotal puntos obtenidos: " + totalPuntos;
        return historial;
    }

    public String calcularImpactoAmbiental(Ciudadano ciudadano){
        if(ciudadano == null){
            return "Error: Ciudadano no encontrado.";
        }
        double totalKg = 0;
        boolean tieneEntregas = false;

        for(Entrega e : entregas){
            if(e.getCiudadano().getCedula().equals(ciudadano.getCedula())){
                totalKg += e.getPesoKg();
                tieneEntregas = true;
            }
        }
        if(!tieneEntregas){
            return "No hay entregas registradas para calcular el impacto.";
        }
        double co2Evitado = totalKg * 1.5;
        double arbolesEquivalentes = co2Evitado / 21;
        return "===IMPACTO AMBIENTAL===\n"+"Ciudadano: "+ciudadano.getNombreCompleto()+"\n"+"Total kg reciclados: "+totalKg+
                "\n"+"CO2 evitado: "+co2Evitado+" kg\n"+"Equivalente a: "+String.format("%.2f", arbolesEquivalentes)+
                " árboles salvados";
    }
}