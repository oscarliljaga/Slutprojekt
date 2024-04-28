import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Singleton class which saves user generated data to a local file:
 * Releases, Artists, Songs, Users, Playlists
 */
public class Database implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private static Database INSTANCE;
    private final ArrayList<Release> releases = new ArrayList<>();
    private final ArrayList<Artist> artists = new ArrayList<>();
    private final ArrayList<Song> songs = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Playlist> playlists = new ArrayList<>();

    //Constructor
    private Database() {
    }

    //Methods

    /**
     * Creates a Database instance if none exists
     *
     * @return the only Database instance
     */
    public static Database getInstance() {
        if (INSTANCE == null) {
            try { //Tries to import existing Database from file "database"
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database"));
                INSTANCE = (Database) ois.readObject();
            } catch (FileNotFoundException ignored) { //Saved Database doesn't exist, create and save a new Database
                INSTANCE = new Database();
                INSTANCE.addUser(new User("admin", "lösen")); //Adds a default admin user
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    /**
     * Saves Database to file "database"
     */
    public static void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database"));
            oos.writeObject(INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Release> getReleases() {
        return releases;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void addRelease(Release release) {
        if (!releases.contains(release)) releases.add(release);
        saveToFile();
    }

    public void addArtist(Artist artist) {
        if (!artists.contains(artist)) artists.add(artist);
    }

    public void addSong(Song song) {
        if (!songs.contains(song)) songs.add(song);
    }

    public void addUser(User user) {
        if (!users.contains(user)) users.add(user);
        saveToFile();
    }

    public void addPlaylist(Playlist playlist) {
        if (!playlists.contains(playlist)) playlists.add(playlist);
        saveToFile();
    }

    /**
     * Prompts menu for picking out Artists from the database.
     * Pass true to only show SoloArtists
     *
     * @param header String to show what Artists are being chosen for
     * @return chosen Artists
     */
    public ArrayList<Artist> chooseArtists(String header) {
        return chooseArtists(false, header);
    }

    /**
     * Prompts menu for picking out Artists from the database
     *
     * @param soloArtistsOnly to only show SoloArtists
     * @param header          String to show what Artists are being chosen for
     * @return chosen Artists
     */
    public ArrayList<Artist> chooseArtists(Boolean soloArtistsOnly, String header) {
        Scanner scanner = Program.SCANNER;
        ArrayList<Artist> chosenArtists = new ArrayList<>();

        while (true) {
            System.out.print(header + " | Choose artists: \n");
            for (int i = 0; i < artists.size(); i++) { //List all Artists
                Artist artist = artists.get(i);
                if (soloArtistsOnly && !(artist instanceof SoloArtist))
                    continue; //Skip if soloArtistsOnly and artist is not SoloArtist
                if (chosenArtists.contains(artist)) System.out.print(">"); //Artist already chosen
                System.out.printf("[%d] %s\n", i, artist.getNAME());
            }

            System.out.println("[N]ew artist\n[Q]uit");
            String input = scanner.nextLine().toUpperCase();
            System.out.println();
            if (input.equals("N")) { //Create a new Artist
                if (soloArtistsOnly) {
                    System.out.print("New solo artist | ");
                    chosenArtists.add(new SoloArtist());
                    System.out.println();
                } else {
                    inputLoop:
                    while (true) {
                        System.out.println("[S]olo artist or [G]roup artist?\n[Q]uit");
                        String artistTypeInput = scanner.nextLine().toUpperCase();
                        System.out.println();
                        switch (artistTypeInput) {
                            case "S": //Solo artist
                                System.out.print("New solo artist | ");
                                chosenArtists.add(new SoloArtist());
                                break inputLoop;
                            case "G": //Group artist
                                System.out.print("New group artist | ");
                                chosenArtists.add(new GroupArtist());
                                break inputLoop;
                            case "Q": //Quit
                                break inputLoop;
                            default: //Invalid input
                                System.out.println("###Please type an option in [brackets]###");
                        }
                    }
                }
            } else if (input.equals("Q")) { //Finish choosing
                System.out.println();
                break;
            } else { //Select existing Artist
                try {
                    Artist artist = artists.get(Integer.parseInt(input));
                    if (!chosenArtists.contains(artist)) chosenArtists.add(artist);
                    else chosenArtists.remove(artist); //Deselect already chosen artist
                } catch (Exception ignored) { //Invalid input
                    System.out.println("###Please type an option in [brackets]###");
                }
            }
        }
        return chosenArtists;
    }

    /**
     * Prompts menu for picking out Songs from the database
     *
     * @param header String to show what songs are being chosen for
     * @return chosen Songs
     */
    public ArrayList<Song> chooseSongs(String header) {
        Scanner scanner = Program.SCANNER;
        ArrayList<Song> chosenSongs = new ArrayList<>();

        while (true) { //Add multiple Songs
            System.out.println(header + " | Choose songs: ");
            Program.listSongs(songs.toArray(new Song[0]), chosenSongs);
            System.out.println("[N]ew song\n[Q]uit");

            String input = scanner.nextLine().toUpperCase();
            System.out.println();
            if (input.equals("N")) { //Create a new Song
                System.out.print("New song | ");
                chosenSongs.add(new Song());
            } else if (input.equals("Q")) break; //Finish choosing
            else try { //Select existing Song
                    Song song = songs.get(Integer.parseInt(input));
                    if (!chosenSongs.contains(song)) chosenSongs.add(song);
                    else chosenSongs.remove(song); //Deselect already chosen song
                } catch (Exception ignored) { //Invalid input
                    System.out.println("###Please type an option in [brackets]###");
                }
        }
        return chosenSongs;
    }
}