package AplicacionImagen.Vista;

import AplicacionImagen.App.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrincipalFrame extends JFrame{
    private static final Logger logger = LogManager.getRootLogger();
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem CargarImagen, guardarImagen, salirPrograma;
    private PanelPrincipal panelPrincipal;
    private PanelSecundario panelSecundario;
    private Imagen imagen;

    public PrincipalFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        unit();
    }
    private void unit() {
        this.setLayout(new BorderLayout());
        implementarPaneles();
        crearMenu();
        accionesMenu();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void crearMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Archivos");
        CargarImagen = new JMenuItem("Cargar Imagen");
        guardarImagen = new JMenuItem("Guardar imagen");
        salirPrograma = new JMenuItem("Salir del programa");
        menuBar.add(menu);
        menu.add(CargarImagen);
        menu.add(guardarImagen);
        menu.add(salirPrograma);
        this.setJMenuBar(menuBar);
    }
    public void accionesMenu(){
        CargarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuCargarImagen();
            }
        });
        guardarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarModelo();
            }
        });
        salirPrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Adios");
                System.exit(0);
            }
        });



    }
    private void guardarModelo() {
    if(panelSecundario.isActivadorPintado()) {
    int ancho = imagen.getImagenPixeles().length;
    int alto = imagen.getImagenPixeles()[0].length;

    BufferedImage bufferImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

    for (int x = 0; x < ancho; x++) {
        for (int y = 0; y < alto; y++) {
            int color = imagen.getImagenPixeles()[x][y];
            bufferImagen.setRGB(x, y, color);
        }
    }
    if (bufferImagen != null) {
        String nombreImagen = JOptionPane.showInputDialog(null, "Nombre, con extencion (png o jpg)");
        String ruta = "C:\\Users\\marco\\OneDrive\\Documentos\\Imagenes\\" + nombreImagen;
        try {
            File guardar = new File(ruta);
            ImageIO.write(bufferImagen, "png", guardar);
            logger.info("Imagen guardada exitosamente: " + ruta);
        } catch (IOException e) {
            logger.debug("Error al guardar la clase " + e.getMessage());
        }
    }
}

    }
    public void implementarPaneles(){
        imagen = new Imagen(500,600);
        panelSecundario = new PanelSecundario(imagen);
        this.getContentPane().add(panelSecundario, BorderLayout.EAST);
        panelSecundario.setVisible(true);
        panelPrincipal = new PanelPrincipal(imagen, panelSecundario);
        this.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        panelPrincipal.setVisible(true);

    }
    private void menuCargarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if(resultado == JFileChooser.APPROVE_OPTION){
            //seleccion de la imagen por el usuario
            File seleccionFile = fileChooser.getSelectedFile();
            nuevoModelo(seleccionFile);
        }

    }
    private void nuevoModelo(File seleccionFile) {
        imagen = new Imagen(seleccionFile);
        panelPrincipal.setImagen(imagen);
        panelSecundario.setImagen(imagen);
        logger.info("Nueva Imagen cargada: " + imagen.getBi().toString());
        this.pack();
    }

}