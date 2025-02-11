package AplicacionImagen;

import AplicacionImagen.command.Invocador;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Imagen {
    private int ancho;
    private int alto;
    private int[][] imagenPixeles;
    private BufferedImage bi;
    private PropertyChangeSupport supportObserver;
    private static final String IMAGEN_OBSERVER = "IMAGEN";
    private Invocador invocador;

    public Imagen( int ancho, int alto, Invocador invocador) {
        this.invocador = invocador;
        supportObserver = new PropertyChangeSupport(this);
        this.ancho = ancho;
        this.alto = alto;
        imagenPixeles = new int[ancho][alto];
        bi = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB );
    }

    public Imagen (File file, Invocador invocador){
        this.invocador = invocador;
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
        if(imagen() !=null){
            g.drawImage(imagen(), 0,0,null);
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

    public BufferedImage imagen(){
        if (bi == null) {
            bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        }

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                // El valor de la matriz es un entero que representa el color en formato ARGB
                int color = imagenPixeles[x][y];
                bi.setRGB(x, y, color);
            }
        }
        return bi;
    }
    public void undo(){
        invocador.deshacer();
    }

    @Override
    public String toString() {
        return imagenPixeles.toString();
    }
}