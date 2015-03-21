package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Matt on 2/21/2015.
 */
public class MineralThumbFragment extends Fragment {

    int id;
    String name;
    int imageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mineral_thumb, container, false);

        TextView text = (TextView)rootView.findViewById(R.id.mineral_thumb_text);
        ImageView image = (ImageView)rootView.findViewById(R.id.mineral_thumb_image);
        return rootView;
    }

    public static MineralThumbFragment newInstance(int id, String name, int imageId, Context mContext){
        MineralThumbFragment mtf = new MineralThumbFragment();

        mtf.id = id;
        mtf.name = name;
        mtf.imageId = imageId;

        return mtf;
    }
}
