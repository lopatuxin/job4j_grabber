package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final String ADD_ROOT = "1.Добавить элемент в корень меню";
    private static final String ADD_CHILD = "2.Добавить элемент к родительскому элементу";
    private static final String ACTION = "3.Вызвать действие, привязанное к пункту меню";
    private static final String PRINT_MENU = "4.Вывести меню в консоль";
    private static final String EXIT = "Для выхода - любая другая цифра";
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private static final String MENU = String.join(System.lineSeparator(),
            ADD_ROOT, ADD_CHILD, ACTION, PRINT_MENU, EXIT + System.lineSeparator());
    private static final String ASK = "Выберите пункт меню ";

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
                case 1:
                    System.out.println("Укажите имя элемента: ");
                    name = sc.nextLine();
                    menu.add(Menu.ROOT, name, DEFAULT_ACTION);
                    break;
                case 2:
                    System.out.println("Укажите имя родительского элемента: ");
                    name = sc.nextLine();
                    System.out.println("Укажите имя добавляемого элемента: ");
                    String child = sc.nextLine();
                    menu.add(name, child, DEFAULT_ACTION);
                    break;
                case 3:
                    DEFAULT_ACTION.delegate();
                    break;
                case 4:
                    printer.print(menu);
                    break;
                default:
                    System.out.println("Программа завершена!");
                    run = false;
            }
        }
    }
}
