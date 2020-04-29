package gitCloner;

import fileManager.CSVmanager;
import fileManager.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;
import org.eclipse.jgit.api.errors.GitAPIException;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestGitCloner {

    GitCloner gitcloner = new GitCloner("USERNAME","PASSWORD");
    FileManager filemanager = new FileManager();
    CSVmanager csvmanager = new CSVmanager();
    ArrayList<Repository> repositories;
    int res;
    boolean isEmpty = true;
    int numberOfElementsAtStart;
    int numberOfElementsAtEnd;

    @Test
    public void testAdd() throws IOException, GitAPIException {

        //check if the object is not null
        assertNotNull(gitcloner);

        //check if the root repositories was correctly inizialized
        assertTrue(gitcloner.initilizedRepositoriesRoot());
            
        //check if the cloning operation success or fail
        isEmpty = filemanager.checkIfAdirectoryIsEmpty(filemanager.getWorkDirectory()+"/repositories");
        repositories = csvmanager.readRepositoryListFromFile("DokerOfficialImages.csv");
        res = gitcloner.cloneRepository(repositories.get(0));
        assertTrue(res > 0);
        assertNotEquals(isEmpty,filemanager.checkIfAdirectoryIsEmpty(filemanager.getWorkDirectory()+"/repositories"));
        assertNotSame(isEmpty,filemanager.checkIfAdirectoryIsEmpty(filemanager.getWorkDirectory()+"/repositories"));
        
        //check if the method clone in pipeline
        numberOfElementsAtStart = filemanager.getFileListInADirectory(filemanager.getWorkDirectory()+"/repositories").length;
        
        ArrayList<Repository> smallRepositoryList = new ArrayList<>();
        smallRepositoryList.add(repositories.get(1));
        smallRepositoryList.add(repositories.get(2));
        smallRepositoryList.add(repositories.get(3));
        smallRepositoryList.add(repositories.get(4));
        smallRepositoryList.add(repositories.get(5));
        
        gitcloner.cloneAllRepository(smallRepositoryList);
        
        numberOfElementsAtEnd = filemanager.getFileListInADirectory(filemanager.getWorkDirectory()+"/repositories").length;
        
        assertTrue(numberOfElementsAtEnd > numberOfElementsAtStart);
        assertTrue((numberOfElementsAtEnd - numberOfElementsAtStart) == 5);
        
        //remove repositories folder from working directory
        File repositories = new File(filemanager.getWorkDirectory()+"/repositories");
        filemanager.deleteFolder(repositories);
        assertFalse(filemanager.fileExist(filemanager.getWorkDirectory()+"/repositories"));
    }
}
