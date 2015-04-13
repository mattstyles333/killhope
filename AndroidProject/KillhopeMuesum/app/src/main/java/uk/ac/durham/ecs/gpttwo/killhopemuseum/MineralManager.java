package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Robert Clarke on 26-02-15.
 */
public class MineralManager {
    private ArrayList<Mineral> mineral = new ArrayList<Mineral>();

    public void loadMinerals(Context context){
        JSONObject mineralData = parseJSONData(context);
        for (int i=0; i<= mineralData.length()-1; i++){
            try {
                JSONObject mineralObj = mineralData.getJSONObject("" + i);
                Mineral m = new Mineral(mineralObj,context);
                addMineral(m);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void addMineral(Mineral m){
        mineral.add(m);
    }

    public int getSize(){
        return mineral.size();
    }

    public Mineral getMineral(int mineralID){
        return mineral.get(mineralID);
    }

    public int getMineralIndex(Mineral mineralToFind){
        return mineral.indexOf(mineralToFind);
    }

    public JSONObject parseJSONData(Context context) {
        String JSONString = null;
        JSONObject jsonObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = context.getAssets().open("MineralData.json");

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

    public int getMineralIdFromQRId(String qrid){
        for(int i=0;i<mineral.size();i++){
            if(mineral.get(i).getQRId().equals(qrid)){
                return i;
            }
        }
        return -1;
    }

    private String lastSearch = "";
    private ArrayList<Mineral> lastSearchMinerals = null;
    private ArrayList<String> searchWords = null;
    private int currentScore = 0;



    public ArrayList<Mineral> getMineralsFromSearch(String search){
        if(lastSearchMinerals == null || !lastSearch.equals(search)) {
            lastSearchMinerals = new ArrayList<Mineral>();
            searchWords = new ArrayList<String>(Arrays.asList(search.split(" ")));
            lastSearch = search;

            if(search.equals("")){
                for (int i = 0; i < getSize(); i++) {
                    lastSearchMinerals.add(getMineral(i));
                }
                Collections.sort(lastSearchMinerals, new Comparator<Mineral>() {
                    public int compare(Mineral o1, Mineral o2) {
                        final String mineral1 = o1.getName();
                        final String mineral2 = o2.getName();
                        return mineral1.compareTo(mineral2);
                    }
                });
            }else{
                //The search function, currently adds if the title contains the query. Lowercase important.
                for (int i = 0; i < getSize(); i++) {
                    currentScore = 0;
                    for (int l = 0; l < searchWords.size(); l++) {

                        if (getMineral(i).getName().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 100;
                        }
                        if (getMineral(i).getName().toLowerCase().contains(" "+searchWords.get(l).toLowerCase())){
                            currentScore = currentScore + 100;
                        }
                        if (getMineral(i).getName().toLowerCase().equals(searchWords.get(l).toLowerCase())) {
                            currentScore = currentScore + 100;
                        }

                        if (getMineral(i).getFormula().toLowerCase().contains(searchWords.get(l).toLowerCase().replace("</sub>", "").replace("<sub>", "").replace("</sup>", "").replace("<sup>", ""))) {
                            currentScore = currentScore + 50;
                        }
                        for (int j = 0; j < getMineral(i).getCount(); j++) {
                            for (int k = 0; k < getMineral(i).getMineralSection(j).getCount(); k++) {
                                if (getMineral(i).getMineralSection(j).getSub(k).getInfo().toLowerCase().contains(searchWords.get(l).toLowerCase())) {
                                    currentScore = currentScore + 10;
                                }
                            }
                        }
                    }
                    getMineral(i).setLastSearchScore(currentScore);
                    if (getMineral(i).getLastSearchScore() > 10) {

                        lastSearchMinerals.add(getMineral(i));

                    }


                    //sort the minerals based on the score
                    Collections.sort(lastSearchMinerals, new Comparator<Mineral>() {
                        public int compare(Mineral o1, Mineral o2) {
                            final int mineral1 = o1.getLastSearchScore();
                            final int mineral2 = o2.getLastSearchScore();
                            return mineral2 > mineral1 ? 1
                                    : mineral2 < mineral1 ? -1 : 0;
                        }
                    });
                }
            }


        }

        return lastSearchMinerals;
    }
}
