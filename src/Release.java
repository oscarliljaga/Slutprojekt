import java.io.Serial;
import java.io.Serializable;

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
        System.out.print(this.NAME + " | ");
        this.ARTISTS = database.chooseArtists();
        System.out.print(this.NAME + " | ");
        this.SONGS = database.chooseSongs();
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
