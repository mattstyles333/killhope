package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.adapters.HelpPagerAdapter;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;


public class HelpActivity extends ActionBarActivity {



    private ViewPager mPager;

    private PagerAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help_page);

        mPager = (ViewPager)findViewById(R.id.help_pager);
        mPageAdapter = new HelpPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPageAdapter);
        int pg = getIntent().getExtras().getInt("helppage");
        if(pg >= 0 && pg < mPageAdapter.getCount()){
            mPager.setCurrentItem(pg);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_prevpage) {
            if(mPager.getCurrentItem() != 0) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }else{
                mPager.setCurrentItem(mPageAdapter.getCount() - 1);
            }
            return true;
        }else if (id == R.id.action_nextpage) {
            if(mPager.getCurrentItem() != mPageAdapter.getCount() - 1) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }else{
                mPager.setCurrentItem(0);
            }
            return true;
        }else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
        super.onBackPressed();
    }
}
