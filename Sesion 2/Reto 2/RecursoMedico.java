import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private final String nombreRecurso;
    private final ReentrantLock bloqueoAcceso = new ReentrantLock();

    public RecursoMedico(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public void usar(String nombreProfesional) {
        bloqueoAcceso.lock();
        try {
            System.out.println(nombreProfesional + " ha ingresado a " + nombreRecurso);
            Thread.sleep(1000);
            System.out.println(nombreProfesional + " ha salido de " + nombreRecurso);
        } catch (InterruptedException excepcionInterrupcion) {
            Thread.currentThread().interrupt();
        } finally {
            bloqueoAcceso.unlock();
        }
    }
}