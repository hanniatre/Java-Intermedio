import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    public CompletableFuture<Boolean> verificarDisponibilidadPista() {
        return CompletableFuture.supplyAsync(() -> {
            simularEsperaAleatoria(2, 3);
            boolean disponible = calcularProbabilidad(0.80);
            System.out.println("Pista disponible: " + disponible);
            return disponible;
        });
    }

    public CompletableFuture<Boolean> verificarCondicionesClimaticas() {
        return CompletableFuture.supplyAsync(() -> {
            simularEsperaAleatoria(2, 3);
            boolean favorable = calcularProbabilidad(0.85);
            System.out.println("Clima favorable: " + favorable);
            return favorable;
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularEsperaAleatoria(2, 3);
            boolean despejado = calcularProbabilidad(0.90);
            System.out.println("Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    public CompletableFuture<Boolean> verificarPersonalEnTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularEsperaAleatoria(2, 3);
            boolean disponible = calcularProbabilidad(0.95);
            System.out.println("Personal en tierra disponible: " + disponible);
            return disponible;
        });
    }

    public void iniciarProcesoVerificacion() {
        System.out.println("Verificando condiciones para el aterrizaje...");

        CompletableFuture<Boolean> pista = verificarDisponibilidadPista()
                .exceptionally(error -> {
                    System.out.println("Error al verificar pista: " + error.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> clima = verificarCondicionesClimaticas()
                .exceptionally(error -> {
                    System.out.println("Error al verificar clima: " + error.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> trafico = verificarTraficoAereo()
                .exceptionally(error -> {
                    System.out.println("Error al verificar tráfico aéreo: " + error.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> personal = verificarPersonalEnTierra()
                .exceptionally(error -> {
                    System.out.println("Error al verificar personal de tierra: " + error.getMessage());
                    return false;
                });

        CompletableFuture<Void> verificacionCompleta = CompletableFuture.allOf(pista, clima, trafico, personal);

        verificacionCompleta.thenRun(() -> {
            try {
                boolean pistaOk = pista.get();
                boolean climaOk = clima.get();
                boolean traficoOk = trafico.get();
                boolean personalOk = personal.get();

                if (pistaOk && climaOk && traficoOk && personalOk) {
                    System.out.println("\nAterrizaje autorizado: todas las condiciones son óptimas.");
                } else {
                    System.out.println("\nAterrizaje denegado: condiciones no son óptimas.");
                }
            } catch (Exception error) {
                System.out.println("\nError al obtener resultados: " + error.getMessage());
            }
        }).join();
    }

    private void simularEsperaAleatoria(int minimoSegundos, int maximoSegundos) {
        try {
            int retardo = ThreadLocalRandom.current().nextInt(minimoSegundos, maximoSegundos + 1);
            TimeUnit.SECONDS.sleep(retardo);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean calcularProbabilidad(double porcentajeExito) {
        return ThreadLocalRandom.current().nextDouble() < porcentajeExito;
    }
}