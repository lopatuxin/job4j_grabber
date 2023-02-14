package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    String space = "----";
    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            String[] spl = menuItemInfo.getNumber().split("\\.");
            int len = spl.length;
            if (len > 1) {
                String[] spaceRes = new String[len - 1];
                for (int i = 0; i < spaceRes.length; i++) {
                    spaceRes[i] = space;
                    System.out.print(spaceRes[i]);
                }
            }
            System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName());
        });
    }
}
