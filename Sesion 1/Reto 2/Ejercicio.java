public class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        this.revisado = true;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Ejercicio - Título: " + titulo + ", Autor: " + autor + ", Revisado: " + revisado);
    }
}