package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        if(getResources().getConfiguration().orientation != 1){
            rootView.setBackgroundResource(R.drawable.bg_land);
        }

        if(getResources().getConfiguration().orientation == 1){

        }else{
            Point size = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(size);
            int centre = (int)((float)size.x*0.45f);
            ((RelativeLayout)rootView).setPadding((size.x - centre)/2,16,(size.x - centre)/2,16);
        }

        return rootView;
    }

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }
}