package Interface;

import Controller.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void menu(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("==== TODO TASK MANAGER ====");

            System.out.println("Select an option: \n 1. Agregar Tarea \n 2. Editar Tarea" +
                    "\n 3. Borrar Tarea \n 4. Listar Tarea");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("-> Has elegido: Agregar Tarea");
                    break;
                case 2:
                    System.out.println("-> Has elegido: Editar Tarea");
                    break;
                case 3:
                    System.out.println("-> Has elegido: Borrar Tarea");
                    break;
                case 4:
                    System.out.println("-> Has elegido: Listar Tareas");
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no válida, intenta de nuevo.");
            }
        } while (option != 5);
    }


}