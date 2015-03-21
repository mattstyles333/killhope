package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.HistoryPagerAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;

public class HistoryActivity extends ActionBarActivity {

    private ViewPager mPager;
    private PagerAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history_page);

        mPager = (ViewPager)findViewById(R.id.history_pager);
        mPageAdapter = new HistoryPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPageAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        if (id == R.id.action_prevpage) {
            if(mPager.getCurrentItem() != 0) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
            return true;
        }else if (id == R.id.action_nextpage) {
            if(mPager.getCurrentItem() != mPageAdapter.getCount() - 1) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }
            return true;
        }else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

}
