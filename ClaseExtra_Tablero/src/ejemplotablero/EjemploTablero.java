package ejemplotablero;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author polares
 */
public class EjemploTablero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);        
        int opcion;
        
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Ejemplo Ordenamiento");
            System.out.println("2. Ejemplo Matriz");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            if(opcion == 1) {
                ordenarJugadoresPorPuntaje();
            } else if(opcion == 2) {
                generarTablero(scanner);
            } else if(opcion == 3) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opcion invalida");
            }
        } while(opcion != 3);
        
        scanner.close();
    }
    
    public static void generarTablero(Scanner scanner) {
        Random random = new Random(); 
        
        System.out.println("Ingresa el número de filas del tablero:");
        int filas = scanner.nextInt();
        
        System.out.println("Ingresa el número de columnas del tablero:");
        int columnas = scanner.nextInt();
        scanner.nextLine();  //Consumir el salto de línea pendiente, que se deja despues del nextInt

        //Se crea la matriz del tablero
        char[][] tablero = new char[filas][columnas];

        //Primero, llenar el tablero con espacios en blanco
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }

        String[] palabras = new String[3]; //Array en donde se guardaran las palabras
        for (int i = 0; i < 3; i++) {
            System.out.println("Ingresa una palabra");
            String palabra = scanner.nextLine();
            palabras[i] = palabra.toUpperCase(); //Mayuscula
        }
        //["dormir","ipc1", "cadena"]
        
        for (int i = 0; i<palabras.length; i++) {
            char[] cadena = palabras[i].toCharArray(); //Convierte cada palabra a un array de chars
            //['d','o','r','m','i','r']
            boolean palabraColocada = false;
        
            while (!palabraColocada) {
                //Elegir la dirección al azar
                boolean esHorizontal = random.nextBoolean();//Va a elegir true o false

                //Se elige una posicion aleatoria
                int filaInicio = random.nextInt(filas);
                int columnaInicio = random.nextInt(columnas);

                //Se verifica si la palabra cabe en la posición seleccionada y no sobreescribe
                if (esHorizontal) {
                    if (columnaInicio + cadena.length <= columnas && !esPosicionOcupada(tablero, filaInicio, columnaInicio, cadena.length, true)) {
                        //Se coloca la palabra horizontalmente.
                        for (int j = 0; j < cadena.length; j++) {
                            tablero[filaInicio][columnaInicio + j] = cadena[j];
                        }
                        palabraColocada = true;
                    }
                } else { //La posicion es vertical
                    if (filaInicio + cadena.length <= filas && !esPosicionOcupada(tablero, filaInicio, columnaInicio, cadena.length, false)) {
                        //Se coloca la palabra verticalmente
                        for (int j = 0; j < cadena.length; j++) {
                            tablero[filaInicio + j][columnaInicio] = cadena[j];
                        }
                        palabraColocada = true;
                    }
                }
            }
        }
        
        //Se llena el tablero con letras aleatorias
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(tablero[i][j] == ' ') {
                    tablero[i][j] = (char) ('A' + random.nextInt(26));  //Letras aleatorias A-Z
                }
            }
        }
        //Se muestra el tablero final
        System.out.println("\nTablero final:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    //Función para verificar si la posición está ocupada
    public static boolean esPosicionOcupada(char[][] tablero, int filaInicio, int columnaInicio, int longitud, boolean esHorizontal) {
        if (esHorizontal) {
            for (int i = 0; i < longitud; i++) {
                //Verificar si la celda está ocupada por una letra
                if (tablero[filaInicio][columnaInicio + i] != ' ') {
                    return true;  //La posición está ocupada
                }
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                //Verificar si la celda está ocupada por una letra
                if (tablero[filaInicio + i][columnaInicio] != ' ') {
                    return true;  //La posición está ocupada
                }
            }
        }
        return false;  //La posición está libre
    }
    
    //Algoritmo de burbuja para ordenar por puntaje
    public static void ordenarJugadoresPorPuntaje() {
        String[][] jugadores = {
            {"Luisa", "50"},
            {"Mar", "90"},
            {"Pedro", "70"},
            {"Roxana", "30"}
        };
        
        //Imprimir jugadores antes de ordenar
        System.out.println("Jugadores antes de ordenar:");
        for (String[] jugador : jugadores) {
            System.out.println("Jugador: " + jugador[0] + " - Puntaje: " + jugador[1]);
        }

        for (int i = 0; i < jugadores.length - 1; i++) {
            for (int j = 0; j < jugadores.length - 1 - i; j++) {
                int puntaje1 = Integer.parseInt(jugadores[j][1]);
                int puntaje2 = Integer.parseInt(jugadores[j + 1][1]);

                //Si el puntaje en la posición j es mayor que el de la posición j + 1, se intercambian
                if (puntaje1 < puntaje2) {
                    String[] temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }
        
        System.out.println("\nJugadores después de ordenar:");
        for (String[] jugador : jugadores) {
            System.out.println("Jugador: " + jugador[0] + " - Puntaje: " + jugador[1]);
        }
    }
}