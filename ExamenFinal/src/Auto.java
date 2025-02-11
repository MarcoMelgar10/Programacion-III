public abstract class Auto {
    private String propietario;
    private double costo;
    private int cantidadTicket;

    public Auto(String propietario) {
        this.propietario = propietario;
    }

    public Auto(  String propietario ,int cantidadTicket) {

        this.propietario = propietario;
        this.cantidadTicket = cantidadTicket;

    }
    public void imprimir(){
        if (getCantidadTicket() <= 4) {
            System.out.println("La cantidad de tickets que tenia " + getPropietario() + " eran: " + cantidadTicket );
          cantidadTicket++;
            System.out.println("La cantidad de tickets que ahora tiene " + getPropietario() + " son: " + cantidadTicket + " y su lavada costo: " + costo);
        } else if (getCantidadTicket() > 4) {
            System.out.println("La cantidad de tickets que tenia " + getPropietario() + " eran: " + cantidadTicket);
            cantidadTicket = 0;
            costo = 0;
            System.out.println("Cantidad de tickets que ahora tiene " + getPropietario() + " son: " +  cantidadTicket + " y su lavada costo: " + costo );
        }
    }


    public String getPropietario() {
        return propietario;
    }

    public double getCosto() {
        return costo;
    }

    public int getCantidadTicket() {
        return cantidadTicket;
    }


    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setCantidadTicket(int cantidadTicket) {
        this.cantidadTicket = cantidadTicket;
    }
}
