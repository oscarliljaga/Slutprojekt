import java.io.Serializable;
import java.util.ArrayList;

public class GroupArtist extends Artist implements Serializable {
    //Attributes
    private ArrayList<SoloArtist> members;

    //Constructor
    public GroupArtist(String NAME, ArrayList<SoloArtist> members) {
        super(NAME);
        this.members = members;
    }

    public GroupArtist() {
        super();
        System.out.println("Add members:");
        for (Artist soloArtist : Database.chooseArtists(true)) this.addMember((SoloArtist) soloArtist);
        Database.artists.add(this);
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
