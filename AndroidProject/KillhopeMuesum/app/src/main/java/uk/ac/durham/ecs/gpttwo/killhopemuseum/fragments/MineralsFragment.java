package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.MineralActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralsAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;


public class MineralsFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_minerals, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.minerals_gridview);

        gridView.setOnItemClickListener(this);

        MineralsAdapter adapter = new MineralsAdapter(getActivity());

        gridView.setAdapter(adapter);

        if(getResources().getConfiguration().orientation == 1){
            gridView.setNumColumns(2);
        }else{
            gridView.setNumColumns(3);
        }

        if(getResources().getConfiguration().orientation !=1){
            rootView.setBackgroundResource(R.drawable.bg_minerals_land);
        }

        return rootView;
    }

    public static MineralsFragment newInstance(){
        return new MineralsFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MineralActivity.class);
        Bundle b = new Bundle();
        b.putInt("mineralID", position);
        intent.putExtras(b);
        startActivity(intent);
    }
}
