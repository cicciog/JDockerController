package featuresExtractor;

import fileManager.FileManager;
import fileManager.Path;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestFeaturesEntity {

    @Test
    public void testAdd() {
        FeaturesEntity featureEntity = new FeaturesEntity("joomla_docker-joomla.json");
        featureEntity.addFeature("FROM");
        featureEntity.addFeature("LABEL");
        featureEntity.addFeature("ENV");
        featureEntity.addFeature("ENV");
        featureEntity.addFeature("ENV");
        featureEntity.addFeature("RUN");
        featureEntity.addFeature("RUN");
        featureEntity.addFeature("RUN");
        featureEntity.addFeature("VOLUME");
        featureEntity.addFeature("COPY");
        featureEntity.addFeature("COPY");
        featureEntity.addFeature("ENTRYPOINT");
        featureEntity.addFeature("CMD");
        
        assertNotNull(featureEntity);
        
        assertEquals(featureEntity.getFROM(), 1);
        assertEquals(featureEntity.getLABEL(), 1);
        assertEquals(featureEntity.getENV(), 3);
        assertEquals(featureEntity.getRUN(), 3);
        assertEquals(featureEntity.getVOLUME(), 1);
        assertEquals(featureEntity.getCOPY(), 2);
        assertEquals(featureEntity.getENTRYPOINT(), 1);
        assertEquals(featureEntity.getCMD(), 1);
        
        assertEquals(featureEntity.getNumberOfFeatures(), 13);
        
        assertEquals(featureEntity.getName(),"joomla_docker-joomla.json");
    }

}
