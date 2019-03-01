package com.aleengo.peach.toolbox.commons.concurrent;

import com.aleengo.peach.toolbox.commons.util.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class PeachSingleThreadExecutor implements PeachExecutor {

    private static final String POOL_NAME = "PeachSinglePool";
    private ExecutorService mSingleThreadExecutor;

    public PeachSingleThreadExecutor() {
        this(POOL_NAME);
    }

    public PeachSingleThreadExecutor(String poolName) {
        this.mSingleThreadExecutor =
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
