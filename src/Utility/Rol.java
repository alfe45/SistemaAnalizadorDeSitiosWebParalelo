package Utility;

public enum Rol {
    
    DIGITADOR("DIGITADOR"),
    GESTOR("GESTOR"),
    ANALISTA("ANALISTA");
    
    private String rol;

    private Rol(String rol) {
        this.rol = rol;
    }
    
    

    @Override
    public String toString() {
        return rol;
    }
    
    
    
}//class
