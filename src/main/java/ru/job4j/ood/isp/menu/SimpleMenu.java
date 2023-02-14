package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean res;
        Optional<ItemInfo> info = findItem(parentName);
        if (info.isEmpty()) {
            res = rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            res = info.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        }
        return res;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> info = findItem(itemName);
        Optional<MenuItemInfo> result = Optional.empty();
        if (info.isPresent()) {
            result = Optional.of(new MenuItemInfo(info.get().menuItem, info.get().number));
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<ItemInfo> list = new ArrayList<>();
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return new Iterator<>() {
            final Iterator<ItemInfo> items = list.iterator();
            ItemInfo itemInfo = null;

            @Override
            public boolean hasNext() {
                return items.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                itemInfo = items.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> optional = Optional.empty();
        if (name == null) {
            return optional;
        }
        DFSIterator dfsIterator = new DFSIterator();
        ItemInfo itemInfo;
        while (dfsIterator.hasNext()) {
            itemInfo = dfsIterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                optional = Optional.of(itemInfo);
            }
        }
        return optional;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
