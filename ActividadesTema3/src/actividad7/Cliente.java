package actividad7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente: Permite al usuario introducir un número, lo envía al servidor,
 * y recibe el cuadrado y cubo calculados.
 */
public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Conectar al servidor en localhost:1234
        try (Socket servidor = new Socket("localhost", 1234);
             ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream())) {

            while (true) {
                // Leer número desde el teclado
                System.out.print("Introduce un número (<= 0 para salir): ");
                int numero = scanner.nextInt();

                // Crear y enviar objeto Numeros
                Numeros numeros = new Numeros(numero);
                salida.writeObject(numeros);
                salida.flush();

                // Verificar si se debe salir
                if (numero <= 0) {
                    System.out.println("Finalizando cliente.");
                    break;
                }

                // Recibir resultados del servidor
                numeros = (Numeros) entrada.readObject();
                System.out.println("Cuadrado: " + numeros.getCuadrado());
                System.out.println("Cubo: " + numeros.getCubo());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
