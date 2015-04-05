package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.FloorPlanFragment;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.InfoFragment;


public class InfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_killhope);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, InfoFragment.newInstance())
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_killhope, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        if (id == R.id.action_help) {
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
