package com.aleengo.peach.toolbox.commons.common;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 *
 * Runnable implementation which always sets its thread name.
 */
public abstract class NamedRunnable implements Runnable {

    protected final String name;

    public NamedRunnable() {
        this("");
    }

    public NamedRunnable(String name) {
        this("%s", name);
    }

    public NamedRunnable(String format, Object... args) {
        this.name = String.format(format, args);
    }

    @Override
    public void run() {
        final String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(name  + " [" + oldName + "]");
        try {
            execute();
        } finally {
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract void execute();
}
