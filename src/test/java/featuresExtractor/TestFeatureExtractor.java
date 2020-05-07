package featuresExtractor;

import fileManager.FileManager;
import fileManager.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestFeatureExtractor {

    FeatureExtractor featureExtractor = new FeatureExtractor();
    FileManager fileManager = new FileManager();
    Path path = new Path();

    @Test
    public void testAdd() throws IOException, FileNotFoundException, ParseException {
        assertNotNull(featureExtractor);

        String[] jsonFiles = fileManager.getFileListInADirectory(fileManager.getWorkDirectory()
                + path.getInput()
                + "/json");

        assertEquals(featureExtractor.extractAllImageFeatures(jsonFiles[0]).getName(),jsonFiles[0]);
        assertNotNull(featureExtractor.extractAllImageFeatures(jsonFiles[0]));
        
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
        
        assertEquals(featureEntity.toString(),featureExtractor.extractAllImageFeatures(jsonFiles[0]).toString());
        
        
        

    }
}
