package com.aleengo.peank.uiadapter.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Copyright Aleengo 2019. All rights reserved.
 * Created by bau.cj on 22/06/2019.
 */

public abstract class ItemView<E> extends FrameLayout {

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void bind(E item, int position);
}
