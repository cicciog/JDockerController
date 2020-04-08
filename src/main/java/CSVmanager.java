
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
import java.util.Collection;
import java.util.List;


/**
 *
 * @author Franecesco-pc
 */
public class CSVmanager {
    

    public CSVmanager() {
    }
    
    public Collection<String[]> readCSVDockerImageList() throws FileNotFoundException, URISyntaxException, IOException{
        
        Path inputFile = new Path();
        FileManager fileMangager = new FileManager();
        ArrayList<String[]> rawDockerList = new ArrayList<>();
        String dockerBuildImagesCmdsCSVfile = fileMangager.getWorkdirectory()+
                                                           inputFile.getInput()+
                                                           "\\DokerBuildImagesCmd.csv";
                
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader(dockerBuildImagesCmdsCSVfile), ',', '"', 1);
       
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
       
        //Read CSV line by line and use the string array as you want
        for(String[] row : allRows){
            rawDockerList.add(row);
        }
        return rawDockerList;
    }
    
    public void writeDockerImagesBuildResults(){}
    
}
