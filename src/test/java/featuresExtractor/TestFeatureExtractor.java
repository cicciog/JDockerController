package featuresExtractor;

import fileManager.FileManager;
import fileManager.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
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
    
    @Test
    public void testExtrcatAllFeaturesOfDockerImagesCollection() throws FilesNotFoundException, IOException, FileNotFoundException, ParseException{
        assertNotNull(featureExtractor);
        
        String source = fileManager.getWorkDirectory()+path.getInput()+"/json/";
        ArrayList<FeaturesEntity> filesFeatures = (ArrayList<FeaturesEntity>) featureExtractor.extrcatAllFeaturesOfDockerImagesCollection(source);
        
        assertTrue(filesFeatures.size() > 0);
        assertFalse(filesFeatures.isEmpty());
        
        assertNotNull(filesFeatures.get(0));
        assertNotNull(filesFeatures.get(filesFeatures.size()-1));
        assertNotNull(filesFeatures.get(filesFeatures.size() / 2));

    }
}
