package fr.univrouen.stb23v1.service;

import fr.univrouen.stb23v1.dto.STBSummariesDTO;
import fr.univrouen.stb23v1.exception.FunctionalException;
import fr.univrouen.stb23v1.model.STB;

/**
 * This services helps you to handle the STBs.
 */
public interface ISTBService {

    /**
     * @return All the STBs from the database.
     */
    Iterable<STB> getAllSTBs();

    /**
     * Returns the STB associated to the given ID.
     *
     * @param  id                  The STB ID.
     * @return                     The STB associated to this ID.
     * @throws FunctionalException Exception thrown if the given ID is invalid.
     */
    STB getSTB(Integer id) throws FunctionalException;

    /**
     * Returns the STB associated to the given ID on an HTML format.
     *
     * @param  id                  The STB ID.
     * @return                     The STB associated to this ID, as an HTML string.
     * @throws FunctionalException Exception thrown if the given ID is invalid or if the STB can't be converted.
     */
    String getSTBAsHTML(Integer id) throws FunctionalException;

    /**
     * Transform the given STB summaries to an HTML view.
     *
     * @param  stbSummaries        The STB summaries.
     * @return                     The HTML view of the summaries.
     * @throws FunctionalException Exception thrown if the STB can't be converted.
     */
    String stbSummariesToHTML(STBSummariesDTO stbSummaries) throws FunctionalException;

    /**
     * Insert the given string STB into the database.
     *
     * @param  stbString           The XML STB string.
     * @return                     The inserted STB.
     * @throws FunctionalException Exception thrown if the given STB is invalid or duplicated.
     */
    STB insertSTBFromString(String stbString) throws FunctionalException;

    /**
     * Deletes the STB associated to the given ID.
     *
     * @param  id                  The STB ID.
     * @return                     The deleted STB.
     * @throws FunctionalException Exception thrown when the given ID is invalid.
     */
    STB deleteSTB(Integer id) throws FunctionalException;

}
