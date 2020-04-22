import fileManager.FileManager;
import fileManager.Path;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author cicciog
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
                DockerImage dockerImage = new DockerImage(dockerBuildCMDS.get(i)[0], dockerBuildCMDS.get(i)[1]);
                dockerImageList.add(dockerImage);
            }
        } catch (URISyntaxException | IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            //for (int i = 0; i < dockerImageList.size(); i++) {
            for (int i = 0; i < 1; i++) {
                    
                    DockerController dockerControler = new DockerController();
                    String cmd = dockerControler.normalizeCommand(dockerImageList.get(i));
                   
                    int start = dockerControler.getStartTime();
                    int exitValue = dockerControler.buildDockerImageByCommand(dockerImageList.get(i).getName(),"sudo "+cmd);
                    int end = dockerControler.getFianlTime();
                    
                    if (exitValue != 0) {
                        dockerImageList.get(i).setBuildable(false);
                    } 
                    
                    System.out.println("Write: "+dockerImageList.get(i).getName()+" [COMPLETED]");
                    dockerImageList.get(i).addOneTimeDockerImageBuild((end - start));                    
                    csvmanager.writeDockerImagesBuildResults(dockerImageList.get(i));

            }

        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < dockerImageList.size(); i++) {
            System.out.println(dockerImageList.get(i).toString());
        }
    }

}
