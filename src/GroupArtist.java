import java.util.ArrayList;

public class GroupArtist extends Artist {
    //Attributes
    private ArrayList<SoloArtist> members;

    //Constructor
    public GroupArtist(String NAME, String URL) {
        super(NAME, URL);
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
