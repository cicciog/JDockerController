
import dockerController.DockerImage;
import fileManager.CSVmanager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cicciog
 */
public class CreateDockerMultipleBuildDataset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSVmanager csvmanager = new CSVmanager();
        ArrayList<DockerImage> dockerImageList = (ArrayList<DockerImage>) csvmanager.readTenBuildingCSVandMergeintoOne();

        try {
            csvmanager.writeDockerImagesMultipleBuildResult(dockerImageList);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
