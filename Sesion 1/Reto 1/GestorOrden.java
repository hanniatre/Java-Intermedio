import java.util.List;

public class GestorOrden {

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\nÓrdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\nProcesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                ((OrdenPersonalizada) obj).ajustarCosto(costoAdicional);
            }
        }
    }

    public static void contarOrdenes(List<OrdenProduccion> total) {
        int masa = 0, personalizadas = 0, prototipos = 0;
        for (OrdenProduccion orden : total) {
            if (orden instanceof OrdenMasa) masa++;
            else if (orden instanceof OrdenPersonalizada) personalizadas++;
            else if (orden instanceof OrdenPrototipo) prototipos++;
        }

        System.out.println("\nResumen total de órdenes:");
        System.out.println("Producción en masa: " + masa);
        System.out.println("Personalizadas: " + personalizadas);
        System.out.println("Prototipos: " + prototipos);
    }
}