package pack.ejLibro;

import java.io.IOException;

public class Ej1 {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("open", "-a", "Terminal");
            Process p = pb.start();
            System.out.println("Se lanza la terminal");
        } catch (IOException e) {
            System.out.println("No se lanza.");
        }
    }
}
