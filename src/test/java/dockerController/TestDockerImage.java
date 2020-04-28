package dockerController;


import dockerController.DockerImage;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestDockerImage {
    
    DockerImage dockerimage = new DockerImage("php", "sudo build -t php .");
    
    @Test
    public void testAdd() {

        //test data
        dockerimage.setBuildable(true);
        dockerimage.addOneTimeDockerImageBuild(100);
        dockerimage.addOneTimeDockerImageBuild(150);
        dockerimage.addOneTimeDockerImageBuild(200);

        //check for equality
        assertEquals("php", dockerimage.getName());

        assertEquals("sudo build -t php .", dockerimage.getCommand());

        assertEquals((100 + 150 + 200) / (float) 3.0, dockerimage.getAverageBuildTime());

        assertEquals(3, dockerimage.getNumberOfBuild());

        //check for false condition
        assertFalse(dockerimage.getNumberOfBuild() > 10);

        assertFalse(dockerimage.getNumberOfBuild() > 3);

        int before = dockerimage.getNumberOfBuild();
        assertEquals(before, dockerimage.getNumberOfBuild());

        dockerimage.addOneTimeDockerImageBuild(300);
        int after = dockerimage.getNumberOfBuild();
        assertEquals(after, dockerimage.getNumberOfBuild());

        assertTrue(after > before);

        //check for not null value
        assertNotNull(dockerimage);
    }
}
