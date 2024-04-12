import java.util.ArrayList;

abstract public class Artist extends PublicEntity {
    //Attributes
    protected ArrayList<Release> releases;

    //Constructor
    public Artist(String NAME, String URL) {
        super(NAME, URL);
    }

    //Methods
    public ArrayList<Release> getReleases() {
        return releases;
    }
}
