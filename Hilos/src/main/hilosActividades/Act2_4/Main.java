package main.hilosActividades.Act2_4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiHilo miHilo = new MiHilo();
        char letra;

        miHilo.start(); // inicia el hilo

        do {
            System.out.println("Introduce una letra (s para suspender, r para reanudar, * para finalizar):");
            letra = sc.next().charAt(0);

            if (letra == 's') {
                miHilo.suspenderHilo();
            } else if (letra == 'r') {
                miHilo.reanudarHilo();
            } else if (letra == '*') {
                miHilo.finalizarHilo();
            } else {
                miHilo.incrementarSiActivo();
            }

        } while (letra != '*');

        System.out.println("Programa principal finalizado.");
    }
}
