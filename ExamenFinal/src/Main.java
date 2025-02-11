public class Main {
    public static void main(String[] args) {

        // Crear el objeto Direccion y Lavadero (15pts)
         Direccion direccion = new Direccion("Santa Cruz", "Calle 11");
        Lavadero lavadero = new Lavadero("Lavadero B", direccion);

        // Crear los 4 autos listados en la tabla (20pts)
        Auto auto1 = new Minivan("Maria Vera", 5);
        Auto auto2 = new Deportivo("Juan Perez");
        Auto auto3 = new Jeep("Marco Guzman", 2);
        Auto auto4 = new Jeep("Andrea Solis", 4);

        // Realizar el lavado de los autos e imprimir cuanto costo el lavado. (30ptos)
        lavadero.lavarAuto(auto1);
        lavadero.lavarAuto(auto2);
        lavadero.lavarAuto(auto3);
        lavadero.lavarAuto(auto4);


        // Imprimir el total de tickets de cada auto (20pts)
        auto1.imprimir(); // El auto de Maria Vera tiene 0 ticket(s)
        auto2.imprimir();
        auto3.imprimir();
        auto4.imprimir();

        // Imprimir el total de ingresos del lavadero (15pts)
        System.out.println("Total de ingresos: " + lavadero.getTotal());

    }
}