package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelPrincipal extends JPanel {
    private int [] estados = new int[5];
    private BufferedImage fondo;
    PanelPrincipal(){
        fondo();
        setPreferredSize(new Dimension(fondo.getWidth(), fondo.getHeight()));
        vistaPanel();
        setVisible(true);
    }

    private void vistaPanel() {

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(fondo!=null){
            g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
        }
        int centroX = getWidth()/2;
        int centroY = getHeight()/2;
        int radio = 200;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g.fillOval(centroX - 50, centroY - 50,100,100);
        for (int i = 0; i < 5; i++) {
            double angle = i * (2 * Math.PI / 5);
            int philX = centroX + (int) (radio * Math.cos(angle)) - 20;
            int philY = centroY + (int) (radio * Math.sin(angle)) - 20;

            if (estados[i] == 2) {
                g2d.setColor(Color.ORANGE);
            } else {
                g2d.setColor(Color.YELLOW); // Espagueti en estado "esperando"
            }
            g2d.drawLine(centroX, centroY, philX + 20, philY + 20);

            // Cambia el color del filósofo según el estado
            switch (estados[i]) {
                case 0 -> g.setColor(Color.BLUE); // Pensando
                case 1 -> g.setColor(Color.RED); // Hambriento
                case 2 -> g.setColor(Color.GREEN); // Comiendo
            }
            g.fillOval(philX, philY, 40, 40);
        }

    }
    public void fondo(){
        try {
            fondo = ImageIO.read(new File("\\C:\\Users\\marco\\Desktop\\Documents\\4to Semestre\\Sistemas Operativos\\fondo.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
