
import fileManager.FileManager;
import fileManager.Path;
import gitCloner.GitCloner;
import gitCloner.Repository;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francesco
 */
public class GithubCloner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FileManager fileManager = new FileManager();
        Path path = new Path();
        
        System.out.println("Working directory: "
                + fileManager.getWorkDirectory()
                + path.getInput());
        
       CSVmanager csvManager = new CSVmanager();
       ArrayList<Repository> repositories = null;
       
          
       try {
            repositories = csvManager.readRepositoryListFromFile("DokerOfficialImages.csv");
            System.out.println("Read "+repositories.size()+" repositories");
            
            for(Repository repository:repositories){
                System.out.println(repository.toString());
            }
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
       
     
       
    }

}
