public class Release extends PublicEntity{
    private Artist[] artists;
    private Song[] songs;

    public Release(String name, String URL, Artist[] artists) {
        super(name, URL);
        this.artists = artists;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public Song[] getSongs() {
        return songs;
    }
}
