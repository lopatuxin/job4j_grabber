package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.tools.CSVReader;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class CSVReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 1000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        CSVReader readerCSV = new CSVReader();
        Report report = new CSVReport(store, parser, "report.csv");
        assertThat(report.generate(em -> true)).isEqualTo(readerCSV.readCSVFile("report.csv"));
    }
}