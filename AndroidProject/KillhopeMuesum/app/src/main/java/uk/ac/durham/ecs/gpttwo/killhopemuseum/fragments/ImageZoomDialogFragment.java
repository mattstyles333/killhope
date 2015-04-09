package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
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

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryItem;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.GlossaryManager;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.GlossaryAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralsAdapter;

/**
 * Created by Ally on 05/04/15.
 */
public class ImageZoomDialogFragment extends DialogFragment {
    public String search;
    public GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_imageview, container, false);

        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        ImageViewTouch mainImage= (ImageViewTouch) rootView.findViewById(R.id.main_image);
        mainImage.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), getArguments().getInt("resid"));
        mainImage.setImageBitmap(bmp, null, -1, 8f);

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
        int height = getResources().getDisplayMetrics().heightPixels;
        height = (6*height) / 7;
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }

    public static ImageZoomDialogFragment newInstance(int resid){
        ImageZoomDialogFragment izf = new ImageZoomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("resid", resid);
        izf.setArguments(bundle);
        return izf;
    }
}
