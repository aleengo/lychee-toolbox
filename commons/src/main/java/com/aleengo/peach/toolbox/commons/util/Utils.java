package com.aleengo.peach.toolbox.commons.util;

import java.util.concurrent.ThreadFactory;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Utils {

    private Utils() {
    }

    public static ThreadFactory threadFactory(String name, boolean deamon) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                final Thread thread = new Thread(runnable, name);
                thread.setDaemon(deamon);
                return thread;
            }
        };
    }
}
