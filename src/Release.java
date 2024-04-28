import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Release extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private final Artist[] ARTISTS;
    private final Song[] SONGS;

    //Constructor
    public Release() {
        super();
        Database database = Database.getInstance();
        ArrayList<Artist> ARTISTS = database.chooseArtists(this.NAME);
        while (ARTISTS.isEmpty()) { //Don't let ARTISTS be empty
            System.out.print("Artists cannot be empty, try again... ");
            ARTISTS = database.chooseArtists(this.NAME);
        }
        this.ARTISTS = ARTISTS.toArray(new Artist[0]);

        ArrayList<Song> SONGS = database.chooseSongs(this.NAME);
        while (SONGS.isEmpty()) { //Don't let SONGS be empty
            System.out.print("Songs cannot be empty, try again... ");
            SONGS = database.chooseSongs(this.NAME);
        }
        this.SONGS = SONGS.toArray(new Song[0]);

        database.addRelease(this);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }

    public Song[] getSONGS() {
        return SONGS;
    }
}
