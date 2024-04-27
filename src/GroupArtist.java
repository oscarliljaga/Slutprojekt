import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class GroupArtist extends Artist implements Serializable {
    //Attributes
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<SoloArtist> members = new ArrayList<>();

    //Constructor
    public GroupArtist(String NAME, ArrayList<SoloArtist> members) {
        super(NAME);
        this.members = members;
    }

    public GroupArtist(Database database) {
        super();
        System.out.println("\nAdd members: ");
        for (Artist soloArtist : database.chooseArtists(true)) this.addMember((SoloArtist) soloArtist);
        database.addArtist(this);
    }

    //Methods
    public ArrayList<SoloArtist> getMembers() {
        return members;
    }

    private void addMember(SoloArtist member) {
        if (!this.members.contains(member)) this.members.add(member); //Only adds new members
    }

    private void removeMember(SoloArtist member) {
        this.members.remove(member);
    }
}
