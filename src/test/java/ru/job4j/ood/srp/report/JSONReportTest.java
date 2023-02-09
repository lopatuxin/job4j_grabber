package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[").append("{").append("\"name\":\"").append(worker.getName()).append("\",")
                .append("\"hired\":{").append("\"year\":").append(worker.getHired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker.getHired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getHired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getHired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getHired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker.getHired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"fired\":{").append("\"year\":").append(worker.getFired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker.getFired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getFired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getFired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getFired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker.getFired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(worker.getSalary()).append("}")
                .append("]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());
    }
}