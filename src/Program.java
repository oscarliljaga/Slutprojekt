import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    //Constructor
    public Program() {
        startProgram();
    }

    //Methods
    public void startProgram() {
        final Database DATABASE = Database.getInstance();
        final Scanner SCANNER = new Scanner(System.in);
        User loggedInUser = null;

        mainMenu:
        while (true) { //Main menu input loop
            login:
            while (loggedInUser == null) { //Not logged in
                System.out.println("Oscar's Songify 2.0\n[L]ogin or [Q]uit");
                switch (SCANNER.nextLine().toUpperCase()) {
                    case "L": //Login
                        System.out.print("\nUsername: ");
                        String username = SCANNER.nextLine().trim();
                        System.out.print("Password: ");
                        String password = SCANNER.nextLine().trim();

                        for (int i = 0; i < DATABASE.getUsers().size(); i++) { //Try to find User with the given username
                            User existingUser = DATABASE.getUsers().get(i);
                            if (existingUser.getNAME().equals(username)) { //User found
                                while (!password.equalsIgnoreCase("Q")) { //Loops until correct password, or user quits
                                    if (existingUser.login(password)) { //Complete login if password is correct
                                        loggedInUser = existingUser;
                                        continue mainMenu; //Continue to main menu
                                    } else {
                                        System.out.printf("\nIncorrect password. Try again or [Q]uit\nUsername: %s\nPassword: ", username);
                                        password = SCANNER.nextLine();
                                    }
                                }
                                continue login; //Input was "Q", go back to start screen
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

            if (loggedInUser.getNAME().equals("admin")) {
                System.out.println("\n[A]dd release\n[L]ogout");
                switch (SCANNER.nextLine().toUpperCase()) {
                    case "A": //Add release
                        System.out.println("\nNew release...");
                        new Release();
                        DATABASE.saveToFile();
                        continue;
                    case "L":
                        loggedInUser.logout();
                        loggedInUser = null;
                        continue;
                    default:
                        System.out.println("Please type an option in [brackets]");
                        break;
                }
            }

            System.out.printf("\nLogged in: %s\n[R]eleases\n[P]laylists\n[L]ogout\n", loggedInUser.getNAME());

            switch (SCANNER.nextLine().toUpperCase()) {
                case "R": //Browse releases
                    ArrayList<Release> releases = DATABASE.getReleases();
                    for (int i = 0; i < releases.size(); i++) {
                        Release release = releases.get(i);
                        System.out.printf("[%d] %s - %s\n",
                                i,
                                Arrays.stream(release.getARTISTS())
                                        .map(Artist::getNAME)
                                        .collect(Collectors.joining(", ")),
                                release.getNAME()); //[i] Artist1, Artist2 - Release
                    }
                    System.out.println("[Q]uit");

                    String input = SCANNER.nextLine().toUpperCase();
                    if (input.equals("Q")) break;
                    else try {
                        Release release = releases.get(Integer.parseInt(input));
                        System.out.printf("\n%s - %s\n",
                                Arrays.stream(release.getARTISTS())
                                        .map(Artist::getNAME)
                                        .collect(Collectors.joining(", ")), release.getNAME()); //Artist1, Artist2 - Release

                        Song[] songs = release.getSONGS();
                        listSongs(songs, new ArrayList<>());

                    } catch (Exception ignored) {
                        System.out.println("Please type an option in [brackets]");
                    }
                    break;
                case "P": //Browse playlists

                    break;
                case "L": //Logout
                    loggedInUser.logout();
                    loggedInUser = null;
                    continue;
                default:
                    System.out.println("Please type an option in [brackets]");
            }
        }
    }

    /**
     * Prints a numbered formatted list of Songs
     *
     * @param songs to list out
     */
    public static void listSongs(Song[] songs, ArrayList<Song> highlighted) {
        for (int i = 0; i < songs.length; i++) {
            Song song = songs[i];
            if (highlighted.contains(song)) System.out.print(">");
            System.out.printf("[%d] %s - %s\n",
                    i,
                    Arrays.stream(song.getARTISTS())
                            .map(Artist::getNAME)
                            .collect(Collectors.joining(", ")),
                    song.getNAME()); //[i] Artist1, Artist2 - Song
        }
    }
}
