
import fileManager.FileManager;
import fileManager.Path;
import gitCloner.GitCloner;
import gitCloner.Repository;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author francesco
 */
public class GithubClonerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        Path path = new Path();

        CSVmanager csvmanager = new CSVmanager();
        ArrayList<Repository> repositories;

        try {
            repositories = csvmanager.readRepositoryListFromFile("DokerOfficialImages.csv");
            System.out.println("Read " + repositories.size() + " repositories\n");
            
            GitCloner gitCloner = new GitCloner("USERNAME","PASSWORD");
            
            int res;
            for (int i = 0; i < 3; i++) {
                System.out.println(repositories.get(i).toString());
                res = gitCloner.cloneRepository(repositories.get(i));
                System.out.println("Result: "+res+"\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
