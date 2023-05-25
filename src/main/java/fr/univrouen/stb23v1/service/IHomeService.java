package fr.univrouen.stb23v1.service;

import fr.univrouen.stb23v1.exception.FunctionalException;

/**
 * This service contains the logic of the HomeController.
 */
public interface IHomeService {

    /**
     * Load the home page file and returns its content.
     *
     * @return The home page content.
     * @throws FunctionalException Exception thrown when an error occurs while getting the home page content.
     */
    String getHomePageContent() throws FunctionalException;

    /**
     * Load the help page file and returns its content.
     *
     * @return The help page content.
     * @throws FunctionalException Exception thrown when an error occurs while getting the help page content.
     */
    String getHelpPageContent() throws FunctionalException;

}
