package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

public class HelpPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layID = getArguments().getInt("layID");
        View rootView = inflater.inflate(layID, container, false);
        return rootView;
    }

    public static HelpPageFragment newInstance(int pos){
        HelpPageFragment hpf = new HelpPageFragment();
        Bundle args = new Bundle();
        int layID = -1;
        switch(pos){
            case(0):
                layID = R.layout.fragment_help_page_0;
                break;
            case(1):
                layID=R.layout.fragment_help_page_1;
                break;
            case(2):
                layID=R.layout.fragment_help_page_2;
                break;
            case(3):
                layID=R.layout.fragment_help_page_3;
                break;
            case(4):
                layID=R.layout.fragment_help_page_4;
                break;

            default:
                layID = R.layout.fragment_help_page;
                break;
        }

        args.putInt("layID", layID);
        hpf.setArguments(args);
        return hpf;
    }
}
