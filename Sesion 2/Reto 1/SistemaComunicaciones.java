import java.util.concurrent.Callable;

public class SistemaComunicaciones implements Callable<String> {
    private int duracionConexionMilisegundos;

    public SistemaComunicaciones(int duracionConexionMilisegundos) {
        this.duracionConexionMilisegundos = duracionConexionMilisegundos;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(duracionConexionMilisegundos);
        return "Sistema de Comunicaciones: enlace establecido" + duracionConexionMilisegundos + " ms.";
    }
}
