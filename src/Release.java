import java.io.Serializable;

public class Release extends PublicEntity implements Serializable {
    //Attributes
    private static final long serialVersionUID = 1L;
    private final Artist[] ARTISTS;
    private final Song[] SONGS;

    //Constructor
    public Release(Database database) {
        super();
        this.ARTISTS = database.chooseArtists();
        this.SONGS = database.chooseSongs();
        database.releases.add(this);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }

    public Song[] getSONGS() {
        return SONGS;
    }
}
