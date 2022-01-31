/*
Clase RadioController. La cual simulara la radio del carro.
Autor1: Erick Guerra | 21781
Autor2: Elias Alvarado | 21808
Fecha: 26 de enero del 2022
*/

public class RadioController_ErickGuerra implements Radio{
  
  public RadioController_ErickGuerra(){}
  private boolean encendido = false;
  private Float[] favoritas = new Float[12];
  private int actualAM = 530;
  private float actualFM = 87.9f;
  private boolean FM = true; //true:FM, false: AM

  @Override
  public void encenderApagar(){
    this.encendido = !this.encendido;
  }

  /** 
  * @param numBoton
  * @return String
  */
  @Override
  public String guardarEmisoraActual(int numBoton){
    //if(numBoton <=0 || numBoton>12)
    //  return "La posicion indicada no es valida";
    if(this.FM)
      favoritas[numBoton-1] = actualFM;
    else
      favoritas[numBoton-1] = (float)actualAM;
    return ("\nEmisora almacenada en el boton " + numBoton + " de forma correcta.");
  }

  /** 
  * @param numBoton
  * @return String
  */
  @Override
  public String seleccionarEmisoraGuardada(int numBoton){
    //if(numBoton <=0 || numBoton>12)
    //  return "\nLa posicion indicada no es valida.";
    if(this.favoritas[numBoton-1] != null)
    {
      float emisora = favoritas[numBoton-1];
      if(emisora >= 530 || emisora <= 1610){
        this.FM = false;
        this.actualAM = (int)emisora;
      }
      else if(emisora >= 87.9 || emisora <= 107.9){
        this.FM = true;
        this.actualFM = emisora;
      }
      return ("\nReproduciendo la emisora " + emisora);
    }
    else return "\nNo se tiene guardada una emisora en esta posicion. Intente otra.";
  }
  
  /** 
  * @param opcion
  * @return String
  */
  @Override
  public String cambiarSenal(boolean opcion){
    FM = !FM;
    String senal = FM ? "FM" : "AM";
    return "\nSe ha cambiado la senal a "+senal + ".";
  }

  /** 
  * @return boolean
  */
  @Override
  public boolean getTipoSenal(){
    return FM;
  }
  @Override
  public void subirEmisora() {
    if(FM) {
      if(actualFM == 107.9f)
        actualFM = 87.9f;
      else
        actualFM += 0.2;
    }else {
      if(actualAM == 1610)
        actualAM = 530;
      else
        actualAM += 10;
    }
  }
  @Override
  public void bajarEmisora(){
    if(FM) {
      if(actualFM == 87.9f)
        actualFM = 107.9f;
      else
        actualFM -= 0.2;
    }else {
      if(actualAM == 530)
        actualAM = 1610;
      else
        actualAM -= 10;
    }
  }
  /** 
  * @return float
  */
  @Override
  public float getEmisoraActual(){
    if(FM)
      return actualFM;
    else
      return actualAM;
  }
  /** 
  * @return boolean
  */
  @Override
  public boolean comprobarEncendida(){
    return encendido;
  }

  public boolean isEncendido() {
	return encendido;
  }
  public Float[] getFavoritas() {
	return favoritas;
  }	
  public int getActualAM() {
	return actualAM;
  }
  public float getActualFM() {
	return actualFM;
  }
  public boolean isFM() {
	return FM;
  }
}