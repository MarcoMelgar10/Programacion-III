package AplicacionImagen.linkedlist;

public class Nodo <A>{
    private Nodo<A> siguiente;
    private A dato;
    public Nodo(A dato){
        this.dato = dato;
    }

    public Nodo<A> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<A> siguiente) {
        this.siguiente = siguiente;
    }

    public A getDato() {
        return dato;
    }

    public void setDato(A dato) {
        this.dato = dato;
    }
}
