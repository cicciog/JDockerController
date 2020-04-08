
import fileManager.FileManager;
import fileManager.Path;

/**
 *
 * @author Franecesco-pc
 */
public class DockerImageTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DockerImage di = new DockerImage("php7.3","build -t php7.3:latest .");
        di.addOneTimeDockerImageBuild(80);
        di.addOneTimeDockerImageBuild(95);
        di.addOneTimeDockerImageBuild(101);
        di.addOneTimeDockerImageBuild(73);
        di.addOneTimeDockerImageBuild(87);
        di.addOneTimeDockerImageBuild(98);
        di.addOneTimeDockerImageBuild(104);
        di.addOneTimeDockerImageBuild(77);
        di.addOneTimeDockerImageBuild(88);
        di.addOneTimeDockerImageBuild(63);
        
        DockerImage di1 = new DockerImage("mysql5.7","build -t mysql5.7:latest .");
        di1.addOneTimeDockerImageBuild(303);
        di1.addOneTimeDockerImageBuild(295);
        di1.addOneTimeDockerImageBuild(301);
        di1.addOneTimeDockerImageBuild(273);
        di1.addOneTimeDockerImageBuild(287);
        di1.addOneTimeDockerImageBuild(298);
        di1.addOneTimeDockerImageBuild(344);
        di1.addOneTimeDockerImageBuild(277);
        di1.addOneTimeDockerImageBuild(288);
        di1.addOneTimeDockerImageBuild(363);
        
        System.out.println(di.toString());
        System.out.println(di1.toString());
        
        
        Path fm = new Path();
        System.out.println("Work directory: "+fm.getWorkdirectory());
        fm.getFileListInADirectory(fm.getWorkdirectory());
        
    }
    
}
