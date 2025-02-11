package Practico1.LogicGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeScore extends JFrame {
    private static final Logger logger = LogManager.getRootLogger();
    private JLabel lbl, lblListaScores;
    private int segundos = 0, milisegundos = 0;

    private List<Score> listaScores;//ArrayList de los Scores que se agreguen

    public TimeScore() {
        super("Scores");
        setLayout(new BorderLayout());
        // Inicializar lista de scores
        listaScores = new ArrayList<>();//ArrayList para agregarlo de manera dinamica
        // Crear etiquetas
        lbl = new JLabel("Aun no hay Scores");
        lblListaScores = new JLabel();
        // Añadir etiquetas al JFrame
        getContentPane().add(lbl, BorderLayout.NORTH);//Etiqueta de referencia
        getContentPane().add(lblListaScores, BorderLayout.CENTER);//Etiqueta de las listas
        // Configurar JFrame
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    public void agregarScores(int segundos, int milisegundos) {//Metodo para agregar records a la lista

        if (milisegundos != 0 || segundos != 0) {//Validacion de que no esten vacios los int
            lbl.setText("");//receteo de texto de informacion


            Score nuevoScore = new Score(segundos, milisegundos);//Crear nuevos scores y agregarlo a la lista
            listaScores.add(nuevoScore);
            // Ordenar la lista de scores de menor a mayor
            listaScores.sort(Comparator.comparingInt(Score::getTotalMilisegundos));//Metodo sort para comparar dentro de un array de enteros que devuelven los milisegundos
            // Actualizar la etiqueta lblListaScores con la lista de scores
            StringBuilder builder = new StringBuilder("<html>");// Texto en formato html para que se itere
            for (Score score : listaScores) {
                builder.append(score).append("<br>");}//Agregar el score al builder seguido al salto de linea
            builder.append("</html>");//Cierra la etiqueta html

            lblListaScores.setText(builder.toString());//Establece el texto en la etiqueta
            lbl.repaint();//Repintar el lbl
            logger.debug("Tiempo agregado y posicionado correctamente");
        }
    }
}

