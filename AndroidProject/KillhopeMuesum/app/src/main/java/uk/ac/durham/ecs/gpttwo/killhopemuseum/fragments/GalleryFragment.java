package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.GalleryAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        final int mineralID = getActivity().getIntent().getExtras().getInt("mineralID");

        final Mineral mineral = ((KillhopeApplication)getActivity().getApplication()).mineralManager.getMineral(mineralID);

        GridView gridView = (GridView) rootView.findViewById(R.id.gallery_gridView);

        gridView.setOnItemClickListener(this);

        GalleryAdapter adapter = new GalleryAdapter(getActivity());

        gridView.setAdapter(adapter);

        ImageViewTouch mainImage= (ImageViewTouch) rootView.findViewById(R.id.selected_image);
        mainImage.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), mineral.getImage(0));
        mainImage.setImageBitmap(bmp, null, -1, 8f);

        return rootView;
    }

    public static GalleryFragment newInstance(){
        return new GalleryFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageViewTouch mainImage= (ImageViewTouch) getView().findViewById(R.id.selected_image);
        GridView gridView = (GridView) parent.findViewById(R.id.gallery_gridView);
        GalleryAdapter adapter = (GalleryAdapter)gridView.getAdapter();
        int bitid = ((KillhopeApplication)this.getActivity().getApplication()).mineralManager.getMineral(getActivity().getIntent().getExtras().getInt("mineralID")).getImage(position);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), bitid);
        mainImage.setImageBitmap(bmp, null, -1f, 8f);
    }
}
