import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User extends PublicEntity implements Serializable {
    //Attributes
    private String password;
    private ArrayList<Playlist> playlists;
    private boolean loggedIn = false;

    //Constructor
    public User(String NAME, String password) {
        super(NAME);
        this.password = password;
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
