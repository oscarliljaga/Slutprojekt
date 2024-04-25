import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Song extends PublicEntity implements Serializable {
    //Attributes
    private final Artist[] ARTISTS;

    //Constructor
    public Song(String name, String URL, Artist[] ARTISTS) {
        super(name, URL);
        this.ARTISTS = ARTISTS;

        for (int i = 0; i < ARTISTS.length; i++) {
            ARTISTS[i].addSong(this);
        }
    }

    public Song() {
        super();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Artist> artists = Database.getArtists();
        ArrayList<Artist> songArtists = new ArrayList<>();

        addArtists:
        while (true) { //Add multiple Artists
            for (int i = 0; i < artists.size(); i++) { //List all Artists
                Artist artist = artists.get(i);
                System.out.println(String.format("[%d] %s | %s", i, artist.getNAME(), artist.getURL()));
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
                            Database.addArtist(newSoloArtist);
                            songArtists.add(newSoloArtist);
                            break inputLoop;
                        case "G":
                            GroupArtist newGroupArtist = new GroupArtist();
                            Database.addArtist(newGroupArtist);
                            songArtists.add(newGroupArtist);
                            break inputLoop;
                        case "Q":
                            break addArtists;
                        default:
                            System.out.println("Please type an option in [brackets]");
                            break;
                    }
                }
            } else if (input.equals("Q")) break addArtists; //Continue with song creation
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
