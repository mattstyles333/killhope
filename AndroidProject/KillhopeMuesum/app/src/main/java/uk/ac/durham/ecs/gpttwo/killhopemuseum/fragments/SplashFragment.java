package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        return rootView;
    }

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }
}