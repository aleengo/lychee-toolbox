package com.aleengo.peach.box.commons.util;

import com.aleengo.peach.box.commons.concurrent.PeachSingleThreadExecutor;
import com.aleengo.peach.box.commons.concurrent.PeachFixedThreadExecutor;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class AppExecutors {

    private PeachSingleThreadExecutor mDiskIO;
    private PeachFixedThreadExecutor mNetworkIO;

    public AppExecutors() {
        this(new PeachSingleThreadExecutor(), new PeachFixedThreadExecutor());
    }

    public AppExecutors(PeachSingleThreadExecutor mDiskIO, PeachFixedThreadExecutor mNetworkIO) {
        this.mDiskIO = mDiskIO;
        this.mNetworkIO = mNetworkIO;
    }

    public PeachSingleThreadExecutor diskIO() {
        return mDiskIO;
    }

    public PeachFixedThreadExecutor networkIO() {
        return mNetworkIO;
    }
}
