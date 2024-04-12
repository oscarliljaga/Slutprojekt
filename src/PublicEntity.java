public abstract class PublicEntity {
    //Attributes
    protected final String NAME;
    protected final String URL;

    //Constructor
    public PublicEntity(String NAME, String URL) {
        this.NAME = NAME;
        this.URL = URL;
    }

    //Methods
    public String getNAME() {
        return NAME;
    }

    public String getURL() {
        return URL;
    }
}
