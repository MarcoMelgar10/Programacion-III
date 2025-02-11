package AplicacionImagen.Vista;

import AplicacionImagen.App.Imagen;
import AplicacionImagen.App.OperacionPintar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

public class PanelPrincipal extends JPanel implements PropertyChangeListener, MouseListener {
    private Imagen imagen;
    private  PanelSecundario panelSecundario;
    private static final Logger logger = LogManager.getRootLogger();
    public PanelPrincipal(Imagen imagen, PanelSecundario ps) {
        panelSecundario = ps;
        this.imagen = imagen;
        addMouseListener(this);
        imagen.addObserver(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            imagen.dibujarImagen(g);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imagen.getAncho(), imagen.getAlto());
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
        this.imagen.addObserver(this);
        repaint();  // Redibuja el panel cuando se asigna una nueva imagen
    }

    public void mouseClicked(MouseEvent e) {
        try {
            if (panelSecundario.isActivadorPintado() & panelSecundario.getRango()>0) {
                OperacionPintar operacionPintar = new OperacionPintar();
                int [][] imagenPintada =  operacionPintar.floodFill(imagen.getImagenPixeles(), e.getX(), e.getY(), panelSecundario.getColorSeleccionado(), panelSecundario.getRango());
                imagen.cambioDePixles(imagenPintada);
            }
        } catch (Exception exception){ exception.printStackTrace();}
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.debug("Se pinto la imagen");
        this.repaint();
    }



}
