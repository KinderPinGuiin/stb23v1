package fr.univrouen.stb23v1.dao.repository;


import fr.univrouen.stb23v1.model.STB;
import org.springframework.data.repository.CrudRepository;

public interface STBRepository extends CrudRepository<STB, Integer> {

    /**
     * Find an STB by its title, version and date
     *
     * @param title   The STB title.
     * @param version The STB version.
     * @param date    The STB date.
     * @return        The STB matching these criteria.
     */
    STB findOneByTitleAndVersionAndDate(String title, Double version, String date);

}
