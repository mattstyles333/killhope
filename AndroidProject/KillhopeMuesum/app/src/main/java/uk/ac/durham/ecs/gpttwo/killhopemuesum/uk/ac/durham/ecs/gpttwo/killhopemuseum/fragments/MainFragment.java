package uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.HistoryActivity;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class MainFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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

        return rootView;
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }
}
