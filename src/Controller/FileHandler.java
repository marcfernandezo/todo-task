package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String PATH = "Usuario/marc/www/Personal/todo-list/src/data-tasks.csv";

    // Guarda toda la lista en el archivo
    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
            for (Task t : tasks) {
                pw.println(t.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Carga las tareas del archivo al ArrayList al iniciar
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) return tasks; // Si no existe, devolvemos lista vacía

        try (Scanner fs = new Scanner(file)) {
            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                String[] data = line.split(",");
                if (data.length == 4) {
                    tasks.add(new Task(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            Integer.parseInt(data[3])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        return tasks;
    }
}