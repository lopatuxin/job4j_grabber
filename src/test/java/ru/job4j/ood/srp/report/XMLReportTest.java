package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {
    @Test
    public void whenReportXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<Employees>\n")
                .append("    <employees>\n")
                .append("        <employee>\n")
                .append("            <fired>").append(format.format(worker.getHired().getTime())).append("</fired>\n")
                .append("            <hired>").append(format.format(worker.getFired().getTime())).append("</hired>\n")
                .append("            <name>").append(worker.getName()).append("</name>\n")
                .append("            <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("        </employee>\n")
                .append("    </employees>\n")
                .append("</Employees>\n");
        assertThat(engine.generate(employee -> true)).isEqualTo(expect.toString());
    }
}