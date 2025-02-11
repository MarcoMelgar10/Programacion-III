package AplicacionImagen.command;

import java.util.Stack;

public class Invocador {
    private Stack<CommandExcecute> historial = new Stack<>();
    //Stack: Estructura de datos (Array) dinamica que sigue el princio LIFO(Last in, first out)

    public void ejecutarComando(CommandExcecute comando) {
        comando.execute();
        historial.push(comando);//Insertar el elemento en la parte superior
    }

    public void deshacer() {
        if (!historial.isEmpty()) {//Verificar si la pila no esta vacia
            CommandExcecute comando = historial.pop();//Retirar el elemento de la parte superior
            comando.deshacer();//Metodo deshacer que devuelve la imagen en el estado previo a execute
        }
    }
}

