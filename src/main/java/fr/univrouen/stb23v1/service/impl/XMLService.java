package fr.univrouen.stb23v1.service.impl;

import fr.univrouen.stb23v1.service.IXMLService;
import jakarta.xml.bind.JAXB;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * The default XML service.
 */
@Service
public class XMLService implements IXMLService {

    @Override
    public boolean isXMLValid(String xml, File xsdFile) throws SAXException, IOException {
        // Get the XML validator and call the validate method
        Validator validator = this.getXMLValidator(xsdFile);
        try {
            validator.validate(new StreamSource(new StringReader(xml)));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }

    @Override
    public <T> T getInstanceFromString(String xml, Class<T> objectClass) {
        return JAXB.unmarshal(new StringReader(xml), objectClass);
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
