/**
 * Interfaz Radio
 * Programado por: Diego Morales Aquino y Daniel Morales 
 * Fecha: 26/01/2022
 */

public interface Radio {
    
    public void encenderApagar();
    public String guardarEmisoraActual(int numBoton);
    public String seleccionarEmisoraGuardada(int numBoton);
    public String cambiarSenal(boolean opcion);
    public boolean getTipoSenal();
    public void subirEmisora();
    public void bajarEmisora();
    public float getEmisoraActual();
    public boolean comprobarEncendida();
    
}
