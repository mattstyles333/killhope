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
    private ArrayList<String> g;
    private String query;
    public GlossaryManager gm;

    // Constructor
    public GlossaryAdapter(Context c,ArrayList<String> glossary) {
g=glossary;
        mContext = c;
    }
    public int getCount(){
        return g.size();
}

    @Override
    public Object getItem(int position) {
        return g.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String gi = g.get(position);
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflator.inflate(R.layout.fragment_glossary_item, parent, false);
        TextView tv = (TextView) convertView.findViewById(R.id.glossary_item_textview);
        tv.setText(gi);
        return convertView;
    }

}