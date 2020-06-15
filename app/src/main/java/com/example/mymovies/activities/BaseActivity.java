package com.example.mymovies.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.example.mymovies.R;
import com.example.mymovies.fragments.MovieDetailsFragment;
import com.example.mymovies.utils.Constants;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseActivity extends AppCompatActivity {

    private ActionBar supportActionBar;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportActionBar = getSupportActionBar();
        supportFragmentManager = getSupportFragmentManager();
    }

    public void showActionBarBackButton(boolean show) {
        if (supportActionBar == null) supportActionBar = getSupportActionBar();
        else supportActionBar.setDisplayHomeAsUpEnabled(show);
    }

    @Override
    public void onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void addFragment(Fragment fragment, boolean addToStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (addToStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.add(R.id.main_activity_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment, boolean addToStack, boolean isTwoPane) {
        boolean isMovieDetailsVisible = fragment instanceof MovieDetailsFragment;
        if (isMovieDetailsVisible) getSupportFragmentManager().popBackStackImmediate();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (addToStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(isTwoPane && isMovieDetailsVisible ? R.id.movie_details_frame_layout : R.id.main_activity_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public int getItemsInRowByScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        if (width > 1080 && width < 1280 || width > 2000) {
            return Constants.FOUR_IN_ROW;
        } else if (width > 600 && width <= 800 || width >= 1280) {
            return Constants.THREE_IN_ROW;
        }
        return Constants.TWO_IN_ROW;
    }
}
