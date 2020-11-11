package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ARRAYLIST {
    public static void main(String[] args) {
        ArrayList<String> todoList = new ArrayList<>();
todoList.add("Первое дело");
todoList.add("Второе дело");
        todoList.add("Третье дело");
        todoList.add("Четвертое дело");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] t = scanner.nextLine().split("\\s+", 3);
            switch (t[0]) {

                case ("LIST"): {
                    for (int i = 0; i < todoList.size(); i++) {
                        if (todoList.get(i) != null) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    }
                    continue;
                }
                case "ADD":
                    System.out.println("Добавить дело в список: ");
                    todoList.add(scanner.nextLine());
                    System.out.println("Дело добавленно!" + " номер в списке: " + todoList.size());
                    continue;
                case ("EDIT"): {
                    if (Pattern.matches("[0-9]", t[1])) {
                        int num = Integer.parseInt(t[1]);
                        if (num < todoList.size() && num > 0) {
                            todoList.set(num, t[2]);
                        } else {
                            System.out.println("Новое название дела");
                        }
                        break;
                    }
                }
                case ("DELETE"): {
                    System.out.println("Удалить дело из списка: ");
                    todoList.remove(scanner.nextInt() - 1);
                    System.out.println("Дело удалено! " + todoList.size());
                    continue;
                }
                default: {
                    System.out.println("неверный ввод");
                    break;
                }
            }
        }
    }
}
