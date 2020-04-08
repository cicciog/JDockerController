
import fileManager.FileManager;
import fileManager.Path;
import java.io.IOException;
import java.net.URISyntaxException;
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
        System.out.println("Work directory: "+fm.getWorkdirectory()+path.getInput());
        String[] repos = fm.getFileListInADirectory(path.getRepositories());
        for(int i = 0; i < repos.length; i++){
            DockerImage di = new DockerImage(repos[i],"docker build -t "+repos[i]+":latest .");
            di.addOneTimeDockerImageBuild(80);
            di.addOneTimeDockerImageBuild(69);
            di.addOneTimeDockerImageBuild(102);
            di.addOneTimeDockerImageBuild(94);
            System.out.println(di.toString());
        }
        
        CSVmanager csvmanager = new CSVmanager();
        try {
            csvmanager.readCSVDockerImageList();
        } catch (URISyntaxException | IOException ex) {
            System.out.println(ex.getMessage());
        } 
             
    }
    
}
