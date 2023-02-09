package ru.job4j.ood.srp.model;

import java.util.Comparator;

public class HRComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o, Employee b) {
        return Double.compare(b.getSalary(), o.getSalary());
    }
}
