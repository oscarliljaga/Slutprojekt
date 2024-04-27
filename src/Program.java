import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    //Attributes
    final static Scanner SCANNER = new Scanner(System.in);

    //Constructor
    public Program() {
        startProgram();
    }

    //Methods
    public void startProgram() {
        final Database DATABASE = Database.getInstance();
        User loggedInUser = null;

        mainMenu:
        while (true) { //Main menu input loop
            login:
            while (loggedInUser == null) { //Not logged in
                System.out.println("Oscar's Songify 2.0\n[L]ogin or [Q]uit (admin password = \"lösen\")");
                String startInput = SCANNER.nextLine().toUpperCase();
                System.out.println();
                switch (startInput) {
                    case "L": //Login
                        System.out.print("Username: ");
                        String username = SCANNER.nextLine().trim().toLowerCase();
                        System.out.print("Password: ");
                        String password = SCANNER.nextLine().trim();

                        for (int i = 0; i < DATABASE.getUsers().size(); i++) { //Try to find User with the given username
                            User existingUser = DATABASE.getUsers().get(i);
                            if (existingUser.getNAME().equals(username)) { //User found
                                while (!password.equalsIgnoreCase("Q")) { //Loops until correct password, or user quits
                                    if (existingUser.login(password)) { //Complete login if password is correct
                                        loggedInUser = existingUser;
                                        continue mainMenu; //Continue to main menu
                                    } else { //Wrong password, try again
                                        System.out.printf("Incorrect password. Try again or [Q]uit\nUsername: %s\nPassword: ", username);
                                        password = SCANNER.nextLine();
                                    }
                                }
                                continue login; //User quit, go back to start screen
                            }
                        }
                        loggedInUser = new User(username, password); //User not found, create it using the credentials and log in
                        break;
                    case "Q": //Quit program
                        break mainMenu;
                    default: //Incorrect input
                        System.out.println("Please type an option in [brackets]");
                }
            }
            //User is logged in

            if (loggedInUser.getNAME().equals("admin")) { //If admin is logged in
                System.out.println("\n[A]dd release\n[L]ogout");
                String adminInput = SCANNER.nextLine().toUpperCase();
                System.out.println();
                switch (adminInput) {
                    case "A": //Add release
                        System.out.print("New release | ");
                        new Release();
                        continue;
                    case "L": //Logout
                        loggedInUser.logout();
                        loggedInUser = null;
                        continue;
                    default: //Invalid input
                        System.out.println("Please type an option in [brackets]");
                        break;
                }
            }

            //Logged in user, not admin
            System.out.printf("Logged in: %s\n[R]eleases\n[P]laylists\n[L]ogout\n", loggedInUser.getNAME());

            String input = SCANNER.nextLine().toUpperCase();
            System.out.println();
            switch (input) {
                case "L":  //Logout
                    loggedInUser.logout();
                    loggedInUser = null;
                    continue;
                case "R":  //Browse releases
                    while (true) {
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
                        System.out.println("Type a release number or [Q]uit\n");

                        Release release;
                        while (true) { //Input loop
                            String releaseInput = SCANNER.nextLine().toUpperCase();
                            System.out.println();
                            if (releaseInput.equals("Q")) continue mainMenu; //Quit to main menu
                            try { //Choose release
                                release = releases.get(Integer.parseInt(releaseInput));
                                break;
                            } catch (Exception ignored) { //Invalid input
                                System.out.println("Please type an option in [brackets]");
                            }
                        }

                        String songInput;
                        while (true) {
                            //Print chosen release
                            System.out.printf("%s - %s\n",
                                    Arrays.stream(release.getARTISTS())
                                            .map(Artist::getNAME)
                                            .collect(Collectors.joining(", ")), release.getNAME()); //Artist1, Artist2 - Release

                            Song[] songs = release.getSONGS();
                            listSongs(songs, new ArrayList<>()); //List songs in release
                            System.out.println("Type a song number to add to playlist or [Q]uit");

                            songInput = SCANNER.nextLine().toUpperCase();
                            System.out.println();
                            if (songInput.equals("Q")) break; //Quit
                            Song chosenSong;
                            try {
                                chosenSong = songs[Integer.parseInt(songInput)]; //Choose a listed song
                            } catch (Exception ignored) { //Invalid input
                                System.out.println("Please type an option in [brackets]");
                                continue;
                            }

                            //A song was chosen
                            ArrayList<Playlist> userPlaylists = loggedInUser.getAllPlaylists();
                            String playlistInput;
                            while (true) { //Input loop
                                for (int i = 0; i < userPlaylists.size(); i++) { //Print user Playlists
                                    Playlist playlist = userPlaylists.get(i);
                                    System.out.printf("[%d] %s - %s\n", i, playlist.getOwner().getNAME(), playlist.getNAME()); //[i] User - Playlist
                                }
                                System.out.println("[N]ew playlist\n[Q]uit\n");

                                playlistInput = SCANNER.nextLine().toUpperCase();

                                if (playlistInput.equals("Q")) break; //Quit
                                else if (playlistInput.equals("N")) { //Add new Playlist
                                    Playlist newPlaylist = new Playlist(loggedInUser);
                                    newPlaylist.addSong(chosenSong); //Add the chosen song
                                    break;
                                } else try {
                                    userPlaylists.get(Integer.parseInt(playlistInput)).addSong(chosenSong); //Add song to chosen playlist
                                } catch (Exception ignored) { //Invalid input
                                    System.out.println("Please type an option in [brackets]");
                                }
                            }
                        }
                    }
                case "P":  //Browse Playlists
                    ArrayList<Playlist> availablePlaylists = loggedInUser.getAllPlaylists();

                    for (Playlist playlist : DATABASE.getPlaylists()) { //Only show the User's playlists, and other User's public playlists
                        if (playlist.isPublic() && playlist.getOwner() != loggedInUser)
                            availablePlaylists.add(playlist);
                    }

                    while (true) { //Input loop
                        for (int i = 0; i < availablePlaylists.size(); i++) { //Print all available Playlists
                            Playlist playlist = availablePlaylists.get(i);
                            System.out.printf("[%d] %s - %s\n", i, playlist.getOwner().getNAME(), playlist.getNAME()); //[i] User - Playlist
                        }
                        System.out.println("Type a playlist number or [Q]uit");

                        String playlistInput = SCANNER.nextLine().toUpperCase();
                        System.out.println();
                        if (playlistInput.equals("Q")) break; //Quit
                        try { //Choose playlist to list Songs
                            Playlist playlist = availablePlaylists.get(Integer.parseInt(playlistInput));
                            listSongs(playlist.getSongs().toArray(new Song[0]), new ArrayList<>());
                            System.out.println();
                        } catch (Exception ignored) { //Invalid input
                            System.out.println("Please type an option in [brackets]");
                        }
                    }
                    break;
                default: //Invalid input
                    System.out.println("Please type an option in [brackets]");
                    break;
            }
        }
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