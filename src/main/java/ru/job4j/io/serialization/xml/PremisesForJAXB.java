package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "premises")
@XmlAccessorType(XmlAccessType.FIELD)
public class PremisesForJAXB {
    @XmlAttribute
    private boolean living;
    @XmlAttribute
    private int area;
    @XmlAttribute
    private String model;
    @XmlElement(name = "address")
    private Address address;
    @XmlElementWrapper
    @XmlElement(name = "room")
    private String[] rooms;

    public PremisesForJAXB() {
    }

    public PremisesForJAXB(boolean living, int area, String model, Address address, String[] rooms) {
        this.living = living;
        this.area = area;
        this.model = model;
        this.address = address;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Premises{"
                + "living=" + living
                + ", area=" + area
                + ", model='" + model + '\''
                + ", address=" + address
                + ", rooms=" + Arrays.toString(rooms)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final PremisesForJAXB premise = new PremisesForJAXB(true, 40, "DTF-14", new Address("Lenina", 13, 42), new String[]{"kitchen", "hallway", "hall"});
        JAXBContext context = JAXBContext.newInstance(PremisesForJAXB.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
// сериализуем объект с помощью JAXB
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(premise, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
// десериализуем объект с помощью JAXB
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            PremisesForJAXB result = (PremisesForJAXB) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
