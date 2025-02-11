package AplicacionImagen.listas;

public class Nodo <A>{
    private A dato;
    private Nodo<A> siguiente;
    private Nodo<A> anterior;
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
    public Nodo<A> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<A> anterior) {
        this.anterior = anterior;
    }


}
