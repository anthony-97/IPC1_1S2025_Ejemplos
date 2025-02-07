/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ipc_ejemplomenu;
import java.util.Scanner;

/**
 *
 * @author polares
 */
public class IPC_EjemploMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Imprimir números del 1 al 10");
            System.out.println("2. Verificar si un número es par o impar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            if(opcion == 1) {
                for(int i = 1; i <= 10; i++) {
                    System.out.println(i);
                }
            } else if(opcion == 2) {
                System.out.print("Ingrese un número: ");
                int numero = scanner.nextInt();
                if(numero % 2 == 0) {
                    System.out.println("El numero " + numero + " es par");
                } else {
                    System.out.println("El numero " + numero + " es impar");
                }
            } else if(opcion == 3) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opcion invalida");
            }
        } while(opcion != 3);
        
        scanner.close();
    }
    
}
