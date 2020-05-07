
import featuresExtractor.FeaturesEntity;
import fileManager.FileManager;
import fileManager.Path;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
        String features[] = {"FROM",
            "LABEL",
            "ENV",
            "RUN",
            "VOLUME",
            "COPY",
            "ENTRYPOINT",
            "CMD",
            "WORKDIR",
            "USER",
            "EXPOSE",
            "MAINTAINER",
            "ARG",
            "STOPSIGNAL",
            "ADD",
            "SHELL"};
        List<String> listOfFeatures = Arrays.asList(features);
        List<FeaturesEntity> listOfFiles = new ArrayList<>();
        FeaturesEntity featuresEntity;

        String[] jsonFiles = fileManager.getFileListInADirectory(fileManager.getWorkDirectory() + path.getInput() + "/json");

        int count = 0;
        while (count < jsonFiles.length) {
            System.out.println("\n" + jsonFiles[count] + "\n");

            JSONParser parser = new JSONParser();
            featuresEntity = new FeaturesEntity(jsonFiles[count]);

            try (Reader reader = new FileReader(fileManager.getWorkDirectory() + path.getInput() + "/json/" + jsonFiles[count])) {

                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                //System.out.println(jsonObject);

                // loop array
                JSONArray docker = (JSONArray) jsonObject.get("docker");
                for (int i = 0; i < docker.size(); i++) {
                    JSONObject image = (JSONObject) docker.get(i);

                    if (listOfFeatures.contains(image.get("name"))) {
                        featuresEntity.addFeature(image.get("name").toString().trim());
                    }
                }

            } catch (IOException | ParseException e) {
                System.out.println(e.getMessage());
            }

            listOfFiles.add(featuresEntity);
            count++;
        }

        // Iterator to traverse the list 
        Iterator iterator = listOfFiles.iterator();

        System.out.println(
                "Features : ");

        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + " ");
            System.out.println();
        }

    }

}
