package fr.univrouen.stb23v1.service;

import org.xml.sax.SAXException;

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
     * @param xml         The XML string.
     * @param objectClass The object class.
     * @param <T>         The type of the object you want to instantiate.
     * @return            The instance of the given object, containing the XML data.
     */
    <T> T getInstanceFromString(String xml, Class<T> objectClass);

}
