package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Ally on 26/02/15.
 */
public class MineralSubSectionAdapter extends BaseAdapter {
    private Context mContext;
    private MineralSection mineralSection;
    // Constructor
    public MineralSubSectionAdapter(Context c, MineralSection mineralSection) {
        mContext = c;
        this.mineralSection = mineralSection;
    }

    public int getCount() {
        return 4;
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

        View view = inflator.inflate(R.layout.fragment_mineral_list_item_sub,parent,false);

        TextView title = (TextView)view.findViewById(R.id.mineral_sub_title);
        TextView desc = (TextView)view.findViewById(R.id.mineral_sub_description);
        title.setText(Html.fromHtml(mineralSection.getSub(position).getTitle()));
        desc.setText(Html.fromHtml(mineralSection.getSub(position).getInfo()));
        return view;
    }
}