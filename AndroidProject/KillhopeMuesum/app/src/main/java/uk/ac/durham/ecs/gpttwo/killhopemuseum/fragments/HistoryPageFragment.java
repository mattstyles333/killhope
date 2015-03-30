package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class HistoryPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layID = getArguments().getInt("layID");
        View rootView = inflater.inflate(layID, container, false);

        return rootView;
    }

    public static HistoryPageFragment newInstance(int pos){
        HistoryPageFragment hpf = new HistoryPageFragment();
        Bundle args = new Bundle();
        int layID = -1;
        switch(pos){
            case(0):
                layID = R.layout.fragment_history_page_0;
                break;
            case(1):
                layID=R.layout.fragment_history_page_1;
                break;
            case(2):
                layID=R.layout.fragment_history_page_2;
                break;
            case(3):
                layID=R.layout.fragment_history_page_3;
                break;
            case(4):
                layID=R.layout.fragment_history_page_4;
                break;
            case(5):
                layID=R.layout.fragment_history_page_5;
                break;

            default:
                layID = R.layout.fragment_history_page;
                break;
        }

        args.putInt("layID", layID);
        hpf.setArguments(args);
        return hpf;
    }
}
