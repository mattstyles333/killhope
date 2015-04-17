package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<String> searchWords = null;


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

    public ArrayList<GlossaryItem> searchGlossary(String query) {
        if (query.equals(lastSearch) && lastSearchResults != null) {
            return lastSearchResults;
        }

        lastSearch = query;
        searchWords = new ArrayList<String>(Arrays.asList(query.split(" ")));
        ArrayList<GlossaryItem> list = new ArrayList<GlossaryItem>();
        if(query.equals("")){
            for (int i = 0; i < getGlossary().size(); i++) {
                if(getGlossary(i).getSubs().size()>0){
                    getGlossary(i).sortSubsAlphabetically();
                }
                list.add(getGlossary(i));

            }
            Collections.sort(list, new Comparator<GlossaryItem>() {
                public int compare(GlossaryItem o1, GlossaryItem o2) {
                    final String glossary1 = o1.getName();
                    final String glossary2 = o2.getName();
                    return glossary1.compareTo(glossary2);
                }
            });
        }else {
            int currentScore = 0;
            int subScore = 0;

            for (int i = 0; i < glossary.size(); i++) {

                currentScore = 0;
                subScore = 0;
                for (int l = 0; l < searchWords.size(); l++) {
                    if (getGlossary(i).getName().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                        currentScore = currentScore + 100;
                    }
                    if (getGlossary(i).getName().toLowerCase().contains(" " + searchWords.get(l).toLowerCase())) {
                        currentScore = currentScore + 100;
                    }
                    if (getGlossary(i).getName().toLowerCase().equals(searchWords.get(l).toLowerCase())) {
                        currentScore = currentScore + 100;
                    }
                    if (getGlossary(i).getInfo().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                        currentScore = currentScore + 50;
                    }
                    if (getGlossary(i).getInfo().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                        currentScore = currentScore + 50;
                    }
                    for (int j = 0; j < getGlossary(i).getSubs().size(); j++) {
                        if (getGlossary(i).getSubs().get(j).getName().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 100;
                            subScore = subScore +100;
                        }
                        if (getGlossary(i).getSubs().get(j).getName().toLowerCase().contains(" " + searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 100;
                            subScore = subScore +100;
                        }
                        if (getGlossary(i).getSubs().get(j).getName().toLowerCase().equals(searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 100;
                            subScore = subScore +100;
                        }
                        if (getGlossary(i).getSubs().get(j).getInfo().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 50;
                            subScore = subScore +50;
                        }
                        getGlossary(i).getSubs().get(j).setSearchScore(subScore);
                    }
                    if(getGlossary(i).getSubs().size()>0){
                        getGlossary(i).sortSubsByScore();
                    }
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
        }


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
