package Practico1.Interface;

import Practico1.LogicGame.tiempoHilo;
import Practico1.LogicGame.TimeScore;
import Practico1.LogicGame.VistasFiguras;
import Practico1.figures.Cuadrados;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Panel extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener {
    private static final Logger logger = LogManager.getRootLogger();
    private Cuadrados[] cuadrados;
    private Point pos1 = new Point(450,30);//Posiciones de la llegada de los cuadrados
    private Point pos2 = new Point(450,200);
    private int tamanoMarcos = 100;//De referencia del tamano de la llegada de los cuadrados
    private boolean dragging, //Indica si un cuadrado esta siendo arrastrado
                    unaVez = true, //Indicador de que el finilizador se de una sola vez
                    juegoActivo = true;  // Indica si se está arrastrando un cuadrado
    private Cuadrados draggedCuadrado = null;   // Cuadrado que se está arrastrando
    private tiempoHilo panelTime;
    private TimeScore ts;

    public Panel(Cuadrados[] cuadrados, tiempoHilo pt, TimeScore ts) {
        this.ts = ts;
        this.panelTime = pt;
        this.cuadrados = cuadrados;
        cuadrados[0].addObserver(this);
        cuadrados[1].addObserver(this);
        addMouseListener(this);
        setVisible(false);
        addMouseMotionListener(this);
    }

    @Override protected void paintComponent(Graphics g) {//Metodo para dibujar los cuadrados y las zonas de llegada de los cuadrados
        super.paintComponent(g);
        VistasFiguras dibujar = new VistasFiguras(cuadrados);//Cuadrados
        dibujar.dibujarPosiciones(g, pos1,pos2,tamanoMarcos);//Posiciones
        dibujar.dibujar(g);//Metodo para dibujarlos

        logger.debug("Cuadrados dibujados");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        logger.info("Cambiaron los cuadrados");

    }

    @Override
    public void mousePressed(MouseEvent e) {//Detecta si se hace clic sobre el cuadrado
        if (!juegoActivo)return;//Validacion de que el juego este activo para que se pueda mover

        if (cuadradoContains(e.getPoint(), cuadrados[0])) {//Validacion del clic en el cuadrado 1
            dragging = true;//Activacion para que comience en arrastre
            draggedCuadrado = cuadrados[0];


        } else if (cuadradoContains(e.getPoint(), cuadrados[1])) {//validacion del clic en el cuadrado 2
            dragging = true;
            draggedCuadrado = cuadrados[1];

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { //Detiene el arrastre
        dragging = false;
        draggedCuadrado = null;
        juegoAcabado();//detiene el juego
    }



    @Override
    public void mouseDragged(MouseEvent e) {//Mientra se arrastra se modifica la posicion
        if(juegoActivo) {
            if (dragging && draggedCuadrado != null) {
                draggedCuadrado.setPosicion(e.getPoint());
                repaint();
            }
        }
    }


    private boolean cuadradoContains(Point point, Cuadrados cuadrado) {//Verifica si la posicion del clic esta dentro de los limites del cuadrado
        int x = cuadrado.getPosicion().x;
        int y = cuadrado.getPosicion().y;
        int tamano = cuadrado.getTamano();

        return point.x >= x && point.x <= x + tamano &&
                point.y >= y && point.y <= y + tamano;//Comparacion de los limites para devolver un booleano
    }

    private void juegoAcabado() {
        if (unaVez && esCuadradoEnZona(cuadrados[0].getPosicion(), cuadrados[0].getTamano(), pos1, tamanoMarcos) &&
                    esCuadradoEnZona(cuadrados[1].getPosicion(), cuadrados[1].getTamano(), pos2, tamanoMarcos)) {
                juegoActivo = false;
                panelTime.stopTime(); // Detener el cronómetro
                panelTime.isRunning(false);
                ts.agregarScores(panelTime.getSegundos(), panelTime.getMilisegundos());
                JOptionPane.showMessageDialog(null, "Juego terminado en: " + panelTime.getSegundos() + " segundos, y " + panelTime.getMilisegundos() + " milisegundos");
                logger.info("Los cuadrados llegaron a la posicion requerida");
                unaVez = false;
        }
    }

    public void setUnaVez(Boolean unaVez){
        this.unaVez = unaVez;
    }




    public static boolean esCuadradoEnZona(Point puntoCuadrado, int tamanoCuadrado, Point puntoZonaRecepcion, int tamanoZonaRecepcion) {

        // Crear rectángulos para el cuadrado y el área de recepción ampliada
        Rectangle rectanguloCuadrado = new Rectangle(puntoCuadrado.x, puntoCuadrado.y, tamanoCuadrado, tamanoCuadrado);

        Rectangle zonaRecepcionAmpliada = new Rectangle(puntoZonaRecepcion.x , puntoZonaRecepcion.y ,tamanoZonaRecepcion + 2,tamanoZonaRecepcion + 2);
        // Verificar si el cuadrado está completamente dentro de la zona ampliada
        boolean completamenteDentro = zonaRecepcionAmpliada.contains(rectanguloCuadrado);

        return completamenteDentro;
    }


      public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }





    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
