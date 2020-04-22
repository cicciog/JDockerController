
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import fileManager.FileManager;
import fileManager.Path;
import gitCloner.Repository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.apache.commons.lang3.StringUtils.split;

/**
 *
 * @author cicciog
 */
public class CSVmanager {

    private String csvFile = "C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\GitHubRestAPIclient\\src\\main\\java\\input\\DokerOfficialImages.csv";
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";

    public CSVmanager() {
    }

    public Collection<String[]> readCSVDockerImageList() throws FileNotFoundException, URISyntaxException, IOException {

        Path inputFile = new Path();
        FileManager fileMangager = new FileManager();
        ArrayList<String[]> rawDockerList = new ArrayList<>();
        String dockerBuildImagesCmdsCSVfile = fileMangager.getWorkdirectory()
                + inputFile.getInput()
                + "/DokerBuildImagesCmd.csv";

        //Build reader instance
        FileReader filereader = new FileReader(dockerBuildImagesCmdsCSVfile);
        CSVReader reader = new CSVReader(filereader, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, '\0');

        //Read all rows at once
        List<String[]> allRows = reader.readAll();

        //Read CSV line by line and use the string array as you want
        for (String[] row : allRows) {
            rawDockerList.add(row);
        }
        return rawDockerList;
    }

    public void writeDockerImagesBuildResults(DockerImage pDockerImage) throws IOException {
        Path path = new Path();
        FileManager fileManager = new FileManager();
        String dataCSV = "DokerBuildImagesDataSet.csv";
        File file = null;
        CSVWriter writer;

        if (!fileManager.fileExist(path.getOutput() + "//" + dataCSV)) {
            file = new File(path.getOutput().concat("//" + dataCSV));
        }

        // create a List which contains String array 
        List<String[]> data = new ArrayList<>();
        writer = new CSVWriter(new FileWriter(dataCSV, true), ',');

        String[] record = {pDockerImage.getName(),
            pDockerImage.getCommand(),
            Integer.toString(pDockerImage.getNumberOfBuild()),
            String.valueOf(pDockerImage.isBuildable()),
            Integer.toString(pDockerImage.getBuildingTime()[0]),
            /*Integer.toString(pDockerImage.getBuildingTime()[1]),
                Integer.toString(pDockerImage.getBuildingTime()[2]),
                Integer.toString(pDockerImage.getBuildingTime()[3]),
                Integer.toString(pDockerImage.getBuildingTime()[4]),
                Integer.toString(pDockerImage.getBuildingTime()[5]),
                Integer.toString(pDockerImage.getBuildingTime()[6]),
                Integer.toString(pDockerImage.getBuildingTime()[7]),
                Integer.toString(pDockerImage.getBuildingTime()[8]),
                Integer.toString(pDockerImage.getBuildingTime()[9]),*/
            String.valueOf(pDockerImage.getAverageBuildTime())};
        data.add(record);

        writer.writeNext(record);

        // closing writer connection 
        writer.close();
    }

    public ArrayList<Repository> readRepositoryListFromFile() throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(csvFile));
        ArrayList<Repository> repositoryList = new ArrayList<>();
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] GitHubRepository = line.split(cvsSplitBy);

            Repository repository = new Repository();
            repository.setName(GitHubRepository[0]);
            repository.setLink(GitHubRepository[1]);

            repositoryList.add(repository);
        }

        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return repositoryList;
    }

}
