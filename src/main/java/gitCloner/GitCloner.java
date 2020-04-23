package gitCloner;

import fileManager.FileManager;
import fileManager.Path;
import java.io.File;
import java.util.ArrayList;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 *
 * @author Franecesco-pc
 */
public class GitCloner {

    private static final int ROOT_NOT_EXIST = -1;
    private static final int ALREADY_CLONED = 0;
    private static final int CLONED = 1;
    private static final int CLONING_ERROR = -2;
    private String destination = null;
    private String localPath = null;
    private String username;
    private String password;

    public GitCloner(String pUsername, String pPassword) {
        this.username = pUsername;
        this.password = pPassword;
    }

    public void cloneAllRepository(ArrayList<Repository> pRepositories) throws GitAPIException {

        FileManager fileManager = new FileManager();
        
        if (initilizedRepositoriesRoot()) {
            for (Repository repository : pRepositories) {
                System.out.println(repository.getName());
                setLocalPATH(repository.getName());

                File localFile = new File(getLocalPATH());

                Git.cloneRepository()
                        .setURI(repository.getLink())
                        .setDirectory(localFile)
                        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(this.username, this.password))
                        .call();

                System.out.println(repository.getLink() + "... [Cloned]");
            }
        }

    }

    public int cloneRepository(Repository pRepository) {
        //check if a directory already exist
        FileManager fileManager = new FileManager();
        int result = ROOT_NOT_EXIST;

        if (initilizedRepositoriesRoot()) {

            setLocalPATH(pRepository.getName());
            System.out.println("local " + getLocalPATH());

            if (!fileManager.fileExist(getLocalPATH())) {
                try {
                    //make clone             
                    File localFile = new File(getLocalPATH());
                    Git.cloneRepository()
                            .setURI(pRepository.getLink())
                            .setDirectory(localFile)
                            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(this.username, this.password))
                            .call();

                } catch (GitAPIException ex) {
                    result = CLONING_ERROR;
                }

                result = CLONED;

            } else {
                result = ALREADY_CLONED;
            }
        }
        return result;
    }

    private void setLocalPATH(String pName) {

        this.localPath = getDestinationPATH()
                + "/"
                + pName.replace("/", "_");
    }

    private String getLocalPATH() {
        return this.localPath;
    }

    private void setDestinationPATH() {
        Path path = new Path();
        FileManager fm = new FileManager();
        this.destination = fm.getWorkDirectory() + path.getRepositories();
    }

    private String getDestinationPATH() {
        if (this.destination == null) {
            setDestinationPATH();
        }
        return this.destination;
    }

    public boolean initilizedRepositoriesRoot() {
        boolean alreadyExist = false;
        FileManager fileManager = new FileManager();

        if (!fileManager.fileExist(getDestinationPATH())) {
            fileManager.createDirectory(getDestinationPATH());
            alreadyExist = fileManager.fileExist(getDestinationPATH());
        } else {
            alreadyExist = true;
        }

        return alreadyExist;
    }

}
