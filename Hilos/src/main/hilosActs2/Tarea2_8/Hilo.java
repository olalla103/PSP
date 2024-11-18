package main.hilosActs2.Tarea2_8;

public class Hilo extends Thread {
    Double cantidad;
    Saldo saldo;
    String nombre;

    public Hilo(Saldo saldo,String nombre) throws InterruptedException {
        this.saldo = saldo;
        cantidad = saldo.getSaldo();
        this.nombre = nombre;

    }

    @Override
    public void run() {
        try {
            saldo.aniadeSaldo(this.getCantidad(),this.getNombre());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getCantidad() {
        return cantidad;
    }


    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
