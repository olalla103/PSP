package actividad10;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTCPAdivina {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(44444)) {
            System.out.println("Servidor iniciado...");

            // Número aleatorio a adivinar (entre 1 y 25)
            int numero = (int) (Math.random() * 25 + 1);
            System.out.println("Número a adivinar: " + numero);

            // Objeto compartido por todos los hilos
            ObjetoCompartido juego = new ObjetoCompartido(numero);

            while (true) {
                // Esperar conexión de un cliente
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente);

                // Crear un nuevo hilo para manejar al cliente
                new HiloServidorAdivina(cliente, juego).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Objeto compartido para manejar el estado del juego
class ObjetoCompartido {
    private final int numero;
    private final AtomicBoolean terminado = new AtomicBoolean(false);
    private int ganador = -1;

    public ObjetoCompartido(int numero) {
        this.numero = numero;
    }

    public synchronized String nuevaJugada(int jugador, int suNumero) {
        if (terminado.get()) {
            return "El juego ya terminó. Ganador: Jugador " + ganador;
        }

        if (suNumero > numero) {
            return "Número demasiado grande.";
        } else if (suNumero < numero) {
            return "Número demasiado pequeño.";
        } else {
            terminado.set(true);
            ganador = jugador;
            return "¡Felicidades, Jugador " + jugador + "! Has adivinado el número.";
        }
    }

    public boolean isTerminado() {
        return terminado.get();
    }
}

// Hilo para manejar a cada cliente
class HiloServidorAdivina extends Thread {
    private final Socket socket;
    private final ObjetoCompartido juego;
    private static int contadorJugadores = 1;
    private final int jugadorId;

    public HiloServidorAdivina(Socket socket, ObjetoCompartido juego) {
        this.socket = socket;
        this.juego = juego;
        this.jugadorId = contadorJugadores++;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            boolean jugando = true;

            while (jugando) {
                Datos datos = (Datos) entrada.readObject();
                int numeroCliente = datos.getNumero();
                String respuesta = juego.nuevaJugada(jugadorId, numeroCliente);

                // Enviar la respuesta al cliente
                datos.setMensaje(respuesta);
                datos.setGanador(juego.isTerminado());
                salida.writeObject(datos);

                // Terminar si el juego ha finalizado
                if (juego.isTerminado()) {
                    jugando = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
