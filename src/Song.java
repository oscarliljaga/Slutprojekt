import java.io.Serial;
import java.io.Serializable;

public class Song extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private final Artist[] ARTISTS;

    //Constructor
    public Song() {
        super();
        Database database = Database.getInstance();
        System.out.print(this.NAME + " | ");
        this.ARTISTS = database.chooseArtists();
        database.addSong(this);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }
}
