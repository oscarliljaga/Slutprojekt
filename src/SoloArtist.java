import java.io.Serializable;

public class SoloArtist extends Artist implements Serializable {
    //Constructor
    public SoloArtist(String NAME, String URL) {
        super(NAME, URL);
    }

    public SoloArtist() {
        super();
    }
}
