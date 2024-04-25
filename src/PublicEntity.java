import java.io.Serializable;
import java.util.Scanner;

public abstract class PublicEntity implements Serializable {
    //Attributes
    protected final String NAME;
    protected final String URL;

    //Constructor
    public PublicEntity(String NAME, String URL) {
        this.NAME = NAME;
        this.URL = URL;
    }

    public PublicEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String NAME = scanner.nextLine();
        System.out.println("URL: ");
        String URL = scanner.nextLine();

        this.NAME = NAME;
        this.URL = URL;
    }

    //Methods
    public String getNAME() {
        return NAME;
    }

    public String getURL() {
        return URL;
    }
}
