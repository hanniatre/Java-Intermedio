import java.util.concurrent.Callable;

public class SistemaSoporteVital implements Callable<String> {
    private final int oxigeno;

    public SistemaSoporteVital(int oxigeno) {
        this.oxigeno = oxigeno;
    }

    public String call() throws Exception {
        Thread.sleep(900);
        return "Soporte vital: oxígeno al " + oxigeno + "% dentro de parámetros normales.";
    }
}