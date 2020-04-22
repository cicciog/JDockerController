package gitCloner;

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
    private String destination = "./Repositories/";
    private File localPath;
    private String username;
    private String password;
    
    public GitCloner(String pUsername, String pPassword){
        this.username = pUsername;
        this.password = pPassword;
    }
    
    public void cloneAllRepository(ArrayList<Repository> pRepositories) throws GitAPIException{
        
        for(Repository repository:pRepositories){
            System.out.println(repository.getName());
            
            localPath = new File(destination+repository.getName().replace("/","_"));
            
            Git.cloneRepository()
                .setURI(repository.getLink())
                .setDirectory(localPath)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(this.username,this.password))
                .call();
  
                System.out.println(repository.getLink()+"... [Cloned]");
            }
    }
}
