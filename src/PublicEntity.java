import java.io.Serializable;
import java.util.Scanner;

public abstract class PublicEntity implements Serializable {
    //Attributes
    private static final long serialVersionUID = 1L;
    protected final String NAME;

    //Constructor
    public PublicEntity(String NAME) {
        this.NAME = NAME;
    }

    public PublicEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nName: ");
        this.NAME = scanner.nextLine();
    }

    //Methods
    public String getNAME() {
        return NAME;
    }
}
