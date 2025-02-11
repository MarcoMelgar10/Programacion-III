package arbolBinario;


public class ResultadoBusqueda {
    boolean encontrada;
    int saltos;
    String palabra;

    public ResultadoBusqueda(boolean encontrada, int saltos, String palabra) {
        this.encontrada = encontrada;
        this.saltos = saltos;
        this.palabra = palabra;
    }

    public boolean isEncontrada() {
        return encontrada;
    }
    public int getSaltos() {
        return saltos;
    }


    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}