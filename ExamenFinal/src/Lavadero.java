public class Lavadero {
    private String nombre;
    private Direccion direccion;
    private double total;

    public Lavadero(String nombre, Direccion direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public double lavarAuto(Auto auto) {
        if(auto.getCantidadTicket() >= 0 && auto.getCantidadTicket() <5) {
            total += auto.getCosto();
            return auto.getCosto();
        }
        return 0 ;

}




    public String getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
