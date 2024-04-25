import java.io.Serializable;

public class SoloArtist extends Artist implements Serializable {
    //Constructor
    public SoloArtist() {
        super();
        Database.artists.add(this);
    }
}
