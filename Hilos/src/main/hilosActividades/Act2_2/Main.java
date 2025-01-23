package main.hilosActividades.Act2_2;

public class Main {
    public static void main(String[] args) {
        TransformaAct1 h1 = new TransformaAct1("Portátil");
        TransformaAct1 h2 = new TransformaAct1("Monitor");
        TransformaAct1 h3 = new TransformaAct1("Cable");
        TransformaAct1 h4 = new TransformaAct1("Ratón");
        TransformaAct1 h5 = new TransformaAct1("Botón");

        // Tengo que usar el método start aunque esté implementando la interfaz runnable
        // porque así es como inicio un hilo
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();


    }
}
