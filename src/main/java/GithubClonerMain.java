
import fileManager.FileManager;
import fileManager.Path;
import gitCloner.GitCloner;
import gitCloner.Repository;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.jgit.api.errors.GitAPIException;

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

        System.out.println(
                "                        ##         .\n"
                + "                  ## ## ##        ==\n"
                + "               ## ## ## ## ##    ===\n"
                + "           /\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\\___/ ===\n"
                + "      ~~~ {~~ ~~~~ ~~~ ~~~~ ~~~ ~ /  ===- ~~~\n"
                + "           \\______ o           __/\n"
                + "             \\    \\         __/\n"
                + "              \\____\\_______/");

        try {
            repositories = csvmanager.readRepositoryListFromFile("DokerOfficialImages.csv");
            System.out.println("Read " + repositories.size() + " repositories\n");

            GitCloner gitCloner = new GitCloner("USERNAME", "PASSWORD");

            int res;
            for (int i = 0; i < repositories.size(); i++) {
                System.out.println(repositories.get(i).toString());
                res = gitCloner.cloneRepository(repositories.get(i));
                System.out.println("Result: " + res + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /*
        CSVmanager csvmanager = new CSVmanager();
        ArrayList<Repository> repositories;

        try {
            repositories = csvmanager.readRepositoryListFromFile("DokerOfficialImages.csv");
            System.out.println("Read " + repositories.size() + " repositories\n");
            
            GitCloner gitCloner = new GitCloner("USERNAME","PASSWORD");
            gitCloner.cloneAllRepository(repositories);
        }catch (IOException | GitAPIException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

}
