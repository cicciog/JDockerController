
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
    
    public void checkDockerVersion() throws IOException, InterruptedException{
         processBuilder.command("powershell.exe", "/c", "docker version > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\version.txt");
         Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
    }
    
    public void getAllDockerContainers() throws IOException, InterruptedException{
         processBuilder.command("powershell.exe", "/c", "docker ps > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\containers.txt");
         Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        
    }
    
    public void getAllDockerImages() throws IOException, InterruptedException{
        processBuilder.command("powershell.exe", "/c", "docker images > C:\\Users\\Franecesco-pc\\Documents\\NetBeansProjects\\JDockerController\\JDockerController\\output\\images.txt");
        Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
    }
    
    
    
}
