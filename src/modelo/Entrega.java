package modelo;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Entrega {
    private static int contadorId = 0;

    private int idEntrega;
    private Ciudadano ciudadano;
    private Material material;
    private double pesoKg;
    private String fecha;
    private int puntosObtenidos;

    public Entrega(Ciudadano ciudadano, Material material,
                   double pesoKg){
        this.idEntrega = ++contadorId;
        setCiudadano(ciudadano);
        setMaterial(material);
        setPesoKg(pesoKg);
        this.fecha = obtenerFechaActual();
        this.puntosObtenidos = calcularPuntos();
    }

    public int getIdEntrega(){
        return idEntrega;
    }

    public Ciudadano getCiudadano(){
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano){
        if(ciudadano != null){
            this.ciudadano = ciudadano;
        }
    }

    public Material getMaterial(){
        return material;
    }

    public void setMaterial(Material material){
        if(material != null){
            this.material = material;
        }
    }

    public double getPesoKg(){
        return pesoKg;
    }

    public void setPesoKg(double pesoKg){
        if(pesoKg > 0){
            this.pesoKg = pesoKg;
        } else {
            this.pesoKg = 0;
        }
    }

    public String getFecha(){
        return fecha;
    }

    public int getPuntosObtenidos(){
        return puntosObtenidos;
    }

    private String obtenerFechaActual(){
        GregorianCalendar gc = new GregorianCalendar();
        int dia = gc.get(Calendar.DAY_OF_MONTH);
        int mes = gc.get(Calendar.MONTH) + 1;
        int anio = gc.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + anio;
    }

    public int calcularPuntos(){
        return (int) material.calcularPuntos(pesoKg);
    }

    public String calcularImpactoAmbiental(){
        double co2Evitado = pesoKg * 1.5;
        double arbolesEquivalentes = co2Evitado / 21;
        return "Kg reciclados: "+pesoKg+"\nCO2 evitado: "+co2Evitado+" kg"+"\nEquivalente a: "+String.format("%.2f", arbolesEquivalentes)+
                "árboles salvados";
    }

    @Override
    public String toString(){
        return "ID entrega: "+idEntrega+"\nCiudadano: "+ciudadano.getNombreCompleto()+"\nMaterial: " + material.getTipoMaterial()+
                "\nPeso: "+pesoKg+" kg"+"\nFecha: "+fecha+"\nPuntos obtenidos: "+puntosObtenidos;
    }
}
