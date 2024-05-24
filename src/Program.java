import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    //Attributes
    final static Scanner SCANNER = new Scanner(System.in);
    final Database DATABASE = Database.getInstance();
    User loggedInUser = null;

    //Constructor
    public Program() {
        startProgram();
    }

    //Methods

    /**
     * Main Program flow
     */
    private void startProgram() {
        while (true) { //Main menu input loop
            while (loggedInUser == null) { //Not logged in
                System.out.println("Oscar's Songify 2.0\n[L]ogin or [Q]uit (admin password = \"lösen\")");
                String startInput = SCANNER.nextLine().toUpperCase();
                System.out.println();
                switch (startInput) { //Login loop
                    case "L": //Login
                        login();
                        break;
                    case "Q": //Quit program
                        return;
                    default: //Incorrect input
                        System.out.println("###Please type an option in [brackets]###");
                }
            }

            //User is logged in
            if (loggedInUser.getNAME().equals("admin")) { //If admin is logged in
                adminMenu();
            } else { //Logged in User is not admin
                userMenu();
            }
        }
    }

    /**
     * Shows the regular user menu
     */
    private void userMenu() {
        //Logged in user, not admin
        System.out.printf("Logged in: %s\n[R]eleases\n[P]laylists\n[L]ogout\n", loggedInUser.getNAME());

        String mainMenuInput = SCANNER.nextLine().toUpperCase();
        System.out.println();
        switch (mainMenuInput) {
            case "L":  //Logout
                loggedInUser.logout();
                loggedInUser = null;
                return;
            case "R":  //Browse releases
                browseReleases();
            case "P":  //Browse Playlists
                browsePlaylists();
                break;
            default: //Invalid input
                System.out.println("###Please type an option in [brackets]###");
                break;
        }
    }

    /**
     * Browse all Playlists visible to the User
     */
    private void browsePlaylists() {
        while (true) { //Input loop
            ArrayList<Playlist> availablePlaylists = loggedInUser.getAllPlaylists();

            for (Playlist playlist : DATABASE.getPlaylists()) { //Only show the User's playlists, and other User's public playlists
                if (playlist.isPublic() && playlist.getOwner() != loggedInUser && !availablePlaylists.contains(playlist))
                    availablePlaylists.add(playlist);
            }

            System.out.println("Playlists: ");
            for (int i = 0; i < availablePlaylists.size(); i++) { //Print all available Playlists
                Playlist playlist = availablePlaylists.get(i);
                System.out.printf("[%d] %s - %s\n", i, playlist.getOwner().getNAME(), playlist.getNAME()); //[i] User - Playlist
            }
            System.out.println("Type a playlist number or [Q]uit");

            String playlistInput = SCANNER.nextLine().toUpperCase();
            System.out.println();
            if (playlistInput.equals("Q")) return; //Quit
            try { //Choose playlist to list Songs
                Playlist playlist = availablePlaylists.get(Integer.parseInt(playlistInput));
                System.out.println(playlist.getNAME() + ":");
                listSongs(playlist.getSongs().toArray(new Song[0]), new ArrayList<>());
                System.out.println();
            } catch (Exception e) { //Invalid input
                e.printStackTrace();
                System.out.println("###Please type an option in [brackets]###");
            }
        }
    }

    /**
     * Browse all Releases
     */
    private void browseReleases() {
        while (true) { //Input loop
            System.out.println("Releases:");
            ArrayList<Release> releases = DATABASE.getReleases();
            for (int i = 0; i < releases.size(); i++) { //Print out releases
                Release release = releases.get(i);
                System.out.printf("[%d] %s - %s\n",
                        i,
                        Arrays.stream(release.getARTISTS())
                                .map(Artist::getNAME)
                                .collect(Collectors.joining(", ")),
                        release.getNAME()); //[i] Artist1, Artist2 - Release
            }

            //Prompt user to choose a release
            Release release;
            System.out.println("Type a release number or [Q]uit");
            String releaseInput = SCANNER.nextLine().toUpperCase();
            System.out.println();
            if (releaseInput.equals("Q")) return; //Quit to main menu
            try { //Choose release
                release = releases.get(Integer.parseInt(releaseInput));
            } catch (Exception ignored) { //Invalid input
                System.out.println("###Please type an option in [brackets]###");
                continue;
            }

            browseReleaseSongs(release);
        }
    }

    /**
     * Browse the Songs of a chosen Release
     *
     * @param release to browse Songs of
     */
    private void browseReleaseSongs(Release release) {
        while (true) {
            //Print chosen release
            System.out.printf("%s - %s:\n",
                    Arrays.stream(release.getARTISTS())
                            .map(Artist::getNAME)
                            .collect(Collectors.joining(", ")), release.getNAME()); //Artist1, Artist2 - Release:

            Song[] songs = release.getSONGS();
            listSongs(songs, new ArrayList<>()); //List songs in release
            System.out.println("Type a song number to add to playlist or [Q]uit");

            String songInput = SCANNER.nextLine().toUpperCase();
            System.out.println();
            if (songInput.equals("Q")) break; //Quit
            Song chosenSong;
            try {
                chosenSong = songs[Integer.parseInt(songInput)]; //Choose a listed song
            } catch (Exception ignored) { //Invalid input
                System.out.println("###Please type an option in [brackets]###");
                continue;
            }

            //A song was chosen
            addSongToPlaylist(chosenSong);
        }
    }

    /**
     * Choose a playlist to add a chosen song to
     *
     * @param chosenSong to add to Playlist
     */
    private void addSongToPlaylist(Song chosenSong) {
        while (true) { //Input loop
            ArrayList<Playlist> userPlaylists = loggedInUser.getAllPlaylists();
            System.out.println("Type a playlist number to add the song");
            for (int i = 0; i < userPlaylists.size(); i++) { //Print user Playlists
                Playlist playlist = userPlaylists.get(i);
                System.out.printf("[%d] %s - %s\n", i, playlist.getOwner().getNAME(), playlist.getNAME()); //[i] User - Playlist
            }
            System.out.println("[N]ew playlist\n[Q]uit");

            String playlistInput = SCANNER.nextLine().toUpperCase();
            System.out.println();
            if (playlistInput.equals("Q")) return; //Quit
            else if (playlistInput.equals("N")) { //Add new Playlist
                System.out.print("New playlist | ");
                Playlist newPlaylist = new Playlist(loggedInUser);
                newPlaylist.addSong(chosenSong); //Add the chosen song
                break;
            } else try {
                userPlaylists.get(Integer.parseInt(playlistInput)).addSong(chosenSong); //Add song to chosen playlist
            } catch (Exception ignored) { //Invalid input
                System.out.println("###Please type an option in [brackets]###");
            }
        }
    }

    /**
     * Shows the admin menu that allows for creation of new Releases
     */
    private void adminMenu() {
        while (true) {
            System.out.println("[A]dd release\n[L]ogout");
            String adminInput = SCANNER.nextLine().toUpperCase();
            System.out.println();
            switch (adminInput) {
                case "A": //Add Release
                    System.out.print("New release | ");
                    new Release();
                    break;
                case "L": //Logout
                    loggedInUser.logout();
                    loggedInUser = null;
                    return;
                default: //Invalid input
                    System.out.println("###Please type an option in [brackets]###");
                    break;
            }
        }
    }

    /**
     * Shows the menu for logging in a User
     */
    private void login() {
        System.out.print("Username: ");
        String username = SCANNER.nextLine().trim().toLowerCase();

        for (int i = 0; i < DATABASE.getUsers().size(); i++) { //Try to find User with the given username
            User existingUser = DATABASE.getUsers().get(i);
            if (existingUser.getNAME().equals(username)) { //User found
                System.out.print("Password: ");
                String password = SCANNER.nextLine().trim();
                System.out.println();
                while (!password.equalsIgnoreCase("Q")) { //Loops until correct password, or user quits
                    if (existingUser.login(password)) { //Complete login if password is correct
                        loggedInUser = existingUser;
                        return; //Continue to main menu
                    } else { //Wrong password, try again
                        System.out.printf("Incorrect password. Try again or [Q]uit\nUsername: %s\nPassword: ", username);
                        System.out.println();
                        password = SCANNER.nextLine();
                    }
                }
                return; //User quit, go back to start screen
            }
        }
        //User not found, create it and log in
        loggedInUser = new User(username);
    }

    /**
     * Prints a numbered formatted list of Songs or Releases
     *
     * @param songs to list out
     */
    public static void listSongs(Song[] songs, ArrayList<Song> highlighted) {
        for (int i = 0; i < songs.length; i++) {
            Song song = songs[i];
            if (highlighted.contains(song)) System.out.print(">"); //If song is already selected
            System.out.printf("[%d] %s - %s\n",
                    i,
                    Arrays.stream(song.getARTISTS())
                            .map(Artist::getNAME)
                            .collect(Collectors.joining(", ")),
                    song.getNAME()); //[i] Artist1, Artist2 - Song
        }
    }
}