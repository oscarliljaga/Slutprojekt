public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*
abstract PublicEntity
    #name: String
    #url: String

    +getName(): String
    +getURL(): String

abstract Artist extends PublicEntity
    #songs: ArrayList<Song>
    #releases: ArrayList<Release>

    +getSongs(): ArrayList<Song>
    +getReleases(): ArrayList<Release>


SoloArtist extends Artist


GroupArtist extends Artist
    -members: ArrayList<SoloArtist>

    +getMembers: ArrayList<SoloArtist>
    -addMember(SoloArtist): void
    -removeMember(SoloArtist): void


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


Playlist extends PublicEntity
    -user: User
    -isPublic: Boolean
    -songs: ArrayList<Song>

    +getUser(): User
    -setPublic(boolean): void
    +getSongs(): ArrayList<Song> //Only if (current user == this.user || public)


User extends PublicEntity
    -playlists: ArrayList<Playlist>

    +getPlaylists(): Playlist[] //Only return public Playlist
*/