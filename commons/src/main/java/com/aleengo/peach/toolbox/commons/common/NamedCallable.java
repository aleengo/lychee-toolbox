package com.aleengo.peach.toolbox.commons.common;

import java.util.concurrent.Callable;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 *
 * Callable implementation which always sets its thread name.
 */
public abstract class NamedCallable<V> implements Callable<V> {

    private final String name;

    public NamedCallable() {
        this("");
    }

    public NamedCallable(String name) {
        this("%s", name);
    }

    public NamedCallable(String format, Object... args) {
        this.name = String.format(format, args);
    }

    @Override
    public V call() throws Exception {
        final String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(name  + " [" + oldName + "]");
        try {
            return execute();
        } finally {
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract V execute();
}
