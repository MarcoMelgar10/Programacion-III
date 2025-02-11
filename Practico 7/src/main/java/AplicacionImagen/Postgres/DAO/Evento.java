package AplicacionImagen.Postgres.DAO;

public class Evento {
        private String nombre;
        private String info;

        public Evento(String nombre, String info) {
            this.nombre = nombre;
            this.info = info;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        }
