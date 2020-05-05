
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

        //convertigo
        for (int i = 0; i < 9; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("convertigo", "/home/francesco/Documenti/repositories/convertigo_docker/7.5/7.5.7/slim/.");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione convertigo " + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }
        
        //cassandra
        for (int i = 0; i < 9; i++) {
            dockercontroller.removeAllImages();
            int start2 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("cassandra", "/home/francesco/Documenti/repositories/docker-library_cassandra/3.11/.");
            int end2 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione cassandra " + (i + 1) + " " + (end2 - start2) + " secondi\n\n");
        }
        
        //django
        for (int i = 0; i < 9; i++) {
            dockercontroller.removeAllImages();
            int start3 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("django", "/home/francesco/Documenti/repositories/docker-library_django/3.4/onbuild/.");
            int end3 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione django " + (i + 1) + " " + (end3 - start3) + " secondi\n\n");
        }
        
    }

}
