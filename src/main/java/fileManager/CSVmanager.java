package fileManager;

import dockerController.DockerImageBuild;
import dockerController.DockerImage;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cicciog
 */
public class CSVmanager {

    private FileManager fileManager;
    private Path path;
    private String line = "";
    private String cvsSplitBy = ",";

    public CSVmanager() {
        fileManager = new FileManager();
    }

    public Collection<String[]> readCSVDockerImageList(String pDockerImageListFile) throws FileNotFoundException, URISyntaxException, IOException {

        path = new Path();
        fileManager = new FileManager();

        ArrayList<String[]> rawDockerList = new ArrayList<>();
        String dockerBuildImagesCmdsCSVfile = fileManager.getWorkDirectory()
                + path.getInput()
                + pDockerImageListFile;

        //Build reader instance
        FileReader filereader = new FileReader(dockerBuildImagesCmdsCSVfile);
        CSVReader reader = new CSVReader(filereader,
                CSVParser.DEFAULT_SEPARATOR,
                CSVParser.DEFAULT_QUOTE_CHARACTER,
                '\0');

        //Read all rows at once
        List<String[]> allRows = reader.readAll();

        //Read CSV line by line and use the string array as you want
        allRows.forEach((row) -> {
            rawDockerList.add(row);
        });
        return rawDockerList;
    }

    public void writeDockerImagesBuildResult(DockerImage pDockerImage) throws IOException {

        Path path = new Path();
        FileManager fileManager = new FileManager();

        String dataCSV = "DokerBuildImagesDataSet.csv";
        File file = null;
        CSVWriter writer;

        if (!fileManager.fileExist(fileManager.getWorkDirectory() + path.getOutput() + "//" + dataCSV)) {
            file = new File(fileManager.getWorkDirectory() + path.getOutput().concat("//" + dataCSV));
        }
        System.out.println("PATH" + file.getAbsolutePath());

        // create a List which contains String array 
        List<String[]> data = new ArrayList<>();
        writer = new CSVWriter(new FileWriter(fileManager.getWorkDirectory()
                + path.getOutput().concat("//" + dataCSV), true), ',');

        String[] record = {
            pDockerImage.getName(),
            pDockerImage.getCommand(),
            Integer.toString(pDockerImage.getNumberOfBuild()),
            String.valueOf(pDockerImage.isBuildable()),
            Integer.toString(pDockerImage.getBuildingTime()[0]),
            String.valueOf(pDockerImage.getAverageBuildTime())
        };

        data.add(record);

        writer.writeNext(record);

        // closing writer connection 
        writer.close();
    }

    //This method is more expensive if you execute them in pipeline
    public void writeDockerImagesBuildResultsWithMultipleExecution(DockerImage pDockerImage) throws IOException {

        Path path = new Path();
        FileManager fileManager = new FileManager();

        String dataCSV = "DokerBuildImagesDataSet.csv";
        File file = null;
        CSVWriter writer;

        if (!fileManager.fileExist(fileManager.getWorkDirectory()
                + path.getOutput().concat("//" + dataCSV))) {

            file = new File(fileManager.getWorkDirectory()
                    + path.getOutput().concat("//" + dataCSV));
        }

        // create a List which contains String array 
        List<String[]> data = new ArrayList<>();
        writer = new CSVWriter(new FileWriter(dataCSV, true), ',');

        String[] record = {pDockerImage.getName(),
            pDockerImage.getCommand(),
            Integer.toString(pDockerImage.getNumberOfBuild()),
            String.valueOf(pDockerImage.isBuildable()),
            Integer.toString(pDockerImage.getBuildingTime()[0]),
            Integer.toString(pDockerImage.getBuildingTime()[1]),
            Integer.toString(pDockerImage.getBuildingTime()[2]),
            Integer.toString(pDockerImage.getBuildingTime()[3]),
            Integer.toString(pDockerImage.getBuildingTime()[4]),
            Integer.toString(pDockerImage.getBuildingTime()[5]),
            Integer.toString(pDockerImage.getBuildingTime()[6]),
            Integer.toString(pDockerImage.getBuildingTime()[7]),
            Integer.toString(pDockerImage.getBuildingTime()[8]),
            Integer.toString(pDockerImage.getBuildingTime()[9]),
            String.valueOf(pDockerImage.getAverageBuildTime())};
        data.add(record);

        writer.writeNext(record);

        // closing writer connection 
        writer.close();
    }

    public ArrayList<Repository> readRepositoryListFromFile(String pCSVfileRepositories) throws FileNotFoundException, IOException {

        //create source path of a .csv file for reader a repository list
        path = new Path();
        String csvFile = fileManager.getWorkDirectory()
                + path.getInput() + "/"
                + pCSVfileRepositories;

        //read repositories from file and create a buffer
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        ArrayList<Repository> repositoryList = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] GitHubRepository = line.split(cvsSplitBy);

            Repository repository = new Repository();
            repository.setName(GitHubRepository[0]);
            repository.setLink(GitHubRepository[1]);

