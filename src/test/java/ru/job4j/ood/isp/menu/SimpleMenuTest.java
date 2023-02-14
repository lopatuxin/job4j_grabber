package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "������� � �������", STUB_ACTION);
        menu.add(Menu.ROOT, "��������� ������", STUB_ACTION);
        menu.add("������� � �������", "������ ��������", STUB_ACTION);
        menu.add("������ ��������", "������ ����", STUB_ACTION);
        menu.add("������ ��������", "������ ������", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("������� � �������",
                List.of("������ ��������"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("������� � �������").get());
        assertThat(new Menu.MenuItemInfo(
                "������ ��������",
                List.of("������ ����", "������ ������"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("������ ��������").get());
        assertThat(new Menu.MenuItemInfo(
                "��������� ������", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("��������� ������").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "������� � �������", STUB_ACTION);
        menu.add(Menu.ROOT, "��������� ������", STUB_ACTION);
        menu.add("������� � �������", "������ ��������", STUB_ACTION);
        menu.add("������ ��������", "������ ����", STUB_ACTION);
        menu.add("������ ��������", "������ ������", STUB_ACTION);
        Optional<Menu.MenuItemInfo> expected = Optional.of(new Menu.MenuItemInfo("������ ��������",
                List.of("������ ����", "������ ������"), STUB_ACTION, "1.1."));
        assertThat(menu.select("������ ��������")).isEqualTo(expected);
    }

    @Test
    public void whenPrint() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        menu.add(Menu.ROOT, "������� � �������", STUB_ACTION);
        menu.add(Menu.ROOT, "��������� ������", STUB_ACTION);
        menu.add("������� � �������", "������ ��������", STUB_ACTION);
        menu.add("������ ��������", "������ ����", STUB_ACTION);
        menu.add("������ ��������", "������ ������", STUB_ACTION);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        printer.print(menu);
        String expected = "1.������� � �������" + System.lineSeparator()
                + "----1.1.������ ��������" + System.lineSeparator()
                + "--------1.1.1.������ ����" + System.lineSeparator()
                + "--------1.1.2.������ ������" + System.lineSeparator()
                + "2.��������� ������" + System.lineSeparator();
        assertThat(output.toString()).isEqualTo(expected);
    }
}