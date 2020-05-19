
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

        //haskell
        for (int i = 0; i < 10; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("haskell","/home/francesco/Documenti/repositories/haskell_docker-haskell/7.8");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione haskell" + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }
        
        
       //bash devel
        for (int i = 0; i < 10; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("bash","/home/francesco/Documenti/repositories/tianon_docker-bash/devel");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione bash " + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }         
      
    }

}
