package fr.univrouen.stb23v1.service.impl;

import fr.univrouen.stb23v1.service.IXMLService;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import net.sf.saxon.jaxp.SaxonTransformerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * The default XML service.
 */
@Service
public class XMLService implements IXMLService {

    @Override
    public void isXMLValid(String xml, File xsdFile) throws SAXException, IOException {
        // Get the XML validator and call the validate method
        Validator validator = this.getXMLValidator(xsdFile);
        validator.validate(new StreamSource(new StringReader(xml)));
    }

    @Override
    public <T> T getInstanceFromString(String xml, Class<T> objectClass) {
        return JAXB.unmarshal(new StringReader(xml), objectClass);
    }

    @Override
    public <T> String xmlToString(T xmlObject, Class<T> objectClass) throws JAXBException {
        // Creates the marshaller and use it to convert the object to string
        StringWriter stringWriter = new StringWriter();
        Marshaller marshaller = JAXBContext.newInstance(objectClass).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlObject, stringWriter);

        return stringWriter.toString();
    }

    @Override
    public String transformXML(String xml, File xsltFile) throws TransformerException {
        // Creates the Transformer
        TransformerFactory transformerFactory = (SaxonTransformerFactory) TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltFile));

        // Transform the XML
        StreamSource xmlStream = new StreamSource(new StringReader(xml));
        StreamResult transformedOutput = new StreamResult(new StringWriter());
        transformer.transform(xmlStream, transformedOutput);

        return transformedOutput.getWriter().toString();
    }

    /**
     * Creates the Validator instance associated to the given XSD file.
     *
     * @param  xsdFile      The XSD file.
     * @return              The Validator associated to the XSD file.
     * @throws SAXException Exception thrown if an error occurs during XSD parsing.
     */
    private Validator getXMLValidator(File xsdFile) throws SAXException {
        // Creates the Schema instance to get the Validator
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return schemaFactory.newSchema(new StreamSource(xsdFile)).newValidator();
    }

}
