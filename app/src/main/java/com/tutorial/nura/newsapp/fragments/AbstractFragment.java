package com.tutorial.nura.newsapp.fragments;

import android.support.v4.app.Fragment;

import com.tutorial.nura.newsapp.activities.MainActivity;

public abstract class AbstractFragment extends Fragment{

    public void changeFragment(AbstractFragment fragment, boolean addToBackStack) {
        ((MainActivity) getActivity()).changeFragment(fragment, addToBackStack);
    }

    public void onBackPressed() {
        getActivity().onBackPressed();
    }
}
