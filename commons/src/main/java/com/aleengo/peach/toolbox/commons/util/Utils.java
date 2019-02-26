package com.aleengo.peach.toolbox.commons.util;

import com.aleengo.peach.toolbox.commons.concurrent.PeachDefaultThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Utils {

    private Utils() {
    }

    public static PeachDefaultThreadFactory threadFactory(String label, boolean deamon) {
        return new PeachDefaultThreadFactory(label, deamon);
    }

    public static PeachDefaultThreadFactory threadFactory(String label) {
        return new PeachDefaultThreadFactory(label, false);
    }
}
