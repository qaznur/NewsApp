package com.tutorial.nura.newsapp.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tutorial.nura.newsapp.R;
import com.tutorial.nura.newsapp.fragments.AbstractFragment;
import com.tutorial.nura.newsapp.fragments.StarterFragment;

public class MainActivity extends AppCompatActivity {

    private static final int MAIN_LAYOUT = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MAIN_LAYOUT);

        changeFragment(new StarterFragment(), false);
    }

    public void changeFragment(AbstractFragment destFragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, destFragment);
        if (addToBackStack) {
            transaction.addToBackStack(destFragment.getClass().getSimpleName());
        }
        transaction.commit();
    }
}
