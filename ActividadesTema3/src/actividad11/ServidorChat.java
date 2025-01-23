package actividad11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
    static final int MAXIMO = 10; // Máximo de conexiones permitidas

    public static void main(String[] args) {
        Socket[] tabla = new Socket[MAXIMO];
        ComunHilos comun = new ComunHilos(MAXIMO, 0, 0, tabla);

        try (ServerSocket servidor = new ServerSocket(44444)) {
            System.out.println("Servidor iniciado...");

            while (comun.getCONEXIONES() < MAXIMO) {
                Socket socket = servidor.accept(); // Aceptar cliente
                System.out.println("Cliente conectado...");

                comun.addTabla(socket, comun.getCONEXIONES());
                comun.setCONEXIONES(comun.getCONEXIONES() + 1);
                comun.setACTUALES(comun.getACTUALES() + 1);

                HiloServidorChat hilo = new HiloServidorChat(socket, comun);
                hilo.start(); // Iniciar hilo para cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
