package AplicacionImagen.Vista;

import AplicacionImagen.App.Imagen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSecundario extends JPanel {
    private Border border;
    private JLabel infoImagen;
    private JButton jColorChooser, activarColor;
    private Imagen imagen;
    private JColorChooser colorChooser;
    private boolean activadorPintado;
    private  Color colorSeleccionado;
    private int rango;
    private GridBagConstraints c;
    public PanelSecundario(Imagen imagen) {
        this.imagen = imagen;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        infoImagen = new JLabel("");
        seleccionColor();
        seleccionRango();
        Bordes();
        this.setVisible(true);
    }

    public void Bordes() {
        // Implementación del borde personalizado
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
                // Definir el tamaño del borde (grosor)
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
        activarColor = new JButton("Activar");
        activarColor.setPreferredSize(new Dimension(105,40));
        jColorChooser.setPreferredSize(new Dimension(80, 80));
        jColorChooser.setBackground(Color.LIGHT_GRAY);
        jColorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JColorChooser para seleccionar el color
                colorChooser = new JColorChooser(jColorChooser.getBackground()); // Color inicial igual al color actual del botón

                // Crear un JDialog para mostrar el selector de color
                JDialog dialog = JColorChooser.createDialog(null, "Selecciona un color", true, colorChooser, e1 -> {
                            colorSeleccionado = colorChooser.getColor();
                            jColorChooser.setBackground(colorSeleccionado);
                            }, null);
                dialog.setVisible(true);}});

            activarColor.addActionListener(e -> {
                if(colorSeleccionado == null){return;}
                if(activadorPintado == false){
                    activadorPintado = true;
                    activarColor.setText("Desactivar");
                }else{
                    activarColor.setText("Activar");
                    activadorPintado = false;
                }
            });


        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,50,50,50);
        add(jColorChooser, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(30,50,50,50);
        add(activarColor, c);
    } //Metodo para devolver un color mediante JColorChooser

    public void seleccionRango() {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Valor inicial, mínimo, máximo, incremento
        spinner.addChangeListener(e -> rango = (int) spinner.getValue());
        c.gridx = 0;
        c.gridy =2;
        c.insets = new Insets(40,50,50,50);
        spinner.setPreferredSize(new Dimension(70,30));
        add(spinner, c);

        System.out.println("Valor seleccionado: " + rango);

    }//Metodo para el Spinner para seleccionar el numero de rango para el floodfill

    public boolean isActivadorPintado(){
        return activadorPintado;
    }

    public int getColorSeleccionado() {
        return colorSeleccionado.getRGB();
    }

    public int getRango() {
        return rango;
    }
}
