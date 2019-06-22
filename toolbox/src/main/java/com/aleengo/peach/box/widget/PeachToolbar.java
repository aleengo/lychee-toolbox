package com.aleengo.peach.box.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.aleengo.peach.box.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class PeachToolbar extends Toolbar {

    private int childLayoutId;

    public PeachToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public PeachToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PeachToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /////////////////////////////
    ///////// PRIVATE ///////////
    /////////////////////////////
    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.peach_toolbar, this);

        if (attrs != null) {
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PeachToolbar);
            try {
                childLayoutId = ta.getResourceId(R.styleable.PeachToolbar_child_layout, View.NO_ID);
                if (childLayoutId != View.NO_ID) {
                    final View child = inflate(context, childLayoutId, null);
                    addView(child);
                }
            } finally {
                ta.recycle();
            }
        }
    }
}
