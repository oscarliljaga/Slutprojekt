import java.io.Serial;
import java.io.Serializable;

abstract public class Artist extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;

    //Constructor
    public Artist() {
        super();
    }
}
