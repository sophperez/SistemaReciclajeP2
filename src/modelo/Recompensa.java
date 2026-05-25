package modelo;

public class Recompensa {
    private static int contadorId = 0;

    private int idRecompensa;
    private String nombre, descripcion, categoria;
    private int puntosRequeridos, stock;
    private String disponibilidad;

    public Recompensa(String nombre, String descripcion, String categoria, int puntosRequeridos, int stock){
        this.idRecompensa = ++contadorId;
        setNombre(nombre);
        setDescripcion(descripcion);
        setCategoria(categoria);
        setPuntosRequeridos(puntosRequeridos);
        setStock(stock);
        actualizarDisponibilidad();
    }

    public int getIdRecompensa(){
        return idRecompensa;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        boolean valido = true;
        if(nombre == null || nombre.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < nombre.length(); i++){
                char c = nombre.charAt(i);
                if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                        c == ' ' || c == 'á' || c == 'é' || c == 'í' ||
                        c == 'ó' || c == 'ú' || c == 'Á' || c == 'É' ||
                        c == 'Í' || c == 'Ó' || c == 'Ú' || c == 'ñ' ||
                        c == 'Ñ')){
                    valido = false;
                    break;
                }
            }
        }
        if(valido){
            this.nombre = nombre;
        } else {
            this.nombre = "Sin nombre";
        }
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        boolean valido = true;
        if(descripcion == null || descripcion.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < descripcion.length(); i++){
                char c = descripcion.charAt(i);
                if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                        (c >= '0' && c <= '9') || c == ' ' || c == 'á' ||
                        c == 'é' || c == 'í' || c == 'ó' || c == 'ú' ||
                        c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' ||
                        c == 'Ú' || c == 'ñ' || c == 'Ñ')){
                    valido = false;
                    break;
                }
            }
        }
        if(valido){
            this.descripcion = descripcion;
        } else {
            this.descripcion = "Sin descripción";
        }
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        if(categoria == null || categoria.isBlank()){
            this.categoria = "PRODUCTO";
        } else if(categoria.equalsIgnoreCase("PRODUCTO")){
            this.categoria = "PRODUCTO";
        } else if(categoria.equalsIgnoreCase("DESCUENTO")){
            this.categoria = "DESCUENTO";
        } else if(categoria.equalsIgnoreCase("EXPERIENCIA")){
            this.categoria = "EXPERIENCIA";
        } else {
            this.categoria = "PRODUCTO";
        }
    }

    public int getPuntosRequeridos(){
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos){
        if(puntosRequeridos > 0){
            this.puntosRequeridos = puntosRequeridos;
        } else {
            this.puntosRequeridos = 1;
        }
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        if(stock >= 0){
            this.stock = stock;
            actualizarDisponibilidad();
        } else {
            this.stock = 0;
            actualizarDisponibilidad();
        }
    }

    public String getDisponibilidad(){
        return disponibilidad;
    }

    public void actualizarDisponibilidad(){
        if(stock > 0){
            this.disponibilidad = "DISPONIBLE";
        } else {
            this.disponibilidad = "AGOTADO";
        }
    }

    public boolean verificarStock(){
        return stock > 0;
    }

    public void reducirStock(){
        if(stock > 0){
            stock--;
            actualizarDisponibilidad();
        }
    }

    @Override
    public String toString(){
        return "ID recompensa: "+idRecompensa+"\nNombre: "+nombre+"\nDescripción: "+descripcion+"\nCategoría: "+categoria+
                "\nPuntos requeridos: "+puntosRequeridos+"\nStock: "+stock+ "\nDisponibilidad: "+disponibilidad;
    }

}