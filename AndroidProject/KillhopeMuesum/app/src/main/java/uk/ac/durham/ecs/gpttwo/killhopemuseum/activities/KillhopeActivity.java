package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.GoogleAnalytics;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.MainFragment;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.SplashFragment;


public class KillhopeActivity extends FragmentActivity {

    public static final long SPLASH_LENGTH = 3000l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracker t = ((KillhopeApplication)getApplication()).getTracker(KillhopeApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Home");
        t.send(new HitBuilders.AppViewBuilder().build());

        setContentView(R.layout.activity_killhope);
        final Context context = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, SplashFragment.newInstance())
                    .commit();
//            getSupportActionBar().hide();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
//                    MineralManager.loadMinerals(context);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, MainFragment.newInstance())
                            .commit();
//                    getSupportActionBar().show();
                }
            }, SPLASH_LENGTH);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Get an Analytics tracker to report app starts and uncaught exceptions etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        //Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_killhope, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_help) {
            Intent helpIntent = new Intent(this, HelpActivity.class);
            Bundle b = new Bundle();
            b.putInt("helppage", 0);
            helpIntent.putExtras(b);
            startActivity(helpIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
