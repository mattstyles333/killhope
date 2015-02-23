package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.MineralThumbFragment;

public class MineralsAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<MineralThumbFragment> mineralThumbs = new ArrayList<MineralThumbFragment>();
    // Constructor
    public MineralsAdapter(Context c) {
        mContext = c;
        MineralThumbFragment mtf = MineralThumbFragment.newInstance(1,"Name", R.drawable.ic_launcher, mContext);

        mineralThumbs.add(mtf);
    }

    public int getCount() {
        return mineralThumbs.size();
    }

    public Object getItem(int position) {
        return mineralThumbs.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflator.inflate(R.layout.fragment_mineral_thumb,parent,false);

        return view;
    }
}