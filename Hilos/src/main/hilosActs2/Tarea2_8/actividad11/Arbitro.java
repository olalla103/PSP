package main.hilosActs2.Tarea2_8.actividad11;

public class Arbitro {
    Integer numero, turno, totalJugadores;

    public Arbitro(Integer totalJugadores) {
        if (totalJugadores <= 0) {
            System.out.println("No se puede jugar");
        } else {
            this.turno = (int) (Math.random() * (totalJugadores - 1 + 1)) + 1;
            this.numero = (int) (Math.random() * 10 + 1);
            this.totalJugadores = totalJugadores;
        }
    }

    public void compruebaJugada(Integer resultado) {
        if (juegoAcabado(resultado)) {
            System.out.println("El jugador ya tiene su resultado");
        } else {
            System.out.println("El jugador ha fallado, el siguiente en jugar es el jugador número " + turno++);
            // tengo que hacer que vuelvan los turnos
        }
    }

    public boolean juegoAcabado(Integer resultado) {
        if (resultado == numero) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public Integer getTotalJugadores() {
        return totalJugadores;
    }

    public void setTotalJugadores(Integer totalJugadores) {
        this.totalJugadores = totalJugadores;
    }
}
