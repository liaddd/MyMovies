package com.example.mymovies.activities;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.mymovies.R;
import com.example.mymovies.fragments.MoviesFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends BaseActivity {

    private boolean isTwoPane;
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewAndListeners();
        if (savedInstanceState == null) {
            addFragment(fragment, false);
        }
        if (isTwoPane) {
            replaceFragment(fragment , false , true);
        }

    }

    private void initViewAndListeners() {
        FragmentManager manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.main_activity_frame_layout);

        if (fragment == null) {
            fragment = MoviesFragment.newInstance();
        }
        isTwoPane = findViewById(R.id.movie_details_frame_layout) != null;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showActionBarBackButton(false);
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }
}
