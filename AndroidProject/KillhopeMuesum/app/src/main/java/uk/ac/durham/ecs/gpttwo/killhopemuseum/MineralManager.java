package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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



    public ArrayList<Mineral> getMineralsFromSearch(String search){
        if(lastSearchMinerals == null || !lastSearch.equals(search)) {
            lastSearchMinerals = new ArrayList<Mineral>();
            lastSearch = search;

            //The search function, currently adds if the title contains the query. Lowercase important.
            for (int i = 0; i < getSize(); i++) {
                if (getMineral(i).getName().toLowerCase().contains(search.toLowerCase())) {
                    lastSearchMinerals.add(getMineral(i));
                }
            }
        }
        return lastSearchMinerals;
    }
}
