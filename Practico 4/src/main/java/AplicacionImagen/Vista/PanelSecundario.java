package AplicacionImagen.Vista;

import AplicacionImagen.Imagen;
import AplicacionImagen.command.Invocador;
import AplicacionImagen.operaciones.DibujarCuadrado;
import AplicacionImagen.operaciones.DibujarLinea;
import AplicacionImagen.operaciones.DibujarPunto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSecundario extends JPanel {
    private Border border;
    private JLabel infoImagen;
    private static final Logger logger = LogManager.getRootLogger();
    private JButton jColorChooser, activarHerramienta, deshacer;
    private Imagen imagen;
    private JColorChooser colorChooser;
    private  Color colorSeleccionado;
    private GridBagConstraints c;
    private boolean seleccionColor, activdorPunto, activadorCuadrado, activadorLinea;
    private Invocador invocador;
    public PanelSecundario(Imagen imagen, Invocador invocador) {
        this.imagen = imagen;
        this.invocador = invocador;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        infoImagen = new JLabel("");
        seleccionColor();
        deshacerUltimaAccion();
        Bordes();
        this.setVisible(true);
    }

    public void Bordes() {
        border = new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                // Dibujar el borde
                g.setColor(Color.BLACK);  // Color del borde
                int grosor = 5;  // Grosor del borde
                // Borde superior
                g.fillRect(x, y, width, grosor);
                // Borde inferior
                g.fillRect(x, y + height - grosor, width, grosor);
                // Borde izquierdo
                g.fillRect(x, y, grosor, height);
                // Borde derecho
                g.fillRect(x + width - grosor, y, grosor, height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                int grosor = 5;
                return new Insets(grosor, grosor, grosor, grosor);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        };
        this.setBorder(border);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, imagen.getAlto());
    }

    public void infoImagen() {
        String altura = "Altura: " + String.valueOf(imagen.getAlto());
        String ancho = ", ancho: " + String.valueOf(imagen.getAncho());
        infoImagen.setText(altura + ancho);
        Font fontImagen = new Font("Calibri", Font.BOLD, 24);
        infoImagen.setFont(fontImagen);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(1,1,1,1);
        add(infoImagen, c);
        this.repaint();
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
        infoImagen();
    }//Para actualizar los datos de la nueva imagen

    private void seleccionColor() {
        jColorChooser = new JButton();
        activarHerramienta = new JButton("Off");
        activarHerramienta.setPreferredSize(new Dimension(105,40));
        jColorChooser.setPreferredSize(new Dimension(80, 80));
        jColorChooser.setBackground(Color.LIGHT_GRAY);
        jColorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorChooser = new JColorChooser(jColorChooser.getBackground());
                JDialog dialog = JColorChooser.createDialog(null, "Selecciona un color", true, colorChooser, e1 -> {
                            colorSeleccionado = colorChooser.getColor();
                            jColorChooser.setBackground(colorSeleccionado);
                            seleccionColor = true;
                            }, null);
                dialog.setVisible(true);}});


        activarHerramienta.addActionListener(e -> {
                if(colorSeleccionado == null){return;}
            if (!activdorPunto&& !activadorCuadrado && !activadorLinea) {
               activdorPunto = true;
                activarHerramienta.setText("Punto");
                logger.info("Punto activado");
            } else if (activdorPunto) {
                activdorPunto = false;
                activadorCuadrado = true;
                activarHerramienta.setText("Cuadrado");
                logger.info("Cuadrado activado");
            } else if (activadorCuadrado) {
                activadorCuadrado = false;
                activadorLinea = true;
                activarHerramienta.setText("Linea");
                logger.info("Linea activado");
            } else if (activadorLinea) {
                activadorLinea = false;
                activarHerramienta.setText("off");
                logger.info("Herramienta desactivada");
            }
        });


            c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,50,50,50);
        add(jColorChooser, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(30,50,50,50);
        add(activarHerramienta, c);
    }
    public void deshacerUltimaAccion(){
        deshacer = new JButton("Deshacer");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(30,50,50,50);
        add(deshacer, c);
        deshacer.addActionListener(e -> {
        invocador.deshacer();
        logger.info("Invocador deshizo la accion");
        });

    }

    public int getColorSeleccionado() {
        return colorSeleccionado.getRGB();
    }

    public boolean isSeleccionColor() {
        return seleccionColor;
    }

    public boolean isActivdorPunto() {
        return activdorPunto;
    }

    public boolean isActivadorCuadrado() {
        return activadorCuadrado;
    }

    public boolean isActivadorLinea() {
        return activadorLinea;
    }
}
