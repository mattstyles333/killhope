package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 05/04/15.
 */
public class InfoDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);

        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        try {
            ((TextView)rootView.findViewById(R.id.textview_version)).setText(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ((Button)rootView.findViewById(R.id.button_info_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    public void onResume() {
        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }

    public static InfoDialogFragment newInstance(){
        return new InfoDialogFragment();
    }
}
