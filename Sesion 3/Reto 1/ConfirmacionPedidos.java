import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConfirmacionPedidos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pedidos> pedidos = new ArrayList<>();

        System.out.print("¿Cuántos pedidos desea realizar? ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("Pedido #" + i);
            System.out.print("Nombre del cliente: ");
            scanner.nextLine();

            System.out.print("Tipo de entrega (a domicilio/ recoger pedido en sucursal): ");
            String tipo = scanner.nextLine();

            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            if (telefono.isBlank()) {
                telefono = null;
            }

            pedidos.add(new Pedidos(tipo, telefono));
        }

        List<String> mensajes = pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("a domicilio"))
                .map(Pedidos::getTelefono)
                .flatMap(Optional::stream)
                .map(tel -> "Confirmación enviada al número: " + tel)
                .toList();

        mensajes.forEach(System.out::println);

    }
}
