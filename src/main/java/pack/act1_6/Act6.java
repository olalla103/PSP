package pack.act1_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Act6 {
    public static void main(String[] args) throws IOException {
        InputStreamReader lector = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(lector);
        int n1, n2;

        try {
            System.out.println("Introduce un numero:");
            n1 = Integer.parseInt(br.readLine()); // el número introducido es n1
            System.out.println(n1);
            System.out.println("Introduce un numero:");
            n2 = Integer.parseInt(br.readLine());
            System.out.println(n2);
            System.out.println("Suma de los numeros: " + (n1 + n2));
            lector.close();

        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        } catch (NumberFormatException e) {
            System.out.println("No ha introducido un número válido.");
        }
    }
}




