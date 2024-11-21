package main.hilosActs2.Tarea2_8.actividad11;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numeroJugadores;

        System.out.println("Introduzca el número de jugadores: ");
        numeroJugadores = sc.nextInt();

        Arbitro arbitro = new Arbitro(numeroJugadores);

        Jugador[] jugadores = new Jugador[numeroJugadores];
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores[i] = new Jugador(i, arbitro);
            jugadores[i].start();
        }

        for (Jugador jugador : jugadores) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
