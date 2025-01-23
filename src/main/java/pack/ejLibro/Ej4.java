package pack.ejLibro;

import java.io.IOException;
import java.io.InputStream;

public class Ej4 {
    public static void main(String[] args) throws IOException {
        // Ejecuta el comando 'date' para obtener la fecha actual en Linux
        Process p = new ProcessBuilder("bash", "-c", "date").start();

        // LECTURA -- obtiene la salida de DATE
        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }
        is.close();

        // COMPROBACIÓN ERROR -- 0 bien, 1 mal
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("\nValor de salida: " + exitVal);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
