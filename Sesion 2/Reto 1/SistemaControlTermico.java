import java.util.concurrent.Callable;

public class SistemaControlTermico implements Callable<String> {
    private final int temperaturaInternaGrados;

    public SistemaControlTermico(int temperaturaInternaGrados) {
        this.temperaturaInternaGrados = temperaturaInternaGrados;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(800);
        return "Sistema de Control Térmico: temperatura estabilizada" + temperaturaInternaGrados + " °C.";
    }
}