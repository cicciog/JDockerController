package featuresExtractor;


/**
 *
 * @author cicciog
 */
public class FeaturesEntity {

    private int FROM = 0;
    private int LABEL = 0;
    private int ENV = 0;
    private int RUN = 0;
    private int VOLUME = 0;
    private int COPY = 0;
    private int ENTRYPOINT = 0;
    private int CMD = 0;
    private int WORKDIR = 0;
    private int USER = 0;
    private int EXPOSE = 0;
    private int MAINTAINER = 0;
    private int ARG = 0;
    private int STOPSIGNAL = 0;
    private int ADD = 0;
    private int SHELL = 0;
    //total amount of feature in a json file
    private int numberOfFeatures = 0;
    //json file name
    private String name;

    //Constructor with param
    public FeaturesEntity(String pName) {
        this.name = pName;
    }

    //Constructor
    public FeaturesEntity() {
    }

    public void addFeature(String pKeyWord) {

        switch (pKeyWord) {
            case "FROM": {
                this.FROM++;
                this.numberOfFeatures++;
            }
            break;

            case "LABEL": {
                this.LABEL++;
                this.numberOfFeatures++;
            }
            break;

            case "ENV": {
                this.ENV++;
                this.numberOfFeatures++;
            }
            break;

            case "RUN": {
                this.RUN++;
                this.numberOfFeatures++;
            }
            break;

            case "VOLUME": {
                this.VOLUME++;
                this.numberOfFeatures++;
            }
            break;

            case "COPY": {
                this.COPY++;
                this.numberOfFeatures++;
            }
            break;

            case "ENTRYPOINT": {
                this.ENTRYPOINT++;
                this.numberOfFeatures++;
            }
            break;

            case "CMD": {
                this.CMD++;
                this.numberOfFeatures++;
            }
            break;

            case "WORKDIR": {
                this.WORKDIR++;
                this.numberOfFeatures++;
            }
            break;

            case "USER": {
                this.USER++;
                this.numberOfFeatures++;
            }
            break;

            case "EXPOSE": {
                this.EXPOSE++;
                this.numberOfFeatures++;
            }
            break;

            case "MAINTAINER": {
                this.MAINTAINER++;
                this.numberOfFeatures++;
            }
            break;

            case "ARG": {
                this.ARG++;
                this.numberOfFeatures++;
            }
            break;

            case "STOPSIGNAL": {
                this.STOPSIGNAL++;
                this.numberOfFeatures++;
            }
            break;

            case "ADD": {
                this.ADD++;
                this.numberOfFeatures++;
            }
            break;

            case "SHELL": {
                this.SHELL++;
                this.numberOfFeatures++;
            }
            break;

            default:
                System.out.println("feature not found");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFROM() {
        return FROM;
    }

    public int getLABEL() {
        return LABEL;
    }

    public int getENV() {
        return ENV;
    }

    public int getRUN() {
        return RUN;
    }

    public int getVOLUME() {
        return VOLUME;
    }

    public int getCOPY() {
        return COPY;
    }

    public int getENTRYPOINT() {
        return ENTRYPOINT;
    }

    public int getCMD() {
        return CMD;
    }

    public int getWORKDIR() {
        return WORKDIR;
    }

    public int getUSER() {
        return USER;
    }

    public int getEXPOSE() {
        return EXPOSE;
    }

    public int getMAINTAINER() {
        return MAINTAINER;
    }

    public int getARG() {
        return ARG;
    }

    public int getSTOPSIGNAL() {
        return STOPSIGNAL;
    }

    public int getADD() {
        return ADD;
    }

    public int getSHELL() {
        return SHELL;
    }

    public int getNumberOfFeatures() {
        return numberOfFeatures;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FeaturesEntity{" + "FROM=" + FROM
                + ", LABEL=" + LABEL
                + ", ENV=" + ENV
                + ", RUN=" + RUN
                + ", VOLUME=" + VOLUME
                + ", COPY=" + COPY
                + ", ENTRYPOINT=" + ENTRYPOINT
                + ", CMD=" + CMD
                + ", WORKDIR=" + WORKDIR
                + ", USER=" + USER
                + ", EXPOSE=" + EXPOSE
                + ", MAINTAINER=" + MAINTAINER
                + ", ARG=" + ARG
                + ", STOPSIGNAL=" + STOPSIGNAL
                + ", ADD=" + ADD
                + ", SHELL=" + SHELL
                + ", numberOfFeatures=" + numberOfFeatures
                + ", name=" + name + '}';
    }

}
