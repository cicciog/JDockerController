package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author cicciog
 */
public class TestFileManager {

    FileManager fileManager = new FileManager();
    String test = "/testdirectory";
    String filename = "file";
    String[] files;

    @Test
    public void testAdd() throws FileNotFoundException, IOException {

        //check for not null value
        assertNotNull(fileManager);

        //check if a working directory path is not empty
        assertTrue(fileManager.getWorkDirectory().length() > 0);

        File file = fileManager.createDirectory(fileManager.getWorkDirectory() + test);
        //check for not null value
        assertNotNull(file);

        //check if the source directory is empty
        assertEquals(fileManager.checkIfAdirectoryIsEmpty(fileManager.getWorkDirectory() + test), true);

        //create files in a directory and check files list
        File file1 = new File(fileManager.getWorkDirectory() + test + "/" + filename + 1 + ".txt");
        File file2 = new File(fileManager.getWorkDirectory() + test + "/" + filename + 2 + ".txt");
        File file3 = new File(fileManager.getWorkDirectory() + test + "/" + filename + 3 + ".txt");
        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
        files = fileManager.getFileListInADirectory(fileManager.getWorkDirectory() + test);
        assertTrue(files.length > 0);

        //delete files and after check if they exist or not
        fileManager.deleteFile(file1.getAbsolutePath());
        fileManager.deleteFile(file2.getAbsolutePath());
        fileManager.deleteFile(file3.getAbsolutePath());
        assertFalse(fileManager.fileExist(file1.getAbsolutePath()));
        assertFalse(fileManager.fileExist(file2.getAbsolutePath()));
        assertFalse(fileManager.fileExist(file3.getAbsolutePath()));

        //check if the delete is correct
        boolean before = fileManager.fileExist(fileManager.getWorkDirectory() + test);
        assertEquals(true, before);

        fileManager.deleteFolder(file);
        boolean after = fileManager.fileExist(fileManager.getWorkDirectory() + test);
        assertNotSame(after, before);
        assertNotEquals(after, before);
    }
}
