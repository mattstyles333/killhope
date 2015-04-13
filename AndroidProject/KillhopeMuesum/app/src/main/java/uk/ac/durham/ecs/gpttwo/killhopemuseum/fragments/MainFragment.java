package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.FloorPlanActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.HelpActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.HistoryActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.MineralsActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.activities.QuizActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class MainFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if(getResources().getConfiguration().orientation != 1){
            rootView.setBackgroundResource(R.drawable.bg_land);
        }

        if(getResources().getConfiguration().orientation == 1){

        }else{
            Point size = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(size);
            int centre = (int)((float)size.x*0.45f);
            ((RelativeLayout)rootView).setPadding((size.x - centre)/2,16,(size.x - centre)/2,16);
        }

        Button v = (Button)rootView.findViewById(R.id.button_menu_history);
        if(v!=null){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), HistoryActivity.class);
                    startActivity(intent);
//                                getSupportFragmentManager()
//                                        .beginTransaction()
//                                        .add(R.id.container, HistoryFragment.newInstance())
//                                        .addToBackStack(null)
//                                        .commit();
                }
            });
        }

        Button v2 = (Button)rootView.findViewById(R.id.button_menu_minerals);
        if(v2!=null){
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {

                    Intent intent = new Intent(getActivity(), MineralsActivity.class);
                    startActivity(intent);
//                                getSupportFragmentManager()
//                                        .beginTransaction()
//                                        .add(R.id.container, HistoryFragment.newInstance())
//                                        .addToBackStack(null)
//                                        .commit();
                }
            });
        }
        Button v3 = (Button)rootView.findViewById(R.id.button_menu_floorplan);
        if(v3!=null){
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v3) {
                    ImageZoomDialogFragment gdf = new ImageZoomDialogFragment().newInstance(R.drawable.floor_plan);
                    gdf.show(getActivity().getSupportFragmentManager(), "floorplan_fragment");
//                    Intent intent = new Intent(getActivity(), FloorPlanActivity.class);
//                    startActivity(intent);
////                                getSupportFragmentManager()
////                                        .beginTransaction()
////                                        .add(R.id.container, HistoryFragment.newInstance())
////                                        .addToBackStack(null)
////                                        .commit();
                }
            });
        }
        Button v4 = (Button)rootView.findViewById(R.id.button_menu_quiz);
        if(v4!=null){
            v4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v4) {

                    Intent intent = new Intent(getActivity(), QuizActivity.class);
                    startActivity(intent);
//                                getSupportFragmentManager()
//                                        .beginTransaction()
//                                        .add(R.id.container, HistoryFragment.newInstance())
//                                        .addToBackStack(null)
//                                        .commit();
                }
            });
        }

        ImageView buttonInfo = (ImageView)rootView.findViewById(R.id.button_main_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), InfoActivity.class);
//                startActivity(intent);
                InfoDialogFragment idf = InfoDialogFragment.newInstance();
                idf.show(getActivity().getSupportFragmentManager(), "info_fragment");

            }
        });

        ImageView buttonGlossary = (ImageView)rootView.findViewById(R.id.button_main_glossary);
        buttonGlossary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlossaryDialogFragment gdf = new GlossaryDialogFragment().newInstance();
                gdf.show(getActivity().getSupportFragmentManager(), "glossary_fragment");
            }
        });

        ImageView buttonHelp = (ImageView)rootView.findViewById(R.id.button_main_help);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        ImageView buttonFacebook = (ImageView)rootView.findViewById(R.id.button_main_facebook);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Killhope"));
                startActivity(intent);
            }
        });

        ImageView buttonTwitter = (ImageView)rootView.findViewById(R.id.button_main_twitter);
        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/killhopemuseum"));
                startActivity(intent);
            }
        });

        ImageView buttonLinkedin = (ImageView)rootView.findViewById(R.id.button_main_linkedin);
        buttonLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.killhope.org.uk"));
                startActivity(intent);
            }
        });

        return rootView;
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }
}
