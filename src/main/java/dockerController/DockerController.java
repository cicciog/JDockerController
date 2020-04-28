package dockerController;

import fileManager.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author cicciog
 */
public class DockerController {

    private ProcessBuilder processBuilder;

    public DockerController() {
        this.processBuilder = new ProcessBuilder();
    }

    public HashMap checkDockerVersion() throws IOException, InterruptedException {

        // Create a hash map
        HashMap client = new HashMap();
        HashMap server = new HashMap();
        HashMap version = new HashMap();

        processBuilder.command("/bin/bash", "-c", "sudo docker version");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        String features[];
        int count = 0;
        while ((line = reader.readLine()) != null) {

            if (count < 8) {
                features = line.split(":");
                if (features.length > 1) {
                    client.put(features[0].trim().replace(" ", "-"), features[1].trim());
                }
            } else if (count > 8) {
                features = line.split(":");
                if (features.length > 1) {
                    server.put(features[0].trim().replace(" ", "-"), features[1].trim());
                }
            }

            count++;
        }
        process.destroy();
        
        version.put("client",client);
        version.put("server",server);
        
        return version;
    }

    public void getAllDockerContainers() throws IOException, InterruptedException {
        processBuilder.command("/bin/bash", "-c", "sudo docker ps");
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
        processBuilder.command("/bin/bash", "-c", "sudo docker images");
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
        processBuilder.command("/bin/bash", "-c", "docker rmi $(sudo docker images -q)");
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
        processBuilder.command("/bin/bash", "-c", "docker rm $(sudo docker ps -a -q)");
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
        processBuilder.command("/bin/bash", "-c", "docker build -t " + pName + " " + pSource);
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

    public int buildDockerImageByCommand(String pName, String pCommand) throws IOException, InterruptedException {
        processBuilder.command("/bin/bash", "-c", pCommand);
        Process process = processBuilder.start();
        System.out.println("BUILD: " + pName);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);

        return exitCode;
    }

    public String normalizeCommand(DockerImage pDockerImage) {
        Path path = new Path();
        String normalizeSource = pDockerImage.getCommand()
                .replace("docker build -t ", "")
                .replaceAll("\\\\", "/")
                .replace(pDockerImage.getName() + " ", "");

        String cmd = "sudo docker build -t "
                + pDockerImage.getName()
                + " " + path.getRepositories()
                + normalizeSource;

        System.out.println(cmd);
        return cmd;
    }

    public int getStartTime() {
        int start = (int) System.currentTimeMillis();
        //before convert milliSeconds to seconds
        return start / 1000;
    }

    public int getFinalTime() {
        int end = (int) System.currentTimeMillis();
        //before convert milliSeconds to seconds
        return end / 1000;
    }

}
