package gitCloner;

/**
 *
 * @author cicciog
 */
public class Repository {
    private String name;
    private String link;

    public Repository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String pLink) {
        this.link = pLink;
    }

    @Override
    public String toString() {
        return "Repository{" + "name=" + name + ", link=" + link + '}';
    }
    
    
    
    
}
