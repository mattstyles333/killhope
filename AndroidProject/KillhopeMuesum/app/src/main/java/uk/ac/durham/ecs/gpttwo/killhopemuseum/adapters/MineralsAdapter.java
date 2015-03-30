package uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.Mineral;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class MineralsAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public MineralsAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return ((KillhopeApplication)mContext.getApplicationContext()).mineralManager.getMineralsFromSearch(((KillhopeApplication) mContext.getApplicationContext()).getCurrentSearch()).size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        Mineral mineral = ((KillhopeApplication) mContext.getApplicationContext()).mineralManager.getMineralsFromSearch(((KillhopeApplication) mContext.getApplicationContext()).getCurrentSearch()).get(position);
        if(convertView == null) {
            LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new ViewHolder();

            convertView = inflator.inflate(R.layout.fragment_minerals_thumb, parent, false);

            holder.txt = (TextView) convertView.findViewById(R.id.mineral_thumb_text);
            holder.img = (ImageView) convertView.findViewById(R.id.mineral_thumb_image);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txt.setText(Html.fromHtml( mineral.getName()));
//        if(holder.id == -1) {
        int id = mineral.getImage(0);
//            holder.id = R.drawable.icon_baryte;
//        }
        try {
            if(holder.id != id) {
                holder.id = id;
//                holder.img.setImageResource(holder.id);

                loadBitmap(holder.id, holder.img);

//                Thread thread = new Thread(new Thread(){
//                    public void run(){
//                        holder.img.setImageBitmap(
//                                decodeSampledBitmapFromResource(mContext.getResources(), holder.id, 100, 100));
//                    }
//                });
//                thread.start();
            }
        } catch (Exception e) {
            System.err.println(holder.id);
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView img;
        TextView txt;
        int id = -1;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public void loadBitmap(int resId, ImageView imageView) {
        if (cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(mContext.getResources(), getPlaceholderBitmap(), task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }

    private int getThumbWidth(){
        Point size = new Point();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        if(mContext.getResources().getConfiguration().orientation == 1){
            return width;
        }else {
            return width;
        }
    }

    private Bitmap mPlaceholderBitmap = null;
    private Bitmap getPlaceholderBitmap(){
        if(mPlaceholderBitmap == null) {
            int width = getThumbWidth();
            int height = width * 864 / 1296;
            mPlaceholderBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            mPlaceholderBitmap.eraseColor(Color.TRANSPARENT);
        }
        return mPlaceholderBitmap;
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            int width = getThumbWidth();
            int height = width*864/1296;
            return decodeSampledBitmapFromResource(mContext.getResources(), data, width, height);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                final BitmapWorkerTask bitmapWorkerTask =
                        getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

    }
}