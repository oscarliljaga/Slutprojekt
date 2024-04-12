public class Song extends PublicEntity {
    //Attributes
    private final Artist[] ARTISTS;

    //Constructor
    public Song(String name, String URL, Artist[] ARTISTS) {
        super(name, URL);
        this.ARTISTS = ARTISTS;
    }

    //Methods
    public Artist[] getARTISTS() {
        return ARTISTS;
    }
}
