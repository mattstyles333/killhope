package uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Matt on 2/21/2015.
 */
public class MineralThumbFragment extends Fragment {

    int id;
    String name;
    View image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mineral_thumb, container, false);

        return rootView;
    }

    public static MineralThumbFragment newInstance(int id, Context mContext){
        MineralThumbFragment mtf = new MineralThumbFragment();


//        ImageView imageView;
//        if (convertView == null) {
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;

        mtf.name = "NAME";
        mtf.id = id;

        return mtf;
    }
}
