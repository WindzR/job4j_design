package ru.job4j.srp;

import java.util.Calendar;
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
        Employees generalObject = new Employees();
        generalObject.setEmployeeList(employees);
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(generalObject, writer);
                xml = writer.getBuffer().toString();
            }
        } catch (IOException | JAXBException ex) {
            ex.printStackTrace();
        }
        return xml;
    }
}
