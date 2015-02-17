package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.FragmentMain;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.FragmentSplash;


public class KillhopeActivity extends ActionBarActivity {

    public static final long SPLASH_LENGTH = 2500l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_killhope);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentSplash())
                    .commit();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentMain())
                            .commit();
                }
            }, SPLASH_LENGTH);

        }
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
        }

        return super.onOptionsItemSelected(item);
    }

}
