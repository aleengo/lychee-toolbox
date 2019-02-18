package com.aleengo.peach.toolbox.commons.concurrent;

import com.aleengo.peach.toolbox.commons.net.RequestWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.Call;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class ConcurrentService {

    private static final int MAX_POOL_SIZE = 3;

    private ConcurrentService() {
    }

    public static Future<String> execute(RequestWrapper wrapper) {
        return execute(Arrays.asList(wrapper)).get(0);
    }

    // execute a list of requests
    public static List<Future<String>> execute(List<RequestWrapper> requests) {
        final List<Future<String>> futureList = new ArrayList<>();

        requests.forEach(wrapper -> futureList.add(_execute(wrapper)));
        // shutdown the service
        getService().shutdown();

        return futureList;
    }

    private static Future<String> _execute(RequestWrapper wrapper) {
        final Runnable runnable = () -> {
            final Call call = wrapper.getClient().newCall(wrapper.getRequest());
            call.enqueue(wrapper.getConfig().getCallback());
        };
        getService().execute(runnable);
        return wrapper.getConfig().getCallback().getFuture();
    }

    private static ExecutorService getService() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final ExecutorService INSTANCE =
                Executors.newFixedThreadPool(MAX_POOL_SIZE);
    }
}
