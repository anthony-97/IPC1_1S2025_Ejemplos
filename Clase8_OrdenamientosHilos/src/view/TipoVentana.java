package view;

public class TipoVentana {
    public enum Ventana {
        APP(1),
        ELECCION_ORDENAMIENTO(2);

        private final int index;

        Ventana(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}