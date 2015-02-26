package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.GalleryAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;

/**
 * Created by Ally on 17/02/15.
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gallery_gridView);

        gridView.setOnItemClickListener(this);

        GalleryAdapter adapter = new GalleryAdapter(getActivity());

        gridView.setAdapter(adapter);

        return rootView;
    }

    public static GalleryFragment newInstance(){
        return new GalleryFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView mainImage= (ImageView) getView().findViewById(R.id.selected_image);
        GridView gridView = (GridView) parent.findViewById(R.id.gallery_gridView);
        GalleryAdapter adapter = (GalleryAdapter)gridView.getAdapter();
        mainImage.setImageResource(adapter.mThumbIds[position]);
    }
}
