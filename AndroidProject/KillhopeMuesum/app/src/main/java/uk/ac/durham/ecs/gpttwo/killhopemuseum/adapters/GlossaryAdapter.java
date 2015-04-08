package uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryItem;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryManager;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class GlossaryAdapter extends BaseAdapter {
    private Context mContext;
    private String query = "";

    // Constructor
    public GlossaryAdapter(Context c) {
        mContext = c;
    }

    public int getCount(){
        return ((KillhopeApplication)mContext.getApplicationContext()).glossaryManager.searchGlossary(query).size();
    }

    public void search(String s){

        if(!query.equals(s)){
            query = s;
            notifyDataSetChanged();

        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GlossaryItem gi = ((KillhopeApplication)mContext.getApplicationContext()).glossaryManager.searchGlossary(query).get(position);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflator.inflate(R.layout.fragment_glossary_item, parent, false);


        TextView tv = (TextView) convertView.findViewById(R.id.glossary_item_textview_name);
        tv.setText(Html.fromHtml(gi.getName()));

        tv = (TextView) convertView.findViewById(R.id.glossary_item_textview_description);
        tv.setText(Html.fromHtml(gi.getInfo()));

        LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.glossary_subitems);
        for(GlossaryItem sub : gi.getSubs()){
            LinearLayout subView = (LinearLayout)inflator.inflate(R.layout.fragment_glossary_item,null,false);

            TextView tvsub = (TextView) subView.findViewById(R.id.glossary_item_textview_name);
            tvsub.setText(Html.fromHtml(sub.getName()));

            tvsub = (TextView) subView.findViewById(R.id.glossary_item_textview_description);
            tvsub.setText(Html.fromHtml(sub.getInfo()));

            ll.addView(subView);
        }
        return convertView;
    }

}