package com.aleengo.peach.toolbox.adapter;

import com.aleengo.peach.toolbox.commons.common.Pair;

/**
 * Created by CK_ALEENGO on 06/03/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public interface ItemView<T> {
    void bind(Pair<T, Integer> pair);
}
