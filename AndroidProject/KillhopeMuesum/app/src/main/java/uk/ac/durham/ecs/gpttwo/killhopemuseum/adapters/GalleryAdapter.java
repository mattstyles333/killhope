package uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.abhi.gif.lib.AnimatedGifImageView;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class GalleryAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public GalleryAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        int  mineralID = ((Activity)mContext).getIntent().getExtras().getInt("mineralID");
        Mineral mineral = ((KillhopeApplication)((Activity) mContext).getApplication()).mineralManager.getMineral(mineralID);
        return mineral.getImageLength();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        int  mineralID = ((Activity)mContext).getIntent().getExtras().getInt("mineralID");
        Mineral mineral = ((KillhopeApplication)((Activity) mContext).getApplication()).mineralManager.getMineral(mineralID);

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }
//        if(position==0){
//            AnimatedGifImageView image = new AnimatedGifImageView(mContext);
//            image.setAnimatedGif(mineral.getImg3d(), AnimatedGifImageView.TYPE.FIT_CENTER);
//            image.setLayoutParams(new GridView.LayoutParams(85, 85));
//            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            image.setPadding(8, 8, 8, 8);
//            imageView = image;
//        }else{
            imageView.setImageResource(mineral.getImage(position ));
//        }

        return imageView;
    }
}