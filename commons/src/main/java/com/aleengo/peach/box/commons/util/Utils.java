package com.aleengo.peach.box.commons.util;

import com.aleengo.peach.box.commons.concurrent.PeachDefaultThreadFactory;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Utils {

    private Utils() {
    }

    public static PeachDefaultThreadFactory defaultThreadFactory(String poolName) {
        return defaultThreadFactory(poolName, false);
    }

    public static PeachDefaultThreadFactory defaultThreadFactory(String poolName, boolean deamon) {
        return new PeachDefaultThreadFactory(poolName, deamon);
    }
}
