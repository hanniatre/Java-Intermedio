import java.util.concurrent.Callable;

public class SistemaNavegacion implements Callable<String> {
    private final int tiempoCalculoTrayectoriaMs;

    public SistemaNavegacion(int tiempoCalculoTrayectoriaMs) {
        this.tiempoCalculoTrayectoriaMs = tiempoCalculoTrayectoriaMs;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(tiempoCalculoTrayectoriaMs);
        return "Sistema de Navegación: trayectoria corregida tras " + tiempoCalculoTrayectoriaMs + " ms.";
    }
}