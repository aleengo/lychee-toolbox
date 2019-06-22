package com.aleengo.peach.box.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aleengo.peach.box.common.FragmentConfig;

import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class Util {

    private Util() {
    }

    /*enum ToastLength {
        SHORT, LONG;
    }*/

    /* Check if the device is connected to a network */
    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isDeviceConnected(Context context) {
        final ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if( null != networkInfo) {
            final NetworkInfo.State state = networkInfo.getState();
            final String extraInfo = networkInfo.getExtraInfo();

            toast(context, "The device is connected to " +
                    networkInfo.getSubtypeName() + " network\n" +
                    "Network state : " + state + "\n" +
                    "Extra Info : " + extraInfo);
        }
        return (networkInfo != null && networkInfo.isConnected());
    }

    /* Toast message with a resId */
    public static void toast(Context context, @StringRes int resId) {
        final String message = context.getResources().getString(resId);
        toast(context, message);
    }

    /* Toast message with a message */
    public static void toast(Context context, String message ) {
        //final int toastLength = length == ToastLength.LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getStringFromTextView(final TextView view) {
        if (view == null) return null;
        return view.getText().toString();
    }

    /* Inflate a view */
    public static View inflateLayout(LayoutInflater inflater, @LayoutRes int resId, ViewGroup parent) {
        return inflater.inflate(resId, parent, false);
    }

    public static void launchActivity(Context context, Class<?> cls, Bundle bundle) {
        final Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * Add a new fragment within a fragment container
     * @param config
     */
    public static void showFragment(FragmentConfig config) {
        final FragmentTransaction transaction = config.getFragmentManager().beginTransaction();

        if (config.isAddToBackStack()) {
            transaction.addToBackStack(config.getBackStackName());
        }

        final String tag = config.getFragmentTag() != null ? config.getFragmentTag() :
                config.getFragment().getClass().getName();

        final Fragment savedFragment = config.getFragmentManager().findFragmentByTag(tag);

        if (savedFragment != null) {
            if (savedFragment.isAdded()) {
                transaction.show(savedFragment);
            } else {
                transaction.add(savedFragment, tag);
            }
        } else {
            transaction.replace(config.getFragmentContainer(), config.getFragment(), tag);
        }

        // commit changes
        transaction.commit();
    }
}
