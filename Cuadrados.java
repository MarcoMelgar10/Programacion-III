package Practico1.figures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Cuadrados {//Figuras y sus caracteristicas
   private int tamano;
   private Color color;
   private Point posicion;
   private final Logger logger = LogManager.getRootLogger();//Logger de informes dependiendo la magnitud del evento
   private final PropertyChangeSupport support;///Soporte para el patron observervador,
   // permitiendo a un objeto notificar a otros objetos cuando cambie alguna de sus propiedades, emisor (objeto), receptor (listener)
   private final String ObserverPosicion = "POSICION";

   public Cuadrados(int tamano, Color color, Point posicion) {
      this.tamano = tamano;
      this.posicion = posicion;
      this.color = color;
      support = new PropertyChangeSupport(this);//Inicializa indicando que este va a ser el objeto propenso a cambios
   }
   public void setPosicion(Point posicion){//Cambiar la posicion del cuadrado y notificar
      Point oldPoint = this.posicion;
      this.posicion = posicion;
      logger.debug("Cambio de posición de: " + oldPoint + " hacia: " + posicion);
      support.firePropertyChange(ObserverPosicion, oldPoint, posicion);
   }


   public void addObserver(PropertyChangeListener observer){
      support.addPropertyChangeListener(observer);//Metodo para agregar un abservado al observador
   }


   //Getters de la figura
   public int getTamano() {
      return tamano;
   }

   public Color getColor() {
      return color;
   }

   public Point getPosicion() {
      return posicion;
   }

}
