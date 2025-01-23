package main.hilosActs2.Tarea2_9;

public class Cola {
    private int n;
    private boolean disponible;

    public int get() {
        if (disponible) {
            disponible = false;
            return n;
        }
        return -1;
    }

    public void put(int valor) {
        n = valor;
        disponible = true;
    }
}