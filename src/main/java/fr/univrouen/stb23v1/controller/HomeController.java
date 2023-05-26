package fr.univrouen.stb23v1.controller;

import fr.univrouen.stb23v1.exception.FunctionalException;
import fr.univrouen.stb23v1.service.IHomeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The HomeController contains the information routes such as / or /help.
 */
@RestController
public class HomeController {

    private final IHomeService homeService;

    public HomeController(IHomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * Display the project information.
     *
     * @return The project information as an HTML.
     * @throws FunctionalException Exception thrown when the project information page is not found.
     */
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() throws FunctionalException {
        return this.homeService.getHomePageContent();
    }

    /**
     * Display the API help (all the routes).
     *
     * @return The project information as an HTML.
     * @throws FunctionalException Exception thrown when the project information page is not found.
     */
    @GetMapping(value = "/help", produces = MediaType.TEXT_HTML_VALUE)
    public String help() throws FunctionalException {
        return this.homeService.getHelpPageContent();
    }

}
