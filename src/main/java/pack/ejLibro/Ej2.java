package pack.ejLibro;

import java.io.IOException;
import java.io.InputStream;

public class Ej2 {
    public static void main(String[] args) throws IOException {
        /* Esta actividad me imprime por letra las carpetas */
        Process p = new ProcessBuilder("bash", "-c", "ls").start();
        try {
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c + " ");
            }
            is.close();

            System.out.println("Se ejecuta");
        } catch (Exception e) {
            System.out.println("No se ejecuta");
        }

        // COMPROBACIOIN DE ERROR 0 BIEN 1 MAL
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
