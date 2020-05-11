
import dockerController.DockerController;
import java.io.IOException;

/**
 *
 * @author cicciog
 */
public class DockerImageTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        DockerController dockercontroller = new DockerController();

        //Jetty
        for (int i = 0; i < 10; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("mongo","/home/francesco/Documenti/repositories/docker-library_mongo/3.6/.");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione mongo" + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }
        
      
    }

}
