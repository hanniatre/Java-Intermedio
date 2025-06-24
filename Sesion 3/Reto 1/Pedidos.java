import java.util.Optional;

public class Pedidos {
    private final String tipoEntrega;
    private final String telefono;

    public Pedidos(String tipoEntrega, String telefono) {
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}
