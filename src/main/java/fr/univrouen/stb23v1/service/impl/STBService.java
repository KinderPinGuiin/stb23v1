package fr.univrouen.stb23v1.service.impl;

import fr.univrouen.stb23v1.constant.Resource;
import fr.univrouen.stb23v1.constant.STB23Error;
import fr.univrouen.stb23v1.constant.STBErrorDetail;
import fr.univrouen.stb23v1.constant.STBStatus;
import fr.univrouen.stb23v1.dao.ResourcesDAO;
import fr.univrouen.stb23v1.dao.repository.STBRepository;
import fr.univrouen.stb23v1.dto.STBStatusDTO;
import fr.univrouen.stb23v1.dto.STBSummariesDTO;
import fr.univrouen.stb23v1.dto.error.STBError;
import fr.univrouen.stb23v1.dto.error.SimpleErrorDTO;
import fr.univrouen.stb23v1.exception.FunctionalException;
import fr.univrouen.stb23v1.model.STB;
import fr.univrouen.stb23v1.service.ISTBService;
import fr.univrouen.stb23v1.service.IXMLService;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Optional;

/**
 * The default STBService.
 */
@Service
public class STBService implements ISTBService {

    /**
     * The STB repository.
     */
    private final STBRepository stbRepository;

    /**
     * The resources DAO.
     */
    private final ResourcesDAO resourcesDAO;

    /**
     * The XML service.
     */
    private final IXMLService xmlService;

    public STBService(STBRepository stbRepository, ResourcesDAO resourcesDAO, IXMLService xmlService) {
        this.stbRepository = stbRepository;
        this.resourcesDAO = resourcesDAO;
        this.xmlService = xmlService;
    }

    @Override
    public Iterable<STB> getAllSTBs() {
        return this.stbRepository.findAll();
    }

    @Override
    public STB getSTB(Integer id) throws FunctionalException {
        // Get the STB and throw an exception if its null
        STB stb = this.stbRepository.findById(id).orElse(null);
        if (stb == null) {
            throw new FunctionalException(
                STB23Error.STB_NOT_FOUND.getErrorMessage(),
                new STBStatusDTO(id, STBStatus.ERROR),
                STB23Error.STB_NOT_FOUND.getHttpStatus()
            );
        }

        return stb;
    }

    @Override
    public String getSTBAsHTML(Integer id) throws FunctionalException {
        // Get the STB
        STB stb = this.getSTB(id);

        // Convert it to HTML
        String result;
        try {
            result = this.xmlService.transformXML(
                this.xmlService.xmlToString(stb, STB.class),
                this.resourcesDAO.loadResourceAsFile(Resource.STB_XSLT_PATH)
            );
        } catch (JAXBException | IOException | TransformerException e) {
            System.out.println(e.getMessage());
            throw new FunctionalException(e.getMessage(), new STBStatusDTO(id, STBStatus.ERROR));
        }

        return result;
    }

    @Override
    public String stbSummariesToHTML(STBSummariesDTO stbSummaries) throws FunctionalException {
        String result;
        try {
            result = this.xmlService.transformXML(
                this.xmlService.xmlToString(stbSummaries, STBSummariesDTO.class),
                this.resourcesDAO.loadResourceAsFile(Resource.STB_SUMMARIES_XSLT_PATH)
            );
        } catch (JAXBException | IOException | TransformerException e) {
            System.out.println(e.getMessage());
            throw new FunctionalException(
                e.getMessage(),
                new SimpleErrorDTO(STB23Error.CANT_CONVERT_STB_TO_HTML.getErrorMessage())
            );
        }

        return result;
    }

    @Override
    public STB insertSTBFromString(String stbString) throws FunctionalException {
        try {
            // Validate the given XML and convert it into an STB
            if (!this.xmlService.isXMLValid(stbString, this.resourcesDAO.loadResourceAsFile(Resource.STB_XSD_PATH))) {
                throw new FunctionalException(
                    STB23Error.STB_IS_NOT_VALID.getErrorMessage(),
                    new STBError(STBStatus.ERROR, STBErrorDetail.INVALID),
                    STB23Error.STB_IS_NOT_VALID.getHttpStatus()
                );
            }
            STB stb = this.xmlService.getInstanceFromString(stbString, STB.class);

            // Check if the STB doesn't already exist
            STB duplicatedSTB = this.stbRepository.findOneByTitleAndVersionAndDate(
                stb.getTitle(),
                stb.getVersion(),
                stb.getDate()
            );
            if (duplicatedSTB != null) {
                throw new FunctionalException(
                    STB23Error.STB_IS_NOT_VALID.getErrorMessage(),
                    new STBError(STBStatus.ERROR, STBErrorDetail.DUPLICATED),
                    STB23Error.STB_IS_NOT_VALID.getHttpStatus()
                );
            }

            // Insert the STB inside the database and returns it
            return this.stbRepository.save(stb);
        } catch (IOException | SAXException e) {
            throw new FunctionalException(
                e.getMessage(),
                new SimpleErrorDTO(STB23Error.CANT_VALIDATE_STB.getErrorMessage()),
                STB23Error.CANT_VALIDATE_STB.getHttpStatus()
            );
        }
    }

    @Override
    public STB deleteSTB(Integer id) throws FunctionalException {
        // Get the STB and delete it
        STB stb = this.getSTB(id);
        this.stbRepository.delete(stb);

        return stb;
    }

}
