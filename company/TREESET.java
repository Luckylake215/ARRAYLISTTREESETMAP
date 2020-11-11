package com.company;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class TREESET {
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<String> MailSet = new TreeSet<>();
        String[] n;
        while (true) {
        n = scanner.nextLine().split("\\s+");
        switch (n[0]) {
        case ("ADD") -> {
        if (isMailCorrect(n[1])) {
        MailSet.add(n[1]);
        } else {
        System.out.println("Неправильный ввод");
        }
        break;
        }
        case ("LIST") -> {
        for (String mail : MailSet) {
        System.out.println(mail);
        }
        break;
        }
default -> System.out.println("Правильный ввод");
        }}
}
static boolean isMailCorrect(String s) {
        return Pattern.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", s);
        }
        }