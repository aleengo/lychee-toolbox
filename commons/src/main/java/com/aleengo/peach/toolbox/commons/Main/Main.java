package com.aleengo.peach.toolbox.commons.Main;

/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Main {

    private static final String BASE_URL = "https://openexchangerates.org/api/";
    public static final String CURRENCIES = "currencies.json";
    public static final String LATEST = "latest.json";

/*    public static void main(String[] args) throws UnknownHostException {

        String host = "";
        InetAddress address = Inet4Address.getByName(host);

        final OkHttpClient client = OkHttpSingleton.getInstance().getClient()
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

        final List<Future<String>> futureList = HttpService.execute(Arrays.asList(req1, req2));

        final Future<String> future1 = futureList.get(0);
        final Future<String> future2 = futureList.get(1);

        String result1 = null;
        String result2 = null;

        while (true) {
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
        }

        HttpService.execute(Arrays.asList(req2, req1), (OnCompleteCallback<String>) response -> {
            if (response.getError() != null) {
                throw new RuntimeException(response.getError());
            }
            response.getValue().forEach(System.out::println);
        });
    }*/
}
