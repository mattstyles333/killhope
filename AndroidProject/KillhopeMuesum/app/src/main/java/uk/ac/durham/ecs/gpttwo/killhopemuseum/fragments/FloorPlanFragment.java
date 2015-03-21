package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;


public class FloorPlanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_floorplan, container, false);

        ImageViewTouch mainImage= (ImageViewTouch) rootView.findViewById(R.id.floor_image);
        mainImage.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        mainImage.setImageBitmap(bmp, null, -1, 8f);

        return rootView;
    }

    public static FloorPlanFragment newInstance(){
        return new FloorPlanFragment();
    }
}
