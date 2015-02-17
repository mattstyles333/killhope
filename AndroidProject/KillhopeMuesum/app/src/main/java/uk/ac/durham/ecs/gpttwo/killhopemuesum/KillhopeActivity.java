package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.FragmentHistory;
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
                    .add(R.id.container, FragmentSplash.newInstance())
                    .commit();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, FragmentMain.newInstance())
                            .commit();

                    View v = findViewById(R.id.button_menu_history);
                    if(v!=null){
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.container, FragmentHistory.newInstance())
                                        .addToBackStack(null)
                                        .commit();
                            }
                        });
                    }
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
