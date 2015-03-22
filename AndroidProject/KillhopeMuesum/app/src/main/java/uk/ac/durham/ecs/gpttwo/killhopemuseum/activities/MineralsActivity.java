package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.KillhopeApplication;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.MineralsAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.MineralsFragment;

public class MineralsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_killhope);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MineralsFragment.newInstance())
                    .commit();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_minerals, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                GridView gv = (GridView)findViewById(R.id.minerals_gridview);
                MineralsAdapter ma = (MineralsAdapter) gv.getAdapter();
                ((KillhopeApplication) getApplication()).setCurrentSearch(s);
                ma.notifyDataSetChanged();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.action_qr){
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Scan a QR code");
            integrator.setResultDisplayDuration(0);
//            integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.initiateScan();
        }
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
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
            String qrid = scanResult.getContents();

            int loadid = ((KillhopeApplication)getApplication()).mineralManager.getMineralIdFromQRId(qrid);
            if(loadid == -1){
                Toast toast = Toast.makeText(this, "QR Code is not a mineral qr code.", Toast.LENGTH_LONG);
                toast.show();
            }else{
                Intent newintent = new Intent(this, MineralActivity.class);
                Bundle b = new Bundle();
                b.putInt("mineralID", loadid);
                newintent.putExtras(b);
                startActivity(newintent);
            }
        }
    }
}
