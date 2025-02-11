package APP;

public class Mesa {
    private Tenedor [] tenedores;
    private boolean tenedorOcupado;


    public Mesa(int numeroTenedores){
        this.tenedores = new Tenedor[numeroTenedores];
    }
    public synchronized void action(){}
}
