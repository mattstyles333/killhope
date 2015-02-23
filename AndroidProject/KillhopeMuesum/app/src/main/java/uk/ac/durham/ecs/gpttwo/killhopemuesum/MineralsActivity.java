package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MineralsActivity extends ActionBarActivity {

    private GridView gridView;
    private MineralsAdapter adapter;
    public static final long SPLASH_LENGTH = 2500l;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_minerals);

        gridView = (GridView) findViewById(R.id.minerals_gridview);

        adapter = new MineralsAdapter(this);

        gridView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


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

        if(id == android.R.id.home){
            onBackPressed();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else
        return super.onOptionsItemSelected(item);
    }

}
