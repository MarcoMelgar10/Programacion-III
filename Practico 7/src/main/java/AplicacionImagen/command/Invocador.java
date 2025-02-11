package AplicacionImagen.command;

import AplicacionImagen.Imagen;
import AplicacionImagen.Vista.PanelPrincipal;
import AplicacionImagen.listas.Pila;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Invocador {
    private final Map<Imagen, Pila<CommandExcecute>> historialesPorImagen = new HashMap<>();//Asociar cada imagen a una pila de comandos
    private final JTabbedPane tabbedPane;
    private static final Logger logger = LogManager.getRootLogger();

    public Invocador(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void ejecutarComando(CommandExcecute comando, Imagen imagen) {
        Pila<CommandExcecute> historial = historialesPorImagen.computeIfAbsent(imagen, k -> new Pila<>());
        //Buscar el historial de la pila por cada comando, sino existe crear una nueva pila
        historial.push(comando);
        comando.execute();
    }

    public void deshacer() {
        Imagen imagenActiva = obtenerImagenDePestañaSeleccionada();
        if (imagenActiva != null) {
            Pila<CommandExcecute> historial = historialesPorImagen.get(imagenActiva);
            if (historial != null && !historial.isEmpty()) {
                imagenActiva.cambioDePixles(historial.pop().getImagenAnterior());
            } else {
                logger.error("No hay acciones para deshacer en esta imagen");
            }
        }
    }

    private Imagen obtenerImagenDePestañaSeleccionada() {
        int index = tabbedPane.getSelectedIndex();
        if (index >= 0) {
            return ((PanelPrincipal) tabbedPane.getSelectedComponent()).getImagen();
        }
        return null;
    }
}


