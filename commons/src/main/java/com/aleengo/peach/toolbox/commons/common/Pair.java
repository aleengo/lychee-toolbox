package com.aleengo.peach.toolbox.commons.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CK_ALEENGO on 13/03/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Pair<F, S> {

    @Getter @Setter
    private F first;
    @Getter @Setter
    private S second;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S > Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
}
