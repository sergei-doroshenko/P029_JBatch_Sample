package org.sergei.batch.csv_to_xml;

import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.xml.XmlIOFactory;
import net.sf.jsefa.xml.XmlSerializer;

import javax.batch.api.chunk.ItemProcessor;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Sergei_Doroshenko on 11/7/2016.
 */
public class BatchCsvToXmlItemProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object o) throws Exception {
        if( o == null ) {
            return null;
        }

        Deserializer deserializer = CsvIOFactory.createFactory(TestApp.Person.class).createDeserializer();
        StringReader reader = new StringReader( o.toString() );
        deserializer.open(reader);

        TestApp.Person p = null;

        while (deserializer.hasNext()) {
            p = deserializer.next();
            // do something useful with it
        }
        deserializer.close(true);

        XmlSerializer serializer = XmlIOFactory.createFactory(TestApp.Person.class).createSerializer();
        StringWriter writer = new StringWriter();
        serializer.open(writer);
        serializer.write(p);
        serializer.close(true);

        return writer.toString();
    }
}
