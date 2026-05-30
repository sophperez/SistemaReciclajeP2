package negocio;
import modelo.Administrador;
import modelo.Ciudadano;
import modelo.Material;
import modelo.Recompensa;
import java.util.Iterator;
import java.util.List;

public class GestorReciclaje {
    private GestorCiudadanos gestorCiudadanos;
    private GestorEntregas gestorEntregas;
    private GestorRecompensas gestorRecompensas;
    private Administrador administrador;
    private Ciudadano ciudadanoActivo;

    public GestorReciclaje(){ //se crea el gestor
        gestorCiudadanos = new GestorCiudadanos();
        gestorEntregas = new GestorEntregas();
        gestorRecompensas = new GestorRecompensas();
        administrador = new Administrador("Bernarda Sandoval",
                "1723456789", "bernarda.sandoval@reciclaje.com",
                "0998765432", "Av. Amazonas N34-56", "admin", "admin1234");
        ciudadanoActivo = null;
        cargarDatosQuemados();
    }

    private void cargarDatosQuemados(){
        // ciudadanos de prueba
        gestorCiudadanos.registrarCiudadano("Genesis Barreiro", "1712345678",
                "genesis.barreiro@hotmail.com", "0991234567", "Av. Amazonas N12-34",
                "gene_barreiro", "gene1234");
        gestorCiudadanos.registrarCiudadano("Tabatha Flores", "1798765432",
                "tabatha.flores@gmail.com", "0987654321", "Calle Sucre 45-67",
                "taba_flores", "taba1234");
        gestorCiudadanos.registrarCiudadano("Naomi Jaya", "1756781234",
                "naomi.jaya@hotmail.com", "0976543210", "Av. 6 de Diciembre N23-45",
                "nao_jaya", "nao1234");
        gestorCiudadanos.registrarCiudadano("Sophia Perez", "1734567890",
                "sophia.perez@gmail.com", "0965432109", "Calle Patria 78-90",
                "soph_perez", "soph123");
        gestorCiudadanos.registrarCiudadano("Teresa Vega", "1723456789",
                "teresa.vega@outlook.com", "0954321098", "Av. Republica E4-56",
                "maria_vega", "maria123");
        gestorCiudadanos.registrarCiudadano("Juan Castillo", "1745678901",
                "juan.castillo@hotmail.com", "0943210987", "Calle Veintimilla 34-12",
                "juan_castillo", "juan123");
        gestorCiudadanos.registrarCiudadano("Valeria Rios", "1767890123",
                "valeria.rios@gmail.com", "0932109876", "Av. Colon N15-78",
                "valeria_rios", "valeria123");

        // recompensas de prueba
        gestorRecompensas.registrarRecompensa("Bolsa ecologica",
                "Bolsa reutilizable de tela", "PRODUCTO", 100, 10);
        gestorRecompensas.registrarRecompensa("Descuento supermercado",
                "Descuento del diez por ciento", "DESCUENTO", 500, 5);
        gestorRecompensas.registrarRecompensa("Entrada al cine",
                "Una entrada para cualquier funcion", "EXPERIENCIA", 1000, 3);
        gestorRecompensas.registrarRecompensa("Kit de reciclaje",
                "Kit con bolsas y contenedores", "PRODUCTO", 300, 8);
        gestorRecompensas.registrarRecompensa("Vale de transporte",
                "Vale para transporte publico", "DESCUENTO", 750, 4);

        // entregas de prueba
        // Genesis — nivel PLATINO (3000+)
        Ciudadano c1 = gestorCiudadanos.buscarPorCedula("1712345678");
        gestorEntregas.registrarEntrega(c1, new Material("METAL"), 50);
        gestorEntregas.registrarEntrega(c1, new Material("ELECTRONICO"), 30);
        gestorEntregas.registrarEntrega(c1, new Material("PLASTICO"), 20);

        // Tabatha — nivel ORO (1500+)
        Ciudadano c2 = gestorCiudadanos.buscarPorCedula("1798765432");
        gestorEntregas.registrarEntrega(c2, new Material("METAL"), 20);
        gestorEntregas.registrarEntrega(c2, new Material("PLASTICO"), 30);
        gestorEntregas.registrarEntrega(c2, new Material("VIDRIO"), 15);

        // Naomi — nivel PLATA (500+)
        Ciudadano c3 = gestorCiudadanos.buscarPorCedula("1756781234");
        gestorEntregas.registrarEntrega(c3, new Material("PAPEL"), 30);
        gestorEntregas.registrarEntrega(c3, new Material("PLASTICO"), 20);

        // Sophia — nivel PLATA (500+)
        Ciudadano c4 = gestorCiudadanos.buscarPorCedula("1734567890");
        gestorEntregas.registrarEntrega(c4, new Material("VIDRIO"), 40);
        gestorEntregas.registrarEntrega(c4, new Material("PAPEL"), 20);

        // Teresa — nivel BRONCE (menos de 500)
        Ciudadano c5 = gestorCiudadanos.buscarPorCedula("1723456789");
        gestorEntregas.registrarEntrega(c5, new Material("PLASTICO"), 5);

        // Juan — nivel BRONCE (menos de 500)
        Ciudadano c6 = gestorCiudadanos.buscarPorCedula("1745678901");
        gestorEntregas.registrarEntrega(c6, new Material("PAPEL"), 10);

        // Valeria — sin entregas aún, nivel BRONCE
    }

