package com.aleengo.peach.box.commons.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class PeachDefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    private boolean deamon;

    public PeachDefaultThreadFactory(String poolName, boolean deamon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = poolName + " [pool-" + poolNumber.getAndIncrement() + "-thread-";
        this.deamon = deamon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread newThread = new Thread(group, runnable,
                namePrefix + threadNumber.getAndIncrement() + "]",
                0);
        newThread.setDaemon(deamon);
        newThread.setPriority(Thread.NORM_PRIORITY);
        return newThread;
    }
}
