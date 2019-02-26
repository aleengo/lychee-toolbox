package com.aleengo.peach.toolbox.commons.concurrent;

import com.aleengo.peach.toolbox.commons.util.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class PeachFixedThreadExecutor implements PeachExecutor {

    private static final int MAX_THREADS = 3;
    private static final String POOL_NAME = "PeachFixedPool";

    private ThreadPoolExecutor executor;

    public PeachFixedThreadExecutor() {
        this(POOL_NAME, MAX_THREADS);
    }

    public PeachFixedThreadExecutor(String poolName) {
        this(poolName, MAX_THREADS);
    }

    public PeachFixedThreadExecutor(int nbThreads) {
        this(POOL_NAME, nbThreads);
    }

    public PeachFixedThreadExecutor(String poolName, int nbThreads) {
        this.executor = (ThreadPoolExecutor)
                Executors.newFixedThreadPool(nbThreads, Utils.defaultThreadFactory(poolName));
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }

    @Override
    public <V> V execute(Callable<V> command) {
        final Future<V> promise = executor.submit(command);
        try {
            return promise.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
