package home.blackharold;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;

public class TagCleaner {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        FileInputStream fis = new FileInputStream(new File("src/main/resources/somexml.xml"));
        Document doc = builder.parse(fis);
        doc.getDocumentElement().normalize();
        DOMSource source = new DOMSource(doc);
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);

        String string = stringWriter.toString();
        string = string.replaceAll(" *<.*?>", "");
//        string = string.replaceAll(" ", "");
        string = string.replaceAll("\r*\n*", "");
        /**
         * Uncomment the string that to see what you counting
         */
        System.out.println(/*string + */"\nlength: " + string.length());
    }
}
