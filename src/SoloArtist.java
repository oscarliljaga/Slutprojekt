import java.io.Serializable;

public class SoloArtist extends Artist implements Serializable {
    private static final long serialVersionUID = 1L;
    //Constructor
    public SoloArtist(Database database) {
        super();
        database.artists.add(this);
    }
}
