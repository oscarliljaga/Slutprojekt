import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class User extends PublicEntity implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private final String password;
    private boolean loggedIn;
    private final ArrayList<Playlist> playlists = new ArrayList<>();

    //Constructor
    public User(String NAME, String password) {
        super(NAME.toLowerCase());
        this.password = password;
        loggedIn = true;
        Database.getInstance().addUser(this);
    }

    public User(String NAME) {
        super(NAME.toLowerCase());
        Scanner scanner = Program.SCANNER;

        System.out.print("New user \"" + NAME + "\" | Password: ");
        String password = scanner.nextLine();
        System.out.println();
        while (password.isBlank()) {
            System.out.print("Password cannot be blank, try again: ");
            password = scanner.nextLine();
        }
        this.password = password;
        loggedIn = true;
        Database.getInstance().addUser(this);
    }

    //Methods
    public boolean login(String password) {
        if (Objects.equals(password, this.password)) {
            loggedIn = true;
            return true;
        } else return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = false;
    }

    public ArrayList<Playlist> getAllPlaylists() {
        if (loggedIn) return playlists;
        else return null;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }
}
