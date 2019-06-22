package com.aleengo.peach.box.commons.concurrent;

import com.aleengo.peach.box.commons.common.OnCompleteCallback;
import com.aleengo.peach.box.commons.model.Response;
import com.aleengo.peach.box.commons.net.RequestWrapper;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class HTTPService {

    private static final String HTTP_SERVICE_NAME = "PeachHTTPService";
    private static final int MAX_POOL_SIZE = 4;

    private HTTPService() {
    }

    public static void execute(RequestWrapper wrapper, OnCompleteCallback callback) {
        execute(Arrays.asList(wrapper), callback);
    }

    // execute a list of requests
    public static void execute(List<RequestWrapper> requests, OnCompleteCallback callback) {
        final Map<String, Future<String>> futures = new LinkedHashMap<>();
        requests.forEach(wrapper -> futures.put(wrapper.getName(), _execute(wrapper)));
        // shutdown the service
        //getService().shutdown();

        process(futures, callback);
    }

    private static Future<String> _execute(RequestWrapper wrapper) {
        final Runnable task = wrapper::execute;
        getService().execute(task);
        return wrapper.getConfig().getCallback().getCompletableFuture();
    }

    private static <T> void process(Map<String, Future<T>> futures, OnCompleteCallback callback) {

        //System.out.println("task execution on thread (process) : " + Thread.currentThread().getName());
        // The CompletableFuture.allOf static method
        // allows to wait for completion of all of the Futures provided
        final CompletableFuture<Map<String, T>> responseCompletableFuture =
                CompletableFuture.allOf(futures.values().toArray(new CompletableFuture[futures.size()]))
                        .thenApply(v -> {
                            //System.out.println("task execution on thread (process thenApply) : " + Thread.currentThread().getName());
                            return futures.keySet().stream()
                                    .collect(Collectors.toMap(key -> key, key -> {
                                        try {
                                            return futures.get(key).get();
                                        } catch (InterruptedException | ExecutionException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }));
                            });

        responseCompletableFuture.whenComplete((results, throwable) -> {
            if (throwable != null) {
                responseCompletableFuture.completeExceptionally(throwable);
                callback.onComplete(new Response(results, throwable));
            } else {
                if (results.size() == 1) {
                    final Response response = new Response();
                    results.keySet().forEach(key -> {
                        response.setError(null);
                        response.setValue(results.get(key));
                    });
                    callback.onComplete(response);
                } else {
                    callback.onComplete(new Response(results, null));
                }
            }
        });
    }

    private static PeachFixedThreadExecutor getService() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final PeachFixedThreadExecutor INSTANCE =
                new PeachFixedThreadExecutor(HTTP_SERVICE_NAME, MAX_POOL_SIZE);
    }
}
