
import fileManager.FileManager;
import fileManager.Path;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cicciog
 */
public class FeatureExtractor {

    private FileManager fileManager = new FileManager();
    private Path path = new Path();
    private String features[] = {"FROM",
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
    private List<String> listOfFeatures = Arrays.asList(features);
    private FeaturesEntity featuresEntity;

    public FeaturesEntity extractAllImageFeatures(String pSource) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        featuresEntity = new FeaturesEntity(pSource);

        Reader reader = new FileReader(fileManager.getWorkDirectory() + path.getInput() + "/json/" + pSource);

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

        return featuresEntity;
    }

    public Collection<FeaturesEntity> extrcatAllFeaturesOfDockerImagesCollection(String pSource) throws FilesNotFoundException, FileNotFoundException, IOException, ParseException {
        
        List<FeaturesEntity> listOfFiles;
        String[] jsonFiles = fileManager.getFileListInADirectory(fileManager.getWorkDirectory()
                + path.getInput()
                + "/json");

        if (jsonFiles.length > 0) {
            listOfFiles = new ArrayList<>();

            int count = 0;
            while (count < jsonFiles.length) {
                System.out.println("\n" + jsonFiles[count] + "\n");

                JSONParser parser = new JSONParser();
                featuresEntity = new FeaturesEntity(jsonFiles[count]);

                Reader reader = new FileReader(fileManager.getWorkDirectory()
                        + path.getInput()
                        + "/json/"
                        + jsonFiles[count]);

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

                listOfFiles.add(featuresEntity);
                count++;
            }

        } else {
            throw new FilesNotFoundException("Files not found in Json directory");
        }

        return listOfFiles;
    }

}
