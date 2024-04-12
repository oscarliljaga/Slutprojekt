import java.util.ArrayList;

public class Playlist extends PublicEntity {
    //Attributes
    private boolean isPublic = false;
    private User owner;
    private ArrayList<Song> songs;

    //Constructor
    public Playlist(String NAME, String URL) {
        super(NAME, URL);
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
