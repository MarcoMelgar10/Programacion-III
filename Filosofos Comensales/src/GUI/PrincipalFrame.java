package GUI;

import APP.Audio;

import javax.swing.*;
import java.awt.*;

public class PrincipalFrame extends JFrame {
    public PrincipalFrame(){
    super("Filosofo comensales");
    unit();
    this.pack();
    setVisible(true);
    }
    private void unit() {
        setDefaultCloseOperation(3);
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        Audio audio = new Audio("C:\\Users\\marco\\Desktop\\Documents\\4to Semestre\\Sistemas Operativos\\Musica(128 kbps).wav");
        audio.play();
        setSize(panelPrincipal.getSize());
        setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }


}
