

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestRadio {

    private Controlador controlador;

    public TestRadio(){
        controlador = new Controlador();
    }
  
  
    @Test
    public void testEncendido(){

        boolean estadoInicial = controlador.comprobarEncendida();
        controlador.encenderApagar();
        assertEquals(!estadoInicial, controlador.comprobarEncendida());

    }

    @Test
    public void testCambioSenal(){

        assertEquals("AM", controlador.cambiarSenal(true));
        assertEquals("FM", controlador.cambiarSenal(false));
    }

    @Test
    public void testCambioEmisoraAM(){

        controlador.cambiarSenal(true);
        float valorInicial = controlador.getEmisoraActual();

        controlador.subirEmisora();
        assertEquals(valorInicial + 10, controlador.getEmisoraActual());

        controlador.bajarEmisora();
        assertEquals(valorInicial, controlador.getEmisoraActual());

    }

    @Test
    public void testCambioEmisoraFM(){

        controlador.cambiarSenal(false);
        float valorInicial = controlador.getEmisoraActual();

        controlador.subirEmisora();
        assertEquals(valorInicial + 0.2f, controlador.getEmisoraActual());

        controlador.bajarEmisora();
        assertEquals(valorInicial, controlador.getEmisoraActual());

    }

    @Test
    public void testGuardarEmisora(){

        float emisoraActual = controlador.getEmisoraActual();
        controlador.guardarEmisoraActual(8);

        assertEquals(String.valueOf(emisoraActual), controlador.seleccionarEmisoraGuardada(8));
    }
}
