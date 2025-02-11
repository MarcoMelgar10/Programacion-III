package AplicacionImagen.Vista;

import AplicacionImagen.Imagen;
import AplicacionImagen.Postgres.DAO.Evento;
import AplicacionImagen.Postgres.DAO.EventosDao;
import AplicacionImagen.command.DibujarCuadradoCommand;
import AplicacionImagen.command.DibujarLineaCommand;
import AplicacionImagen.command.DibujarPuntoCommand;
import AplicacionImagen.command.Invocador;
import AplicacionImagen.excepcion.ExepcionImagen;
import AplicacionImagen.operaciones.DibujarCuadrado;
import AplicacionImagen.operaciones.DibujarLinea;
import AplicacionImagen.operaciones.DibujarPunto;
import AplicacionImagen.operaciones.ParametrosOperacion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class PanelPrincipal extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener {
    private Imagen imagen;
    private PanelSecundario panelSecundario;
    private static final Logger logger = LogManager.getRootLogger();
    private DibujarPunto dibujarPunto;
    private DibujarCuadrado dibujarCuadrado;
    private DibujarLinea dibujarLinea;
    private ParametrosOperacion parametrosOperacion;
    private int [][] moldeImagenEditada;
    private int xPrevio, yPrevio, xActual, yActual, xFinal, yFinal;
    private Invocador invocador;
    private DibujarCuadradoCommand commandCuadrado;
    private DibujarLineaCommand commandLinea;
    private DibujarPuntoCommand commandPunto;

    public PanelPrincipal(Imagen imagen, PanelSecundario ps, Invocador invocador) {
        panelSecundario = ps;
        this.invocador = invocador;
        dibujarPunto = new DibujarPunto();
        dibujarLinea = new DibujarLinea();
        dibujarCuadrado = new DibujarCuadrado();
        this.imagen = imagen;
        addMouseListener(this);
        addMouseMotionListener(this);
        imagen.addObserver(this);
        xPrevio = 0;
        yPrevio = 0;
        xActual = 0;
        yActual = 0;
        xFinal = 0;
        yFinal = 0;
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
              if(!panelSecundario.isSeleccionColor()){return;}
              if(e.getX() < 0 || e.getY() <0 ||e.getX() >= imagen.getAncho() || e.getY() >= imagen.getAlto()){return;}
              if(parametrosOperacion == null){parametrosOperacion = new ParametrosOperacion(e.getX(), e.getY(), panelSecundario.getColorSeleccionado());}
              parametrosOperacion.setX1(e.getX());
              parametrosOperacion.setY1(e.getY());
              xPrevio = e.getX();
              yPrevio = e.getY();

             try {
                  if(panelSecundario.isActivdorPunto()){
                      ParametrosOperacion parametros = new ParametrosOperacion(xPrevio, yPrevio, panelSecundario.getColorSeleccionado());
                      commandPunto = new DibujarPuntoCommand( dibujarPunto, imagen, parametros);
                      invocador.ejecutarComando(commandPunto, imagen);
                      moldeImagenEditada =  dibujarPunto.getImagenPintada();
                      imagen.cambioDePixles(moldeImagenEditada);
                 }
             } catch (Exception ex) {
                  logger.error("Error al pintar en la imagen: " + ex.getMessage(), ex);
             }
          }

           @Override
          public void mouseDragged(MouseEvent e) {
             if(!panelSecundario.isSeleccionColor()){return;}
               if(e.getX() < 0 || e.getY() <0 ||e.getX() >= imagen.getAncho() || e.getY() >= imagen.getAlto()){return;}
              if (parametrosOperacion == null) {parametrosOperacion = new ParametrosOperacion(e.getX(), e.getY(), panelSecundario.getColorSeleccionado());}

              try {
                     xPrevio = parametrosOperacion.getX1();
                     yPrevio = parametrosOperacion.getY1();
                     xActual = e.getX();
                     yActual = e.getY();
                  if (panelSecundario.isActivdorPunto()) {
                     parametrosOperacion.setX1(xActual);
                      parametrosOperacion.setY1(yActual);
                      dibujarPunto.dibujarLineaEntrePuntos(xPrevio, yPrevio, xActual, yActual, panelSecundario.getColorSeleccionado());
                      moldeImagenEditada = dibujarPunto.getImagenPintada();
                      imagen.cambioDePixles(dibujarPunto.getImagenPintada());
                 }
              } catch(ExepcionImagen ex){
                      logger.error("Error al pintar en la imagen: " + ex.getMessage(), ex);
                  }
              }


    @Override
    public void mouseReleased(MouseEvent e) {
        xFinal = e.getX();
        yFinal = e.getY();
        if(e.getX() < 0 || e.getY() <0 ||e.getX() >= imagen.getAncho() || e.getY() >= imagen.getAlto()){return;}
        if(panelSecundario.isActivadorLinea()) {
            commandLinea = new DibujarLineaCommand(dibujarLinea, imagen, xPrevio, yPrevio, xFinal, yFinal, panelSecundario.getColorSeleccionado());
            invocador.ejecutarComando(commandLinea, imagen);
            moldeImagenEditada = dibujarLinea.getImagenPintada();
            imagen.cambioDePixles(moldeImagenEditada);
        }else if (panelSecundario.isActivadorCuadrado()) {
            commandCuadrado = new DibujarCuadradoCommand(dibujarCuadrado, imagen, xPrevio, yPrevio, xFinal, yFinal, panelSecundario.getColorSeleccionado());
            invocador.ejecutarComando(commandCuadrado, imagen);
            moldeImagenEditada = dibujarCuadrado.getImagenPintada();
            imagen.cambioDePixles(moldeImagenEditada);
        } else if (panelSecundario.isActivdorPunto()) {
            EventosDao dao = new EventosDao();
            try {
                dao.llamdaProcedimiento(new Evento("Dibujar punto", "Se dibujo un punto o una serie de puntos en el panel hasta las cordenadas: " + xFinal+   " " +yFinal));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.debug("La imagen ha sido modificada");
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public Imagen getImagen() {
        return imagen;
    }
}
