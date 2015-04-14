package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class HelpPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layID = getArguments().getInt("layID");
        View rootView = inflater.inflate(layID, container, false);
//        doImage(rootView, R.id.helpsearch, R.drawable.helpsearch);
//        doImage(rootView, R.id.helpsearchicon, R.drawable.helpsearchicon);
//        doImage(rootView, R.id.helpqr, R.drawable.helpqr);
//        doImage(rootView, R.id.helpqrcode, R.drawable.helpqrcode);
//        doImage(rootView, R.id.helpsearch_1, R.drawable.helpsearch);
//        doImage(rootView, R.id.helpsearch_2, R.drawable.helpsearch);
//        doImage(rootView, R.id.helpmainmenu, R.drawable.helpmainmenu);
//        doImage(rootView, R.id.helpminerals, R.drawable.helpminerals);
//        doImage(rootView, R.id.helpquiz, R.drawable.helpquiz);
        return rootView;
    }

    private void doImage(View v, int layid, final int resid){
        ImageView image1 = (ImageView)v.findViewById(layid);
        if(image1!=null) {
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageZoomDialogFragment gdf = new ImageZoomDialogFragment().newInstance(resid);
                    gdf.show(getActivity().getSupportFragmentManager(), "imagezoom_fragment");
                }
            });
        }
    }

    public static HelpPageFragment newInstance(int pos){
        HelpPageFragment hpf = new HelpPageFragment();
        Bundle args = new Bundle();
        int layID = -1;
        switch(pos){
            case(0):
                layID = R.layout.fragment_help_page_4;
                break;
            case(1):
                layID=R.layout.fragment_help_page_0;
                break;
            case(2):
                layID=R.layout.fragment_help_page_1;
                break;
            case(3):
                layID=R.layout.fragment_help_page_2;
                break;
            case(4):
                layID=R.layout.fragment_help_page_3;
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
