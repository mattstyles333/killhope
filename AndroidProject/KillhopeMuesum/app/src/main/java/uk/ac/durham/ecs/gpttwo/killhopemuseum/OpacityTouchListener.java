package uk.ac.durham.ecs.gpttwo.killhopemuseum;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Ally on 30/03/15.
 */
public class OpacityTouchListener implements View.OnTouchListener {

    private float touchOpacity = 1f;

    public OpacityTouchListener(float touchOpacity){
        this.touchOpacity = touchOpacity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case(MotionEvent.ACTION_DOWN):
                v.setAlpha(0.7f);
                break;
            case(MotionEvent.ACTION_UP):
                v.setAlpha(1f);
                break;
        }
        return false;
    }
}
