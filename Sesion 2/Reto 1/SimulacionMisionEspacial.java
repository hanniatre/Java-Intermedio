import java.util.Scanner;
import java.util.concurrent.*;

public class SimulacionMisionEspacial {
    public static void main(String[] args) throws Exception {
        Scanner lectorEntrada = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("Ingrese datos para los sistemas:");

        System.out.print("Tiempo estimado de cálculo en milisegundos para el sistema de navegación: ");
        int tiempoNavegacionMs = Integer.parseInt(lectorEntrada.nextLine());

        System.out.print("Nivel actual de oxígeno en porcentaje para el sistema de soporte vital: ");
        int porcentajeOxigeno = Integer.parseInt(lectorEntrada.nextLine());

        System.out.print("Temperatura interna en °C para el sistema de control térmico: ");
        int temperaturaInterna = Integer.parseInt(lectorEntrada.nextLine());

        System.out.print("Duración de conexión en milisegundos para el sistema de comunicaciones: ");
        int duracionConexionMs = Integer.parseInt(lectorEntrada.nextLine());

        // Tareas para cada sistema
        Future<String> resultadoComunicaciones = executor.submit(new SistemaComunicaciones(duracionConexionMs));
        Future<String> resultadoSoporteVital = executor.submit(new SistemaSoporteVital(porcentajeOxigeno));
        Future<String> resultadoControlTermico = executor.submit(new SistemaControlTermico(temperaturaInterna));
        Future<String> resultadoNavegacion = executor.submit(new SistemaNavegacion(tiempoNavegacionMs));

        System.out.println("\nSimulación de misión espacial iniciada...");
        System.out.println(resultadoComunicaciones.get());
        System.out.println(resultadoSoporteVital.get());
        System.out.println(resultadoControlTermico.get());
        System.out.println(resultadoNavegacion.get());
        System.out.println("Todos los sistemas reportan estado operativo.");

        executor.shutdown();
        System.out.println("Simulación finalizada.");
    }
}