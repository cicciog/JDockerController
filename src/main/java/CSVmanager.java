
import com.opencsv.CSVReader;
import fileManager.FileManager;
import fileManager.Path;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Franecesco-pc
 */
public class CSVmanager {
    

    public CSVmanager() {
    }
    
    public void readCSVDockerImageList() throws FileNotFoundException, URISyntaxException, IOException{
        Path inputFile = new Path();
        FileManager fileMangager = new FileManager();
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader(fileMangager.getWorkdirectory()+inputFile.getInput()+"\\DokerBuildImagesCmd.csv"), ',', '"', 1);
       
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
       
        //Read CSV line by line and use the string array as you want
        for(String[] row : allRows){
         System.out.println("NAME: "+row[0]+" COMMAND: "+row[1]);
        }
    }
    
    public void writeDockerImagesBuildResults(){}
    
}
