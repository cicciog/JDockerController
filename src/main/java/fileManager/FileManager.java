package fileManager;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Franecesco-pc
 */
public class FileManager {

    private static final boolean IS_EMPTY = true;
    private static final boolean IS_NOT_EMPTY = false;

    public FileManager() {}

    public boolean checkIfAdirectoryIsEmpty(String pSource) throws FileNotFoundException {
        File file = new File(pSource);

        if (file.isDirectory()) {
            if (file.list().length > 0) {
                return IS_NOT_EMPTY;
            } else {
                return IS_EMPTY;
            }
        } else {
            throw new FileNotFoundException("This is not a directory");
        }
    }

    public boolean fileExist(String pPath) {
        boolean exist = false;

        File file = new File(pPath);

        if (file.exists()) {
            exist = true;
        }

        return exist;
    }

    public File createDirectory(String pPath) {
        File directory = new File(pPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        return directory;
    }

    public void deleteFolder(File pFolder) {
        File[] files = pFolder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        pFolder.delete();
    }

    public void deleteFile(String pPath) {
        File fileToDelete = new File(pPath);

        if (fileToDelete.isFile()) {
            fileToDelete.delete();
        }
    }

     public String getWorkDirectory(){
         String path = System.getProperty("user.dir");
         return path; 
    }
     
    public String[] getFileListInADirectory(String pSourceDirectory) {

        //Creating a File object for directory
        File directoryPath = new File(pSourceDirectory);
        //List of all files and directories
        String contents[] = directoryPath.list();

        return contents;
    }
}
