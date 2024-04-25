import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Song extends PublicEntity implements Serializable {
    //Attributes
    private final Artist[] ARTISTS;

    //Constructor
    public Song() {
        super();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Artist> artists = Database.artists;
        ArrayList<Artist> songArtists = new ArrayList<>();

        addArtists:
        while (true) { //Add multiple Artists
            for (int i = 0; i < artists.size(); i++) { //List all Artists
                Artist artist = artists.get(i);
                if(songArtists.contains(artist)) System.out.print(">");
                System.out.printf("[%d] %s\n", i, artist.getNAME());
            }
            System.out.println("[N]ew artist\n[Q]uit");

            String input = scanner.nextLine().toUpperCase();
            if (input.equals("N")) { //Create a new Artist
                inputLoop:
                while (true) {
                    System.out.println("[S]olo Artist or [G]roup Artist?\n[Q]uit to main menu");

                    switch (scanner.nextLine().toUpperCase()) {
                        case "S":
                            SoloArtist newSoloArtist = new SoloArtist();
                            Database.artists.add(newSoloArtist);
                            songArtists.add(newSoloArtist);
                            break inputLoop;
                        case "G":
                            GroupArtist newGroupArtist = new GroupArtist();
                            Database.artists.add(newGroupArtist);
                            songArtists.add(newGroupArtist);
                            break inputLoop;
                        case "Q":
                            break addArtists;
                        default:
                            System.out.println("Please type an option in [brackets]");
                            break;
                    }
                }
            } else if (input.equals("Q")) break; //Continue with song creation
            else { //Select existing Artist
                try {
                    songArtists.add(artists.get(Integer.parseInt(input)));
                } catch (Exception e) {
                    System.out.println("Please type an option in [brackets]");
                }
            }
        }
        this.ARTISTS = songArtists.toArray(new Artist[0]);
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }
}
