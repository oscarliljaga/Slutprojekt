public class Song extends PublicEntity {
private Artist[] artists;

    public Song(String name, String URL, Artist[] artists) {
        super(name, URL);
        this.artists = artists;
    }

    public Artist[] getArtists() {
        return artists;
    }
}
