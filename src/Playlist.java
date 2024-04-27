import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends PublicEntity implements Serializable {
    //Attributes
    private boolean isPublic = false;
    private final User owner;
    private ArrayList<Song> songs;

    //Constructor
    public Playlist(String NAME, User owner) {
        super(NAME);
        this.owner = owner;
    }

    public boolean isPublic() {
        return isPublic;
    }

    private void setPublic(boolean state) {
        if (owner.isLoggedIn()) isPublic = state;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Song> getSongs() {
        if (owner.isLoggedIn()) return songs;
        else return null;
    }

    private void addSong(Song song) {
        if (owner.isLoggedIn() && !songs.contains(song)) songs.add(song);
    }

    private void removeSong(Song song) {
        if (owner.isLoggedIn()) songs.remove(song);
    }
}
