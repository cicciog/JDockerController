package dockerController;

import java.sql.Time;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestDockerController {
    
    DockerController dockerController = new DockerController();
    
    @Test
    public void testAdd() throws InterruptedException {
        
        //check if the object is not null
        assertNotNull(dockerController);
        
        int start = dockerController.getStartTime();
        Thread.sleep(1000);
        int end = dockerController.getFinalTime();
        assertTrue(start < (int) (System.currentTimeMillis()/1000));
        assertTrue(end < (int) (System.currentTimeMillis()/1000));
        assertTrue(end > start);
        
    }
    
}
