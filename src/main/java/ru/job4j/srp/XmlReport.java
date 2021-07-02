package ru.job4j.srp;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

public class XmlReport implements ReportForm {

    @Override
    public String generate(List<Employee> employees) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                for (Employee employee : employees) {
                    marshaller.marshal(employee, writer);
                }
                xml = writer.getBuffer().toString();
            }
        } catch (IOException | JAXBException ex) {
            ex.printStackTrace();
        }
        return xml;
    }
}
