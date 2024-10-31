package main.hilosActividades.Act2_4;

public class SolicitaSuspender {
    private boolean suspender;

    public synchronized void set(boolean b) {
        suspender = b;
        notifyAll(); // Notifica a los hilos que están en espera si cambia el estado de `suspender`
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait(); // Suspende el hilo hasta que se cambie el estado
        }
    }

    public synchronized boolean isSuspendido() {
        return suspender;
    }
}
