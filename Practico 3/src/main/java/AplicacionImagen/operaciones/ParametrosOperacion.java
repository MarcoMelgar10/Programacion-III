package AplicacionImagen.operaciones;

public class ParametrosOperacion {
private int x1;
private int y1;
private int color;

    public ParametrosOperacion(int x1, int y1, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getColor() {
        return color;
    }

}
