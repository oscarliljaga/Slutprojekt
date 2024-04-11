public class Main {
    public static void main(String[] args) {
        new Program();
    }
}

/*
abstract PublicEntity
    #name: String
    #url: String

    +getName(): String
    +getURL(): String

Artist extends PublicEntity
    #songs: ArrayList<Song>
    #releases: ArrayList<Release>

    +getSongs(): ArrayList<Song>
    +getReleases(): ArrayList<Release>

GroupArtist extends Artist
    -members: ArrayList<Artist>

    +getMembers: ArrayList<Artist>
    -addMember(Artist): void
    -removeMember(Artist): void


Song extends PublicEntity
    -artists: Artist[]
    -releases: ArrayList<Release>

    Song(name: String, url: String, artists: Artist[])
    +getArtists(): Artist[]
    +getReleases(): ArrayList<Release>


Release extends PublicEntity
    -artists: Artist[]
    -songs: Song[]

    Release(name: String, url: String, artists: Artist[], songs: Song[]) //Adds each song, and this, to each Artist
    +getArtists(): Artist[]
    +getSongs(): Song[]


User extends PublicEntity
    -password: String
    -playlists: ArrayList<Playlist>

    +login(password: String): Boolean
    -setPassword(password: String): Boolean
    +getPlaylists(): Playlist[] //Only return public Playlist


Playlist extends PublicEntity
    -isPublic: Boolean
    -owner: User
    -songs: ArrayList<Song>

    +isPublic(): Boolean
    -setPublic(boolean): void
    +getOwner(): User
    +getSongs(): ArrayList<Song> //Only if (current user == this.user || public)
    -addSong(song: Song): void
    -removeSong(song: Song): void

*/