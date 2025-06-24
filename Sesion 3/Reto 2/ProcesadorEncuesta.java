import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProcesadorEncuesta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Sucursal> sucursales = new ArrayList<>();

        System.out.print("¿Cuántas sucursales desea ingresar? ");
        int numSucursal = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numSucursal; i++) {
            System.out.print("Nombre de la sucursal #" + i + ": ");
            String nombreSucursal = scanner.nextLine();

            List<Encuesta> encuestas = new ArrayList<>();
            System.out.print("¿Cuántas encuestas desea ingresar para " + nombreSucursal + "? ");
            int numEncuestas = Integer.parseInt(scanner.nextLine());

            for (int j = 1; j <= numEncuestas; j++) {
                System.out.println("Encuesta #" + j);
                System.out.print("Nombre del paciente: ");
                String paciente = scanner.nextLine();

                System.out.print("Comentario: ");
                String comentario = scanner.nextLine();
                if (comentario.isBlank()) {
                    comentario = null;
                }

                System.out.print("Calificación (1 a 5): ");
                int calificacion = Integer.parseInt(scanner.nextLine());

                encuestas.add(new Encuesta(paciente, comentario, calificacion));
            }

            sucursales.add(new Sucursal(nombreSucursal, encuestas));
        }

        System.out.println("Resultados de seguimiento:");
        sucursales.stream()
                .flatMap(sucursal -> sucursal.getEncuestas().stream()
                        .filter(e -> e.getCalificacion() <= 3)
                        .map(e -> e.getComentario()
                                .map(c -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + c + "\"")
                        )
                )
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}