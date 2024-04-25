import java.io.Serializable;
import java.util.ArrayList;

abstract public class Artist extends PublicEntity implements Serializable {
    //Attributes
    protected ArrayList<Song> songs;
    protected ArrayList<Release> releases;

    //Constructor
    public Artist(String NAME) {
        super(NAME);
    }

    public Artist() {
        super();
    }

    //Methods
    public ArrayList<Release> getReleases() {
        return releases;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addRelease(Release release) {
        releases.add(release);
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
