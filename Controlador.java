/**
 * Clase Controlador
 * Programado por: Diego Morales Aquino y Daniel Morales 
 * Fecha: 26/01/2022
 */

import java.text.DecimalFormat;
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
     * @param numBoton Botones del 1 al 12
     * @return Mensaje indicando que la emisora se a guardado en un botón
     */    
    @Override
    public String guardarEmisoraActual(int numBoton) {
 
        if(tipoSenal){ 
            emisorasGuardadas[numBoton - 1] = emisoraAM_actual;
            return "La emisora: "+emisoraAM_actual+" se a guardado en el boton: "+ (numBoton);
        }else{
            emisorasGuardadas[numBoton - 1] = emisoraFM_actual;
            return "La emisora: "+emisoraFM_actual+" se a guardado en el boton: "+ (numBoton);
        }
    
    }

    /**
     * Se encarga de verificar si el numero de boton proporcionado es valido.
     * @param numBoton numero del boton
     * @return boolean
     */
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

        numBoton -= 1;
        
        if(validarBoton(numBoton)){
            
            float emisora = emisorasGuardadas[numBoton];
            Boolean tipoSenalEmisora = getTipoSenalEmisora(emisora);
        
            if(tipoSenalEmisora == null) return null;
            else if (tipoSenalEmisora != tipoSenal){

                //cambiar tipo de senal
                cambiarSenal(tipoSenalEmisora); 
            }

            //cambiando emisora
            if(tipoSenal) emisoraAM_actual = emisorasGuardadas[numBoton];
            else emisoraFM_actual = emisorasGuardadas[numBoton];

            return String.valueOf(emisorasGuardadas[numBoton]);
        }else{
            return null;
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

    /**
     * Retorna el tipo de senal actual.
     * @return true:AM, false:FM
     */
    @Override
    public boolean getTipoSenal() {
        return tipoSenal;
    }

    /**
     * Avanza la frecuencia de la emisora actual.  
     */
    @Override
    public void subirEmisora() {
        if (tipoSenal){
            //AM
            emisoraAM_actual = emisoraAM_actual + 10;
            if (emisoraAM_actual>1610)
                emisoraAM_actual = 530;
        }else{
            //FM
            DecimalFormat df = new DecimalFormat("###.#");
            emisoraFM_actual = Float.parseFloat(df.format(emisoraFM_actual + 0.2f));
            if (emisoraFM_actual > 107.9)
                emisoraFM_actual = 87.9f;             
        }

    }
    /**
     * Retrocede la frecuencia de la emisora actual.
     */
    @Override
    public void bajarEmisora() {
        if (tipoSenal){
            //AM
            emisoraAM_actual = emisoraAM_actual -10;
            if (emisoraAM_actual<530)
                emisoraAM_actual = 1610;
        }else{
            //FM
            DecimalFormat df = new DecimalFormat("###.#");
            emisoraFM_actual = Float.parseFloat(df.format(emisoraFM_actual - 0.2f));
            if (emisoraFM_actual < 87.9)
                emisoraFM_actual = 107.9f;             
        }
        
    }

    /** Retorna la emisora actual.
     *  @return Float
     */
    @Override
    public float getEmisoraActual() {
        if(tipoSenal){ //AM
            return emisoraAM_actual;
        }else{ //FM
            return emisoraFM_actual;
        }
        
    }

    /** Retorna la informacion del estado del radio.
     * @return String. 
     */
    @Override
    public String toString(){
        return String.format("""
        \nEstado:
           - Senal: %s
           - Emisora: %s
        """, tipoSenal ? "AM" : "FM", tipoSenal ? emisoraAM_actual : emisoraFM_actual);
    }


    
}
