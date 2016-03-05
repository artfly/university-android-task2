package io.github.noveo.artfly.task2.app.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public final class TransactionUtilities {

    public static void replaceFragment(FragmentManager manager, int container, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(container, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void addFragment(FragmentManager manager, int container, Fragment fragment, String tag) {
        manager.beginTransaction().add(container, fragment, tag).commit();
    }

    public static boolean popFragment(FragmentManager manager) {
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
            return true;
        }
        return false;
    }

    public static boolean isFragmentExisting(FragmentManager manager, String tag) {
        return manager.findFragmentByTag(tag) != null;
    }
}
