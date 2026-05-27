package modelo;

public class Usuario {
    private String nombreCompleto, cedula, correo, telefono, direccion,  nombreUsuario, contrasena;

    public Usuario(String nombreCompleto, String cedula, String correo, String telefono, String direccion,
                   String nombreUsuario, String contrasena){
        setNombreCompleto(nombreCompleto);
        setCedula(cedula);
        setCorreo(correo);
        setTelefono(telefono);
        setDireccion(direccion);
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        boolean valido = true;
        if(nombreCompleto == null || nombreCompleto.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < nombreCompleto.length(); i++){
                char c = nombreCompleto.charAt(i); // El ciclo sigue mientras i sea menor que el tamaño.
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
            this.nombreCompleto = nombreCompleto; //guarda el nombre en el atributo del objeto
        }
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        boolean valido = true;
        if(cedula == null || cedula.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < cedula.length(); i++){
                char c = cedula.charAt(i);
                if(!(c >= '0' && c <= '9')){
                    valido = false;
                    break;
                }
            }
            if(cedula.length() != 10){
                valido = false;
            }
        }
        if(valido){
            this.cedula = cedula;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        boolean valido = true;
        if(correo == null || correo.isBlank()){
            valido = false;
        } else {
            boolean tieneArroba = false;
            boolean tienePunto = false;
            for(int i = 0; i < correo.length(); i++){
                if(correo.charAt(i) == '@') tieneArroba = true;
                if(correo.charAt(i) == '.') tienePunto = true;
            }
            if(!tieneArroba|| !tienePunto){
                valido = false;
            }
        }
        if(valido){
            this.correo = correo;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        boolean valido = true;
        if(telefono == null || telefono.isBlank()){
            valido = false;
        } else {
            for(int i = 0; i < telefono.length(); i++){
                char c = telefono.charAt(i);
                if(!(c >= '0' && c <= '9')){
                    valido = false;
                    break;
                }
            }
            if(telefono.length() != 10){
                valido = false;
            }
        }
        if(valido){
            this.telefono = telefono;
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(direccion != null && !direccion.isBlank()){
            this.direccion = direccion;
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        boolean valido = true;
        if(nombreUsuario == null || nombreUsuario.isBlank()){
            valido = false;
        }else{
            for(int i = 0; i < nombreUsuario.length(); i++){
                char c = nombreUsuario.charAt(i);
                if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                        (c >= '0' && c <= '9') || c == '_')){
                    valido = false;
                    break;
                }
            }
        }
        if(valido){
            this.nombreUsuario = nombreUsuario;
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if(contrasena != null && !contrasena.isBlank() && contrasena.length() >= 4){
            this.contrasena = contrasena;
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena){
        if(nombreUsuario == null || contrasena == null){
            return false;
        }
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }

    @Override
    public String toString(){
        return "Nombre Completo: "+nombreCompleto+"\nCédula: "+cedula+"\nCorreo: "+correo+"\nTelefono: "+telefono+"\nDirección: "+direccion+"\nNombre de usuario: "+nombreUsuario;
    }
}
