package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Ally on 19/02/15.
 */
public class HelpPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help_page, container, false);
        return rootView;
    }



    public static HelpPageFragment newInstance(){
        return new HelpPageFragment();
    }
}
