public abstract class PublicEntity {
    protected String name;
    protected String URL;

    public PublicEntity (String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }
}
