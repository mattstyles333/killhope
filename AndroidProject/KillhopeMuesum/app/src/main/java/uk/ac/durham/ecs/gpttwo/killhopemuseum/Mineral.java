package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.content.Context;

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
    private int lastSearchScore;
    private int img3d;
    private String qrid;
    private ArrayList<Integer> images;
    private ArrayList<MineralSection> mineralSection;

    public Mineral(JSONObject data, Context context){
        mineralSection = new ArrayList<MineralSection>();
        images = new ArrayList<Integer>();
        try {
            name = data.getString("name");
            formula = data.getString("formula");
            String img3dname = data.getString("3dcrystal");
            img3d = context.getResources().getIdentifier(img3dname, "raw", context.getPackageName());
            qrid = data.getString("qrcode");
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
        try {
            JSONArray mineralObj = data.getJSONArray("images");
            for(int i=0;i<mineralObj.length();i++){
                String resname = mineralObj.getString(i);
                int id = context.getResources().getIdentifier(resname, "drawable", context.getPackageName());
                images.add(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQRId() {
        return qrid;
    }

    public void setQRId(String id) {
        this.qrid = id;
    }

    public int getImg3d() {
        return img3d;
    }

    public void setImg3d(int img3d) {
        this.img3d = img3d;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getImage(int i){
        return images.get(i);
    }

    public void addImage(int s){
        images.add(s);
    }

    public int getImageLength(){
        return images.size();
    }

    public void addMineralSection(MineralSection ms)
    {
        mineralSection.add(ms);
    }

    public MineralSection getMineralSection(int id){
        return mineralSection.get(id);
    }

    public int getCount(){
        return mineralSection.size();
    }

    public int[] getImages(){
        int[] l = new int[images.size()];
        for(int i=0;i<l.length;i++){
            l[i] = images.get(i);
        }
        return l;
    }

    public int getLastSearchScore() {return lastSearchScore;}

    public void setLastSearchScore(int lastSearchScore) {this.lastSearchScore = lastSearchScore;}

}
