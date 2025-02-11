package AplicacionImagen.App;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Imagen {
    private int ancho;
    private int alto;
    private int[][] imagenPixeles;
    private BufferedImage bi;
    private PropertyChangeSupport supportObserver;
    private static final String IMAGEN_OBSERVER = "IMAGEN";

    public Imagen( int ancho, int alto) {
        supportObserver = new PropertyChangeSupport(this);
        this.ancho = ancho;
        this.alto = alto;
        imagenPixeles = new int[ancho][alto];
    }

    public Imagen (File file){
        bi = null;
        try {
            bi = ImageIO.read(file);
        }catch (IOException e){e.printStackTrace();}
        ancho = bi.getWidth();
        alto = bi.getHeight();
        imagenPixeles = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j <alto ; j++) {
                imagenPixeles[i][j] = bi.getRGB(i,j);
            }
        }
        supportObserver = new PropertyChangeSupport(this);
    }

    public void dibujarImagen(Graphics g ) {
        for (int i = 0; i <ancho; i++) {
            for (int j = 0; j < alto; j++) {
                g.setColor(new Color(imagenPixeles[i][j]));
                g.drawLine(i,j,i,j);
            }
        }
    }

    public void addObserver(PropertyChangeListener observer) {
        supportObserver.addPropertyChangeListener(observer);
    }


    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public int[][] getImagenPixeles() {
        return imagenPixeles;
    }

    public void cambioDePixles(int [][] pixelesNuevos){
        int [][] imagenPixelesAntiguo = Arrays.copyOf(imagenPixeles, imagenPixeles.length);
        imagenPixeles = pixelesNuevos;
        supportObserver.firePropertyChange(IMAGEN_OBSERVER, imagenPixeles,imagenPixelesAntiguo);
    }



}