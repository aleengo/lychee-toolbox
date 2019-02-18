package com.aleengo.peach.toolbox.commons.Main;

import com.aleengo.peach.toolbox.commons.concurrent.ConcurrentService;
import com.aleengo.peach.toolbox.commons.concurrent.DefaultCallback;
import com.aleengo.peach.toolbox.commons.net.OkHttpSingleton;
import com.aleengo.peach.toolbox.commons.net.RequestConfig;
import com.aleengo.peach.toolbox.commons.net.RequestWrapper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Main {

    private static final String BASE_URL = "https://openexchangerates.org/api/";
    public static final String CURRENCIES = "currencies.json";
    public static final String LATEST = "latest.json";

    public static void main(String[] args) throws UnknownHostException {

        final DefaultCallback callback = new DefaultCallback();
        String host = "";
        InetAddress address = Inet4Address.getByName(host);

        final OkHttpClient client = OkHttpSingleton.getInstance().getClient()
                .newBuilder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, 3000)))
                .build();

        final RequestConfig latest =
                new RequestConfig.Builder(RequestWrapper.GET_METHOD, new DefaultCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(LATEST)
                        .build();

        final RequestConfig currencies =
                new RequestConfig.Builder(RequestWrapper.GET_METHOD, new DefaultCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(CURRENCIES)
                        .build();

        final RequestWrapper req1 = new RequestWrapper(client, latest);
        final RequestWrapper req2 = new RequestWrapper(client, currencies);

        final List<Future<String>> futureList = ConcurrentService.execute(Arrays.asList(req1, req2));

        final Future<String> future1 = futureList.get(0);
        final Future<String> future2 = futureList.get(1);

        futureList.forEach(stringFuture -> {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
