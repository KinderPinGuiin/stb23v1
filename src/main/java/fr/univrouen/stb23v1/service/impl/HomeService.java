package fr.univrouen.stb23v1.service.impl;

import fr.univrouen.stb23v1.constant.Resource;
import fr.univrouen.stb23v1.constant.STB23Error;
import fr.univrouen.stb23v1.dao.ResourcesDAO;
import fr.univrouen.stb23v1.dto.error.SimpleErrorDTO;
import fr.univrouen.stb23v1.exception.FunctionalException;
import fr.univrouen.stb23v1.service.IHomeService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Default home service.
 */
@Service
public class HomeService implements IHomeService {

    /**
     * The resources DAO, that allows us to access the resources files.
     */
    ResourcesDAO resourcesDAO;

    public HomeService(ResourcesDAO resourcesDAO) {
        this.resourcesDAO = resourcesDAO;
    }

    public String getHomePageContent() throws FunctionalException {
        try {
            return this.resourcesDAO.loadResourceAsString(Resource.HOME_PAGE_PATH);
        } catch (IOException e) {
            throw new FunctionalException(
                e.getMessage(),
                new SimpleErrorDTO(STB23Error.CANT_GET_HOME_PAGE.getErrorMessage())
            );
        }
    }

    @Override
    public String getHelpPageContent() throws FunctionalException {
        try {
            return this.resourcesDAO.loadResourceAsString(Resource.HELP_PAGE_PATH);
        } catch (IOException e) {
            throw new FunctionalException(
                e.getMessage(),
                new SimpleErrorDTO(STB23Error.CANT_GET_HELP_PAGE.getErrorMessage())
            );
        }
    }

}