    // ======= AUTENTICACIÓN =======

    public String iniciarSesionCiudadano(String nombreUsuario, String contrasena){
        Ciudadano c = gestorCiudadanos.iniciarSesion(nombreUsuario, contrasena);
        if(c != null){
            ciudadanoActivo = c;
            return "CIUDADANO";
        }
        return null;
    }

    public String iniciarSesionAdmin(String nombreUsuario, String contrasena){
        if(administrador.iniciarSesion(nombreUsuario, contrasena)){
            return "ADMINISTRADOR";
        }
        return null;
    }

    public void cerrarSesion(){
        ciudadanoActivo = null;
    }

    public Ciudadano getCiudadanoActivo(){
        return ciudadanoActivo;
    }

    // ======= MÓDULO 1: CIUDADANOS =======

    public String registrarCiudadano(String nombreCompleto, String cedula, String correo, String telefono, String direccion,
                                     String nombreUsuario, String contrasena){
        return gestorCiudadanos.registrarCiudadano(nombreCompleto, cedula,
                correo, telefono, direccion, nombreUsuario, contrasena);
    }

    public String consultarPerfil(String cedula){
        return gestorCiudadanos.consultarPerfil(cedula);
    }

    public String darDeBaja(String cedula){
        return gestorCiudadanos.darDeBaja(cedula);
    }

    public String listarCiudadanos(){
        return gestorCiudadanos.listarCiudadanos();
    }

    public String resetearContrasena(String cedula, String nuevaContrasena){
        return gestorCiudadanos.resetearContrasena(cedula, nuevaContrasena);
    }

    // ======= MÓDULO 2: ENTREGAS =======

    public String registrarEntrega(String cedula, String tipoMaterial,
                                   double pesoKg){
        Ciudadano c = gestorCiudadanos.buscarPorCedula(cedula);
        if(c == null){
            return "Error: Ciudadano no encontrado.";
        }
        Material material = new Material(tipoMaterial);
        return gestorEntregas.registrarEntrega(c, material, pesoKg);
    }

    public String consultarHistorialEntregas(String cedula){
        Ciudadano c = gestorCiudadanos.buscarPorCedula(cedula);
        if(c == null){
            return "Error: Ciudadano no encontrado.";
        }
        return gestorEntregas.consultarHistorial(c);
    }

    public String calcularImpactoAmbiental(String cedula){
        Ciudadano c = gestorCiudadanos.buscarPorCedula(cedula);
        if(c == null){
            return "Error: Ciudadano no encontrado.";
        }
        return gestorEntregas.calcularImpactoAmbiental(c);
    }

    public String reporteMaterialPorTipo(){
        return gestorEntregas.reporteMaterialPorTipo();
    }

    // ======= MÓDULO 3:  RECOMPENSAS =======

    public String registrarRecompensa(String nombre, String descripcion,
                                      String categoria, int puntosRequeridos,
                                      int stock){
        return gestorRecompensas.registrarRecompensa(nombre, descripcion,
                categoria, puntosRequeridos, stock);
    }

    public String actualizarRecompensa(int idRecompensa, String descripcion,
                                       int puntosRequeridos, int stock){
        return gestorRecompensas.actualizarRecompensa(idRecompensa,
                descripcion, puntosRequeridos, stock);
    }

    public String consultarCatalogo(){
        return gestorRecompensas.consultarCatalogo();
    }

    // ======= ITERATOR — listar recompensas disponibles =======

    public String listarRecompensasDisponibles(){
        List<Recompensa> recompensas = gestorRecompensas.getRecompensas();
        if(recompensas.isEmpty()){
            return "No hay recompensas registradas en el catálogo.";
        }
        String lista = "===RECOMPENSAS DISPONIBLES===\n";
        Iterator<Recompensa> it = recompensas.iterator();
        while(it.hasNext()){
            Recompensa r = it.next();
            if(r.verificarStock()){
                lista += r.toString() + "\n-----------\n";
            }
        }
        return lista;
    }
}