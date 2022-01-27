/**
 * Clase Principal
 * Programado por: Diego Morales Aquino y Daniel Morales 
 * Fecha: 26/01/2022
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Principal{

    /**
     * Se encarga de solicitarle al usuario un valor entero y lo valida.
     *
     * @param sc Scanner.
     * @param mensaje String. Mensaje a desplegar al usuario
     * @param valoresValidos Integer. Valores validos.
     * @return Int. Valor ingresado.
     */
    private static int getIntValido(Scanner sc, String mensaje, Integer... valoresValidos) {

        var listaValoresValidos = new ArrayList<Integer>();
        if (valoresValidos != null) {
            Collections.addAll(listaValoresValidos, valoresValidos);
        }

        while (true) {

            try {

                System.out.println(mensaje);
                Integer value = sc.nextInt();
                sc.nextLine();

                if (valoresValidos == null || listaValoresValidos.contains(value)) {
                    return value;
                }

            } catch (Exception e) {
                sc.nextLine();
            }

            System.out.println("Porfavor, ingresa una valor valido.");

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Controlador controlador = new Controlador();
        boolean terminar = false;

        System.out.println("Simulador de radio 1.0\n");

        while(!terminar){


            String menu = String.format("""
            \nSelecciona una opcion: 

            %s
            2. Cambiar senal
            3. Avanzar emisora
            4. Retroceder emisora
            5. Guardar emisora
            6. Seleccionar emisora guardada
            7. Salir
            """,
            controlador.comprobarEncendida() ? "1. Apagar Radio" : "1. Encender radio");

            int opcion = getIntValido(sc, menu, 1,2,3,4,5,6,7);

            switch(opcion){

                case 1:{ //encender/apagar

                    System.out.println(controlador.comprobarEncendida() ? "Apagando radio..." : "Encendiendo radio...");
                    controlador.encenderApagar();
                    break;
                }
                case 2:{ //cambiar tipo de senal

                    //verificar que se encuentre encendido
                    if(!controlador.comprobarEncendida()){
                        System.out.println("El radio se encuentra apagado.");
                        continue;
                    }

                    String tipoSenal = controlador.cambiarSenal(!controlador.getTipoSenal());
                    System.out.println("Senal " + tipoSenal + " seleccionada");
                    break;
                    
                }
                case 3:{ //avanzar emisora

                    //verificar que se encuentre encendido
                    if(!controlador.comprobarEncendida()){
                        System.out.println("El radio se encuentra apagado.");
                        continue;
                    }

                    controlador.subirEmisora();
                    System.out.println("Reproduciendo emisora " + controlador.getEmisoraActual());
                    break;

                }
                case 4:{

                    //verificar que se encuentre encendido
                    if(!controlador.comprobarEncendida()){
                        System.out.println("El radio se encuentra apagado.");
                        continue;
                    }

                    controlador.bajarEmisora();
                    System.out.println("Reproduciendo emisora " + controlador.getEmisoraActual());
                    break;
                }
                case 5:{ //guardar emisora actual

                    //verificar que se encuentre encendido
                    if(!controlador.comprobarEncendida()){
                        System.out.println("El radio se encuentra apagado.");
                        continue;
                    }

                    Integer[] opcionesValidas = new Integer[12];
                    for(int i = 1; i <= opcionesValidas.length; i++) opcionesValidas[i - 1] = i;

                    int numBoton = getIntValido(sc, "Ingresa el boton en donde se almacenara la emisora(1-12):", opcionesValidas);
                    System.out.println(controlador.guardarEmisoraActual(numBoton - 1));
                    break;
                }
                case 6:{ //reproducir emisora guardada

                    //verificar que se encuentre encendido
                    if(!controlador.comprobarEncendida()){
                        System.out.println("El radio se encuentra apagado.");
                        continue;
                    }
                    
                    Integer[] opcionesValidas = new Integer[12];
                    for(int i = 1; i <= opcionesValidas.length; i++) opcionesValidas[i - 1] = i;
                    
                    int numBoton = getIntValido(sc, "Ingresar el boton a presionar(1-12):", opcionesValidas);
                    System.out.println(controlador.seleccionarEmisoraGuardada(numBoton - 1));
                    break;
                }
                case 7:{ //salir
                    terminar = true;
                    break;
                }

            }

        }
        
    }
}