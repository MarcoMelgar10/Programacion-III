package AplicacionImagen.Vista;

import AplicacionImagen.Imagen;
import AplicacionImagen.Postgres.DAO.Evento;
import AplicacionImagen.Postgres.DAO.EventosDao;
import AplicacionImagen.command.Invocador;
import AplicacionImagen.listas.GestorPaneles;
import AplicacionImagen.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class PrincipalFrame extends JFrame{
    private static final Logger logger = LogManager.getRootLogger();
    private JMenuBar menuBar;
    private JMenu menu, menu2, vistasDB;
    private JTabbedPane tabbedPane;
    private JMenuItem CargarImagen, guardarImagen, salirPrograma, eliminarImagen, verBaseDatos, verTipoDeEvento;
    private PanelPrincipal panelPrincipal;
    private PanelSecundario panelSecundario;
    private Imagen imagen;
    private Invocador invocador;
    private GestorPaneles<PanelPrincipal> gestorPaneles;
    private EventosDao dao;
    private String [] listaTipo;
    private String seleccion;

    public PrincipalFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        unit();
    }
    private void unit() {
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        gestorPaneles = new GestorPaneles<>(tabbedPane);
        dao = new EventosDao();
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
        vistasDB = new JMenu("Base de datos");
        CargarImagen = new JMenuItem("Cargar Imagen");
        guardarImagen = new JMenuItem("Guardar imagen");
        salirPrograma = new JMenuItem("Salir del programa");
        eliminarImagen = new JMenuItem("Eliminar Imagen");
        verBaseDatos = new JMenuItem("Eventos completos");
        verTipoDeEvento = new JMenuItem("Por tipo de evento");
        menuBar.add(menu);
        menuBar.add(menu2);
        menuBar.add(vistasDB);
        menu.add(CargarImagen);
        menu.add(guardarImagen);
        menu.add(salirPrograma);
        menu2.add(eliminarImagen);
        vistasDB.add(verBaseDatos);
        vistasDB.add(verTipoDeEvento);
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
            EventosDao dao = new EventosDao();
            try {
                dao.llamdaProcedimiento(new Evento("Cerrar programa", "Se cerro exitosamente el programa"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);

        });
        eliminarImagen.addActionListener(e->{
            int index = tabbedPane.getSelectedIndex();
            if (index >= 0) {
                tabbedPane.removeTabAt(index);
                gestorPaneles.eliminarPanel(index);
                logger.info("Pestaña nro " + (index+1) + " eliminada" );
                try {
                    dao.llamdaProcedimiento(new Evento("Eliminar imagen", "Se elimino la imagen del panel"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
            logger.error("No existen pestaña para eliminar");            }
        });
        verBaseDatos.addActionListener(e -> {
            Lista<String[]> datos = dao.getDatos();
            if (datos != null) {
                System.out.println(datos);
            } else {
                System.out.println("Error al obtener los datos.");
            }
        });

        verTipoDeEvento.addActionListener(e-> {
            listaTipo = new String[]{"Cerrar programa","Dibujar Linea", "Dibujar punto", "Dibujar cuadrado","Agregar imagen", "Eliminar imagen", "Guardar imagen", "Deshacer"};
            seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el evento:",
                    "Tipos de Eventos",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listaTipo,
                    listaTipo[0]
            );
            if(seleccion!= null) {
                Lista<String[]> datos = dao.geEventoPorNombre(seleccion);
                if (datos != null) {
                    System.out.println(datos);
                } else {
                    System.out.println("Error al obtener los datos.");
                }
            }
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
        String ruta = "C:\\Users\\marco\\Documents\\Imagenes" + nombreImagen;
        try {
            File guardar = new File(ruta);
            ImageIO.write(bufferImagen, "png", guardar);
            logger.info("Imagen guardada exitosamente: " + ruta);
            try {
                dao.llamdaProcedimiento(new Evento("Guardar imagen", "Se guardo imagen exitosamente en la computadora local"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
        try {
            dao.llamdaProcedimiento(new Evento("Agregar imagen", "Se agrego una nueva imagen al panel"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        this.pack();
    }

}