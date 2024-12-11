package Practico1.Interface;

import Practico1.LogicGame.tiempoHilo;
import Practico1.LogicGame.TimeScore;
import Practico1.figures.Cuadrados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {//Frame principal
    private Cuadrados[] cuadrados;
    private Point point1, point2;//Puntos de los cuadrados
    private JMenuBar menuBar;
    private JMenu menu;
    private JLabel info;
    private JMenuItem nuevo, score, salir;
    private tiempoHilo panelTime;
    private Panel panel;
    private TimeScore timeScore;
    private boolean primerJuego = true;


    public Frame() {
        figuras();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Init();//Metodo de desarrollo del frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void Init() {
        setSize(700, 400);//Dimensiones del frame
        getContentPane().setLayout(new BorderLayout());
        info = new JLabel("      ----Bienvenido al Juego, por favor inicie----");
        info.setFont(new Font("Calibri", Font.BOLD, 32));
        getContentPane().add(info, BorderLayout.NORTH);
        panelTime = new tiempoHilo();
        timeScore = new TimeScore();
        panel = new Panel(cuadrados, panelTime, timeScore);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panelTime, BorderLayout.SOUTH);
        menuAcciones();
    }

    public void figuras() {//Inicializacion de las figuras para arrastrar
        cuadrados = new Cuadrados[2];
         point1 = new Point(30, 50);
         point2 = new Point(30, 170);
        cuadrados[0] = new Cuadrados(80, Color.blue, point1);
        cuadrados[1] = new Cuadrados(80, Color.RED, point2);
    }



    public void menuAcciones(){
        menuBar = new JMenuBar();
        menu = new JMenu("Archivos");
        nuevo = new JMenuItem("Nuevo");
        score = new JMenuItem("Score");
        salir = new JMenuItem("Salir");

        nuevo.addActionListener(this);
        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeScore.setVisible(true);

            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(menu);
        menu.add(nuevo);
        menu.add(score);
        menu.add(salir);
        setJMenuBar(menuBar);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if (primerJuego) {
           info.setText("");
           panelTime.startCronometro(); // Inicia el cronómetro
           panelTime.setVisible(true);//Hacer visible el panel del tiempo
           panel.setVisible(true);// Hacer visible el panel de los cuadrados
           primerJuego = false;
       }

        volverPosicion();
        panelTime.resetCronometro();
        panelTime.startCronometro(); // Inicia el cronómetro
        panel.setJuegoActivo(true);//Volver a activar los cuadrados

    }

    private void volverPosicion() {//Metodo para Volver los cuadrados a su posicin y reiniciar todo
        cuadrados[0].setPosicion(point1);
        cuadrados[1].setPosicion(point2);
        panel.setUnaVez(true);
        repaint();
    }


}
