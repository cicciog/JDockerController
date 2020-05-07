
import featuresExtractor.FeatureExtractor;
import featuresExtractor.FeaturesEntity;
import featuresExtractor.FilesNotFoundException;
import fileManager.CSVmanager;
import fileManager.FileManager;
import fileManager.Path;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author francesco
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        Path path = new Path();
        FeatureExtractor featureextractor = new FeatureExtractor();
        ArrayList<FeaturesEntity> list;
        CSVmanager csvmanager = new CSVmanager();
        
        try {

          //list = (ArrayList<FeaturesEntity>) featureextractor.extrcatAllFeaturesOfDockerImagesCollection(fileManager.getWorkDirectory()+path.getInput()+"/json/");
          //csvmanager.writeDockerImagesFeaturesFromList(list,fileManager.getWorkDirectory()+path.getOutput()+"/DockerImagesFeatures.csv");
          list = (ArrayList<FeaturesEntity>) csvmanager.readDockerImagesFeaturesFromFile("DockerImagesFeatures.csv");
          for(FeaturesEntity item : list){
              System.out.println(item.toString());
          }
        } catch (IOException  ex) {
            System.out.println(ex.getMessage());
        }
       
    }

}
