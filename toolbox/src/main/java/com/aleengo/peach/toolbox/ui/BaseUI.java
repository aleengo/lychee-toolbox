package com.aleengo.peach.toolbox.ui;

import android.view.View;

import androidx.annotation.LayoutRes;


/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public interface BaseUI {

    /**
     * Return the name of the class. It is used as TAG
     * when using {@link android.util.Log} util
     * @return
     */
    String getClassName();

    /**
     * Return a layout resource ID
     * @return
     */
    default @LayoutRes
    int getLayoutResId() {
        return View.NO_ID;
    }

    /**
     * Return a view
     * @return
     */
    default View getLayoutView() {
        return null;
    }
}
