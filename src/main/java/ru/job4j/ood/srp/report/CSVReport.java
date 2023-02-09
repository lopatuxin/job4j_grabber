package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import ru.job4j.ood.srp.tools.CSVReader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class CSVReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String fileName;

    public CSVReport(Store store, DateTimeParser<Calendar> dateTimeParser, String fileName) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.fileName = fileName;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.append("Name; Hired; Fired; Salary;")
                    .append(System.lineSeparator());
            for (Employee employee : store.findBy(filter)) {
                writer.append(employee.getName()).append(" ")
                        .append(dateTimeParser.parse(employee.getHired())).append(" ")
                        .append(dateTimeParser.parse(employee.getFired())).append(" ")
                        .append((char) employee.getSalary())
                        .append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVReader csvReader = new CSVReader();
        return csvReader.readCSVFile(fileName);
    }
}
