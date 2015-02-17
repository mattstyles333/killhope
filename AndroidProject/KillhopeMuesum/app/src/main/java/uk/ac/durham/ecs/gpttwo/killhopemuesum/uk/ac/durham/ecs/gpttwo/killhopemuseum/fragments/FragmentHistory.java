package uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class FragmentHistory extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        return rootView;
    }

    public static FragmentHistory newInstance(){
        return new FragmentHistory();
    }
}
