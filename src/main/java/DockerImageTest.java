
import fileManager.FileManager;
import fileManager.Path;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franecesco-pc
 */
public class DockerImageTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileManager fm = new FileManager();
        Path path = new Path();
        ArrayList<DockerImage> dockerImageList = new ArrayList<>();

        CSVmanager csvmanager = new CSVmanager();
        ArrayList<String[]> dockerBuildCMDS;

        try {
            dockerBuildCMDS = (ArrayList<String[]>) csvmanager.readCSVDockerImageList();
            for (int i = 0; i < dockerBuildCMDS.size(); i++) {
                DockerImage dockerImage = new DockerImage(dockerBuildCMDS.get(i)[0],dockerBuildCMDS.get(i)[1]);
                dockerImageList.add(dockerImage);
                System.out.println(dockerImage.toString());
            }
        } catch (URISyntaxException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
