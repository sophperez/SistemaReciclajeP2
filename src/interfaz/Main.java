package interfaz;

import negocio.GestorReciclaje;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static GestorReciclaje gestor = new GestorReciclaje();

    public static void main(String[] args) {
        int opc = 0;
        String opcion;
        do {
            menuLogin();
            opcion = sc.nextLine();
            if(esEntero(opcion)){
                opc = Integer.parseInt(opcion);
            } else {
                System.out.println("Error: Ingrese un número válido.");
                opc = 0;
            }
            switch(opc){
                case 1: {
                    iniciarSesionCiudadano();
                } break;
                case 2: {
                    iniciarSesionAdmin();
                } break;
                case 3: {
                    registrarCiudadano();
                } break;
                case 4: {
                    System.out.println("Gracias por usar el sistema de reciclaje.");
                } break;
                default: {
                    System.out.println("Opción inválida, ingrese un número del 1 al 4.");
                }
            }
        } while(opc != 4);
    }

    // ======= VALIDACIONES =======

    public static boolean esEntero(String texto){
        boolean valido = true;
        if(texto == null || texto.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < texto.length(); i++){
                char c = texto.charAt(i);
                if(!(c >= '0' && c <= '9')){
                    valido = false;
                    break;
                }
            }
        }
        return valido;
    }

    public static boolean esDecimal(String texto){
        boolean valido = true;
        int cantPuntos = 0;
        if(texto == null || texto.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < texto.length(); i++){
                char c = texto.charAt(i);
                if(c == '.'){
                    cantPuntos++;
                } else if(!(c >= '0' && c <= '9')){
                    valido = false;
                    break;
                }
            }
            if(cantPuntos > 1){
                valido = false;
            }
        }
        return valido;
    }

    public static String leerContrasena(String mensaje) {
        System.out.print(mensaje);
        if (System.console() != null) {
            char[] chars = System.console().readPassword();
            return new String(chars);
        } else {
            return sc.nextLine();
        }
    }

    // ======= MENÚS =======

    public static void menuLogin(){
        System.out.println("\n=============================");
        System.out.println("  SISTEMA DE RECICLAJE Y RECOMPENSAS");
        System.out.println("=============================");
        System.out.println("1. Iniciar sesión como ciudadano");
        System.out.println("2. Iniciar sesión como administrador");
        System.out.println("3. Registrarse");
        System.out.println("4. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public static void menuCiudadano(){
        System.out.println("\n=============================");
        System.out.println("  MENÚ CIUDADANO");
        System.out.println("=============================");
        System.out.println("1. Ver mi perfil");
        System.out.println("2. Registrar entrega de material");
        System.out.println("3. Ver historial de entregas");
        System.out.println("4. Ver impacto ambiental");
        System.out.println("5. Ver catálogo de recompensas");
        System.out.println("6. Cerrar sesión");
        System.out.print("Ingrese una opción: ");
    }

    public static void menuAdministrador(){
        System.out.println("\n=============================");
        System.out.println("  MENÚ ADMINISTRADOR");
        System.out.println("=============================");
        System.out.println("1. Listar ciudadanos");
        System.out.println("2. Registrar entrega de material");
        System.out.println("3. Registrar recompensa");
        System.out.println("4. Actualizar recompensa");
        System.out.println("5. Ver catálogo de recompensas");
        System.out.println("6. Dar de baja a ciudadano");
        System.out.println("7. Ver material reciclado por tipo");
        System.out.println("8. Resetear contraseña de ciudadano");
        System.out.println("9. Cerrar sesión");
        System.out.print("Ingrese una opción: ");
    }

    // ======= LOGIN =======

    public static void iniciarSesionCiudadano(){
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        String contrasena = leerContrasena("Ingrese contraseña: ");
        String rol = gestor.iniciarSesionCiudadano(nombreUsuario, contrasena);
        if(rol != null){
            System.out.println("Bienvenido al sistema.");
            menuCiudadanoOpciones();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    public static void iniciarSesionAdmin(){
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        String contrasena = leerContrasena("Ingrese contraseña: ");
        String rol = gestor.iniciarSesionAdmin(nombreUsuario, contrasena);
        if(rol != null){
            System.out.println("Bienvenido, Administrador.");
            menuAdministradorOpciones();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    // ======= REGISTRO =======
    public static void registrarCiudadano(){
        String nombreCompleto, cedula, correo, telefono, direccion, nombreUsuario, contrasena;

        // nombre
        do {
            System.out.print("Ingrese nombre completo: ");
            nombreCompleto = sc.nextLine();
            if(nombreCompleto.isBlank()){
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while(nombreCompleto.isBlank());

        // cédula
        do {
            System.out.print("Ingrese cédula (10 dígitos): ");
            cedula = sc.nextLine();
            if(!esEntero(cedula) || cedula.length() != 10){
                System.out.println("Error: La cédula debe tener exactamente 10 dígitos numéricos.");
            }
        } while(!esEntero(cedula) || cedula.length() != 10);

        // correo
        do {
            System.out.print("Ingrese correo: ");
            correo = sc.nextLine();
            if(!correo.contains("@") || !correo.contains(".")){
                System.out.println("Error: El correo debe contener @ y un punto.");
            }
        } while(!correo.contains("@") || !correo.contains("."));

        // teléfono — se agrega el 0 automáticamente
        do {
            System.out.print("Ingrese teléfono (9 dígitos sin el 0 inicial): ");
            telefono = sc.nextLine();
            if(!esEntero(telefono) || telefono.length() != 9){
                System.out.println("Error: Ingrese 9 dígitos numéricos sin el 0 inicial.");
            }
        } while(!esEntero(telefono) || telefono.length() != 9);
        telefono = "0" + telefono;

        // dirección
        do {
            System.out.print("Ingrese dirección: ");
            direccion = sc.nextLine();
            if(direccion.isBlank()){
                System.out.println("Error: La dirección no puede estar vacía.");
            }
        } while(direccion.isBlank());

        // nombre de usuario
        do {
            System.out.print("Ingrese nombre de usuario: ");
            nombreUsuario = sc.nextLine();
            if(nombreUsuario.isBlank()){
                System.out.println("Error: El nombre de usuario no puede estar vacío.");
            }
        } while(nombreUsuario.isBlank());

        // contraseña
        do {
            contrasena = leerContrasena("Ingrese contraseña (mínimo 4 caracteres): ");
            if(contrasena.length() < 4){
                System.out.println("Error: La contraseña debe tener mínimo 4 caracteres.");
            }
        } while(contrasena.length() < 4);

        String resultado = gestor.registrarCiudadano(nombreCompleto, cedula, correo,
                telefono, direccion, nombreUsuario, contrasena);
        System.out.println(resultado);
    }

    // ======= MENÚ CIUDADANO =======
    public static void menuCiudadanoOpciones(){
        int opc = 0;
        String opcion;
        do {
            menuCiudadano();
            opcion = sc.nextLine();
            if(esEntero(opcion)){
                opc = Integer.parseInt(opcion);
            } else {
                System.out.println("Error: Ingrese un número válido.");
                opc = 0;
            }
            switch(opc){
                case 1: {
                    System.out.println(gestor.consultarPerfil(
                            gestor.getCiudadanoActivo().getCedula()));
                } break;
                case 2: {
                    registrarEntrega();
                } break;
                case 3: {
                    System.out.println(gestor.consultarHistorialEntregas(
                            gestor.getCiudadanoActivo().getCedula()));
                } break;
                case 4: {
                    System.out.println(gestor.calcularImpactoAmbiental(
                            gestor.getCiudadanoActivo().getCedula()));
                } break;
                case 5: {
                    System.out.println(gestor.listarRecompensasDisponibles());
                } break;
                case 6: {
                    gestor.cerrarSesion();
                    System.out.println("Sesión cerrada correctamente.");
                }  break;
                default: {
                    System.out.println("Opción inválida, ingrese un número del 1 al 6.");
                }
            }
        } while(opc != 6);
    }

    // ======= MENÚ ADMINISTRADOR =======

    public static void menuAdministradorOpciones(){
        int opc = 0;
        String opcion;
        do {
            menuAdministrador();
            opcion = sc.nextLine();
            if(esEntero(opcion)){
                opc = Integer.parseInt(opcion);
            } else {
                System.out.println("Error: Ingrese un número válido.");
                opc = 0;
            }
            switch(opc){
                case 1: {
                    System.out.println(gestor.listarCiudadanos());
                } break;
                case 2: { registrarEntradaAdmin(); } break;
                case 3: { registrarRecompensa(); } break;
                case 4: { actualizarRecompensa(); } break;
                case 5: {
                    System.out.println(gestor.consultarCatalogo());
                } break;
                case 6: { darDeBaja(); } break;
                case 7: {
                    System.out.println(gestor.reporteMaterialPorTipo());
                } break;
                case 8: { resetearContrasena(); } break;
                case 9: {
                    gestor.cerrarSesion();
                    System.out.println("Sesión cerrada correctamente.");
                } break;
                default: {
                    System.out.println("Opción inválida, ingrese un número del 1 al 9.");
                }
            }
        } while(opc != 9);
    }

    // ======= OPERACIONES =======

    public static void registrarEntrega(){
        String tipoMaterial, peso;
        String cedula = gestor.getCiudadanoActivo().getCedula();

        System.out.println("Tipos de material: PLÁSTICO / VIDRIO / PAPEL / METAL / ELECTRÓNICO");
        do {
            System.out.print("Ingrese tipo de material: ");
            tipoMaterial = sc.nextLine();
            if(tipoMaterial.isBlank()){
                System.out.println("Error: Debe ingresar un tipo de material.");
            }
        } while(tipoMaterial.isBlank());

        do {
            System.out.print("Ingrese peso en kg: ");
            peso = sc.nextLine();
            if(!esDecimal(peso)){
                System.out.println("Error: El peso debe ser un número válido mayor a 0.");
            }
        } while(!esDecimal(peso));

        System.out.println(gestor.registrarEntrega(cedula, tipoMaterial,
                Double.parseDouble(peso)));
    }

    public static void registrarEntradaAdmin(){
        String cedula, tipoMaterial, peso;

        do {
            System.out.print("Ingrese cédula del ciudadano: ");
            cedula = sc.nextLine();
            if(!esEntero(cedula) || cedula.length() != 10){
                System.out.println("Error: La cédula debe tener 10 dígitos.");
            }
        } while(!esEntero(cedula) || cedula.length() != 10);

        System.out.println("Tipos de material: PLÁSTICO / VIDRIO / PAPEL / METAL / ELECTRÓNICO");
        do {
            System.out.print("Ingrese tipo de material: ");
            tipoMaterial = sc.nextLine();
            if(tipoMaterial.isBlank()){
                System.out.println("Error: Debe ingresar un tipo de material.");
            }
        } while(tipoMaterial.isBlank());

        do {
            System.out.print("Ingrese peso en kg: ");
            peso = sc.nextLine();
            if(!esDecimal(peso)){
                System.out.println("Error: El peso debe ser un número válido mayor a 0.");
            }
        } while(!esDecimal(peso));

        System.out.println(gestor.registrarEntrega(cedula, tipoMaterial,
                Double.parseDouble(peso)));
    }

    public static void registrarRecompensa(){
        String nombre, descripcion, categoria, puntos, stock;

        do {
            System.out.print("Ingrese nombre de la recompensa: ");
            nombre = sc.nextLine();
            if(nombre.isBlank()){
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while(nombre.isBlank());

        do {
            System.out.print("Ingrese descripción: ");
            descripcion = sc.nextLine();
            if(descripcion.isBlank()){
                System.out.println("Error: La descripción no puede estar vacía.");
            }
        } while(descripcion.isBlank());

        System.out.println("Categorías: PRODUCTO / DESCUENTO / EXPERIENCIA");
        do {
            System.out.print("Ingrese categoría: ");
            categoria = sc.nextLine();
            if(!categoria.equalsIgnoreCase("PRODUCTO") &&
                    !categoria.equalsIgnoreCase("DESCUENTO") &&
                    !categoria.equalsIgnoreCase("EXPERIENCIA")){
                System.out.println("Error: Categoría inválida. Use PRODUCTO, DESCUENTO o EXPERIENCIA.");
            }
        } while(!categoria.equalsIgnoreCase("PRODUCTO") &&
                !categoria.equalsIgnoreCase("DESCUENTO") &&
                !categoria.equalsIgnoreCase("EXPERIENCIA"));

        do {
            System.out.print("Ingrese puntos requeridos: ");
            puntos = sc.nextLine();
            if(!esEntero(puntos) || Integer.parseInt(puntos) <= 0){
                System.out.println("Error: Los puntos deben ser un número entero mayor a 0.");
            }
        } while(!esEntero(puntos) || Integer.parseInt(puntos) <= 0);

        do {
            System.out.print("Ingrese stock disponible: ");
            stock = sc.nextLine();
            if(!esEntero(stock) || Integer.parseInt(stock) <= 0){
                System.out.println("Error: El stock debe ser un número entero mayor a 0.");
            }
        } while(!esEntero(stock) || Integer.parseInt(stock) <= 0);

        System.out.println(gestor.registrarRecompensa(nombre, descripcion,
                categoria, Integer.parseInt(puntos), Integer.parseInt(stock)));
    }

    public static void actualizarRecompensa(){
        String id, descripcion, puntos, stock;

        System.out.println(gestor.consultarCatalogo());

        do {
            System.out.print("Ingrese ID de la recompensa a actualizar: ");
            id = sc.nextLine();
            if(!esEntero(id)){
                System.out.println("Error: El ID debe ser un número entero.");
            }
        } while(!esEntero(id));

        System.out.print("Ingrese nueva descripción (Enter para no cambiar): ");
        descripcion = sc.nextLine();

        do {
            System.out.print("Ingrese nuevos puntos requeridos (0 para no cambiar): ");
            puntos = sc.nextLine();
            if(!esEntero(puntos)){
                System.out.println("Error: Los puntos deben ser un número entero.");
            }
        } while(!esEntero(puntos));

        do {
            System.out.print("Ingrese nuevo stock (0 para no cambiar): ");
            stock = sc.nextLine();
            if(!esEntero(stock)){
                System.out.println("Error: El stock debe ser un número entero.");
            }
        } while(!esEntero(stock));

        System.out.println(gestor.actualizarRecompensa(Integer.parseInt(id),
                descripcion, Integer.parseInt(puntos), Integer.parseInt(stock)));
    }

    public static void darDeBaja(){
        String cedula;
        System.out.println(gestor.listarCiudadanos());
        do {
            System.out.print("Ingrese cédula del ciudadano a dar de baja: ");
            cedula = sc.nextLine();
            if(!esEntero(cedula) || cedula.length() != 10){
                System.out.println("Error: La cédula debe tener 10 dígitos.");
            }
        } while(!esEntero(cedula) || cedula.length() != 10);
        System.out.println(gestor.darDeBaja(cedula));
    }

    public static void resetearContrasena(){
        String cedula, nuevaContrasena;
        System.out.println(gestor.listarCiudadanos());

        do {
            System.out.print("Ingrese cédula del ciudadano: ");
            cedula = sc.nextLine();
            if(!esEntero(cedula) || cedula.length() != 10){
                System.out.println("Error: La cédula debe tener 10 dígitos.");
            }
        } while(!esEntero(cedula) || cedula.length() != 10);

        do {
            nuevaContrasena = leerContrasena("Ingrese nueva contraseña (mínimo 4 caracteres): ");
            if(nuevaContrasena.length() < 4){
                System.out.println("Error: La contraseña debe tener mínimo 4 caracteres.");
            }
        } while(nuevaContrasena.length() < 4);

        System.out.println(gestor.resetearContrasena(cedula, nuevaContrasena));
    }
}