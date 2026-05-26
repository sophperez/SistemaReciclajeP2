package modelo;

public class Material {
    private String tipoMaterial;
    private int puntosPorKg;

    public Material(String tipoMaterial){
        setTipoMaterial(tipoMaterial);
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

    public double calcularPuntos(double kg){
        if(kg > 0){
            return kg*puntosPorKg;
        }else{
            return 0;
        }
    }

    @Override
    public String toString(){
        return "Tipo de material: "+tipoMaterial+"\nPuntos por kg: "+puntosPorKg;
    }
}
