import java.io.Serial;
import java.io.Serializable;

public class SoloArtist extends Artist implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //Constructor
    public SoloArtist() {
        super();
        Database database = Database.getInstance();
        database.addArtist(this);
    }
}
