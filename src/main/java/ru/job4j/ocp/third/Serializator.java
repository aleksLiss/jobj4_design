package ru.job4j.ocp.third;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.json.JSONObject;
import ru.job4j.serialization.json.Cat;
import ru.job4j.serialization.json.Kitty;
import ru.job4j.serialization.xml.Address;
import ru.job4j.serialization.xml.Person;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Serializator {

    /**
     * Задание: необходимо проводить сериализацию объектов Java.
     * Изначально в JSON-формат.
     *
     * @return JSON object
     */
    public JSONObject toJson() {
        List<String> list = new ArrayList<>();
        list.add("Alex");
        list.add("Lila");
        JSONObject object = new JSONObject();
        final Kitty son = new Kitty("Nick", 1);
        final Cat cat = new Cat("Bob", 8, true, new String[]{son.getName()}, son);
        object.put("name", cat.getName());
        object.put("age", cat.getAge());
        object.put("hasChild", cat.isHasChild());
        object.put("childNames", cat.getChildNames());
        object.put("son", cat.getSon());
        return object;
    }

    /**
     * Затем требование поменялось и нужно проводить
     * сериализацию уже в XML-формат.
     *
     * @return JAXBContext
     */
    public JAXBContext toXml() {
        JAXBContext context = null;
        try {
            Person person = new Person("Alex", 12,
                    new Address("Crosby Street", 67, 9));
            context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml;
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(person, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return context;
    }
}
