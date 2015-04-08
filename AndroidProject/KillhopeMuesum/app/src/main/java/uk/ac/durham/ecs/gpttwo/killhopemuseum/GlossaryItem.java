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
    private ArrayList<GlossaryItem> subs;

    public GlossaryItem(String name, String info){
        this.name = name;
        this.info = info;
        subs = new ArrayList<GlossaryItem>();
    }

    public GlossaryItem(JSONObject data){
        try {
            name = data.getString("word");
            info = data.getString("definition");
            subs = new ArrayList<GlossaryItem>();
            if(data.has("Types")){
                JSONArray tps = data.getJSONArray("Types");
                for(int i=0;i<tps.length();i++){
                    subs.add(new GlossaryItem(tps.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GlossaryItem> getSubs(){
        return subs;
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
        if(name.toLowerCase().contains(query.toLowerCase()) || info.toLowerCase().contains(query.toLowerCase())){
            return true;
        }
        for(GlossaryItem gi : subs){
            if(gi.search(query)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        names.add(name);
        for(GlossaryItem gi : subs) {
            names.addAll(gi.getNames());
        }
        return names;
    }
}
