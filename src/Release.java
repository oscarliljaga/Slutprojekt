import java.io.Serializable;

public class Release extends PublicEntity implements Serializable {
    //Attributes
    private final Artist[] ARTISTS;
    private final Song[] SONGS;

    //Constructor
    public Release() {
        super();
        this.ARTISTS = Database.chooseArtists();
        this.SONGS = Database.chooseSongs();
        Database.releases.add(this);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }

    public Song[] getSONGS() {
        return SONGS;
    }
}
