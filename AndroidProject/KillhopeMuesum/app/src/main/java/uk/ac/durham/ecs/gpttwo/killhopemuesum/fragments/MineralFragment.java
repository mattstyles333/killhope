package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.MineralSectionAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.MineralsAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class MineralFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mineral, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.mineral_list);

        MineralSectionAdapter adapter = new MineralSectionAdapter(getActivity());

        listView.setAdapter(adapter);

        return rootView;
    }

    public static MineralFragment newInstance() {
        return new MineralFragment();
    }

}