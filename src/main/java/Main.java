
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cicciog
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Hashmap to map CSV data to  
        // Bean attributes. 
        Map<String, String> mapping = new HashMap<String, String>();

        mapping.put("name", "Name");
        mapping.put("buildcommand", "BuildCommand");
        mapping.put("numberofexecution", "NumberOfExecution");
        mapping.put("buildable", "Buildable");
        mapping.put("build", "Build");
        mapping.put("average", "Average");

        // HeaderColumnNameTranslateMappingStrategy 
        // for DockerImageBuild class 
        HeaderColumnNameTranslateMappingStrategy<javaBean> strategy
                = new HeaderColumnNameTranslateMappingStrategy<javaBean>();
        strategy.setType(javaBean.class);
        strategy.setColumnMapping(mapping);

        // Create castobaen and csvreader object 
        try {
            CSVReader csvReader = new CSVReader(new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/ciccio1.csv"));
            CsvToBean csvToBean = new CsvToBean();

            // call the parse method of CsvToBean 
            // pass strategy, csvReader to parse method 
            List<javaBean> list = csvToBean.parse(strategy, csvReader);

            // print details of Bean object 
            for (javaBean e : list) {
                System.out.println(e);
            }

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch block 
            e.printStackTrace();
        }

    }

}
