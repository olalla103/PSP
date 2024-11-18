package main.hilosActs2.Tarea2_8;

import static java.lang.Thread.sleep;

public class Saldo {
    Double saldo;

    // En este constructor inicializo con el saldo que ponga el usuario y el nombre del usuario
    public Saldo(Double saldo) {
        this.saldo = saldo;
    }

    // Este método añade valor al saldo actual
    public synchronized void aniadeSaldo(Double nuevoSaldo, String nombre) throws InterruptedException {
        Double guarda = this.getSaldo();
        this.setSaldo(saldo + nuevoSaldo);
        System.out.println("Su saldo es: " + guarda + ".\n" + nombre + " ha añadido un total de " +
                nuevoSaldo + ".\nAhora su saldo es: " + this.getSaldo() + "\n");
    }

    // Getter y Setter con un sleep con una duración aleatoria
    public Double getSaldo() throws InterruptedException {
        int tiempo = (int) (Math.random() * 1000);
        sleep(tiempo);
        return saldo;
    }

    public void setSaldo(Double saldo) throws InterruptedException {
        int tiempo = (int) (Math.random() * 1000);
        sleep(tiempo);
        this.saldo = saldo;
    }


}
