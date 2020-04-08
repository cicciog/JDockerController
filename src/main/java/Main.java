
import fileManager.Path;
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
            Path path = new Path();
            dockerController.checkDockerVersion();
            //dockerController.buildDockerImage("covid19italy","C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\GitHubRestAPIclient\\Repositories1\\covid19-italy\\.");
            dockerController.buildDockerImageByCommand("apache_couchdb-docker","docker build -t apache_couchdb "+path.getRepositories()+"\\apache_couchdb-docker\\3.0.0\\.");
            dockerController.getAllDockerContainers();
            dockerController.getAllDockerImages();
            dockerController.removeAllContainers();
            dockerController.removeAllImages();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
