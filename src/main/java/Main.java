import fileManager.Path;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cicciog
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
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
            DockerController dockerController = new DockerController();
            dockerController.checkDockerVersion();
            System.out.println(dockerController.getFianlTime());
            DockerImage di = new DockerImage("PippoPlante","build -t pippoplante .");
            CSVmanager csvmanager = new CSVmanager();
            csvmanager.writeDockerImagesBuildResults(di);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
