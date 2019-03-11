package com.aleengo.peach.toolbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aleengo.peach.toolbox.commons.common.PeachConfig;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseUI {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onCreate() method called");
        if (PeachConfig.isDebug()) Log.d(logTag(), "DaggerConfiguration.");
        daggerConfiguration();

        final View view = getLayoutView();
        setContentView(view);

        unbinder = ButterKnife.bind(view);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onAttachFragment() = " + fragment.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onStart().");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onResume().");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onPause().");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: OnStop().");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onDestroy().");
        unbinder.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onActivityResult().");
    }
}
