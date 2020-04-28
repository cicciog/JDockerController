package dockerController;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestDockerImageBuild {

    DockerImageBuild dockerImageBuild = new DockerImageBuild();
    String name = "nginx";
    String buildCommand = "sudo docker build -t ningx .";
    int numberOfExecution = 10;
    String buildable = "true";

    @Test
    public void testAdd() {

        //check for not null value
        assertNotNull(dockerImageBuild);

        //check for null value
        assertNull(dockerImageBuild.getName());

        //check for null value
        assertNull(dockerImageBuild.getBuildCommand());

        //check for null value
        assertNull(dockerImageBuild.getNumberOfExecution());

        //check for null value
        assertNull(dockerImageBuild.getBuildable());

        //check for null value
        assertNull(dockerImageBuild.getAverage());

        //check for null value
        assertNull(dockerImageBuild.getBuild());

        dockerImageBuild.setName(name);
        //check for not null value
        assertNotNull(dockerImageBuild.getName());
        //test getName length method
        assertTrue(dockerImageBuild.getName().length() > 0);

        dockerImageBuild.setBuildCommand(buildCommand);
        //check for not null value
        assertNotNull(dockerImageBuild.getBuildCommand());
        //test getBuildCommand length method
        assertTrue(dockerImageBuild.getBuildCommand().length() > 0);

        dockerImageBuild.setNumberOfExecution(Integer.toString(numberOfExecution));
        //check for not null value
        assertNotNull(dockerImageBuild.getNumberOfExecution());
        //test getNumberOfExecution length method
        assertTrue(dockerImageBuild.getNumberOfExecution().length() > 0);

        dockerImageBuild.setBuildable(buildable);
        //check for not null value
        assertNotNull(dockerImageBuild.getBuildable());
        //test getBuildable length method
        assertTrue(dockerImageBuild.getBuildable().length() > 0);

    }

}
