package view;

public class TipoVentana {
    public enum Ventana {
        LOGIN(1),
        INICIO(2),
        REGISTRO_ESTUDIANTE(3),
        REGISTRO_CURSO(4),
        ASIGNACION_CURSO(5),
        VISTA_ESTUDIANTES(6),
        HISTORIAL_TRANSACCIONES(7);

        private final int index;

        Ventana(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}

