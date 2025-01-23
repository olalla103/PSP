import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContadorApplet extends Applet implements Runnable, ActionListener {
    private Thread h;
    long CONTADOR = 0;
    private boolean parar;
    private Font fuente;
    private Button b1, b2;

    public void init() {
        setBackground(Color.yellow);
        add(b1 = new Button("Iniciar contador"));
        b1.addActionListener(this);
        add(b2 = new Button("Finalizar contador"));
        b2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    @Override
    public void run() {
        parar = false;
        Thread hiloActual = Thread.currentThread();
        while (h == hiloActual && !parar) {
            try {
                Thread.sleep(300);
                CONTADOR++;  // Incrementa el contador en cada ciclo
                repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString(Long.toString(CONTADOR), 80, 100);
    }

    // Control de la pulsación del botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            b1.setLabel("Continuar");
            if (h != null && h.isAlive()) {
                // Si el hilo ya está activo, no hacemos nada
            } else {
                h = new Thread(this);
                h.start();
            }
        } else if (e.getSource() == b2) {
            parar = true;
        }
    }

    public void stop() {
        h = null;
    }
}
