package dockerController;

/**
 *
 * @author cicciog
 */
public class DockerImageBuild {
    private String Name;
    private String BuildCommand;
    private String NumberOfExecution;
    private String Buildable;
    private String Build;
    private String Average;

    public String getName() {
        return Name;
    }

    public void setName(String pName) {
        this.Name = pName;
    }

    public String getBuildCommand() {
        return BuildCommand;
    }

    public void setBuildCommand(String pBuildCommand) {
        this.BuildCommand = pBuildCommand;
    }

    public String getNumberOfExecution() {
        return NumberOfExecution;
    }

    public void setNumberOfExecution(String pNumberOfExecution) {
        this.NumberOfExecution = pNumberOfExecution;
    }

    public String getBuildable() {
        return Buildable;
    }

    public void setBuildable(String pBuildable) {
        this.Buildable = pBuildable;
    }

    public String getBuild() {
        return Build;
    }

    public void setBuild(String pBuild) {
        this.Build = pBuild;
    }

    public String getAverage() {
        return Average;
    }

    public void setAverage(String pAverage) {
        this.Average = pAverage;
    }

    @Override
    public String toString() {
        return "DockerImageBuild{" + 
                "Name=" + Name + 
                ", BuildCommand=" + BuildCommand + 
                ", NumberOfExecution=" + NumberOfExecution + 
                ", Buildable=" + Buildable + 
                ", Build=" + Build + 
                ", Average=" + Average + '}';
    }
   
    
    
}
