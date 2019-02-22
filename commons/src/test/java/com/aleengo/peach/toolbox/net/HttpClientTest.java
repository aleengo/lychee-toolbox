package com.aleengo.peach.toolbox.net;

import com.aleengo.peach.toolbox.commons.factory.Singleton;
import com.aleengo.peach.toolbox.commons.net.HttpClient;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.OkHttpClient;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class HttpClientTest {

    @Test
    public void testInstanceOfHttpClient() {
        final HttpClient httpClient = Singleton.of(HttpClient.class);
        Assert.assertTrue("is not the instanceOf ", httpClient instanceof HttpClient);
    }

    @Test
    public void testInstanceOfOkHttpClient() {
        final OkHttpClient client = Singleton.of(HttpClient.class).get();
        Assert.assertTrue("is not the instanceOf ", client instanceof OkHttpClient);
    }
}
