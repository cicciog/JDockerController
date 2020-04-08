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
        
        System.out.println(di.toString());
    }
    
}
