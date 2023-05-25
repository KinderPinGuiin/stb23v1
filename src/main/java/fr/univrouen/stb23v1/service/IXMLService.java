package fr.univrouen.stb23v1.service;

import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * The XML service allows you to handle XML files, such as validation, transformation...
 */
public interface IXMLService {

    /**
     * Validate the given XML string with the XSD at the given location.
     *
     * @param  xml          The XML string.
     * @param  xsdFile      The XSD schema file.
     * @return              True if the xml is valid, false otherwise.
     * @throws SAXException Exception thrown if the given XSD can't be parsed.
     * @throws IOException  Exception thrown by the underlying XMLReader.
     */
    boolean isXMLValid(String xml, File xsdFile) throws SAXException, IOException;

    /**
     * Convert the given XML string to an instance of the given object class. This method assume that the given xml
     * string is valid.
     *
     * @param  xml         The XML string.
     * @param  objectClass The object class.
     * @param  <T>         The type of the object you want to instantiate.
     * @return             The instance of the given object, containing the XML data.
     */
    <T> T getInstanceFromString(String xml, Class<T> objectClass);

    /**
     * Converts the given object to an XML String.
     *
     * @param  xmlObject     The JAXB compatible object.
     * @param  objectClass   The object class.
     * @param  <T>           The type of the object you want to convert to String.
     * @return               The XML string.
     * @throws JAXBException Exception thrown when the JAXB marshaller fails to convert to String.
     */
    <T> String xmlToString(T xmlObject, Class<T> objectClass) throws JAXBException;

    /**
     * Transform the given XML by using the given xsltFile.
     *
     * @param  xml                  The XML string.
     * @param  xsltFile             The XSLT file.
     * @return                      The String obtained by the transformation.
     * @throws TransformerException Exception thrown when the xslt is invalid or if the object can't be transformed.
     */
    String transformXML(String xml, File xsltFile) throws TransformerException;

}
