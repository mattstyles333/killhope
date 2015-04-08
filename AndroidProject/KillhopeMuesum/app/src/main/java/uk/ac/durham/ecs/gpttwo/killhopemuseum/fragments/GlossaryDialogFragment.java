package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryManager;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.GlossaryAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralsAdapter;

/**
 * Created by Ally on 05/04/15.
 */
public class GlossaryDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle("Glossary");
        getDialog().setCanceledOnTouchOutside(true);

        View rootView = inflater.inflate(R.layout.fragment_glossary, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.glossary_gridview);
        GlossaryManager gm = new GlossaryManager();
        gm.loadGlossary(this.getActivity());
        ArrayList<String> g = gm.getGlossary();
        GlossaryAdapter adapter = new GlossaryAdapter(this.getActivity(),g);
        gridView.setAdapter(adapter);

        return rootView;
    }

    public void onResume() {
        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }

    public static GlossaryDialogFragment newInstance(){
        return new GlossaryDialogFragment();
    }
}
