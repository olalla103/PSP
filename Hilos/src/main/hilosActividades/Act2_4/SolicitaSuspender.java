package main.hilosActividades.Act2_4;

public class SolicitaSuspender {
    private boolean suspender;

    public synchronized void set(boolean b) {
        suspender = b;
        notifyAll();
    }

    public synchronized boolean esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait();
        }
        return suspender;
    }
}
