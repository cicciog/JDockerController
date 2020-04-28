package fileManager;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestPath {
    Path path = new Path();
    
     @Test
    public void testAdd() {

        //check for not null value
        assertNotNull(path);
        
        //check for not null value
        assertNotNull(path.getInput());
        
        //check for not null value
        assertNotNull(path.getOutput());
        
        //check for not null value
        assertNotNull(path.getRepositories());

        //test getInput length method
        assertTrue(path.getInput().length() > 0);
        
        //test getOutput length method
        assertTrue(path.getOutput().length() > 0);
        
        //test getRepositories length method
        assertTrue(path.getRepositories().length() > 0);

    }
}
