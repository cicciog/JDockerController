package fileManager;

import dockerController.DockerImage;
import gitCloner.Repository;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
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
        images = (ArrayList<String[]>) csvmanager.readCSVDockerImageList("/DokerBuildImagesCmd.csv",path.getInput());
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
        assertNotNull(repositories.get(repositories.size()/2));
        assertNotNull(repositories.get(repositories.size()-1));
        assertTrue(repositories.get(0).getName().length() > 0);
        assertTrue(repositories.get(0).getLink().length() > 0);
        
        //read merged file from ten csv and check the content
        dockerImages = (ArrayList<DockerImage>) csvmanager.readTenBuildingCSVandMergeintoOne();
        assertNotNull(dockerImages);
        assertTrue(dockerImages.size() > 0);
        assertNotNull(dockerImages.get(0));
        assertNotNull(dockerImages.get(dockerImages.size()/2));
        assertNotNull(dockerImages.get(dockerImages.size()-1));
        assertTrue(dockerImages.get(0).getName().length() > 0);
        assertTrue(dockerImages.get(0).getCommand().length() > 0);
        assertTrue(dockerImages.get(0).getNumberOfBuild() == 10);
        
        float sum = 0;
        for(int i = 0; i < 10; i++){
            sum = sum + dockerImages.get(0).getBuildingTime()[i];
        }
       
        assertEquals(dockerImages.get(0).getAverageBuildTime(),sum/10);
        
        //check if the file will be write
        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory()+path.getOutput()+"/DockerBuildImages_1.csv"));
        csvmanager.writeDockerImagesMultipleBuildResult(dockerImages,"DockerBuildImages_1.csv");
        assertTrue(filemanager.fileExist(filemanager.getWorkDirectory()+path.getOutput()+"/DockerBuildImages_1.csv"));
        filemanager.deleteFile(filemanager.getWorkDirectory()+path.getOutput()+"/DockerBuildImages_1.csv");
        
        DockerImage dockerImage = new DockerImage("example","sudo docker build -t example .");
        dockerImage.setBuildable(true);
        dockerImage.addOneTimeDockerImageBuild(100);
        
        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory()+path.getOutput()+"/DokerBuildImagesDataSet[prova].csv"));
        csvmanager.writeDockerImageSingleBuildResult(dockerImage,"DokerBuildImagesDataSet[prova].csv");
        assertTrue(filemanager.fileExist(filemanager.getWorkDirectory()+path.getOutput()+"/DokerBuildImagesDataSet[prova].csv"));
        
        filemanager.deleteFile(filemanager.getWorkDirectory()+path.getOutput()+"/DokerBuildImagesDataSet[prova].csv.csv");
        
        
    }
}
