
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franecesco-pc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DockerController dockerController = new DockerController();
        System.out.println(
                            "                        ##         .\n" +
                            "                  ## ## ##        ==\n" +
                            "               ## ## ## ## ##    ===\n" +
                            "           /\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\\___/ ===\n" +
                            "      ~~~ {~~ ~~~~ ~~~ ~~~~ ~~~ ~ /  ===- ~~~\n" +
                            "           \\______ o           __/\n" +
                            "             \\    \\         __/\n" +
                            "              \\____\\_______/" );
        try {
            dockerController.checkDockerVersion();
            dockerController.getAllDockerContainers();
            dockerController.getAllDockerImages();
            dockerController.removeAllContainers();
            dockerController.removeAllImages();
            //dockerController.buildDockerImage("covid19italy","C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\GitHubRestAPIclient\\Repositories1\\covid19-italy\\.");
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
