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
                    break;
                }
                case "ADD":{
                    System.out.println("Добавить дело в список: ");
                    todoList.add(scanner.nextLine());
                    System.out.println("Дело добавленно!" + " номер в списке: " + todoList.size());
                    if (Pattern.matches("[0-9]", t[1])) {
                        if (Integer.parseInt(t[1]) < todoList.size() && Integer.parseInt(t[1]) >= 0) {
                            todoList.add(Integer.parseInt(t[1]), t[2]);
                        } else {
                            todoList.add(t[2]);
                        }
                    } else {
                        if (t.length > 2) {
                            String conc = t[1] + t[2];
                            todoList.add(conc);
                        } else {
                            todoList.add(t[1]);
                        }
                    }
                }
                    break;
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
                case ("DELETE"):
                {
                    if (Pattern.matches("[0-9]", t[1])) {
                        int num = Integer.parseInt(t[1]);
                        if (num < todoList.size() && num > 0) {
                            todoList.remove(num);
                        } else {
                            System.out.println("нет такого дела");
                        }
                        break;
                    }
                }
                default: {
                    System.out.println("неверный ввод");
                    break;
                }
            }
        }
    }
}
