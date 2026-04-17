import java.io.File;
import java.util.ArrayList;

import Controller.FileHandler;
import Controller.Task;
import Interface.Menu;

public class Main {
    public static void main(String[] args) {

        ArrayList<Task> lista = new ArrayList<>();
        File file = new File("src/data-tasks.csv");

        Menu.menu(lista, file);
    }
}
