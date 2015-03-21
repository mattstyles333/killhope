package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ally on 21/03/15.
 */
public class GlossaryItem {

    private String name;
    private String info;
    private ArrayList<GlossaryItem> types;

    public GlossaryItem(String name, String info){
        this.name = name;
        this.info = info;
        types = new ArrayList<GlossaryItem>();
    }

    public GlossaryItem(JSONObject data){
        try {
            name = data.getString("word");
            info = data.getString("definition");
            types = new ArrayList<GlossaryItem>();
            if(data.has("type")){
                JSONArray tps = data.getJSONArray("Types");
                for(int i=0;i<tps.length();i++){
                    types.add(new GlossaryItem(tps.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public String getInfo(){
        return info;
    }

    public void setName(String s){
        name = s;
    }

    public void setInfo(String s){
        info = s;
    }

    public boolean search(String query){
        if(query.contains(name)){
            return true;
        }
        for(GlossaryItem gi : types){
            if(gi.search(query)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        names.add(name);
        for(GlossaryItem gi : types) {
            names.addAll(gi.getNames());
        }
        return names;
    }
}
