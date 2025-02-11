package Practico1.LogicGame;

public class Score {//Clase de scores del tiempo
    private final int segundos;
    private final int milisegundos;

    public Score(int segundos, int milisegundos) {//Clase de los tiempos records
        this.segundos = segundos;
        this.milisegundos = milisegundos;
    }

    public int getTotalMilisegundos() {//Suma del tiempo en milisegundos para comparar en milisegundos
        return (segundos * 1000) + milisegundos;
    }

    @Override
    public String toString() {//String del tiempo
        return "Segundos: " + segundos + ", Milisegundos: " + milisegundos;
    }

}
