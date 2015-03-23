package uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.MineralSection;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 26/02/15.
 */
public class MineralSectionAdapter extends BaseAdapter {
    private Context mContext;
    private Mineral mineral;
    private boolean isFront = false;
    // Constructor
    public MineralSectionAdapter(Context c, Mineral mineral, boolean isFront) {
        mContext = c;
        this.mineral = mineral;
        this.isFront = isFront;
    }

    public int getCount() {
        return 2;
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

        View view = inflator.inflate(R.layout.fragment_mineral_list_item,parent,false);

//        ListView listView = (ListView) view.findViewById(R.id.mineral_sublist);

//        MineralSubSectionAdapter adapter = new MineralSubSectionAdapter(mContext,mineral.getMineralSection(position));

//        listView.setAdapter(adapter);
        int extra = 0;
        if(!isFront)extra = 2;

        TextView sectionName = (TextView)view.findViewById(R.id.section_name);
        sectionName.setText("Section " + (position + extra + 1));

        MineralSection ms = mineral.getMineralSection(position + extra);

        LinearLayout listView = (LinearLayout) view.findViewById(R.id.mineral_sublist);

        for(int i=0;i<ms.getCount();i++) {
            View subview = inflator.inflate(R.layout.fragment_mineral_list_item_sub, parent, false);

            TextView title = (TextView) subview.findViewById(R.id.mineral_sub_title);
            TextView desc = (TextView) subview.findViewById(R.id.mineral_sub_description);
            title.setText(Html.fromHtml(ms.getSub(i).getTitle()));
            desc.setText(Html.fromHtml(ms.getSub(i).getInfo()));

            listView.addView(subview);
        }

        return view;
    }
}