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
public class PeachSingleThreadExecutor implements PeachExecutor {

    private static final String POOL_NAME = "PeachSinglePool";
    private ThreadPoolExecutor mSingleThreadExecutor;

    public PeachSingleThreadExecutor() {
        this(POOL_NAME);
    }

    public PeachSingleThreadExecutor(String poolName) {
        this.mSingleThreadExecutor = (ThreadPoolExecutor)
                Executors.newSingleThreadExecutor(Utils.defaultThreadFactory(poolName));
    }

    @Override
    public void execute(Runnable command) {
        mSingleThreadExecutor.execute(command);
    }

    @Override
    public <V> V execute(Callable<V> command) {
        final Future<V> promise = mSingleThreadExecutor.submit(command);
        try {
            return promise.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
