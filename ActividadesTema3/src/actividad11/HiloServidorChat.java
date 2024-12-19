package actividad11;

import java.io.*;
import java.net.Socket;

public class HiloServidorChat extends Thread {
    private DataInputStream fentrada;
    private Socket socket;
    private ComunHilos comun;

    public HiloServidorChat(Socket socket, ComunHilos comun) {
        this.socket = socket;
        this.comun = comun;

        try {
            this.fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexión actual: " + comun.getACTUALES());
            EnviarMensajeATodos(comun.getMensajes()); // Enviar historial al nuevo cliente

            String cadena;
            while (true) {
                cadena = fentrada.readUTF(); // Leer mensaje del cliente
                if (cadena.equals("*")) { // Si el cliente envía "*", se desconecta
                    comun.setACTUALES(comun.getACTUALES() - 1);
                    break;
                }
                comun.setMensajes(comun.getMensajes() + cadena + "\n");
                EnviarMensajeATodos(comun.getMensajes()); // Enviar mensaje a todos
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void EnviarMensajeATodos(String texto) {
        for (int i = 0; i < comun.getCONEXIONES(); i++) {
            Socket s = comun.getElementoTabla(i);
            if (s != null && !s.isClosed()) {
                try {
                    DataOutputStream fsalida = new DataOutputStream(s.getOutputStream());
                    fsalida.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
