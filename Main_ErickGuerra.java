/*
Clase Main. Sera la clase que conecte el programa con el usuario.
Autor1: Erick Guerra | 21781
Autor2: Elías Alvarado | 21808
Fecha: 26 de enero del 2022
*/

import java.util.Scanner;

class Main_ErickGuerra {
  /** 
  * @param args
  */
  private static Controlador controlador = new Controlador();
  
  /** 
   * @param args
   */
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    
    int respuesta = 0;
    int espacioEmisora = 0;
    boolean buclePrincipal = true;
    boolean bucle = true;

    System.out.println("¡Bienvenido! Este es un simulador de una radio convencional.\nEn la cual podra escuchar las distintas emisoras de las dos frecuencias de radio (AM y FM).\nAdemas, podra tener una lista de emisoras favoritas para reproducirlas cuando lo desee.");

    while(buclePrincipal)
    {
      respuesta = pregunta("\nMenu:\n1. Encender radio.\n2. Cambiar frecuencia.\n3. Cambiar emisora.\n4. Guardar emisora actual.\n5. Seleccionar emisora guardada.\n6. Apagar radio.\n7. Cerrar simulacion. \nRespuesta: ",7);
      switch(respuesta)
      {
        case 1:
        if(!controlador.comprobarEncendida())
        {
          controlador.encenderApagar();
          System.out.println("\nLa radio se ha encendido.");
        }
        else System.out.println("\nLa radio ya esta encendida.");
          break;
        
        case 2:
        if(controlador.comprobarEncendida())
        {
          if(controlador.getTipoSenal()) System.out.println(controlador.cambiarSenal(false));
          else System.out.println(controlador.cambiarSenal(true));
        }
        else System.out.println("\nLa radio no esta encendida.");
          break;
        
        case 3:
        if(controlador.comprobarEncendida())
        {
          respuesta = pregunta("¿Que desea hacer?\n1. Subir emisora.\n2. Bajar emisora.\nRepuesta: ", 2);
          if(respuesta == 1) controlador.subirEmisora();
          else controlador.bajarEmisora();
        }
        else System.out.println("\nLa radio no esta encendida.");
          break;
        
        case 4:
        if(controlador.comprobarEncendida())
        {
          bucle = true;
          while(bucle)
          {
            try
            {
              System.out.println("\nIngrese el boton donde desea guarda la emisora (1-12): ");
              espacioEmisora = scanner.nextInt();
              scanner.nextLine();
              bucle = false;
              if(espacioEmisora <= 0 || espacioEmisora > 12)
              {
                  System.out.println("\nRespuesta no valida. Debe ser un numero en el rango de 1 a 12.");
                  bucle = true;
              }
            } catch(Exception e)
            {
              System.out.println("Repuesta no valida. Ingrese solamente numeros.\n");
              scanner.nextLine();
              bucle = true;
            }
          }
          System.out.println(controlador.guardarEmisoraActual(espacioEmisora));
        }
        else System.out.println("\nLa radio no esta encendida.");
          break;
        
        case 5: //revisar
        if(controlador.comprobarEncendida())
        {
          bucle = true;
          while(bucle)
          {
            try
            {
              System.out.println("\nIngrese el boton donde guardo la emisora: ");
              espacioEmisora = scanner.nextInt();
              scanner.nextLine();
              bucle = false;
              if(espacioEmisora <= 0 || espacioEmisora > 12)
              {
                  System.out.println("\nRespuesta no valida. Debe ser un numero en el rango de 1 a 12.");
                  bucle = true;
              }
            } catch(Exception e)
            {
              System.out.println("Repuesta no valida. Ingrese solamente numeros.\n");
              scanner.nextLine();
              bucle = true;
            }
          }
          System.out.println(controlador.seleccionarEmisoraGuardada(espacioEmisora));
        }
        else System.out.println("\nLa radio no esta encendida.");
          break;
        
        case 6:
        if(controlador.comprobarEncendida())
        {
          controlador.encenderApagar();
          System.out.println("\nLa radio se ha apagado.");
        }
        else System.out.println("\nLa radio ya esta apagada.");
        
          break;

        case 7:
        buclePrincipal = false;
          break;
      }
      if(controlador.comprobarEncendida()) System.out.println(info());
    }
    scanner.close();
    System.out.println("\n¡Gracias por utilizar nuestro simulador de radio! Esperamos que haya sido de tu agrado.");
    
  }

  /** 
  * @param pregunta
  * @param opciones
  * @return int
  */
  public static int pregunta(String pregunta, int opciones)
  {
      boolean bucle = true;
      int respuesta = 0;
      Scanner scanner = new Scanner(System.in);
      try 
      {
          while(bucle)
          {
              System.out.println(pregunta);
              respuesta = scanner.nextInt();
              scanner.nextLine();
              if(respuesta > 0 && respuesta <= opciones) bucle = false;
              else System.out.println("\nRepuesta no valida.\n");
          }    
      } catch (Exception e) {
          System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
          respuesta = pregunta(pregunta, opciones);
      }
      return respuesta;
  }

  /**
   * @return String
   */
  public static String info(){
    String info = "\n---Estado---";
    if(controlador.getTipoSenal())
    {
      info += "\nFrecuencia: FM.";
      info += "\nEmisora: " + controlador.getEmisoraActual() + "MHz";
    }
    else
    {
      info += "\nFrecuencia: AM.";
      info += "\nEmisora: " + controlador.getEmisoraActual() + "KHz";
    }
    return info;
  }
}