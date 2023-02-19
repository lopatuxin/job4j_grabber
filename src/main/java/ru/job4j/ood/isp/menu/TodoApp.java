package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final String MENU = """
            1.Добавить элемент в корень меню.
            2.Добавить элемент к родительскому элементу.
            3.Вывод приветствия.
            4.Печать меню.
            Для выхода - любая другая цифра.
            """;
    private static final int ADD_ROOT = 1;
    private static final int ADD_CHILD = 2;
    private static final int SOME_ACTION = 3;
    private static final int PRINT_MENU = 4;
    private static final String ASK = "Выберите пункт меню ";
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Hello!");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String name;
        while (run) {
            System.out.println(MENU);
            System.out.println(ASK);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case ADD_ROOT:
                    System.out.println("Укажите имя элемента: ");
                    name = sc.nextLine();
                    menu.add(Menu.ROOT, name, DEFAULT_ACTION);
                    break;
                case ADD_CHILD:
                    System.out.println("Укажите имя родительского элемента: ");
                    name = sc.nextLine();
                    System.out.println("Укажите имя добавляемого элемента: ");
                    String child = sc.nextLine();
                    menu.add(name, child, DEFAULT_ACTION);
                    break;
                case SOME_ACTION:
                    DEFAULT_ACTION.delegate();
                    break;
                case PRINT_MENU:
                    printer.print(menu);
                    break;
                default:
                    System.out.println("Программа завершена!");
                    run = false;
            }
        }
    }
}
