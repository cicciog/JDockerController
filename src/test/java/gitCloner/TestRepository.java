package gitCloner;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestRepository {

    @Test
    public void testAdd() {

        //test data
        Repository repository = new Repository();
        String link = "/home/Documenti/repositories/";
        String name = "repos";
        
        //check for not null value
        assertNotNull(repository);
        
        //test setLink method
        assertNull(repository.getLink());
        
        repository.setLink(link);
        assertEquals(link,repository.getLink());
        
         //test setName method
        assertNull(repository.getName());
        
        repository.setName(name);
        assertEquals(name,repository.getName());
       
        
    }
}
