package actividad11;

import java.net.Socket;

public class ComunHilos {
    private int MAXIMO, ACTUALES, CONEXIONES;
    private Socket[] tabla; // Almacena los sockets de los clientes
    private String mensajes = ""; // Historial de mensajes del chat

    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        this.MAXIMO = maximo;
        this.ACTUALES = actuales;
        this.CONEXIONES = conexiones;
        this.tabla = tabla;
    }

    // Getters y setters sincronizados para manejar datos compartidos
    public synchronized int getCONEXIONES() {
        return CONEXIONES;
    }

    public synchronized void setCONEXIONES(int conexiones) {
        this.CONEXIONES = conexiones;
    }

    public synchronized int getACTUALES() {
        return ACTUALES;
    }

    public synchronized void setACTUALES(int actuales) {
        this.ACTUALES = actuales;
    }

    public synchronized String getMensajes() {
        return mensajes;
    }

    public synchronized void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    // Agregar un socket a la tabla
    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }

    public synchronized Socket getElementoTabla(int i) {
        return tabla[i];
    }
}
