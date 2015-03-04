package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments.MineralThumbFragment;

public class MineralsAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public MineralsAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return MineralManager.getSize();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflator.inflate(R.layout.fragment_mineral_thumb,parent,false);

        TextView title = (TextView)view.findViewById(R.id.mineral_thumb_text);
        title.setText(Html.fromHtml(MineralManager.getMineral(position).getName()));

        return view;
    }
}