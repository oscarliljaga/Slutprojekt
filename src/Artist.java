import java.util.ArrayList;

public class Artist extends PublicEntity{
    protected ArrayList<Release> releases;

    public Artist(String name, String URL) {
        super(name, URL);
    }

    public ArrayList<Release> getReleases() {
        return releases;
    }
}
