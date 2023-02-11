package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.HRComparator;
import ru.job4j.ood.srp.store.MemStore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

class HRReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", now, now, 100000);
        Employee petr = new Employee("Petr", now, now, 150000);
        Employee sergei = new Employee("Sergei", now, now, 5000);
        Employee anna = new Employee("Anna", now, now, 88000);
        Employee darya = new Employee("Darya", now, now, 200000);
        store.add(ivan);
        store.add(petr);
        store.add(sergei);
        store.add(anna);
        store.add(darya);
        List<Employee> workers = store.findBy(em -> true);
        workers.sort(new HRComparator());
        Report engine = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workers.get(0).getName()).append(" ")
                .append(workers.get(0).getSalary())
                .append(System.lineSeparator())
                .append(workers.get(1).getName()).append(" ")
                .append(workers.get(1).getSalary())
                .append(System.lineSeparator())
                .append(workers.get(2).getName()).append(" ")
                .append(workers.get(2).getSalary())
                .append(System.lineSeparator())
                .append(workers.get(3).getName()).append(" ")
                .append(workers.get(3).getSalary())
                .append(System.lineSeparator())
                .append(workers.get(4).getName()).append(" ")
                .append(workers.get(4).getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}