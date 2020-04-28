package com.mycompany.jdockercontroller;

import dockerController.DockerController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francesco
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        DockerController dockerController = new DockerController();
        try {
            System.out.println(dockerController.checkDockerVersion().toString());
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
}
