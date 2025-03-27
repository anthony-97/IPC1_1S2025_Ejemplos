package model;

public class TipoOrdenamiento {
    public enum Ordenamiento {
        BURBUJA(1),
        QUICKSORT(2);

        private final int index;

        Ordenamiento(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}