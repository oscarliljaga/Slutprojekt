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
    private ArrayList<Release> releases = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();

    //Constructor
    private Database() {
        users.add(new User("admin", "lösen")); //Adds a default admin user
        this.saveToFile();
    }

    //Methods
    public static Database getInstance() { //Returns or creates the only Database instance
        if (INSTANCE == null) {
            try { //Tries to import existing Database from file "database"
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database"));
                INSTANCE = (Database) ois.readObject();
            } catch (FileNotFoundException ignored) { //Saved Database doesn't exist, create and save a new Database
                INSTANCE = new Database();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    /**
     * Saves Database to file "database"
     */
    public boolean saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database"));
            oos.writeObject(this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Release> getReleases() {
        return releases;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void addRelease(Release release) {
        releases.add(release);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    /**
     * Prompts menu for picking out Artists from the database.
     * Pass true to only show SoloArtists
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
                        }
                    }
                }
            } else if (input.equals("Q")) break; //Finish choosing
            else { //Select existing Artist
                try {
                    chosenArtists.add(artists.get(Integer.parseInt(input)));
                } catch (Exception ignored) {
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
            Program.listSongs(songs.toArray(new Song[0]), chosenSongs);
            System.out.println("[N]ew song\n[Q]uit");

            String input = scanner.nextLine().toUpperCase();
            if (input.equals("N")) { //Create a new Song
                chosenSongs.add(new Song(this));
            } else if (input.equals("Q")) break; //Finish choosing
            else try {//Select existing Song
                    chosenSongs.add(songs.get(Integer.parseInt(input)));
                } catch (Exception ignored) {
                    System.out.println("Please type an option in [brackets]");
                }

        }
        return chosenSongs.toArray(new Song[0]);
    }
}