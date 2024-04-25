import java.io.Serializable;
import java.util.Scanner;

public abstract class PublicEntity implements Serializable {
    //Attributes
    protected final String NAME;

    //Constructor
    public PublicEntity(String NAME) {
        this.NAME = NAME;
    }

    public PublicEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        this.NAME = scanner.nextLine();
    }

    //Methods
    public String getNAME() {
        return NAME;
    }
}
