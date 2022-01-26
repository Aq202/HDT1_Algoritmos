import java.util.*;
public class Controlador implements Radio{

    private boolean encendido = false;
    private boolean tipoSenal = true; //true:AM, false:FM
    private float emisoraAM_actual;
    private float emisoraFM_actual;
    private Float[] emisorasGuardadas;
    
    public Controlador(){
        emisorasGuardadas = new Float[12];
        emisoraAM_actual = 530;
        emisoraFM_actual = 87.9f;
    }
    
    /**
     * Alterna el estado del dispositivo entre encendido y apagado.
     */
    @Override
    public void encenderApagar() {
        encendido = !encendido;
        
    }

    /**
     * Método que permite saber si el estado del dispositivo es encendido o apagado.
     * @return true: encendido. false: apagado.
     */
    @Override
    public boolean comprobarEncendida() {
        return encendido;
    }
    
    /**
     * Método que permite guardar la emisora actual en el botón seleccionado por el usuario.
     * @return Mensaje indicando que la emisora se a guardado en un botón
     */    
    @Override
    public String guardarEmisoraActual(int numBoton) {
 
        if(tipoSenal){ 
            emisorasGuardadas[numBoton] = emisoraAM_actual;
            return "La emisora: "+emisoraAM_actual+" se a guardado en el boton: "+numBoton;
        }else{
            emisorasGuardadas[numBoton] = emisoraFM_actual;
            return "La emisora: "+emisoraFM_actual+" se a guardado en el boton: "+numBoton;
        }
    
    }

    private boolean validarBoton(int numBoton){
        return (numBoton >= 0 && numBoton < emisorasGuardadas.length && emisorasGuardadas[numBoton] != null);
    }

    /**
     * Determina que tipo de senal es una emisora en especifico.
     * @param emisora numero de la emisora
     * @return true: Am, false:FM, null: emisora no valida
     */
    private Boolean getTipoSenalEmisora(float emisora){
        
        if(emisora >= 530 && emisora <= 1610) return true;
        else if (emisora >= 87.9 && emisora <= 107.9) return false;
        
        return null;
    }

    /**
     * Permite reproducir la emisora guardada en una casilla en especifico.
     * @param numBoton indice del espacio en donde se almacena la emisora
     * @return String. Mensaje de respuesta.
     */
    @Override
    public String seleccionarEmisoraGuardada(int numBoton) {
        
        if(validarBoton(numBoton)){
            
            float emisora = emisorasGuardadas[numBoton];
            Boolean tipoSenalEmisora = getTipoSenalEmisora(emisora);
            String respuesta = "";
        

            if(tipoSenalEmisora == null) return "Emisora no válida.";
            else{
                String senal = cambiarSenal(tipoSenalEmisora);
                respuesta += "Senal cambiada a " + senal + "\n"; 
            }

            return respuesta += "Reproduciendo emisora " + emisorasGuardadas[numBoton];
        }else{
            return "No se tiene almacenada una emisora en la casilla " + numBoton;
        }
    }

    /**
     * Cambia la senal de radio. 
     * @param opcion true: Am, false: FM
     * @return retorna la senal (AM o FM)
     */
    @Override
    public String cambiarSenal(boolean opcion) {
        tipoSenal = opcion;
        return opcion ? "AM" : "FM";
    }

    @Override
    public boolean getTipoSenal() {
        
        return tipoSenal;
    }

    @Override
    public void subirEmisora() {
        if (tipoSenal){
            //AM
            emisoraAM_actual = emisoraAM_actual +10;
            if (emisoraAM_actual>1610)
                emisoraAM_actual = 530;
        }else{
            //FM
            emisoraFM_actual = emisoraFM_actual+0.2f;
            if (emisoraFM_actual>107.9)
                emisoraFM_actual = 87.9f;             
        }
        
    }

    @Override
    public void bajarEmisora() {
        if (tipoSenal){
            //AM
            emisoraAM_actual = emisoraAM_actual -10;
            if (emisoraAM_actual<530)
                emisoraAM_actual = 1610;
        }else{
            //FM
            emisoraFM_actual = emisoraFM_actual-0.2f;
            if (emisoraFM_actual<87.9)
                emisoraFM_actual = 107.9f;             
        }
        
    }

    @Override
    public float getEmisoraActual() {
        if(tipoSenal){
            return emisoraAM_actual;
        }else{
            return emisoraAM_actual;
        }
        
    }


    
}
