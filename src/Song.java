import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Song extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private final Artist[] ARTISTS;

    //Constructor
    public Song() {
        super();
        Database database = Database.getInstance();

        ArrayList<Artist> ARTISTS = database.chooseArtists(this.NAME);
        while (ARTISTS.isEmpty()) { //Don't let ARTISTS be empty
            System.out.print("Artists cannot be empty, try again... ");
            ARTISTS = database.chooseArtists(this.NAME);
        }
        this.ARTISTS = ARTISTS.toArray(new Artist[0]);
        database.addSong(this);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }
}
