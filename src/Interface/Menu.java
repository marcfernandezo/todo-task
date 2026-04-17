package Interface;

import Controller.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);

    public static void menu(ArrayList<Task> tasks, File file) {
        cargarDatos(tasks, file);

        int option = 0;
        do {
            System.out.println("\n--- TODO TASK MANAGER ---");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Editar Tarea");
            System.out.println("3. Borrar Tarea");
            System.out.println("4. Listar Tareas");
            System.out.println("5. Salir");
            System.out.print("Selecciona: ");

            try {
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1: agregarTarea(tasks); break;
                    case 2: editarTarea(tasks); break;
                    case 3: borrarTarea(tasks); break;
                    case 4: listarTareas(tasks); break;
                    case 5: System.out.println("¡Adiós!"); break;
                    default: System.out.println("Opción no válida.");
                }

                if (option >= 1 && option <= 3) {
                    guardarDatos(tasks, file);
                }

            } catch (Exception e) {
                System.out.println("Error: Introduce un número válido.");
                sc.nextLine();
            }
        } while (option != 5);
    }

    private static void agregarTarea(ArrayList<Task> tasks) {
        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        System.out.print("Fecha (numérico): ");
        int date = sc.nextInt();

        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        tasks.add(new Task(id, name, desc, date));
        System.out.println("✅ Tarea creada.");
    }

    private static void listarTareas(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("La lista está vacía.");
        } else {
            System.out.println("\nID | NOMBRE | DESCRIPCIÓN | FECHA");
            for (Task t : tasks) {
                System.out.println(t.getId() + " | " + t.getName() + " | " + t.getDescription() + " | " + t.getDate());
            }
        }
    }

    private static void borrarTarea(ArrayList<Task> tasks) {
        listarTareas(tasks);
        if (tasks.isEmpty()) return;

        System.out.print("ID de la tarea a eliminar: ");
        int id = sc.nextInt();
        boolean removed = tasks.removeIf(t -> t.getId() == id);

        if (removed) System.out.println("🗑️ Tarea borrada.");
        else System.out.println("❌ ID no encontrado.");
    }

    private static void editarTarea(ArrayList<Task> tasks) {
        listarTareas(tasks);
        if (tasks.isEmpty()) return;

        System.out.print("ID de la tarea a editar: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Task t : tasks) {
            if (t.getId() == id) {
                System.out.print("Nuevo nombre (" + t.getName() + "): ");
                t.setName(sc.nextLine());
                System.out.print("Nueva desc (" + t.getDescription() + "): ");
                t.setDescription(sc.nextLine());
                System.out.println("📝 Tarea actualizada.");
                return;
            }
        }
        System.out.println("❌ ID no encontrado.");
    }

    private static void guardarDatos(ArrayList<Task> tasks, File file) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Task t : tasks) {
                pw.println(t.getId() + "," + t.getName() + "," + t.getDescription() + "," + t.getDate());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void cargarDatos(ArrayList<Task> tasks, File file) {
        if (!file.exists()) return;

        try (Scanner fr = new Scanner(file)) {
            while (fr.hasNextLine()) {
                String[] d = fr.nextLine().split(",");
                if (d.length == 4) {
                    tasks.add(new Task(Integer.parseInt(d[0]), d[1], d[2], Integer.parseInt(d[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos.");
        }
    }
}