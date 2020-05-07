package fileManager;

import dockerController.DockerImage;
import featuresExtractor.FeaturesEntity;
import gitCloner.Repository;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestCSVmanager {

    CSVmanager csvmanager = new CSVmanager();
    FileManager filemanager = new FileManager();
    Path path = new Path();
    ArrayList<String[]> images;
    ArrayList<String[]> images1;
    ArrayList<Repository> repositories;
    ArrayList<DockerImage> dockerImages;

    @Test
    public void testAdd() throws URISyntaxException, IOException {

        //check for not null value
        assertNotNull(csvmanager);

        //check content read from csv file
        images = (ArrayList<String[]>) csvmanager.readCSVDockerImageList("/DokerBuildImagesCmd.csv", path.getInput());
        assertNotNull(images);
        assertTrue(images.size() > 0);
        assertTrue(images.get(0).length == 2);
        assertTrue(images.get(0)[0].length() > 0);
        assertTrue(images.get(0)[1].length() > 0);

        //check content related to read repository list from csv file
        repositories = (ArrayList<Repository>) csvmanager.readRepositoryListFromFile("/DokerOfficialImages.csv");
        assertNotNull(repositories);
        assertTrue(repositories.size() > 0);
        assertNotNull(repositories.get(0));
        assertNotNull(repositories.get(repositories.size() / 2));
        assertNotNull(repositories.get(repositories.size() - 1));
        assertTrue(repositories.get(0).getName().length() > 0);
        assertTrue(repositories.get(0).getLink().length() > 0);

        //read merged file from ten csv and check the content
        dockerImages = (ArrayList<DockerImage>) csvmanager.readTenBuildingCSVandMergeintoOne();
        assertNotNull(dockerImages);
        assertTrue(dockerImages.size() > 0);
        assertNotNull(dockerImages.get(0));
        assertNotNull(dockerImages.get(dockerImages.size() / 2));
        assertNotNull(dockerImages.get(dockerImages.size() - 1));
        assertTrue(dockerImages.get(0).getName().length() > 0);
        assertTrue(dockerImages.get(0).getCommand().length() > 0);
        assertTrue(dockerImages.get(0).getNumberOfBuild() == 10);

        float sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = sum + dockerImages.get(0).getBuildingTime()[i];
        }

        assertEquals(dockerImages.get(0).getAverageBuildTime(), sum / 10);

        //check if the file will be write
        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DockerBuildImages_1.csv"));
        csvmanager.writeDockerImagesMultipleBuildResult(dockerImages, "DockerBuildImages_1.csv");
        assertTrue(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DockerBuildImages_1.csv"));
        filemanager.deleteFile(filemanager.getWorkDirectory() + path.getOutput() + "/DockerBuildImages_1.csv");

        DockerImage dockerImage = new DockerImage("example", "sudo docker build -t example .");
        dockerImage.setBuildable(true);
        dockerImage.addOneTimeDockerImageBuild(100);

        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DokerBuildImagesDataSet[prova].csv"));
        csvmanager.writeDockerImageSingleBuildResult(dockerImage, "DokerBuildImagesDataSet[prova].csv");
        assertTrue(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DokerBuildImagesDataSet[prova].csv"));

        filemanager.deleteFile(filemanager.getWorkDirectory() + path.getOutput() + "/DokerBuildImagesDataSet[prova].csv");

        FeaturesEntity featureEntity = new FeaturesEntity("heroguard");
        featureEntity.setFROM(1);
        featureEntity.setRUN(6);
        featureEntity.setMAINTAINER(1);
        featureEntity.setCMD(1);
        featureEntity.setNumberOfFeatures(9);

        FeaturesEntity featureEntity1 = new FeaturesEntity("ImageAnalyzer");
        featureEntity1.setFROM(1);
        featureEntity1.setRUN(8);
        featureEntity1.setARG(3);
        featureEntity1.setENTRYPOINT(1);
        featureEntity1.setVOLUME(1);
        featureEntity1.setCMD(1);
        featureEntity1.setNumberOfFeatures(15);

        assertNotNull(featureEntity);
        assertNotNull(featureEntity1);

        ArrayList<FeaturesEntity> list = new ArrayList<>();
        list.add(featureEntity);
        list.add(featureEntity1);

        assertTrue(list.size() > 0);
        assertEquals(list.size(), 2);
        assertSame(list.size(), 2);

        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DokerImagesFeatures[prova].csv"));
        csvmanager.writeDockerImagesFeaturesFromList(list, filemanager.getWorkDirectory() + path.getOutput() + "/DokerImagesFeatures[prova].csv");
        assertTrue(filemanager.fileExist(filemanager.getWorkDirectory() + path.getOutput() + "/DokerImagesFeatures[prova].csv"));

        ArrayList<FeaturesEntity> features = (ArrayList<FeaturesEntity>) csvmanager.readDockerImagesFeaturesFromFile("DokerImagesFeatures[prova].csv");
        assertTrue(features.size() > 0);
        assertEquals(features.size(), 2);
        assertSame(features.size(), 2);

        assertEquals(featureEntity.toString(), features.get(0).toString());
        assertEquals(featureEntity.getName(), features.get(0).getName());
        assertEquals(featureEntity.getNumberOfFeatures(),features.get(0).getNumberOfFeatures());

        assertEquals(featureEntity1.toString(), features.get(1).toString());
        assertEquals(featureEntity1.getName(), features.get(1).getName());
        assertEquals(featureEntity1.getNumberOfFeatures(),features.get(1).getNumberOfFeatures());

        filemanager.deleteFile(filemanager.getWorkDirectory() + path.getOutput() + "/DokerImagesFeatures[prova].csv");

    }
}
