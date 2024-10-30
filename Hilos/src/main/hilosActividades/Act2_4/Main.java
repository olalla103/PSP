package main.hilosActividades.Act2_4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ATRIBUTOS
        Scanner sc = new Scanner(System.in);
        MiHilo miHilo = new MiHilo();
        char letra;
        miHilo.start(); // inicia el hilo

        System.out.println("Introduce un único caracter");
        letra = sc.next().charAt(0);
        while (letra != '*') {
            miHilo.run(letra); // ejecuta el método `run` con el carácter especificado
            System.out.println("Introduce una letra o un símbolo, es muy importante que sea un único caracter");
            letra = sc.next().charAt(0);
            miHilo.run(letra);
        }

    }
}
