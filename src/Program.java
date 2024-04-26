import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    //Attributes
    private Database DATABASE;
    private final Scanner SCANNER = new Scanner(System.in);

    //Constructor
    public Program() {
        startProgram();
    }

    //Methods
    public void startProgram() {
        try { //Tries to import existing Database from file "database"
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database"));
            DATABASE = (Database) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }

        mainMenu:
        while (true) { //Main menu input loop
            System.out.println("[A]dd release\n[B]rowse releases\n[P]laylists\n[Q]uit");

            switch (SCANNER.nextLine().toUpperCase()) {
                case "A": //Add release
                    System.out.println("\nNew release...");
                    new Release(DATABASE);
                    saveDB(DATABASE);
                    break;
                case "B": //Browse releases
                    for (Release release : DATABASE.releases) {
                        System.out.println(release.getNAME());
                    }
                    break;
                case "P": //Browse playlists

                    break;
                case "Q": //Quit program
                    break mainMenu;
                default:
                    System.out.println("Please type an option in [brackets]");
                    break;
            }
        }
    }

    /**
     * Saves Database to file "database"
     */
    public void saveDB(Database database) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database"));
            oos.writeObject(database);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
