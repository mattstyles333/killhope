package uk.ac.durham.ecs.gpttwo.killhopemuseum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;
import uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments.MineralFragment;


public class MineralActivity extends ActionBarActivity {

    public static final long SPLASH_LENGTH = 2500l;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_killhope);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, MineralFragment.newInstance())
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        setContentView(R.layout.fragment_mineral);

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, MineralFrontFragment.newInstance())
//                    .commit();
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }else{
//            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
//        }
//        getFragmentManager().addOnBackStackChangedListener(this);
    }
//
//    boolean mShowingBack = false;
//
//    private void flipCard() {
//        if (mShowingBack) {
//            getFragmentManager().popBackStack();
//            return;
//        }
//
//        // Flip to the back.
//
//        mShowingBack = true;
//
//        // Create and commit a new fragment transaction that adds the fragment for the back of
//        // the card, uses custom animations, and is part of the fragment manager's back stack.
//
//        getFragmentManager()
//                .beginTransaction()
//
//                        // Replace the default fragment animations with animator resources representing
//                        // rotations when switching to the back of the card, as well as animator
//                        // resources representing rotations when flipping back to the front (e.g. when
//                        // the system Back button is pressed).
//                .setCustomAnimations(
//                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
//                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
//
//                        // Replace any fragments currently in the container view with a fragment
//                        // representing the next page (indicated by the just-incremented currentPage
//                        // variable).
//                .replace(R.id.container, MineralBackFragment.newInstance())
//
//                        // Add this transaction to the back stack, allowing users to press Back
//                        // to get to the front of the card.
//                .addToBackStack(null)
//
//                        // Commit the transaction.
//                .commit();
//
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                invalidateOptionsMenu();
//            }
//        });
//    }
//

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

//    @Override
//    public void onBackStackChanged() {
//        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
//
//        invalidateOptionsMenu();
//    }
}
