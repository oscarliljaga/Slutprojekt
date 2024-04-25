import javax.swing.*;
import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    //Attributes
    private Database database = new Database();
    private ArrayList<Artist> artists = database.artists;
    private ArrayList<Song> songs = database.songs;
    private ArrayList<Release> releases = database.releases;

    private Scanner scanner = new Scanner(System.in);

    //Constructor
    public Program() {
        startProgram();
    }

    //Methods
    public void startProgram() {
        mainMenu:
        while (true) { //Main menu input loop
            System.out.println("[A]dd release\n[B]rowse database\n[Q]uit");

            switch (scanner.nextLine().toUpperCase()) {
                case "A": //Add release
                    System.out.println("Release name: ");
                    String releaseName = scanner.nextLine();
                    System.out.println("Release URL: ");
                    String releaseURL = scanner.nextLine();

                    ArrayList<Artist> releaseArtists = new ArrayList<>();

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
                                        Artist newSoloArtist = new SoloArtist();
                                        artists.add(newSoloArtist);
                                        releaseArtists.add(newSoloArtist);
                                        break inputLoop;
                                    case "G":
                                        Artist newGroupArtist = new GroupArtist();
                                        artists.add(newGroupArtist);
                                        releaseArtists.add(newGroupArtist);
                                        break inputLoop;
                                    case "Q":
                                        break addArtists;
                                    default:
                                        System.out.println("Please type an option in [brackets]");
                                        break;
                                }
                            }
                        } else if (input.equals("Q")) break addArtists; //Continue with release creation
                        else { //Select existing Artist
                            try {
                                releaseArtists.add(artists.get(Integer.parseInt(input)));
                            } catch (Exception e) {
                                System.out.println("Please type an option in [brackets]");
                            }
                        }
                    }

                    ArrayList<Song> releaseSongs = new ArrayList<>();

                    addSongs:
                    while (true) { //Add multiple Songs
                        for (int i = 0; i < songs.size(); i++) { //List all Songs
                            Song song = songs.get(i);
                            System.out.println(String.format("[%d] %s - %s | %s",
                                    i,
                                    Arrays.stream(song.getARTISTS())
                                            .map(Artist::getNAME)
                                            .collect(Collectors.joining(", ")),
                                    song.getNAME(),
                                    song.getURL()));
                        }
                        System.out.println("[N]ew song\n[Q]uit");

                        String input = scanner.nextLine().toUpperCase();
                        if (input.equals("N")) { //Create a new Song
                            Song newSong = new Song();
                            songs.add(newSong);
                            releaseSongs.add(newSong);
                        } else if (input.equals("Q")) break addSongs; //Continue with release creation
                        else { //Select existing Song
                            try {
                                releaseSongs.add(songs.get(Integer.parseInt(input)));
                            } catch (Exception e) {
                                System.out.println("Please type an option in [brackets]");
                            }
                        }
                    }

                    releases.add(new Release(releaseName, releaseURL, releaseArtists.toArray(new Artist[0]), releaseSongs.toArray(new Song[0])));

                    break;
                case "B": //Browse database
                    for (int i = 0; i < releases.size(); i++) {
                        System.out.println(releases.get(i).getNAME());
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

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }
}
