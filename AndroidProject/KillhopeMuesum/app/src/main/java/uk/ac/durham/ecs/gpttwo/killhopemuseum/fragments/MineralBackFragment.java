package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.gif.lib.AnimatedGifImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.GalleryActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralSectionAdapter;

/**
 * Created by Ally on 22/03/15.
 */
public class MineralBackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mineral_back, container, false);

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        TextView name = (TextView)rootView.findViewById(R.id.mineral_name);
        TextView formula = (TextView)rootView.findViewById(R.id.mineral_formula);
//      ImageView image = (ImageView)rootView.findViewById(R.id.mineral_image);

        name.setText(Html.fromHtml(mineral.getName()));
        formula.setText(Html.fromHtml(mineral.getFormula()));
//        image.setImageResource(mineral.getImage(0));


        ListView listView = (ListView) rootView.findViewById(R.id.mineral_list);

        MineralSectionAdapter adapter = new MineralSectionAdapter(getActivity(),mineral,false);

        listView.setAdapter(adapter);


        return rootView;
    }

    public static MineralBackFragment newInstance() {
        return new MineralBackFragment();
    }
}
