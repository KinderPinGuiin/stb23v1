package fr.univrouen.stb23v1.adapter;

import fr.univrouen.stb23v1.dto.STBSummariesDTO;
import fr.univrouen.stb23v1.dto.STBSummaryDTO;
import fr.univrouen.stb23v1.model.STB;

import java.util.ArrayList;
import java.util.List;

/**
 * The STB adapter can adapt an STB entity into different types of DTO.
 */
public class STBAdapter {

    /**
     * Convert an iterable of STB into a STBSummariesDTO.
     *
     * @param  STBs The STBs to adapt.
     * @return      The summarized STBs.
     */
    public static STBSummariesDTO fromSTBs(Iterable<STB> STBs) {
        List<STBSummaryDTO> summaries = new ArrayList<>();
        for (STB stb : STBs) {
            summaries.add(new STBSummaryDTO(
                stb.getId(),
                stb.getTitle(),
                stb.getDescription(),
                stb.getDate(),
                stb.getClient().getEntity()
            ));
        }

        return new STBSummariesDTO(summaries);
    }

}
