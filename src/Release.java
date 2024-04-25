import java.io.Serializable;

public class Release extends PublicEntity implements Serializable {
    //Attributes
    private final Artist[] ARTISTS;
    private final Song[] SONGS;

    //Constructor
    public Release(String name, String URL, Artist[] ARTISTS, Song[] SONGS) {
        super(name, URL);
        this.ARTISTS = ARTISTS;
        this.SONGS = SONGS;
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }

    public Song[] getSONGS() {
        return SONGS;
    }
}
