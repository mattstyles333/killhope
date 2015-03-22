package uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class MineralsAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public MineralsAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return ((KillhopeApplication)mContext.getApplicationContext()).mineralManager.getMineralsFromSearch(((KillhopeApplication) mContext.getApplicationContext()).getCurrentSearch()).size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Mineral mineral = ((KillhopeApplication) mContext.getApplicationContext()).mineralManager.getMineralsFromSearch(((KillhopeApplication) mContext.getApplicationContext()).getCurrentSearch()).get(position);
        if(convertView == null) {
            LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new ViewHolder();

            convertView = inflator.inflate(R.layout.fragment_mineral_thumb, parent, false);

            holder.txt = (TextView) convertView.findViewById(R.id.mineral_thumb_text);
            holder.img = (ImageView) convertView.findViewById(R.id.mineral_thumb_image);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txt.setText(Html.fromHtml( mineral.getName()));
//        if(holder.id == -1) {
            holder.id = mineral.getImage(0);
//        }
        try {
            holder.img.setImageResource(holder.id);
        } catch (Exception e) {
            System.err.println(holder.id);
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView img;
        TextView txt;
        int id = -1;
    }
}