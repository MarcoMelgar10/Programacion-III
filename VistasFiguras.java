package Practico1.LogicGame;

import Practico1.figures.Cuadrados;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class VistasFiguras {
    private static final Logger logger = LogManager.getRootLogger();
    private Cuadrados [] cuadrados;


    public VistasFiguras(Cuadrados [] c){
        this.cuadrados = c;
    }


    public void dibujar (Graphics g){//Metodo que dibuja las figuras y las posiciones para arrastrar
        g.setColor(cuadrados[0].getColor());
        g.fillRect(cuadrados[0].getPosicion().x, cuadrados[0].getPosicion().y, cuadrados[0].getTamano(), cuadrados[0].getTamano());

        g.setColor(cuadrados[1].getColor());
        g.fillRect(cuadrados[1].getPosicion().x, cuadrados[1].getPosicion().y, cuadrados[1].getTamano(), cuadrados[1].getTamano());

        logger.info("Dibujo figuras");
    }


    public void dibujarPosiciones(Graphics g, Point pos1, Point pos2, int tamanoMarco){//Dibujar las posiciones especificas de la meta
        g.setColor(Color.BLACK); // Color del marco
        g.drawRect(pos1.x, pos1.y, tamanoMarco, tamanoMarco);//Dibujar posicion de meta

        // Dibujar el segundo marco en la posición especificada
        g.drawRect(pos2.x, pos2.y, tamanoMarco, tamanoMarco);

        logger.info("Dibujo posiciones de referencia");

    }

}
