import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class User extends PublicEntity implements Serializable {
    //Attributes
    private String password;
    private ArrayList<Playlist> playlists;
    private boolean loggedIn = false;

    //Constructor
    public User() {
        super();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPassword: ");
        password = scanner.nextLine();
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

    private boolean setPassword(String oldPassword, String newPassword) {
        if (login(oldPassword)) {
            this.password = newPassword;
            return true;
        } else return false;
    }

    public ArrayList<Playlist> getPublicPlaylists() {
        ArrayList<Playlist> publicPlaylists = new ArrayList<Playlist>();
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).isPublic()) publicPlaylists.add(playlists.get(i));
        }
        return publicPlaylists;
    }

    private ArrayList<Playlist> getAllPlaylists() {
        if (loggedIn) return playlists;
        else return null;
    }
}
