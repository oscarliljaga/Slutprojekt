import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Database implements Serializable {
    //Attributes
    private static final long serialVersionUID = 1L;
    public ArrayList<Artist> artists = new ArrayList<>();
    public ArrayList<Song> songs = new ArrayList<>();
    public ArrayList<Release> releases = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Playlist> playlists = new ArrayList<>();
    //Constructor

    /**
     * Constructs Database from file "database"
     */
    public Database() {

    }

    //Methods

    /**
     * Prompts menu for picking out Artists from the database
     *
     * @return chosen Artists
     */
    public Artist[] chooseArtists() {
        return chooseArtists(false);
    }

    /**
     * Prompts menu for picking out Artists from the database
     *
     * @param soloArtistsOnly to only show SoloArtists
     * @return chosen Artists
     */
    public Artist[] chooseArtists(Boolean soloArtistsOnly) {
        Scanner scanner = new Scanner(System.in);
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
                if (soloArtistsOnly) chosenArtists.add(new SoloArtist(this));
                else {
                    inputLoop:
                    while (true) {
                        System.out.println("\n[S]olo Artist or [G]roup Artist?\n[Q]uit");
                        switch (scanner.nextLine().toUpperCase()) {
                            case "S":
                                chosenArtists.add(new SoloArtist(this));
                                break inputLoop;
                            case "G":
                                chosenArtists.add(new GroupArtist(this));
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
    public Song[] chooseSongs() {
        Scanner scanner = new Scanner(System.in);
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
                chosenSongs.add(new Song(this));
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