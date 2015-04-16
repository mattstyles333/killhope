package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.abhi.gif.lib.AnimatedGifImageView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;

import java.util.ArrayList;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
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
        View rootView;
        if(getResources().getConfiguration().orientation == 1){
            rootView = inflater.inflate(R.layout.fragment_mineral, container, false);
        }else{
            rootView = inflater.inflate(R.layout.fragment_mineral_land, container, false);
        }

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        TextView name = (TextView)rootView.findViewById(R.id.mineral_name);
        final AnimatedGifImageView animage = (AnimatedGifImageView)rootView.findViewById(R.id.mineral_image);
        animage.setAnimatedGif(mineral.getImg3d(), AnimatedGifImageView.TYPE.FIT_CENTER);
//        System.out.println(mineral.getImg3d());
        final VelocityTracker velocityTracker = VelocityTracker.obtain();

        animage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float f = (float)event.getX() / (float)v.getWidth();
                switch(event.getAction()){
                    case(MotionEvent.ACTION_DOWN):
                        animage.touchDown(f);
                        break;
                    case(MotionEvent.ACTION_UP):
                        velocityTracker.computeCurrentVelocity(1000,600f);
                        animage.touchUp(f, velocityTracker.getXVelocity());
                        break;
                    case(MotionEvent.ACTION_MOVE):
                        velocityTracker.addMovement(event);
                        animage.touchMove(f);
                        break;
                }
//                System.out.println("Touch:" + event.getX() + "," + event.getY());
                return true;
            }
        });

        name.setText(Html.fromHtml(mineral.getName() + ": " + mineral.getFormula()));

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml(mineral.getName() /*+ ": <small style=\"font-size:60%;\">" + mineral.getFormula() + "</small>"*/));

        animage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                Bundle b = new Bundle();
                b.putInt("mineralID", mineralID);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        LinearLayout list = (LinearLayout) rootView.findViewById(R.id.mineral_list);
        list.addView(getNextView(0, null, null));

//        ((ImageButton)rootView.findViewById(R.id.imgbutton_nextimage)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        ((ImageButton)rootView.findViewById(R.id.imgbutton_previmage)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        ((Button)rootView.findViewById(R.id.button_more_images)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageZoomDialogFragment gdf = new ImageZoomDialogFragment().newInstance(mineral.getImages());
                gdf.show(((ActionBarActivity) getActivity()).getSupportFragmentManager(), "imagezoom_fragment");
            }
        });

        try{
            ((KillhopeApplication)(getActivity().getApplication())).getTracker(KillhopeApplication.TrackerName.APP_TRACKER).setScreenName("Mineral:" + mineral.getName());
            ((KillhopeApplication)(getActivity().getApplication())).getTracker(KillhopeApplication.TrackerName.APP_TRACKER).send(new HitBuilders.ScreenViewBuilder().build());
            GoogleAnalytics.getInstance(getActivity().getBaseContext()).dispatchLocalHits();
        }catch(Exception e){
        }

        return rootView;
    }

    public View getNextView(final int position, final ImageButton prevReduceButton, final ImageButton prevExpandButton) {
        LayoutInflater inflator = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflator.inflate(R.layout.fragment_mineral_list_item,new LinearLayout(getActivity()),false);

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        TextView sectionName = (TextView)view.findViewById(R.id.section_name);
        sectionName.setText("Section " + (position + 1));
        if(position == 0){
            sectionName.setText("Basic");
        }else if(position == 1){
            sectionName.setText("Intermediate");
        }else if(position == 2){
            sectionName.setText("Advanced");
        }else{
            sectionName.setText("Expert");
        }

        MineralSection ms = mineral.getMineralSection(position);

        LinearLayout listView = (LinearLayout) view.findViewById(R.id.mineral_sublist);

        ArrayList<String> glossaryTerms = ((KillhopeApplication)getActivity().getApplication()).glossaryManager.getNames();

        for(int i=0;i<ms.getCount();i++) {
            View subview = inflator.inflate(R.layout.fragment_mineral_list_item_sub, new RelativeLayout(getActivity()), false);

            TextView title = (TextView) subview.findViewById(R.id.mineral_sub_title);
            TextView desc = (TextView) subview.findViewById(R.id.mineral_sub_description);

            String titleText = ms.getSub(i).getTitle();
            String descText = ms.getSub(i).getInfo();

            for(String term : glossaryTerms){
                String nocaps = term.toLowerCase();
//                String firstCap = (nocaps.charAt(0) + "").toUpperCase() + nocaps.substring(1);
                titleText.replace(nocaps, "<b>" + nocaps + "</b>");
                titleText.replace(term, "<b>" + term + "</b>");

                descText.replace(nocaps, "<b>" + nocaps + "</b>");
                descText.replace(term, "<b>" + term + "</b>");
//                System.out.println(term);
            }

            //placeholder, soon to be the whole glossary
//            descText = descText.replace("and", "<b>and</b>");

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
                ((LinearLayout)v.getParent().getParent().getParent()).removeView(view);
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
                ((ScrollView)getView().findViewById(R.id.mineral_paneright)).post(new Runnable() {
                    @Override
                    public void run() {
                        ((ScrollView) getView().findViewById(R.id.mineral_paneright)).fullScroll(View.FOCUS_DOWN);
                    }
                });
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
