import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Database implements Serializable {
    //Attributes
    public static ArrayList<Artist> artists = new ArrayList<>();
    public static ArrayList<Song> songs = new ArrayList<>();
    public static ArrayList<Release> releases = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    //Constructor
    public Database() {
    }

    //Methods

    /**
     * Prompts menu for picking out Artists from the database
     *
     * @return chosen Artists
     */
    public static Artist[] chooseArtists() {
        return chooseArtists(false);
    }

    /**
     * Prompts menu for picking out Artists from the database
     *
     * @param soloArtistsOnly to only show SoloArtists
     * @return chosen Artists
     */
    public static Artist[] chooseArtists(Boolean soloArtistsOnly) {
        ArrayList<Artist> chosenArtists = new ArrayList<>();

        while (true) { //Add multiple Artists
            System.out.println("\nChoose artists: ");
            for (int i = 0; i < artists.size(); i++) { //List all Artists
                Artist artist = artists.get(i);
                if (soloArtistsOnly && !((Object) artist).getClass().getSimpleName().equals("SoloArtist"))
                    continue; //Skip if soloArtistsOnly and artist is not SoloArtist
                if (chosenArtists.contains(artist)) System.out.print(">");
                System.out.printf("[%d] %s\n", i, artist.getNAME());
            }
            System.out.println("[N]ew artist\n[Q]uit");

            String input = scanner.nextLine().toUpperCase();
            if (input.equals("N")) { //Create a new Artist
                if (soloArtistsOnly) chosenArtists.add(new SoloArtist());
                else {
                    inputLoop:
                    while (true) {
                        System.out.println("\n[S]olo Artist or [G]roup Artist?\n[Q]uit");
                        switch (scanner.nextLine().toUpperCase()) {
                            case "S":
                                chosenArtists.add(new SoloArtist());
                                break inputLoop;
                            case "G":
                                chosenArtists.add(new GroupArtist());
                                break inputLoop;
                            case "Q":
                                break inputLoop;
                            default:
                                System.out.println("Please type an option in [brackets]");
                                break;
                        }
                    }
                }
            } else if (input.equals("Q")) break; //Finish choosing
            else { //Select existing Artist
                try {
                    chosenArtists.add(artists.get(Integer.parseInt(input)));
                } catch (Exception e) {
                    System.out.println("Please type an option in [brackets]");
                }
            }
        }
        return chosenArtists.toArray(new Artist[0]);
    }

    /**
     * Prompts menu for picking out Songs from the database
     *
     * @return chosen Songs
     */
    public static Song[] chooseSongs() {
        ArrayList<Song> chosenSongs = new ArrayList<>();

        while (true) { //Add multiple Songs
            System.out.println("Choose songs: ");
            for (int i = 0; i < songs.size(); i++) { //List all Songs
                Song song = songs.get(i);
                if (chosenSongs.contains(song)) System.out.print(">");
                System.out.printf("[%d] %s - %s\n",
                        i,
                        Arrays.stream(song.getARTISTS())
                                .map(Artist::getNAME)
                                .collect(Collectors.joining(", ")),
                        song.getNAME());
            }
            System.out.println("[N]ew song\n[Q]uit");

            String input = scanner.nextLine().toUpperCase();
            if (input.equals("N")) { //Create a new Song
                chosenSongs.add(new Song());
            } else if (input.equals("Q")) break; //Finish choosing
            else { //Select existing Song
                try {
                    chosenSongs.add(songs.get(Integer.parseInt(input)));
                } catch (Exception e) {
                    System.out.println("Please type an option in [brackets]");
                }
            }
        }
        return chosenSongs.toArray(new Song[0]);
    }
}