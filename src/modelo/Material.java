package modelo;

public class Material {
    private String tipoMaterial;
    private int puntosPorKg;
    private String descripcion;

    public Material(String tipoMaterial, String descipcion){
        setTipoMaterial(tipoMaterial);
        setDescripcion(descipcion);
    }

    public String getTipoMaterial(){
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial){
        if(tipoMaterial == null || tipoMaterial.isBlank()){
            this.tipoMaterial = "DESCONOCIDO";
            this.puntosPorKg = 0;
        } else if(tipoMaterial.equalsIgnoreCase("PLASTICO") ||
                tipoMaterial.equalsIgnoreCase("PLÁSTICO")){
            this.tipoMaterial = "PLÁSTICO";
            this.puntosPorKg = 10;
        } else if(tipoMaterial.equalsIgnoreCase("VIDRIO")){
            this.tipoMaterial = "VIDRIO";
            this.puntosPorKg = 8;
        } else if(tipoMaterial.equalsIgnoreCase("PAPEL")){
            this.tipoMaterial = "PAPEL";
            this.puntosPorKg = 5;
        } else if(tipoMaterial.equalsIgnoreCase("METAL")){
            this.tipoMaterial = "METAL";
            this.puntosPorKg = 15;
        } else if(tipoMaterial.equalsIgnoreCase("ELECTRONICO") ||
                tipoMaterial.equalsIgnoreCase("ELECTRÓNICO")){
            this.tipoMaterial = "ELECTRÓNICO";
            this.puntosPorKg = 20;
        } else {
            this.tipoMaterial = "DESCONOCIDO";
            this.puntosPorKg = 0;
        }
    }

    public int getPuntosPorKg(){
        return puntosPorKg;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        boolean valido = true;
        if (descripcion == null || descripcion.isBlank()) {
            valido = false;
        } else {
            for (int i = 0; i < descripcion.length(); i++) {
                char c = descripcion.charAt(i);
                if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                        (c >= '0' && c <= '9') || c == ' ' || c == 'á' ||
                        c == 'é' || c == 'í' || c == 'ó' || c == 'ú' ||
                        c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú')) {
                    valido = false;
                    break;
                }
            }
        }
        if (valido) {
            this.descripcion = descripcion;
        } else {
            this.descripcion = "Sin descripción";
        }
    }

    public double calcularPuntos(double kg){
        if(kg > 0){
            return kg*puntosPorKg;
        }else{
            return 0;
        }
    }

    @Override
    public String toString(){
        return "Tipo de material: "+tipoMaterial+"\nPuntos por kg: "+puntosPorKg+"\nDescripción: "+descripcion;
    }
}
