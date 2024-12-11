package Practico1.LogicGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class tiempoHilo extends JPanel {
    private static final Logger logger = LogManager.getRootLogger();
    private JLabel lbl, lbl2;
    private int milisegundos = 0;
    private int segundos = 0;
    private  Thread t1 = new Thread();
    private boolean running;

    public tiempoHilo() {
        setLayout(new BorderLayout());
        lbl = new JLabel("Segundos: 0", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 15));
        lbl2 = new JLabel("Milisegundos: 0", SwingConstants.CENTER);
        lbl2.setFont(new Font("Arial", Font.BOLD, 15));
        add(lbl, BorderLayout.CENTER);
        add(lbl2, BorderLayout.SOUTH);
        setVisible(false);
    }
    public void startCronometro() {//Iniciar el Thread para el cronomtro
        if (t1 != null && t1.isAlive()) {
            stopTime(); // Detener el cronómetro actual antes de iniciar uno nuevo
        }
        running = true;
        t1 = new Thread(() -> {//Hilo del cronometro
            try {
                while (running) {
                    milisegundos++;
                    if (milisegundos == 1000) {
                        milisegundos = 0;
                        segundos++;
                    }

                    // Actualizar la interfaz gráfica en el hilo de la EDT (Event Dispatch Thread)
                    SwingUtilities.invokeLater(() -> {
                        lbl.setText("Segundos: " + segundos);
                        lbl2.setText("Milisegundos: " + milisegundos);
                    });

                    Thread.sleep(1); // Pausa de 1 milisegundo
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                logger.debug("Cronómetro interrumpido.");
            }
        });

        t1.start();//Metodo de inicio del hilo
        logger.debug("Cronómetro iniciado.");
    }




    public int getMilisegundos() {
        return milisegundos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void isRunning(Boolean activador) {//setter del running, para indicar si esta corriendo
        this.running = activador;
        logger.debug("Estado del tiempo cambio a:" + activador);
    }

    public void stopTime() {//Metodo para detener el cronometro
        if (t1 != null && t1.isAlive()) {
            running = false;
            t1.interrupt(); // Interrumpir el hilo
        }
        logger.debug("Cronómetro detenido.");
    }



    public void resetCronometro() {//Metodo para reiniciar el cronometro
        stopTime(); // Detener el cronómetro si está en ejecución
        segundos = 0;
        milisegundos = 0;
        lbl.setText("Segundos: 0");
        lbl2.setText("Milisegundos: 0");
        logger.debug("Cronómetro reiniciado.");
    }
}



