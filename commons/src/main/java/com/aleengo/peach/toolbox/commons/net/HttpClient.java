package com.aleengo.peach.toolbox.commons.net;

import okhttp3.OkHttpClient;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 *
 * #see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
 */
public class HttpClient {

    private OkHttpClient client;

    private HttpClient() {
        client = new OkHttpClient.Builder()
                .build();
    }

    public OkHttpClient get() {
        return client;
    }

    // Bill Pugh implementation
    private static class LazyHolder {
        private static final HttpClient INSTANCE = new HttpClient();
    }

    public static HttpClient getInstance() {
        return LazyHolder.INSTANCE;
    }

}
