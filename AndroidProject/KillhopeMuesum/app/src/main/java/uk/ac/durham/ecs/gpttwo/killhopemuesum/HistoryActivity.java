package uk.ac.durham.ecs.gpttwo.killhopemuesum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments.HelpPageFragment;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments.HistoryFragment;
import uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments.HistoryPageFragment;

public class HistoryActivity extends ActionBarActivity {


    public static final int NUM_PAGES = 5;

    private ViewPager mPager;

    private PagerAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help_page);

        mPager = (ViewPager)findViewById(R.id.help_pager);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            if(mPager.getCurrentItem() != NUM_PAGES - 1) {
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

    private class HistoryPagerAdapter extends FragmentStatePagerAdapter {
        public HistoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new HistoryPageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
