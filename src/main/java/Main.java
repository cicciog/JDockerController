
import dockerController.DockerImage;
import fileManager.CSVmanager;
import java.util.ArrayList;


/**
 *
 * @author cicciog
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSVmanager csvmanager = new CSVmanager();
        ArrayList<DockerImage> dockerImageList = (ArrayList<DockerImage>) csvmanager.readTenBuildingCSVandMergeintoOne();
        
        for(DockerImage di: dockerImageList){
            //csvmanager.
        }

    }

}
