package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.gif.lib.AnimatedGifImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.GalleryActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.HelpActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralSectionAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class MineralFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mineral, container, false);

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        TextView name = (TextView)rootView.findViewById(R.id.mineral_name);
        TextView formula = (TextView)rootView.findViewById(R.id.mineral_formula);
//      ImageView image = (ImageView)rootView.findViewById(R.id.mineral_image);
        final AnimatedGifImageView image = (AnimatedGifImageView)rootView.findViewById(R.id.mineral_image);
        image.setAnimatedGif(mineral.getImg3d(), AnimatedGifImageView.TYPE.FIT_CENTER);

        name.setText(Html.fromHtml(mineral.getName()));
        formula.setText(Html.fromHtml(mineral.getFormula()));
//        image.setImageResource(mineral.getImage(0));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                Bundle b = new Bundle();
                b.putInt("mineralID", mineralID);
                intent.putExtras(b);
                startActivity(intent);
            }
        });


        ListView listView = (ListView) rootView.findViewById(R.id.mineral_list);

        MineralSectionAdapter adapter = new MineralSectionAdapter(getActivity(),mineral);

        listView.setAdapter(adapter);


        return rootView;
    }

    public static MineralFragment newInstance() {
        return new MineralFragment();
    }

}
