package ru.job4j.lsp.parking;

public class Auto {
    private int size;
    private String name;

    public Auto(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auto auto = (Auto) o;

        if (size != auto.size) return false;
        return name != null ? name.equals(auto.name) : auto.name == null;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
