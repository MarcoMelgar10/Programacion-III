package AplicacionImagen.Vista;

import AplicacionImagen.Imagen;
import AplicacionImagen.command.Invocador;
import AplicacionImagen.listas.GestorPaneles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrincipalFrame extends JFrame{
    private static final Logger logger = LogManager.getRootLogger();
    private JMenuBar menuBar;
    private JMenu menu, menu2;
    private JTabbedPane tabbedPane;
    private JMenuItem CargarImagen, guardarImagen, salirPrograma, eliminarImagen;
    private PanelPrincipal panelPrincipal;
    private PanelSecundario panelSecundario;
    private Imagen imagen;
    private Invocador invocador;
    private GestorPaneles<PanelPrincipal> gestorPaneles;


    public PrincipalFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        unit();
    }
    private void unit() {
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        gestorPaneles = new GestorPaneles<>(tabbedPane);
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
        menu2 = new JMenu("Eliminar");
        CargarImagen = new JMenuItem("Cargar Imagen");
        guardarImagen = new JMenuItem("Guardar imagen");
        salirPrograma = new JMenuItem("Salir del programa");
        eliminarImagen = new JMenuItem("Eliminar Imagen");
        menuBar.add(menu);
        menuBar.add(menu2);
        menu.add(CargarImagen);
        menu.add(guardarImagen);
        menu.add(salirPrograma);
        menu2.add(eliminarImagen);
        this.setJMenuBar(menuBar);
    }
    public void accionesMenu(){
        CargarImagen.addActionListener(e-> {
                menuCargarImagen();
        });
        guardarImagen.addActionListener(e->{
                guardarModelo();

        });
        salirPrograma.addActionListener(e ->  {
               JOptionPane.showMessageDialog(null, "Adios");
                System.exit(0);

        });
        eliminarImagen.addActionListener(e->{
            int index = tabbedPane.getSelectedIndex();
            if (index >= 0) {
                tabbedPane.removeTabAt(index);
                gestorPaneles.eliminarPanel(index);
                logger.info("Pestaña nro " + (index+1) + " eliminada" );
            } else {
            logger.error("No existen pestaña para eliminar");            }
        });



    }

    private void guardarModelo() {
    if(imagen.getImagenPixeles() != null) {
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

        invocador = new Invocador(tabbedPane);
        imagen = new Imagen(500,600, invocador);
        panelSecundario = new PanelSecundario(imagen, tabbedPane, invocador);
        this.getContentPane().add(panelSecundario, BorderLayout.EAST);
        panelSecundario.setVisible(true);
        panelPrincipal = new PanelPrincipal(imagen, panelSecundario, invocador);
        gestorPaneles.agregarPanel(panelPrincipal);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        panelPrincipal.setVisible(true);
        }


    private void menuCargarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if(resultado == JFileChooser.APPROVE_OPTION){
            File seleccionFile = fileChooser.getSelectedFile();
           nuevoModelo(seleccionFile);
          }

    }
    private void nuevoModelo(File seleccionFile) {
        imagen = new Imagen(seleccionFile, invocador);
        panelSecundario.setImagen(imagen);
        PanelPrincipal nuevoPanel= new PanelPrincipal(imagen, panelSecundario,invocador);
        gestorPaneles.agregarPanel(nuevoPanel);
        logger.info("Nueva Imagen cargada: " + imagen.getBi().toString());
        this.pack();
    }

}