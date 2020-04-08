
import fileManager.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author cicciog
 */
public class DockerController {

    private ProcessBuilder processBuilder;

    public DockerController() {
        this.processBuilder = new ProcessBuilder();
    }

    public void checkDockerVersion() throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker version > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\version.txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public void getAllDockerContainers() throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker ps > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\containers.txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);

    }

    public void getAllDockerImages() throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker images > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\images.txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public void removeAllImages() throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker rmi $(docker images -q) > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\imagesRemoved.txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public void removeAllContainers() throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker rm $(docker ps -a -q) > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\containersRemoved.txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public void buildDockerImage(String pName, String pSource) throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", "docker build -t " + pName + " " + pSource + " > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\" + pName + ".txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public void buildDockerImageByCommand(String pName, String pCommand) throws IOException, InterruptedException {
        processBuilder.command("powershell.exe", "/c", pCommand + " > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\" + pName + ".txt");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public String normalizeCommand(DockerImage pDockerImage) {
        Path path = new Path();
        String normalizeSource = pDockerImage.getCommand()
                .replace("docker build -t ", "")
                .replace(pDockerImage.getName() + " ", "");

        String cmd = "docker build -t "
                + pDockerImage.getName()
                + " " + path.getRepositories()
                + normalizeSource;

        System.out.println(cmd);
        return cmd;
    }
    
    public int getStartTime(){
            int start = (int) System.currentTimeMillis();
            //before convert milliSeconds to seconds
            return start/1000;
    }
    
    public int getFianlTime(){
            int end = (int) System.currentTimeMillis();
            //before convert milliSeconds to seconds
            return end/1000;
    }

}
