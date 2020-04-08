package fileManager;

import java.io.File;

/**
 *
 * @author Franecesco-pc
 */
public class FileManager {

    public FileManager() {
    }
    
    
    public boolean fileExist (String pPath) {
        boolean exist = false;

        File file = new File(pPath);

        if (file.exists())
            exist = true;

        return exist;
    }
    
    public File createDirectory (String pPath) {
        File directory = new File(pPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        return directory;
    }
    
    public void deleteFolder(File pFolder) {
        File[] files = pFolder.listFiles();
        if (files!=null) {
            for (File f: files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        pFolder.delete();
    }

    public void deleteFile (String pPath) {
        File fileToDelete = new File(pPath);

        if (fileToDelete.isFile()) {
            fileToDelete.delete();
        }
    }
    
    public String getWorkdirectory(){
        return System.getProperty("user.dir");
    }
    
    public String[] getFileListInADirectory(String pSourceDirectory){
        
      //Creating a File object for directory
      File directoryPath = new File(pSourceDirectory);
      //List of all files and directories
      String contents[] = directoryPath.list();
     
      return contents;
    }
}
