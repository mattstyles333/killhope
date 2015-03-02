package uk.ac.durham.ecs.gpttwo.killhopemuesum;

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
    private static ArrayList<Mineral> mineral = new ArrayList<Mineral>();;

    public static void loadMinerals(Context context){
        JSONObject mineralData = parseJSONData(context);
        for (int i=0; i<= mineralData.length(); i++){
            
        }

    }

    public static void addMineral(Mineral m){
        mineral.add(m);
    }

    public static JSONObject parseJSONData(Context context) {
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
            System.out.println(JSONString);
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


}
