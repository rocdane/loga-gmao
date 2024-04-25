package tech.loga.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.loga.vendor.ReferenceBuilder;

import java.util.Date;

@Service
public class QualityResource implements QualityManagement {

    private final QualityRepository qualityRepository;
    private final ReferenceBuilder referenceBuilder;

    @Autowired
    public QualityResource(QualityRepository qualityRepository,
                           ReferenceBuilder referenceBuilder) {
        this.qualityRepository = qualityRepository;
        this.referenceBuilder = referenceBuilder;
    }

    @Override
    public Quality createQuality(Quality quality) {
        quality.setCreatedAt(new Date(System.currentTimeMillis()));
        quality.setReference(referenceBuilder.build(quality.getCustomer()));
        return qualityRepository.save(quality);
    }
}
