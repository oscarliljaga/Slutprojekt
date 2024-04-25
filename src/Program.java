import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    //Attributes
    private final Database DATABASE = new Database();
    private final ArrayList<Artist> ARTISTS = Database.artists;
    private final ArrayList<Song> SONGS = Database.songs;
    private final ArrayList<Release> RELEASES = Database.releases;

    private final Scanner SCANNER = new Scanner(System.in);

    //Constructor
    public Program() {
        startProgram();
    }

    //Methods
    public void startProgram() {
        mainMenu:
        while (true) { //Main menu input loop
            System.out.println("[A]dd release\n[B]rowse database\n[Q]uit");

            switch (SCANNER.nextLine().toUpperCase()) {
                case "A": //Add release
                    System.out.println("\n New release...");
                    new Release();
                    break;
                case "B": //Browse database
                    for (Release release : RELEASES) {
                        System.out.println(release.getNAME());
                    }
                    break;
                case "Q":
                    break mainMenu;
                default:
                    System.out.println("Please type an option in [brackets]");
                    break;
            }
        }
    }

    public ArrayList<Artist> getARTISTS() {
        return ARTISTS;
    }

    public void addArtist(Artist artist) {
        this.ARTISTS.add(artist);
    }
}
