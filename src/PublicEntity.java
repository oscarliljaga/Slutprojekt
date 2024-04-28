import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public abstract class PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    protected final String NAME;

    //Constructor
    public PublicEntity(String NAME) {
        Scanner scanner = Program.SCANNER;
        while (NAME.isBlank()) {
            System.out.print("Name cannot be blank, try again: ");
            NAME = scanner.nextLine();
        }
        this.NAME = NAME;
    }

    public PublicEntity() {
        Scanner scanner = Program.SCANNER;
        System.out.print("Name: ");
        String NAME = scanner.nextLine();
        while (NAME.isBlank()) {
            System.out.print("Name cannot be blank, try again: ");
            NAME = scanner.nextLine();
        }
        System.out.println();
        this.NAME = NAME;
    }

    //Methods
    public String getNAME() {
        return NAME;
    }
}
