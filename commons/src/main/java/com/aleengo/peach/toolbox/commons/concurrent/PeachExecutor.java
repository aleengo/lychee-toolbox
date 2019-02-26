package com.aleengo.peach.toolbox.commons.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
@FunctionalInterface
public interface PeachExecutor extends Executor {
    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, and returns a result or throw an exception
     *
     * @param command The callable task
     * @param <V>
     * @return
     */
    <V> V execute(Callable<V> command);
}
