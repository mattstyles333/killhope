package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryItem;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryManager;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.GlossaryAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralsAdapter;

/**
 * Created by Ally on 05/04/15.
 */
public class GlossaryDialogFragment extends DialogFragment {
    public String search;
    public GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_glossary, container, false);
        gridView = (GridView) rootView.findViewById(R.id.glossary_gridview);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final EditText et = (EditText) rootView.findViewById(R.id.glossary_search);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search(et.getText().toString());
            }
        });

//                ;addTextChangedListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//
//                    System.out.println("query");
//                    return true;
//                }
//                return false;
//            }
//        });

        GlossaryAdapter adapter = new GlossaryAdapter(this.getActivity());
        gridView.setAdapter(adapter);

        return rootView;
    }


    public void onResume() {
        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }
    public void search(String s){
        ((GlossaryAdapter)gridView.getAdapter()).search(s);
    }

    public static GlossaryDialogFragment newInstance(){
        return new GlossaryDialogFragment();
    }
}