            repositoryList.add(repository);
        }

        //close buffer at the end of file
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return repositoryList;
    }

    public Collection<DockerImage> readTenBuildingCSVandMergeintoOne() {

        FileReader reader1 = null;
        FileReader reader2 = null;
        FileReader reader3 = null;
        FileReader reader4 = null;
        FileReader reader5 = null;
        FileReader reader6 = null;
        FileReader reader7 = null;
        FileReader reader8 = null;
        FileReader reader9 = null;
        FileReader reader10 = null;
        ArrayList<DockerImage> dockerImageList = null;

        try {

            reader1 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_1.csv");
            reader2 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_2.csv");
            reader3 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_3.csv");
            reader4 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_4.csv");
            reader5 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_5.csv");
            reader6 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_6.csv");
            reader7 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_7.csv");
            reader8 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_8.csv");
            reader9 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_9.csv");
            reader10 = new FileReader("/home/francesco/NetBeansProjects/JDockerController/JDockerController/output/DokerBuildImagesDataSet_10.csv");

            CSVReader csvReader1 = new CSVReader(reader1);
            ArrayList<String[]> list1 = new ArrayList<>();
            list1 = (ArrayList<String[]>) csvReader1.readAll();
            reader1.close();
            csvReader1.close();

            CSVReader csvReader2 = new CSVReader(reader2);
            ArrayList<String[]> list2 = new ArrayList<>();
            list2 = (ArrayList<String[]>) csvReader2.readAll();
            reader2.close();
            csvReader2.close();

            CSVReader csvReader3 = new CSVReader(reader3);
            ArrayList<String[]> list3 = new ArrayList<>();
            list3 = (ArrayList<String[]>) csvReader3.readAll();
            reader3.close();
            csvReader3.close();

            CSVReader csvReader4 = new CSVReader(reader4);
            ArrayList<String[]> list4 = new ArrayList<>();
            list4 = (ArrayList<String[]>) csvReader4.readAll();
            reader4.close();
            csvReader4.close();

            CSVReader csvReader5 = new CSVReader(reader5);
            ArrayList<String[]> list5 = new ArrayList<>();
            list5 = (ArrayList<String[]>) csvReader5.readAll();
            reader5.close();
            csvReader5.close();

            CSVReader csvReader6 = new CSVReader(reader6);
            ArrayList<String[]> list6 = new ArrayList<>();
            list6 = (ArrayList<String[]>) csvReader6.readAll();
            reader6.close();
            csvReader6.close();

            CSVReader csvReader7 = new CSVReader(reader7);
            ArrayList<String[]> list7 = new ArrayList<>();
            list7 = (ArrayList<String[]>) csvReader7.readAll();
            reader7.close();
            csvReader7.close();

            CSVReader csvReader8 = new CSVReader(reader8);
            ArrayList<String[]> list8 = new ArrayList<>();
            list8 = (ArrayList<String[]>) csvReader8.readAll();
            reader8.close();
            csvReader8.close();

            CSVReader csvReader9 = new CSVReader(reader9);
            ArrayList<String[]> list9 = new ArrayList<>();
            list9 = (ArrayList<String[]>) csvReader9.readAll();
            reader9.close();
            csvReader9.close();

            CSVReader csvReader10 = new CSVReader(reader10);
            ArrayList<String[]> list10 = new ArrayList<>();
            list10 = (ArrayList<String[]>) csvReader10.readAll();
            reader10.close();
            csvReader10.close();

            dockerImageList = new ArrayList<>();

            for (int i = 1; i < list1.size(); i++) {

                DockerImageBuild dockerImageBuild1 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild2 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild3 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild4 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild5 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild6 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild7 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild8 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild9 = new DockerImageBuild();
                DockerImageBuild dockerImageBuild10 = new DockerImageBuild();

                dockerImageBuild1.setName(list1.get(i)[0]);
                dockerImageBuild1.setBuildCommand(list1.get(i)[1]);
                dockerImageBuild1.setNumberOfExecution(list1.get(i)[2]);
                dockerImageBuild1.setBuildable(list1.get(i)[3]);
                dockerImageBuild1.setBuild(list1.get(i)[4]);
                dockerImageBuild1.setAverage(list1.get(i)[5]);

                dockerImageBuild2.setName(list2.get(i)[0]);
                dockerImageBuild2.setBuildCommand(list2.get(i)[1]);
                dockerImageBuild2.setNumberOfExecution(list2.get(i)[2]);
                dockerImageBuild2.setBuildable(list2.get(i)[3]);
                dockerImageBuild2.setBuild(list2.get(i)[4]);
                dockerImageBuild2.setAverage(list2.get(i)[5]);

                dockerImageBuild3.setName(list3.get(i)[0]);
                dockerImageBuild3.setBuildCommand(list3.get(i)[1]);
                dockerImageBuild3.setNumberOfExecution(list3.get(i)[2]);
                dockerImageBuild3.setBuildable(list3.get(i)[3]);
                dockerImageBuild3.setBuild(list3.get(i)[4]);
                dockerImageBuild3.setAverage(list3.get(i)[5]);

                dockerImageBuild4.setName(list4.get(i)[0]);
                dockerImageBuild4.setBuildCommand(list4.get(i)[1]);
                dockerImageBuild4.setNumberOfExecution(list4.get(i)[2]);
                dockerImageBuild4.setBuildable(list4.get(i)[3]);
                dockerImageBuild4.setBuild(list4.get(i)[4]);
                dockerImageBuild4.setAverage(list4.get(i)[5]);

                dockerImageBuild5.setName(list5.get(i)[0]);
                dockerImageBuild5.setBuildCommand(list5.get(i)[1]);
                dockerImageBuild5.setNumberOfExecution(list5.get(i)[2]);
                dockerImageBuild5.setBuildable(list5.get(i)[3]);
                dockerImageBuild5.setBuild(list5.get(i)[4]);
                dockerImageBuild5.setAverage(list5.get(i)[5]);

                dockerImageBuild6.setName(list6.get(i)[0]);
                dockerImageBuild6.setBuildCommand(list6.get(i)[1]);
                dockerImageBuild6.setNumberOfExecution(list6.get(i)[2]);
                dockerImageBuild6.setBuildable(list6.get(i)[3]);
                dockerImageBuild6.setBuild(list6.get(i)[4]);
                dockerImageBuild6.setAverage(list6.get(i)[5]);

                dockerImageBuild7.setName(list7.get(i)[0]);
                dockerImageBuild7.setBuildCommand(list7.get(i)[1]);
                dockerImageBuild7.setNumberOfExecution(list7.get(i)[2]);
                dockerImageBuild7.setBuildable(list7.get(i)[3]);
                dockerImageBuild7.setBuild(list7.get(i)[4]);
                dockerImageBuild7.setAverage(list7.get(i)[5]);

                dockerImageBuild8.setName(list8.get(i)[0]);
                dockerImageBuild8.setBuildCommand(list8.get(i)[1]);
                dockerImageBuild8.setNumberOfExecution(list8.get(i)[2]);
                dockerImageBuild8.setBuildable(list8.get(i)[3]);
                dockerImageBuild8.setBuild(list8.get(i)[4]);
                dockerImageBuild8.setAverage(list8.get(i)[5]);

                dockerImageBuild9.setName(list9.get(i)[0]);
                dockerImageBuild9.setBuildCommand(list9.get(i)[1]);
                dockerImageBuild9.setNumberOfExecution(list9.get(i)[2]);
                dockerImageBuild9.setBuildable(list9.get(i)[3]);
                dockerImageBuild9.setBuild(list9.get(i)[4]);
                dockerImageBuild9.setAverage(list9.get(i)[5]);

                dockerImageBuild10.setName(list10.get(i)[0]);
                dockerImageBuild10.setBuildCommand(list10.get(i)[1]);
                dockerImageBuild10.setNumberOfExecution(list10.get(i)[2]);
                dockerImageBuild10.setBuildable(list10.get(i)[3]);
                dockerImageBuild10.setBuild(list10.get(i)[4]);
                dockerImageBuild10.setAverage(list10.get(i)[5]);

                DockerImage dockerImage = new DockerImage(dockerImageBuild1.getName(), dockerImageBuild1.getBuildCommand());
                dockerImage.setBuildable(Boolean.parseBoolean(dockerImageBuild1.getBuildable()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild1.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild2.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild3.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild4.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild5.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild6.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild7.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild8.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild9.getBuild()));
                dockerImage.addOneTimeDockerImageBuild(Integer.parseInt(dockerImageBuild10.getBuild()));

                dockerImageList.add(dockerImage);
                System.out.println(dockerImage.toString());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return dockerImageList;
    }

    public void writeDockerImagesMultipleBuildResult(Collection<DockerImage> pDockerImageList) throws IOException {

        Path path = new Path();
        FileManager fileManager = new FileManager();

        String dataCSV = "DockerBuildImages.csv";
        File file = null;
        CSVWriter writer;

        CSVWriter csvwriter; 
        csvwriter = new CSVWriter(new FileWriter(fileManager.getWorkDirectory() + path.getOutput()+"//" + dataCSV), ',');
        
        for (DockerImage dockerImage : pDockerImageList) {
            String[] record = {
                dockerImage.getName(),
                dockerImage.getCommand(),
                Integer.toString(dockerImage.getNumberOfBuild()),
                String.valueOf(dockerImage.isBuildable()),
                Integer.toString(dockerImage.getBuildingTime()[0]),
                Integer.toString(dockerImage.getBuildingTime()[1]),
                Integer.toString(dockerImage.getBuildingTime()[2]),
                Integer.toString(dockerImage.getBuildingTime()[3]),
                Integer.toString(dockerImage.getBuildingTime()[4]),
                Integer.toString(dockerImage.getBuildingTime()[5]),
                Integer.toString(dockerImage.getBuildingTime()[6]),
                Integer.toString(dockerImage.getBuildingTime()[7]),
                Integer.toString(dockerImage.getBuildingTime()[8]),
                Integer.toString(dockerImage.getBuildingTime()[9]),
                String.valueOf(dockerImage.getAverageBuildTime())
            };
            
            csvwriter.writeNext(record);
        }
        pDockerImageList.clear();
        csvwriter.close();
    }

}
