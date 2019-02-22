package com.aleengo.peach.toolbox.commons.concurrent;

import com.aleengo.peach.toolbox.commons.common.OnCompleteCallback;
import com.aleengo.peach.toolbox.commons.model.Response;
import com.aleengo.peach.toolbox.commons.net.RequestWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class HTTPService {

    private static final int MAX_POOL_SIZE = 2;

    private HTTPService() {
    }

    public static void execute(RequestWrapper wrapper, OnCompleteCallback callback) {
        execute(Arrays.asList(wrapper), callback);
    }

    // execute a list of requests
    public static void execute(List<RequestWrapper> requests, OnCompleteCallback callback) {
        final List<Future<String>> futures = new ArrayList<>();
        requests.forEach(wrapper -> futures.add(_execute(wrapper)));
        // shutdown the service
        getService().shutdown();

        process(futures, callback);
    }

    private static Future<String> _execute(RequestWrapper wrapper) {
        final Runnable task = wrapper::execute;
        getService().submit(task);
        return wrapper.getConfig().getCallback().getCompletableFuture();
    }

    private static <T> void process(List<Future<T>> futures, OnCompleteCallback callback) {

        //System.out.println("task execution on thread (process) : " + Thread.currentThread().getName());
        // The CompletableFuture.allOf static method
        // allows to wait for completion of all of the Futures provided
        final CompletableFuture<List<T>> responseCompletableFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                        .thenApply(v -> {
                            //System.out.println("task execution on thread (process thenApply) : " + Thread.currentThread().getName());
                            return futures.stream()
                                    .map(future -> {
                                        try {
                                            return future.get();
                                        } catch (InterruptedException | ExecutionException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }).collect(Collectors.toList());
                        });

        responseCompletableFuture.whenComplete((results, throwable) -> {
            if (throwable != null) {
                responseCompletableFuture.completeExceptionally(throwable);
                callback.onComplete(new Response(results, throwable));
            } else {
                if (results.size() == 1) {
                    callback.onComplete(new Response(results.get(0), null));
                } else {
                    callback.onComplete(new Response(results, null));
                }
            }
        });
    }

    private static ExecutorService getService() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final ExecutorService INSTANCE = Executors.newFixedThreadPool(MAX_POOL_SIZE);
    }
}
