package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.gif.lib.AnimatedGifImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.MineralManager;
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

        int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        TextView name = (TextView)rootView.findViewById(R.id.mineral_name);
        TextView formula = (TextView)rootView.findViewById(R.id.mineral_formula);
//      ImageView image = (ImageView)rootView.findViewById(R.id.mineral_image);
        final AnimatedGifImageView image = (AnimatedGifImageView)rootView.findViewById(R.id.mineral_image);
        image.setAnimatedGif(mineral.getImg3d(), AnimatedGifImageView.TYPE.FIT_CENTER);

        name.setText(Html.fromHtml(mineral.getName()));
        formula.setText(Html.fromHtml(mineral.getFormula()));
//        image.setImageResource(mineral.getImage(0));


        ListView listView = (ListView) rootView.findViewById(R.id.mineral_list);

        MineralSectionAdapter adapter = new MineralSectionAdapter(getActivity(),mineral);

        listView.setAdapter(adapter);


        return rootView;
    }

    public static MineralFragment newInstance() {
        return new MineralFragment();
    }

}
