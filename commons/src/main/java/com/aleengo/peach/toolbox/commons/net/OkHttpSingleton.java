package com.aleengo.peach.toolbox.commons.net;

import lombok.Getter;
import okhttp3.OkHttpClient;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 *
 * #see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
 */
public class OkHttpSingleton {

    @Getter
    private OkHttpClient client;

    private OkHttpSingleton() {
        client = new OkHttpClient.Builder()
                .build();
    }

    // Bill Pugh implementation
    private static class LazyHolder {
        public static final OkHttpSingleton INSTANCE = new OkHttpSingleton();
    }


    public static OkHttpSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
