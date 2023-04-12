package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.TestSTB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PostController {

    @PostMapping(value = "/poststb", produces = MediaType.APPLICATION_XML_VALUE)
    public String postTest() throws IOException {
        TestSTB stb = new TestSTB();
        return stb.loadFileXML();
    }

}
