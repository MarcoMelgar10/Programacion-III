package Interface;
import arbolBinario.LectorArchivo;
import arbolBinario.ResultadoBusqueda;
import arbolBinario.binarySearchTree;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private binarySearchTree arbol;

    public VentanaPrincipal() {
        arbol = new binarySearchTree();
        setTitle("Árbol Binario de Búsqueda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemCargar = new JMenuItem("Cargar palabras");
        JMenuItem itemSalir = new JMenuItem("Salir");

        menuArchivo.add(itemCargar);
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JTextArea areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane);

        itemCargar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                LectorArchivo.cargarPalabras(arbol, rutaArchivo);
                areaTexto.append("Palabras cargadas.\n");
            }
        });
        itemSalir.addActionListener(e -> System.exit(0));
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(e -> buscarPalabra(areaTexto));
        add(botonBuscar, "South");
        setVisible(true);
    }

    private void buscarPalabra(JTextArea areaTexto) {
        String palabra = JOptionPane.showInputDialog(this, "Ingrese la palabra a buscar:");
        if (palabra != null && !palabra.isEmpty()) {
            ResultadoBusqueda resultado = arbol.buscar(palabra);
            if (resultado.isEncontrada()) {
                areaTexto.append("Palabra encontrada en " + resultado.getSaltos() + " saltos.\n");
            } else {
                areaTexto.append("Palabra no encontrada. Saltos realizados: " + resultado.getSaltos() + ".\n");
                if (resultado.getPalabra() != null) {
                    areaTexto.append("Palabra más cercana: " + resultado.getPalabra() + ".\n");
                }
            }
        }
    }



}

