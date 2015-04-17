package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ally on 21/03/15.
 */
public class GlossaryItem {

    private String name;
    private String info;
    private ArrayList<GlossaryItem> subs;
    private int searchScore;

    public GlossaryItem(String name, String info){
        this.name = name;
        this.info = info;
        subs = new ArrayList<GlossaryItem>();
        searchScore = 0;
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

    public void sortSubsAlphabetically()
    {

        Collections.sort(subs, new Comparator<GlossaryItem>() {
            public int compare(GlossaryItem o1, GlossaryItem o2) {
                final String glossary1 = o1.getName();
                final String glossary2 = o2.getName();
                return glossary1.compareTo(glossary2);
            }
        });
    }

    public void sortSubsByScore()
    {

        Collections.sort(subs, new Comparator<GlossaryItem>() {
            public int compare(GlossaryItem o1, GlossaryItem o2) {
                final int glossary1 = o1.getSearchScore();
                final int glossary2 = o2.getSearchScore();
                return glossary2 > glossary1 ? 1
                        : glossary2 < glossary1 ? -1 : 0;
            }
        });
    }

    public void setName(String s){
        name = s;
    }

    public void setInfo(String s){
        info = s;
    }
    public int getSearchScore() {
        return searchScore;
    }

    public void setSearchScore(int searchScore) {
        this.searchScore = searchScore;
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
