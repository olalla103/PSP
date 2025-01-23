package main.hilosActs2.Tarea2_8.actividad11;

import java.util.Random;

public class Jugador extends Thread {
    private int jugadorId;
    private Arbitro arbitro;

    public Jugador(int jugadorId, Arbitro arbitro) {
        this.jugadorId = jugadorId;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!arbitro.isJuegoTerminado()) {
            if (arbitro.getTurno() == jugadorId) {
                int numeroJugado = random.nextInt(10) + 1; // Número entre 1 y 10
                if (arbitro.comprobarJugada(jugadorId, numeroJugado)) {
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
