/** 
 * Programa de simulación de una radio. Permite subir y bajar de estacion, cambiar entre senal AM y FM, guardar estaciones
 * como favoritas y reproducir estaciones ya guardadas.
 * Realizado por Sebastian Silva y Pablo Zamora el 30/01/2022
*/
import java.text.DecimalFormat;
public class Controlador_PabloZamora implements Radio {
    private boolean encendido;
    private boolean tipoSenal; // false = FM, true = AM
    private float amActual;
    private float fmActual;
    private Float[] emisorasGuardadas;

    public Controlador_PabloZamora() {
        encendido = false;
        tipoSenal = false;
        fmActual = 87.9f;
        amActual = 530f;
        emisorasGuardadas = new Float[12];
    }

    public void encenderApagar() {
        encendido = !encendido;

    }

    public String guardarEmisoraActual(int numBoton) {
        String mensaje = "\nIngrese un numero de boton valido";
        if (numBoton > 0 && numBoton < 13) {
            if (tipoSenal) {
                emisorasGuardadas[numBoton - 1] = amActual;
                mensaje = "\nLa emisora " + amActual + " AM se ha guardado correctamente en el boton " + numBoton;
            } else {
                emisorasGuardadas[numBoton - 1] = fmActual;
                mensaje = "\nLa emisora " + fmActual + " FM se ha guardado correctamente en el boton " + numBoton;
            }
        }

        return mensaje;
    }

    public String seleccionarEmisoraGuardada(int numBoton) {
        String mensaje = "\nIngrese un numero de boton valido";
        if (numBoton > 0 && numBoton < 13) {
            if (emisorasGuardadas[numBoton-1] != null) {
                float emisora = emisorasGuardadas[numBoton-1];
                if (emisora >= 530) {
                    amActual = emisora;
                    tipoSenal = true;
                    mensaje = "\nReproduciendo " + amActual + " AM";
                } else {
                    fmActual = emisora;
                    tipoSenal = false;
                    mensaje = "\nReproduciendo " + fmActual + " FM";
                }
            } else {
                mensaje = "\nNo se ha guardado ninguna emisora en este boton";
            }
        }
        return mensaje;
    }

    public String cambiarSenal(boolean opcion) {
        String senal = "AM";
        tipoSenal = !tipoSenal;
        if (!tipoSenal) {
            senal = "FM";
        }
        return "\nSe ha cambiado la senal a " + senal;
    }

    public boolean getTipoSenal() {
        return tipoSenal;
    }

    public void subirEmisora() {
        if (tipoSenal) {
            amActual += 10;
                if (amActual > 1610){
                    amActual = 530f;
                }
        } else {
            DecimalFormat formato = new DecimalFormat("###.#");
            fmActual = Float.parseFloat(formato.format(fmActual + 0.2f));
            if (fmActual > 107.9){
                fmActual = 87.9f;
            }
            
        }
    }
    
    public void bajarEmisora() {
        if (tipoSenal) {
            amActual -= 10;
                if (amActual < 530){
                    amActual = 1610f;
                }
        } else {
            DecimalFormat formato = new DecimalFormat("###.#");
            fmActual = Float.parseFloat(formato.format(fmActual - 0.2f));
            if (fmActual < 87.9){
                fmActual = 107.9f;
            }
            
        }
    }

    public float getEmisoraActual() {
        float emisora = 0;
        if (tipoSenal) {
            emisora = amActual;
        } else {
            emisora = fmActual;
        }
        return emisora;
    }

    public boolean comprobarEncendida() {
        return encendido;
    }
}