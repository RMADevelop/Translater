package com.example.roma.translater.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Roma on 12.08.2017.
 */

public class ActivityUtils {

    public static void addFragmentToActivity (FragmentManager fragmentManager, Fragment fragment, int frameId) {
        fragmentManager
                .beginTransaction()
                .replace(frameId,fragment)
                .commit();
    }
}
