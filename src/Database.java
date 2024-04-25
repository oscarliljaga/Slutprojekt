import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
    //Attributes
    public static ArrayList<Artist> artists = new ArrayList<>();
    public ArrayList<Song> songs = new ArrayList<>();
    public ArrayList<Release> releases = new ArrayList<>();

    //Constructor
    public Database() {
    }

    //Methods

    public static ArrayList<Artist> getArtists() {
        return artists;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Release> getReleases() {
        return releases;
    }

    public static void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addRelease(Release release) {
        releases.add(release);
    }
}
