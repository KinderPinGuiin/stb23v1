package fr.univrouen.stb23v1.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Wrap multiples STBSummaryDTO inside a <stb-summaries> tag.
 */
@XmlRootElement(name = "stb-summaries")
@XmlAccessorType(XmlAccessType.FIELD)
public class STBSummariesDTO {

    /**
     * The STB summaries.
     */
    @XmlElement(name = "summary")
    private List<STBSummaryDTO> summaries;

    /**
     * @param summaries The STB summaries.
     */
    public STBSummariesDTO(List<STBSummaryDTO> summaries) {
        this.summaries = summaries;
    }

    public STBSummariesDTO() {

    }

    public List<STBSummaryDTO> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<STBSummaryDTO> summaries) {
        this.summaries = summaries;
    }

}
