import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class MonitoreoUCI {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();


        Flux<SignosVitales> paciente1 = generarSignosVitales(1, random);
        Flux<SignosVitales> paciente2 = generarSignosVitales(2, random);
        Flux<SignosVitales> paciente3 = generarSignosVitales(3, random);

        Flux.merge(paciente1, paciente2, paciente3)
                .filter(SignosVitales::esCritico)
                .sort((e1, e2) -> e1.prioridad() - e2.prioridad())
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Thread.sleep(15000);

    private static Flux<SignosVitales> generarSignosVitales(int pacienteId, Random random) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> {
                    int fc = 40 + random.nextInt(100);
                    int pas = 80 + random.nextInt(80);
                    int pad = 50 + random.nextInt(50);
                    int spo2 = 85 + random.nextInt(15);
                    return new SignosVitales(pacienteId, fc, pas, pad, spo2);
                })
                .take(10);

    }

}