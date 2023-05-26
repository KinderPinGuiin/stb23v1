package fr.univrouen.stb23v1.controller;

import fr.univrouen.stb23v1.adapter.STBAdapter;
import fr.univrouen.stb23v1.constant.STBStatus;
import fr.univrouen.stb23v1.dto.STBStatusDTO;
import fr.univrouen.stb23v1.dto.STBSummariesDTO;
import fr.univrouen.stb23v1.exception.FunctionalException;
import fr.univrouen.stb23v1.model.STB;
import fr.univrouen.stb23v1.service.ISTBService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The STBController contains all the routes that can handle the STBs.
 */
@RestController
public class STBController {

    /**
     * The STB service.
     */
    private final ISTBService stbService;

    public STBController(ISTBService stbService) {
        this.stbService = stbService;
    }

    /**
     * @return All the summarized STBs contained inside the database.
     */
    @GetMapping(value = "/stb23/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody STBSummariesDTO stbSummaries() {
        return STBAdapter.fromSTBs(this.stbService.getAllSTBs());
    }

    /**
     * @return                     All the summarized STBs contained inside the database, formatted as an HTML.
     * @throws FunctionalException Exception thrown when the STB summaries can't be converted to HTML.
     */
    @GetMapping(value = "/stb23/resume", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public String stbSummariesAsHTML() throws FunctionalException {
        STBSummariesDTO stbSummaries = STBAdapter.fromSTBs(this.stbService.getAllSTBs());
        return this.stbService.stbSummariesToHTML(stbSummaries);
    }

    /**
     * Returns the STB associated to the given ID.
     *
     * @param  id                  The ID of the STB to return.
     * @return                     The STB.
     * @throws FunctionalException Exception thrown when the given ID is invalid.
     */
    @GetMapping(value = "/stb23/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody STB getXmlSTB(@PathVariable("id") Integer id) throws FunctionalException {
        return this.stbService.getSTB(id);
    }

    /**
     * Returns the STB associated to the given ID, on an HTML format.
     *
     * @param  id                  The ID of the STB to return.
     * @return                     The STB on an HTML format.
     * @throws FunctionalException Exception thrown when the given ID is invalid or if the STB can't be converted.
     */
    @GetMapping(value = "/stb23/html/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public String getHtmlSTB(@PathVariable("id") Integer id) throws FunctionalException {
        return this.stbService.getSTBAsHTML(id);
    }

    /**
     * Insert the given STB into the database.
     *
     * @param  stbString           The XML STB to insert.
     * @return                     The current status of the given STB.
     * @throws FunctionalException Exception thrown if the STB is invalid, or a duplicate.
     */
    @PostMapping(
        value = "/stb23/insert",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public @ResponseBody STBStatusDTO insertSTB(@RequestBody String stbString) throws FunctionalException {
        STB stb = this.stbService.insertSTBFromString(stbString);
        return new STBStatusDTO(stb.getId(), STBStatus.INSERTED);
    }

    /**
     * Delete the STB associated to the given ID.
     *
     * @param  id                  The ID of the STB to delete.
     * @return                     The status of the deleted STB.
     * @throws FunctionalException Exception thrown when the given ID is invalid.
     */
    @DeleteMapping(value = "/stb23/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody STBStatusDTO deleteSTB(@PathVariable("id") Integer id) throws FunctionalException {
        this.stbService.deleteSTB(id);
        return new STBStatusDTO(id, STBStatus.DELETED);
    }


}
