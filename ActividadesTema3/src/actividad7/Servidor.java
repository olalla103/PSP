package actividad7;

import java.io.*;
import java.net.*;

/**
 * Servidor: Recibe objetos Numeros desde un cliente, calcula el cuadrado y el cubo,
 * y envía los resultados de vuelta al cliente.
 */
public class Servidor {
    public static void main(String[] args) {
        // Inicializar el servidor en el puerto 1234
        try (ServerSocket servidor = new ServerSocket(1234)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Aceptar la conexión de un cliente
            try (Socket cliente = servidor.accept();
                 ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                 ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream())) {

                System.out.println("Cliente conectado.");

                while (true) {
                    // Recibir objeto Numeros del cliente
                    Numeros numeros = (Numeros) entrada.readObject();

                    // Finalizar si el número es menor o igual a 0
                    if (numeros.getNumero() <= 0) {
                        System.out.println("Número menor o igual a 0. Finalizando servidor.");
                        break;
                    }

                    // Calcular cuadrado y cubo
                    int numero = numeros.getNumero();
                    numeros.setCuadrado((long) numero * numero);
                    numeros.setCubo((long) numero * numero * numero);

                    // Enviar objeto modificado de vuelta al cliente
                    salida.writeObject(numeros);
                    salida.flush();
                    System.out.println("Resultados enviados al cliente.");
                }
            } catch (Exception e) {
                System.err.println("Error en la conexión con el cliente: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
