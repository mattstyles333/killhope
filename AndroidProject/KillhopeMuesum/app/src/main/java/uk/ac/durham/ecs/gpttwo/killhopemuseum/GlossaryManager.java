package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.GlossaryDialogFragment;

/**
 * Created by Ally on 21/03/15.
 */
public class GlossaryManager {

    private String lastSearch = "";
    private ArrayList<GlossaryItem> lastSearchResults = null;
    private ArrayList<GlossaryItem> glossary = new ArrayList<GlossaryItem>();


    public void loadGlossary(Context context){
        JSONObject glossaryData = parseJSONData(context);
        JSONArray glossaryArray = null;
        try {
            glossaryArray = glossaryData.getJSONArray("glossary");
            for(int i=0;i<glossaryArray.length();i++){
                GlossaryItem gi = new GlossaryItem(glossaryArray.getJSONObject(i));
                addGlossary(gi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addGlossary(GlossaryItem gi){
        glossary.add(gi);
    }

    public int getSize(){
        return glossary.size();
    }

    public GlossaryItem getGlossary(int id){
        return glossary.get(id);
    }

    public JSONObject parseJSONData(Context context) {
        String JSONString = null;
        JSONObject jsonObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = context.getAssets().open("GlossaryData.json");

            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
//            System.out.println(JSONString);
            jsonObject = new JSONObject(JSONString);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    private int currentScore = 0;
    public ArrayList<GlossaryItem> searchGlossary(String query) {
        if (query.equals(lastSearch) && lastSearchResults != null) {
            return lastSearchResults;
        }
        lastSearch = query;
        ArrayList<GlossaryItem> list = new ArrayList<GlossaryItem>();

        for (int i = 0; i < glossary.size(); i++) {
            currentScore = 0;
            if (getGlossary(i).getName().toLowerCase().contains(query.toLowerCase())) {
                currentScore = currentScore + 100;
            }
            if (getGlossary(i).getName().toLowerCase().contains(" " + query.toLowerCase())) {
                currentScore = currentScore + 100;
            }
            if (getGlossary(i).getName().toLowerCase().equals(query.toLowerCase())) {
                currentScore = currentScore + 100;
            }
            if (getGlossary(i).getInfo().toLowerCase().contains(query.toLowerCase())) {
                currentScore = currentScore + 50;
            }
            getGlossary(i).setSearchScore(currentScore);
            if (getGlossary(i).getSearchScore() > 10) {

                list.add(getGlossary(i));

            }
        }
        Collections.sort(list, new Comparator<GlossaryItem>() {
            public int compare(GlossaryItem o1, GlossaryItem o2) {
                final int glossary1 = o1.getSearchScore();
                final int glossary2 = o2.getSearchScore();
                return glossary2 > glossary1 ? 1
                        : glossary2 < glossary1 ? -1 : 0;
            }
        });


        lastSearchResults = list;
        return list;
        }


    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(GlossaryItem gi : glossary){
            names.addAll(gi.getNames());
        }
        return names;
    }
    public ArrayList<GlossaryItem> getGlossary(){
        ArrayList<GlossaryItem> g = new ArrayList<GlossaryItem>();
        for(GlossaryItem gi : glossary){
            g.add(gi);
        }
        return g;
    }


}
