package com.company;
import java.io.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
public class MAP{

    private static String DATA_FILE_NAME = ".phone_book_demo";


    public static void main(String[] args) {

        String name, number = null;
        TreeMap<String,String>  phoneBook;


        phoneBook = new TreeMap<String,String>();


        File userHomeDirectory = new File( System.getProperty("user.home") );
        File dataFile = new File( userHomeDirectory, DATA_FILE_NAME );

        if ( ! dataFile.exists() ) {
            System.out.println("Файл данных телефонной книги не найден");
            System.out.println("Будет создан новый");
            System.out.println("Имя файла:  " + dataFile.getAbsolutePath());
        }
        else {
            System.out.println("Чтение данных телефонной книги: ");
            try {
                Scanner scanner = new Scanner( dataFile );
                while (scanner.hasNextLine()) {
                    String phoneEntry = scanner.nextLine();
                    int separatorPosition = phoneEntry.indexOf('%');
                    if (separatorPosition == -1)
                        throw new IOException("Файл не является файлом данных телефонной книги");
                    name = phoneEntry.substring(0, separatorPosition);
                    number = phoneEntry.substring(separatorPosition+1);
                    phoneBook.put(name,number);
                }
            }
            catch (IOException e) {
                System.out.println("Ошибка в файле данных телефонной книги");
                System.out.println("Имя файла:  " + dataFile.getAbsolutePath());
                System.out.println("Эта программа не может продолжаться");
                System.exit(1);
            }
        }
        Scanner in = new Scanner( System.in );
        boolean changed = false;  // В каталог были внесены какие-либо изменения?

        mainLoop: while (true) {
            System.out.println("\nВыберите действие, которое вы хотите выполнить:");
            System.out.println("   1.  Найдите номер телефона");
            System.out.println("   2.  Добавьте или измените номер телефона");
            System.out.println("   3.  Удалите запись из телефонного справочника");
            System.out.println("   4.  Перечислить весь телефонный справочник");
            System.out.println("   5.  Выход из программы");
            System.out.println("Введите команду (1-5):  ");
            int command;
            if ( in.hasNextInt() ) {
                command = in.nextInt();
                in.nextLine();
            }
            else {
                System.out.println("\nНеправильный ввод");
                in.nextLine();
                continue;
            }
            switch(command) {
                case 1:
                    System.out.print("\nВведите имя, номер которого вы хотите найти: ");
                    name = in.nextLine().trim().toLowerCase();
                    number = phoneBook.get(name);
                    if (number == null)
                        System.out.println("\nНомер не найден " + name);
                    else
                        System.out.println("\nНомер для " + name + ":  " + number);
                    break;
                case 2:
                    System.out.print("\nВведите имя: ");
                    name = in.nextLine().trim().toLowerCase();
                    if (name.length() == 0)
                        System.out.println("\nПустой список");
                    else if (name.indexOf('%') >= 0)
                        System.out.println("\nНе может содержать \"%\".");
                    else {
                        System.out.print("Введите номер телефона: ");
                        number = in.nextLine().trim();
                        if (number.length() == 0)
                            System.out.println("\nПусто");
                        else {
                            phoneBook.put(name, number);
                            changed = true;
                        }
                    }
                   {
                    number = number.replaceAll("[^0-9]", "");
                    StringBuffer result = new StringBuffer();
                    if (number.charAt(0) == '7' && number.length() == 11) {
                        result.append(number);
                        result.insert(0, "+");
                        result.insert(2, " (");
                        result.insert(7, ") ");
                        result.insert(12, "-");
                        result.insert(15, "-");

                    } else if (number.charAt(0) == '8' && number.length() == 11) {
                        result.append(number);
                        result.delete(0, 1);
                        result.insert(0, "+7");
                        result.insert(2, " (");
                        result.insert(7, ") ");
                        result.insert(12, "-");
                        result.insert(15, "-");

                    } else if (number.length() == 10) {
                        result.append(number);
                        result.insert(0, "+7");
                        result.insert(2, " (");
                        result.insert(7, ") ");
                        result.insert(12, "-");
                        result.insert(15, "-");

                    } else {
                        System.out.println("Неверный ввод");
                    }

            }
                    break;
                case 3:
                    System.out.print("\nВведите имя, запись которого вы хотите удалить: ");
                    name = in.nextLine().trim().toLowerCase();
                    number = phoneBook.get(name);
                    if (number == null)
                        System.out.println("\nНет записи" + name);
                    else {
                        phoneBook.remove(name);
                        changed = true;
                        System.out.println("\nЗапись удалена " + name);
                    }
                    break;
                case 4:
                    System.out.println("\nСписок:\n");
                    for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
                        System.out.println("   " + entry.getKey() + ": " + entry.getValue() );
                    break;
                case 5:
                    System.out.println("\nВыход из программы");
                    break mainLoop;
                default:
                    System.out.println("\nНеправильный ввод");
            }
        }

        if (changed) {
            System.out.println("Сохранение изменений телефонной книги в файл " +
                    dataFile.getAbsolutePath() + " ...");
            PrintWriter out;
            try {
                out = new PrintWriter( new FileWriter(dataFile) );
            }
            catch (IOException e) {
                System.out.println("ERROR");
                return;
            }
            for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
                out.println(entry.getKey() + "%" + entry.getValue() );
            out.close();
            if (out.checkError())
                System.out.println("ERROR");
            else
                System.out.println("Выполненно");
        }

    }

}