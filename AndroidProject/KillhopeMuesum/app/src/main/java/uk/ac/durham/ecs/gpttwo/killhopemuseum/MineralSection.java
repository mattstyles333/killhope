package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class MineralSection {

    private ArrayList<MineralSectionSub> mineralSectionSubs;

    public MineralSection(JSONArray data) {
        mineralSectionSubs = new ArrayList<MineralSectionSub>();
        for (int i=0; i<= data.length()-1; i++){
            try {
                JSONObject mineralObj = data.getJSONObject(i);
                MineralSectionSub ms = new MineralSectionSub(mineralObj.getString("title"),mineralObj.getString("info"));
                addMineralSectionSub(ms);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void addMineralSectionSub(MineralSectionSub mss)
    {
        mineralSectionSubs.add(mss);
    }

    public MineralSectionSub getSub(int id){
        return mineralSectionSubs.get(id);
    }

}
