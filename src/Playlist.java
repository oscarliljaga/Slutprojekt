import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean isPublic = false;
    private final User owner;
    private final ArrayList<Song> songs = new ArrayList<>();

    //Constructor
    public Playlist(User owner) {
        super();
        this.owner = owner;
        inputLoop:
        while (true) { //Input loop
            System.out.println("Make this playlist public? [Y]/[N]");
            String publicityInput = Program.SCANNER.nextLine().toUpperCase();
            System.out.println();
            switch (publicityInput) {
                case "Y":
                    this.isPublic = true;
                case "N": // Not public is default
                    break inputLoop;
                default: // Invalid input
                    System.out.println("###Please type an option in [brackets]###");
            }
        }
        owner.addPlaylist(this);
        Database.getInstance().addPlaylist(this);
    }

    public boolean isPublic() {
        return isPublic;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Song> getSongs() {
        if (!this.isPublic && !owner.isLoggedIn()) return new ArrayList<>();
        else return songs;
    }

    public void addSong(Song song) {
        if (owner.isLoggedIn() && !songs.contains(song)) songs.add(song);
        Database.saveToFile();
    }

}
