package main.hilosActividades.Act2_3;

public class HiloContador extends Thread {
    private int contador;
    public HiloContador(int contador) {
        this.contador = contador;
    }
}
