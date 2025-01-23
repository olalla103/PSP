package main.hilosActividades.Act2_4;

public class MiHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private boolean sigueHilo = true;
    private int contador = 0;

    public void suspenderHilo() {
        suspender.set(true);
    }

    public void reanudarHilo() {
        suspender.set(false);
    }

    public void finalizarHilo() {
        sigueHilo = false;
    }

    // Incrementa si el hilo está activo
    public synchronized void incrementarSiActivo() {
        if (!suspender.isSuspendido() && sigueHilo) {
            contador++;
        }
    }

    @Override
    public void run() {
        while (sigueHilo) {
            try {
                // Suspende el hilo si está en estado de suspensión
                suspender.esperandoParaReanudar();

                // Si no lo pongo se me va el ordenador, le hago una pausa
                Thread.sleep(100);

            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + e.getMessage());
            }
        }
        System.out.println("Hilo finalizado. Valor final del contador: " + contador);
    }

    public int getContador() {
        return contador;
    }
}
