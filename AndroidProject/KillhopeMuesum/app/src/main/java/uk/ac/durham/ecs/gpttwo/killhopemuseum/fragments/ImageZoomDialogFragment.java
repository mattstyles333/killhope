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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
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
        getDialog().setCanceledOnTouchOutside(true);

        final ImageViewTouch mainImage= (ImageViewTouch) rootView.findViewById(R.id.main_image);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), getRes(getCurrentResIndex()));
        mainImage.setImageBitmap(bmp, null, 0, 8f);
        mainImage.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        ((Button)rootView.findViewById(R.id.button_info_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        rootView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                dismiss();
//                return true;
//            }
//        });

        if(getResLength()==1){
            ((ImageButton)rootView.findViewById(R.id.imgbutton_nextimage)).setVisibility(ImageButton.GONE);
            ((ImageButton)rootView.findViewById(R.id.imgbutton_previmage)).setVisibility(ImageButton.GONE);
        }else {

            ((ImageButton) rootView.findViewById(R.id.imgbutton_nextimage)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), getRes(changeCurrentResIndex(1)));
                    mainImage.setImageBitmap(bmp, null, -1, 8f);
                }
            });
            ((ImageButton) rootView.findViewById(R.id.imgbutton_previmage)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), getRes(changeCurrentResIndex(-1)));
                    mainImage.setImageBitmap(bmp, null, -1, 8f);
                }
            });
        }

        Toast.makeText(getActivity().getApplicationContext(), "Pinch to zoom!", Toast.LENGTH_SHORT).show();

        return rootView;
    }

    public int getCurrentResIndex(){
        System.out.println(getArguments().getInt("curres"));
        return getArguments().getInt("curres");
    }

    public int changeCurrentResIndex(int increment){
        int curres = getArguments().getInt("curres");
        curres += increment;
        if(curres<0){
            curres += getResLength();
        }
        curres %= getResLength();
        getArguments().putInt("curres", curres);
        return getCurrentResIndex();
    }

    public int getRes(int id){
        int[] bmpids = getArguments().getIntArray("resid");
        System.out.println(bmpids[id]);
        return bmpids[id];
//        return R.drawable.historypage2pic;
    }

    public int getResLength(){
        return getArguments().getIntArray("resid").length;
    }


    public void onResume() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
//        height = (6*height) / 7;
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }

    public static ImageZoomDialogFragment newInstance(int resid){
        return newInstance(new int[]{resid});
    }

    public static ImageZoomDialogFragment newInstance(int[] resids){
        ImageZoomDialogFragment izf = new ImageZoomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("resid", resids);
        bundle.putInt("curres",0);
        izf.setArguments(bundle);
        return izf;
    }
}
