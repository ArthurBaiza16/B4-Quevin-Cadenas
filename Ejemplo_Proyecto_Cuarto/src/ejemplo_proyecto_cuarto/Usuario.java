package ejemplo_proyecto_cuarto;

public class Usuario {
    String nombreUsuario, contraUsuario;
    
    public Usuario(String nombreUsuario,String contraUsuario){
        this.nombreUsuario = nombreUsuario;
        this.contraUsuario = contraUsuario;
    }
    
    public String getNombre(){
        return this.nombreUsuario;
    }
    
    public String getContra(){
        return this.contraUsuario;
    }
    
}
