package dockerController;

import fileManager.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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

        // Create an hash map
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

        version.put("client", client);
        version.put("server", server);

        return version;
    }

    public Collection<HashMap> getAllDockerContainers() throws IOException, InterruptedException {
        HashMap dockerContainer;
        List<String> list = new ArrayList<>();
        List<HashMap> containers = new ArrayList<>();

        processBuilder.command("/bin/bash", "-c", "sudo docker ps");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        String[] fields;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            fields = line.split("\\s*(=>|,|\\s)\\s*");
            if (count == 0) {
                list.add(fields[0]+ "_" + fields[1]);
                list.add(fields[2]); 
                list.add(fields[3]);
                list.add(fields[4]);
                list.add(fields[5]);
                list.add(fields[5]);
            } else {
                dockerContainer = new HashMap();
                dockerContainer.put(list.get(0), fields[0]);
                containers.add(dockerContainer);
            }
            count++;
        }

        process.destroy();
        return containers;
    }

    public Collection<HashMap> getAllDockerImages() throws IOException, InterruptedException {
        HashMap dockerImage;
        List<String> list = new ArrayList<>();
        List<HashMap> images = new ArrayList<>();

        processBuilder.command("/bin/bash", "-c", "sudo docker images");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        String[] fields;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            fields = line.split("\\s*(=>|,|\\s)\\s*");
            if (count == 0) {
                list.add(fields[0]);
                list.add(fields[1]);
                list.add(fields[2] + "_" + fields[3]);
                list.add(fields[4]);
                list.add(fields[5]);
            } else {
                dockerImage = new HashMap();
                dockerImage.put(list.get(0), fields[0]);
                dockerImage.put(list.get(1), fields[1]);
                dockerImage.put(list.get(2), fields[2]);
                dockerImage.put(list.get(3), fields[3] + " " + fields[4] + " " + fields[5]);
                dockerImage.put(list.get(4), fields[6]);

                images.add(dockerImage);
            }
            count++;
        }

        process.destroy();
        return images;
    }

    public void removeAllImages() throws IOException, InterruptedException {
        processBuilder.command("/bin/bash", "-c", "sudo docker rmi $(sudo docker images -q)");
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        process.destroy();
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
        processBuilder.command("/bin/bash", "-c","sudo docker build -t " + pName + " " + pSource);
        Process process = processBuilder.start();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        process.destroy();
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
