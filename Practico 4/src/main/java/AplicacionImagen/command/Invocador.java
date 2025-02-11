package AplicacionImagen.command;

import AplicacionImagen.linkedlist.Pila;

public class Invocador {
    private Pila<CommandExcecute> historial = new Pila<>();


    public void ejecutarComando(CommandExcecute comando) {
        comando.execute();
        historial.push(comando);

    }

    public void deshacer() {
        if (!historial.isEmpty()) {//Verificar si la pila no esta vacia
            CommandExcecute comando = historial.pop();
            comando.deshacer();//Metodo deshacer que devuelve la imagen en el estado previo a execute
        }
    }
}
