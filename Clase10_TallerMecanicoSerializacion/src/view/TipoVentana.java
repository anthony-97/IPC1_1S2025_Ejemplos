package view;

public class TipoVentana {
    public enum Ventana {
        LOGIN(1),
        REGISTRO(2),
        INICIO_ADMIN(3),
        INICIO_CLIENTE(4),
        VER_PROGRESO_ADMIN(5),
        VENTANA_REPORTES(6);

        private final int index;

        Ventana(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}