import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class GroupArtist extends Artist implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private final ArrayList<SoloArtist> members = new ArrayList<>();

    //Constructor
    public GroupArtist() {
        super();
        Database database = Database.getInstance();
        for (Artist soloArtist : database.chooseArtists(true, this.NAME + " members"))
            this.addMember((SoloArtist) soloArtist); //Adds Solo Artist members chosen from chooseArtists()
        database.addArtist(this);
    }

    //Methods
    private void addMember(SoloArtist member) {
        if (!this.members.contains(member)) this.members.add(member); //Only adds new members
    }
}
