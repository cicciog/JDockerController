package dockerController;

import fileManager.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestDockerController {

    DockerController dockerController = new DockerController();
    FileManager fileManager = new FileManager();
    String cmd;

    @Test
    public void testAdd() throws InterruptedException, IOException, FileNotFoundException, URISyntaxException {

        //check if the object is not null
        assertNotNull(dockerController);

        //check start and final time
        int start = dockerController.getStartTime();
        Thread.sleep(1000);
        int end = dockerController.getFinalTime();
        assertTrue(start < (int) (System.currentTimeMillis() / 1000));
        assertTrue(end < (int) (System.currentTimeMillis() / 1000));
        assertTrue(end > start);

        //check if the hashmap is not empty
        HashMap hm = new HashMap();
        hm = dockerController.checkDockerVersion();
        assertTrue(hm.size() > 0);
        assertFalse(hm.isEmpty());

        //check if exist this keys
        assertTrue(hm.containsKey("client"));
        assertTrue(hm.containsKey("server"));

        //check if there are built images
        assertTrue(dockerController.getAllDockerImages().size() == 0);
        assertTrue(dockerController.getAllDockerImages().isEmpty());

        //check if there are built containers
        assertTrue(dockerController.getAllDockerContainers().size() == 0);
        assertTrue(dockerController.getAllDockerContainers().isEmpty());
        
        cmd = fileManager.getWorkDirectory()+"/input/example/"+".";
        dockerController.buildDockerImage("example",cmd);
        //check if there are built images
        assertFalse(dockerController.getAllDockerImages().size() == 0);
        assertFalse(dockerController.getAllDockerImages().isEmpty());
        

    }

}
