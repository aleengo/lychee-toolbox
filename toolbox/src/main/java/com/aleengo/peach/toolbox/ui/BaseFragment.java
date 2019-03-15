package com.aleengo.peach.toolbox.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleengo.peach.toolbox.commons.common.PeachConfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.Getter;


/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class BaseFragment extends Fragment implements BaseUI {

    @Getter
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onCreated().");
        if (PeachConfig.isDebug()) Log.d(logTag(), "DaggerConfiguration.");
        daggerConfiguration();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onAttach().");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onCreateView().");
        //return inflater.inflate(getLayoutResId(), container, false);
        return getLayoutView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onViewCreated().");
        unbinder = ButterKnife.bind(view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onSaveInstanceState().");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onActivityCreated().");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onStart().");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onResume().");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onPause().");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: OnStop().");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onDestroyView().");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onDestroy().");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (PeachConfig.isDebug()) Log.d(logTag(), "EVENT: onDetach().");
    }
}
