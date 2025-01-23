package main.hilosActs2.Tarea2_8.actividad11;

import java.util.Random;

public class Arbitro {
    private int numeroAdivinar;
    private int numeroJugadores;
    private int turno;
    private boolean juegoTerminado;

    public Arbitro(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
        this.numeroAdivinar = new Random().nextInt(10) + 1; // Número entre 1 y 10
        this.turno = 0;
        this.juegoTerminado = false;
        System.out.println("NÚMERO A ADIVINAR: " + numeroAdivinar);
    }

    public synchronized boolean comprobarJugada(int jugadorId, int numeroJugado) {
        if (juegoTerminado) {
            return true; // Se acertó el número
        }

        System.out.println("Jugador" + (jugadorId + 1) + " dice: " + numeroJugado);
        if (numeroJugado == numeroAdivinar) {
            System.out.println("Jugador " + (jugadorId + 1) + " gana, ¡adivinó el número!");
            juegoTerminado = true;
            return true;
        }

        // Cambia el turno al siguiente jugador
        turno = (turno + 1) % numeroJugadores;
        System.out.println("Le toca a Jug" + (turno + 1));
        return false;
    }

    public int getTurno() {
        return turno;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
