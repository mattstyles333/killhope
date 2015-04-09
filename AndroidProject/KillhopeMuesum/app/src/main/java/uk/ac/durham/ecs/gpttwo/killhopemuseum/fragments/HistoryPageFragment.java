package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class HistoryPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layID = getArguments().getInt("layID");
        int pgNum = getArguments().getInt("pgnum");
        View rootView = inflater.inflate(layID, container, false);

        doImage(rootView, R.id.historypage2pic, R.drawable.historypage2pic);
        doImage(rootView, R.id.historypage2table, R.drawable.historypage2table);

        doImage(rootView, R.id.historypage3pic, R.drawable.historypage3pic);
        doImage(rootView, R.id.historypage3table, R.drawable.historypage3table);

        doImage(rootView, R.id.historypage4table, R.drawable.historypage4table);
        doImage(rootView, R.id.historypage4pic, R.drawable.historypage4pic);
        doImage(rootView, R.id.historypage4pic2, R.drawable.historypage4pic2);

        doImage(rootView, R.id.historypage6pic, R.drawable.historypage6pic);
        doImage(rootView, R.id.historypage5pic, R.drawable.historypage5pic);
        doImage(rootView, R.id.historypage5table, R.drawable.historypage5table);

        doImage(rootView, R.id.historypage6table, R.drawable.historypage6table);

        return rootView;
    }

    private void doImage(View v, int layid, final int resid){
        ImageView image1 = (ImageView)v.findViewById(layid);
        if(image1!=null) {
//            image1.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                        ImageZoomDialogFragment gdf = new ImageZoomDialogFragment().newInstance(resid);
//                        gdf.show(getActivity().getSupportFragmentManager(), "imagezoom_fragment");
//                        return true;
//                    }
//                    return false;
//                }
//            });
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageZoomDialogFragment gdf = new ImageZoomDialogFragment().newInstance(resid);
                    gdf.show(getActivity().getSupportFragmentManager(), "imagezoom_fragment");
                }
            });
        }
    }

    public static HistoryPageFragment newInstance(int pos){
        HistoryPageFragment hpf = new HistoryPageFragment();
        Bundle args = new Bundle();
        int layID = -1;
        switch(pos){
//            case(0):
//                layID = R.layout.fragment_history_page_0;
//                break;
            case(0):
                layID=R.layout.fragment_history_page_1;
                break;
            case(1):
                layID=R.layout.fragment_history_page_2;
                break;
            case(2):
                layID=R.layout.fragment_history_page_3;
                break;
            case(3):
                layID=R.layout.fragment_history_page_4;
                break;
            case(4):
                layID=R.layout.fragment_history_page_5;
                break;
            default:
                layID = R.layout.fragment_history_page;
                break;
        }

        args.putInt("layID", layID);
        args.putInt("pgnum", pos);
        hpf.setArguments(args);
        return hpf;
    }
}
