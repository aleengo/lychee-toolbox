package com.aleengo.peach.toolbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aleengo.peach.toolbox.common.PeachConfig;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseUI {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onCreate() method called");

        View view;
        if (getLayoutView() != null) {
            view = getLayoutView();
        } else if (getLayoutResId() != View.NO_ID) {
            view = getLayoutInflater().inflate(getLayoutResId(), null);
        } else {
            throw new RuntimeException("You may implements either getLayoutResId() or getLayoutView()");
        }

        setContentView(view);

        ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onAttachFragment() = " + fragment.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onStart().");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onResume().");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onPause().");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: OnStop().");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onDestroy().");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PeachConfig.isDebug()) Log.d(getClassName(), "EVENT: onActivityResult().");
    }

    /**
     * Performs initialization
     *
     * This method is called by [onCreate()] for an {@link androidx.appcompat.app.AppCompatActivity}
     */
    protected abstract void initialize(@Nullable Bundle savedInstanceState);
}
