package com.aleengo.peach.toolbox.net;

import com.aleengo.peach.toolbox.commons.concurrent.HTTPService;
import com.aleengo.peach.toolbox.commons.concurrent.ResponseCallback;
import com.aleengo.peach.toolbox.commons.factory.Singleton;
import com.aleengo.peach.toolbox.commons.model.RawJSON;
import com.aleengo.peach.toolbox.commons.model.Result;
import com.aleengo.peach.toolbox.commons.net.HttpClient;
import com.aleengo.peach.toolbox.commons.net.RequestConfig;
import com.aleengo.peach.toolbox.commons.net.RequestWrapper;
import com.aleengo.peach.toolbox.commons.strategy.RawJSONDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class GetRequestTest {

    private static final String BASE_URL = "https://openexchangerates.org/api/";
    public static final String CURRENCIES = "currencies.json";
    public static final String LATEST = "latest.json";


    private static OkHttpClient client;
    private static RequestConfig latestConfig;
    private static RequestConfig currenciesConfig;

    private Result<List<Currency>> currenciesResult;

    @BeforeClass
    public static void setup() throws UnknownHostException {
        String host = "";
        InetAddress address = Inet4Address.getByName(host);
        client = Singleton.of(HttpClient.class).get()
                .newBuilder()
                //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, 2800)))
                .build();

        latestConfig = new RequestConfig.Builder(new ResponseCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(LATEST)
                        .build();
        currenciesConfig = new RequestConfig.Builder(new ResponseCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(CURRENCIES)
                        .build();
    }

    @AfterClass
    public static void cleanup() {
        client = null;
        latestConfig = null;
        currenciesConfig = null;
    }

    @Test
    public void testSizeGreaterThanZero() throws InterruptedException, ExecutionException {
        final RequestWrapper req1 = new RequestWrapper(client, currenciesConfig);
        final CompletableFuture<Result> future = new CompletableFuture<>();

        System.out.println(" testResultSizeGreaterThanZero: " + Thread.currentThread().getName());

        final Runnable task = () -> {
            System.out.println(" Runnable task: " + Thread.currentThread().getName());
            HTTPService.execute(req1, response -> {
                System.out.println(" OnCompleteCallback : " + Thread.currentThread().getName());
                if (response.getError() != null) {
                    Result result = new Result<>(null, response.getError());
                    future.complete(result);
                    return;
                }

                final String json = (String) response.getValue();

                final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(RawJSON.class, new RawJSONDeserializer())
                        .create();

                final RawJSON rawData = gson.fromJson(json, RawJSON.class);

                final List<Currency> currencies = rawData.getItems().entrySet()
                        .stream()
                        .map(entry -> new Currency(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());

                future.complete(new Result<>(currencies, null));
            });
        };

        final ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);

        System.out.println(" before future.get(): " + Thread.currentThread().getName());

        currenciesResult = future.get();

        System.out.println(" after future.get(): " + Thread.currentThread().getName());

        Assert.assertTrue(currenciesResult.getValue() instanceof List);
        Assert.assertTrue(currenciesResult.getValue().size() > 0);

        System.out.println(" testResultSizeGreaterThanZero: " + Thread.currentThread().getName());
    }

}
