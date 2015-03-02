package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import java.util.ArrayList;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class MineralSection {

    private ArrayList<MineralSectionSub> mineralSectionSubs;

    public MineralSection() {
        mineralSectionSubs = new ArrayList<MineralSectionSub>();
    }

    public void addMineralSectionSub(MineralSectionSub mss)
    {
        mineralSectionSubs.add(mss);
    }

}
