package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class Mineral {
    private String name;
    private String formula;
    private ArrayList<MineralSection> mineralSection;

    public Mineral(JSONObject data){
        mineralSection = new ArrayList<MineralSection>();
        try {
            name = data.getString("name");
            formula = data.getString("formula");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=1; i<= 4; i++){
            try {
                JSONArray mineralObj = data.getJSONArray("stage" + i);
                MineralSection ms = new MineralSection(mineralObj);
                addMineralSection(ms);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void addMineralSection(MineralSection ms)
    {
        mineralSection.add(ms);
    }

}
