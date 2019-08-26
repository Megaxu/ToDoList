import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static final String REGEX_DELETE = "DELETE\\s+\\d+";
    private static final String REGEX_LIST = "LIST";
    private static final String REGEX_EDIT = "EDIT\\s+\\d+\\s+.+";
    private static final String REGEX_ADD_INDEX = "ADD\\s+\\d+\\s+.+";
    private static final String REGEX_ADD = "ADD\\s+.+";
    private static final String REGEX_END = "END";

    private static ArrayList<String> toDoList = new ArrayList<>();
    private static String text = "";
    private static String target = "";

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("Список команд: \n LIST - выводит список дел \n ADD - добавляет дело в список " +
                    "\n ADD * - добавляет дело в список по индексу * \n EDIT * - заменяет дело под индексом * " +
                    "\n DELETE * - удаляет дело с индексом * \n END - выход из программы.");
            System.out.println("Введите команду: ");
            Scanner scan = new Scanner(System.in);
            text = scan.nextLine();

            if (text.matches(REGEX_DELETE)) {
                deleteTodo(text);
            }
            else if (text.matches(REGEX_LIST)) {
                listTodo();
            }
            else if (text.matches(REGEX_EDIT)) {
                editTodo(text);
            }
            else if (text.matches(REGEX_ADD_INDEX)) {
                addToDoIndex(text);
            }
            else if (text.matches(REGEX_ADD)) {
                addTodo(text);
            }
            else if(text.matches(REGEX_END))
            {
                break;
            }
            else {
                System.out.println("Комманда указана неверно. Повторите ввод.");
            }

            target = "";
        }
    }

    private static void deleteTodo(String text)
    {
        String[] command = text.split("\\s+");

        if (Integer.parseInt(command[1]) < toDoList.size())
        {
            toDoList.remove(Integer.parseInt(command[1]));
        }
        else {
            System.out.println("Отсутсвует запись с таким индексом. Удаление невозможно.");
        }
    }

    private static void listTodo()
    {
        for(int i = 0; i < toDoList.size(); i++)
        {
            System.out.println(i + " - " + toDoList.get(i));
        }
    }

    private static void editTodo(String text)
    {
        String[] command = text.split("\\s+");

        if (Integer.parseInt(command[1]) < toDoList.size())
        {
            for (int i = 2; i < command.length; i++)
            {
                target += command[i] + " ";
            }

            toDoList.set(Integer.parseInt(command[1]), target);
        }
        else {
            System.out.println("Отсутсвует запись с таким индексом. Редактирование невозможно.");
        }


    }

    private static void addToDoIndex(String text)
    {
        String[] command = text.split("\\s+");

        for (int i = 2; i < command.length; i++)
        {
            target += command[i] + " ";
        }

        if (Integer.parseInt(command[1]) < toDoList.size())
        {
            toDoList.add(Integer.parseInt(command[1]), target);
        }
        else {
            toDoList.add(target);
        }
    }

    private static void addTodo(String text)
    {
        String[] command = text.split("\\s+");

        for (int i = 1; i < command.length; i++)
        {
            target += command[i] + " ";
        }

        toDoList.add(target);
    }
}