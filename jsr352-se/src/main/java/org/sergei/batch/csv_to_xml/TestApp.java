package org.sergei.batch.csv_to_xml;

import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.xml.XmlIOFactory;
import net.sf.jsefa.xml.XmlSerializer;
import net.sf.jsefa.xml.annotation.XmlDataType;
import net.sf.jsefa.xml.annotation.XmlElement;
import net.sf.jsefa.xml.namespace.QName;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 11/7/2016.
 */
public class TestApp {

    @XmlDataType(defaultElementName = "person")
    @CsvDataType()
    public static class Person {

        @XmlElement(pos = 1)
        @CsvField(pos = 1)
        String name;

        @XmlElement(name = "birth-date", pos = 2, format = "dd.MM.yyyy")
        @CsvField(pos = 2, format = "dd.MM.yyyy")
        Date birthDate;
    }

    public static void main(String[] args) throws IOException {
        List<Person> persons = new ArrayList<>();

        Deserializer deserializer = CsvIOFactory.createFactory(Person.class).createDeserializer();
//        StringReader reader = new StringReader(writer.toString());
        FileReader fr = new FileReader("test.csv");
        deserializer.open(fr);

        while (deserializer.hasNext()) {
            Person p = deserializer.next();
            // do something useful with it
            persons.add(p);
        }
        deserializer.close(true);

        FileWriter fw = new FileWriter("file.txt");

        XmlSerializer serializer = XmlIOFactory.createFactory(Person.class).createSerializer();
        StringWriter writer = new StringWriter();
        serializer.open(fw);

        serializer.getLowLevelSerializer().writeXmlDeclaration("1.0", "UTF-8");
        serializer.getLowLevelSerializer().writeStartElement(QName.create("persons"));

        /*Person person = new Person();
        person.name = "TestName";
        person.birthDate = new Date();*/

        for (Person p : persons ) {
            // call serializer.write for every object to serialize
            serializer.write(p);
        }


        serializer.getLowLevelSerializer().writeEndElement();
        serializer.close(true);
    }
}
