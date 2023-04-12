package fr.univrouen.stb23v1.controllers;

import fr.univrouen.stb23v1.model.STB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    @GetMapping("/resume")
    public String getListSTBinXML() {
        return "Envoi de la liste des STB";
    }

    @GetMapping("/stbid")
    public String getSTBinXML(
        @RequestParam(value = "id") String text
    ) {
        return "Détail de la STB n°" + text;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody STB getXML() {
        STB stb = new STB(123,"Test STB","2023-04-01T14:22:33");
        return stb;
    }

}
