package com.aleengo.peach.toolbox.commons.Main;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Main {

    private static final String BASE_URL = "https://openexchangerates.org/api/";
    public static final String CURRENCIES = "currencies.json";
    public static final String LATEST = "latest.json";

   /* public static void main(String[] args) throws UnknownHostException {

        String host = "";
        InetAddress address = Inet4Address.getByName(host);

        final OkHttpClient client = HttpClient.of().getClient()
                .newBuilder()
                //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, 2800)))
                .build();

        final RequestConfig latestCfg =
                new RequestConfig.Builder(new DefaultCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(LATEST)
                        .build();

        final RequestConfig currenciesCfg =
                new RequestConfig.Builder(new DefaultCallback())
                        .baseUrl(BASE_URL)
                        .endPoint(CURRENCIES)
                        .build();

        final RequestWrapper req1 = new RequestWrapper(client, latestCfg);
        final RequestWrapper req2 = new RequestWrapper(client, currenciesCfg);

        *//*final List<Future<String>> futureList = HttpService.execute(Arrays.asList(req1, req2));

        final Future<String> future1 = futureList.get(0);
        final Future<String> future2 = futureList.get(1);

        String result1 = null;
        String result2 = null;*//*

       *//* while (true) {
            if (future1.isDone() && future2.isDone()) {
                try {
                    result1 = future1.get();
                    result2 = future2.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("task execution on thread (while run): " + Thread.currentThread().getName());
                break;
            }
        }*//*

      *//*  HTTPService.execute(req2, response -> {
            if (response.getError() != null) {
                throw new RuntimeException(response.getError());
            }
            System.out.println("response: " + (String) response.getValue());
        });*//*


       *//* HTTPService.execute(Arrays.asList(req2, req1), response -> {
            if (response.getError() != null) {
                throw new RuntimeException(response.getError());
            }
            ((List<String>)response.getValue()).forEach(System.out::println);
        });*//*
    }*/

   /* public static void main(String[] args) {

        *//*ExecutorService service = Executors.newFixedThreadPool(2);
        Future<DefaultCallback> f1 = service.submit(() -> {
            System.out.println("Thread f1 : " + Thread.currentThread().getName());
            return Singleton.of(DefaultCallback.class);
        });
        Future<DefaultCallback> f2 = service.submit(() -> {
            System.out.println("Thread f2 : " + Thread.currentThread().getName());
            return Singleton.of(DefaultCallback.class);
        } );
        Future<DefaultCallback> f3 = service.submit(() -> {
            System.out.println("Thread f3: " + Thread.currentThread().getName());
            return Singleton.of(DefaultCallback.class);
        } );

        *//**//*DefaultCallback callback = Singleton.of(DefaultCallback.class);
        DefaultCallback callback2 = Singleton.of(DefaultCallback.class);*//**//*

        while (true) {
            if (f1.isDone() && f2.isDone() && f3.isDone()) {

                try {
                    DefaultCallback callback = f1.get();
                    DefaultCallback callback2 = f2.get();
                    DefaultCallback callback3 = f3.get();

                    int hash1 = callback.hashCode();
                    int hash2 = callback2.hashCode();
                    int hash3 = callback3.hashCode();
                    System.out.println(hash1 == hash2 ? "Yay" : "Nay");
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        service.shutdown();
*//*

        final SingletonTest singletonTest = Singleton.of(SingletonTest.class);
        singletonTest.print(" Hello Singleton!!!");

    }*/
}
