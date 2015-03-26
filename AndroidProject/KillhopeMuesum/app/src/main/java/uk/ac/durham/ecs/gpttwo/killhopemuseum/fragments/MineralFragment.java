package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.gif.lib.AnimatedGifImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.MineralSection;
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

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float f = (float)event.getX() / (float)v.getWidth();
                switch(event.getAction()){
                    case(MotionEvent.ACTION_DOWN):
                        image.touchDown(f);
                        break;
                    case(MotionEvent.ACTION_UP):
                        image.touchUp(f);
                        break;
                    case(MotionEvent.ACTION_MOVE):
                        image.touchMove(f);
                        break;
                }
//                System.out.println("Touch:" + event.getX() + "," + event.getY());
                return true;
            }
        });

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


//        ListView listView = (ListView) rootView.findViewById(R.id.mineral_list);
//
//        MineralSectionAdapter adapter = new MineralSectionAdapter(getActivity(),mineral,true);
//
//        listView.setAdapter(adapter);
        LinearLayout list = (LinearLayout) rootView.findViewById(R.id.mineral_list);
        list.addView(getNextView(0, null, null));

        return rootView;
    }

    public View getNextView(final int position, final ImageButton prevReduceButton, final ImageButton prevExpandButton) {
        LayoutInflater inflator = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflator.inflate(R.layout.fragment_mineral_list_item,new LinearLayout(getActivity()),false);

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

//        ListView listView = (ListView) view.findViewById(R.id.mineral_sublist);

//        MineralSubSectionAdapter adapter = new MineralSubSectionAdapter(mContext,mineral.getMineralSection(position));

//        listView.setAdapter(adapter);

        TextView sectionName = (TextView)view.findViewById(R.id.section_name);
        sectionName.setText("Section " + (position + 1));

        MineralSection ms = mineral.getMineralSection(position);

        LinearLayout listView = (LinearLayout) view.findViewById(R.id.mineral_sublist);

//        ArrayList<String> glossaryTerms = mContext

        for(int i=0;i<ms.getCount();i++) {
            View subview = inflator.inflate(R.layout.fragment_mineral_list_item_sub, new RelativeLayout(getActivity()), false);

            TextView title = (TextView) subview.findViewById(R.id.mineral_sub_title);
            TextView desc = (TextView) subview.findViewById(R.id.mineral_sub_description);

            String titleText = ms.getSub(i).getTitle();
            String descText = ms.getSub(i).getInfo();

            //placeholder, soon to be the whole glossary
            descText = descText.replace("and", "<b>and</b>");

            title.setText(Html.fromHtml(titleText));
            desc.setText(Html.fromHtml(descText));

            listView.addView(subview);
        }

        final ImageButton reduceButton = (ImageButton)view.findViewById(R.id.button_reduce);
        if(position == 0){
            reduceButton.setVisibility(View.GONE);
        }
        reduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)v.getParent().getParent()).removeView(view);
                if(prevReduceButton!=null && position > 1){
                    prevReduceButton.setVisibility(View.VISIBLE);
                }
                if(prevExpandButton!=null){
                    prevExpandButton.setVisibility(View.VISIBLE);
                }
            }
        });
        final ImageButton expandButton = (ImageButton)view.findViewById(R.id.button_expand);
        if(position > 2){
            expandButton.setVisibility(View.GONE);
        }
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout nextLevel = (LinearLayout)getView().findViewById(R.id.mineral_list);
                nextLevel.addView(getNextView(position + 1,reduceButton,expandButton));
                reduceButton.setVisibility(View.GONE);
                expandButton.setVisibility(View.GONE);
            }
        });

        return view;
    }

    public static MineralFragment newInstance() {
        return new MineralFragment();
    }

}
