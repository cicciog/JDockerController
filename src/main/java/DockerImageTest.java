
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

        //mageia
        for (int i = 0; i < 2; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("mageia","/home/francesco/Documenti/repositories/juanluisbaptiste_docker-brew-mageia/build2release/.");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione mageia " + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }
        
        //httpd
        /* for (int i = 0; i < 9; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("httpd","/home/francesco/Documenti/repositories/docker-library_httpd/2.4/.");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione httpd " + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }*/
 /* //busybox
        for (int i = 0; i < 9; i++) {
            dockercontroller.removeAllImages();
            int start1 = dockercontroller.getStartTime();
            dockercontroller.buildDockerImage("busybox","/home/francesco/Documenti/repositories/busybox/musl/.");
            int end1 = dockercontroller.getFinalTime();
            System.out.println("Esecuzione busybox " + (i + 1) + " " + (end1 - start1) + " secondi\n\n");
        }*/
    }

}
