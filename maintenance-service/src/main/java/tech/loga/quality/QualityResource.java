package tech.loga.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualityManagement implements QualityManagement {

    @Autowired
    private QualityRepository qualityRepository;


    /**
     * TODO:Cette méthode permet d'enregistrer un objet Qualite dans la base de données.
     * @param quality
     * @return Quality
     */
    @Override
    public Quality createQuality(Quality quality) {
        return qualityRepository.save(quality);
    }
}
