package pack.Act1_4;

import java.io.IOException;

public class LeerNombre {

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.out.println("Argumentos incorrectos en main()");
            System.exit(-1);
        }
        System.out.println("Nombre: \s \n" + args[0]);
        System.exit(1);


    }
}


