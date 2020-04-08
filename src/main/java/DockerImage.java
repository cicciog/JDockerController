
import java.util.Arrays;



/**
 *
 * @author cicciog
 */
public class DockerImage {
    private String name;
    private String command;
    private int numberOfBuild;
    private int[] buildingTime;
    private float averageBuildTime;
    private static final int MAX_NUMBER_OF_BUILD = 10;

    public DockerImage(String pName, String pCommand) {
        this.name = pName;
        this.command = pCommand;
        this.numberOfBuild = 0;
        this.buildingTime = new int[10];
        this.averageBuildTime = 0;
    }
    
    public void addOneTimeDockerImageBuild(int pBuildingTime){
        if(numberOfBuild <= MAX_NUMBER_OF_BUILD){
            this.buildingTime[numberOfBuild] = pBuildingTime;
            this.numberOfBuild = numberOfBuild + 1; 
            this.averageBuildTime = computesAverageTime();
        }
    }
    
    private float computesAverageTime(){
        if(numberOfBuild == 0){
           this.averageBuildTime = 0;
        }
        
        if(numberOfBuild > 0){
            int totalTime = 0;
            for(int i = 0; i < buildingTime.length; i++){
                totalTime = totalTime + buildingTime[i];
            }
            this.averageBuildTime = (float) totalTime / numberOfBuild;
        }
        
        return this.averageBuildTime;
    }

    @Override
    public String toString() {
        return "DockerImage{" + "name=" + name + 
                                ", command=" + command + 
                                ", numberOfBuild=" + numberOfBuild + 
                                ", buildingTime=" + Arrays.toString(buildingTime) + 
                                ", averageBuildTime=" + averageBuildTime + '}';
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public int getNumberOfBuild() {
        return numberOfBuild;
    }

    public int[] getBuildingTime() {
        return buildingTime;
    }

    public float getAverageBuildTime() {
        return averageBuildTime;
    }
    
    
    
    
    
}
