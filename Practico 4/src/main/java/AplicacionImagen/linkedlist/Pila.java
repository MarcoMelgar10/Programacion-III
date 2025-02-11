package AplicacionImagen.linkedlist;


public class Pila <A> {
    protected Nodo<A> raiz;

    public Pila (){}

    public void push(A dato) {
        Nodo<A> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
    }

    public boolean isEmpty() {
    return raiz ==null;
    }

    public A pop() {
        if(raiz == null){
            return null;
        }

        A dato = raiz.getDato();
        raiz = raiz.getSiguiente();
        return dato;
    }
}
