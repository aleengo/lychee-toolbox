package com.aleengo.peach.toolbox.adapter;

import android.view.View;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public interface PeachAdapter<E> {

    /**
     * Returns the layout Resource R.layout.name_of_the_layout
     * @return Resource of the layout
     */
    @LayoutRes int getLayoutResId();

    /**
     * Clear the data within the Adapter
     * and notify all the observers by calling notifyDataSetChanged
     */
    void clear();

    /**
     * Update the data (items) of the Adapter
     * with the new one
     * @param newItems New items to populate Adapter with
     */
    void updateItems(List<E> newItems);
}
