package com.aleengo.peach.box.commons.net;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 *
 * Wrapper of {@link okhttp3.Request}
 */

public final class RequestWrapper {

    private static final OkHttpClient DEFAULT_CLIENT = HttpClient.getInstance().get();

    @Getter @Setter
    private String name;
    @Getter
    private OkHttpClient client;
    @Getter
    private RequestConfig config;

    @Getter
    private Request request;

    public RequestWrapper(String name, RequestConfig config) {
        this(name, DEFAULT_CLIENT, config);
    }

    public RequestWrapper(String name, OkHttpClient client, RequestConfig config) {
        this.name = name;
        this.client = client;
        this.config = config;

        this.request = buildRequest();
    }

    public void execute() {
        final Call call = client.newCall(this.request);
        call.enqueue(config.getCallback());
    }

    private Request buildRequest() {

        final StringBuilder sb = new StringBuilder();

        // if baseUrl == null, then endPoint is an absolute endPoint
        // otherwise, endPoint is a relative endPoint, and it is concatenated with the baseUrl
        if (this.config.getBaseUrl() == null) {
            sb.append(config.getEndPoint());
        } else if (config.getEndPoint() == null) {
            sb.append(config.getBaseUrl());
        } else {
            sb.append(config.getBaseUrl()).append(config.getEndPoint());
        }

        final HttpUrl _url = HttpUrl.parse(sb.toString());

        final Request.Builder builder = new Request.Builder()
                .url(_url.toString())
                .method(config.getMethod(), config.getRequestBody());

        Request currentRequest = builder.build();

        if (config.getRequestBuilder() != null) {
            final Request newRequest = config.getRequestBuilder().build();
            if (newRequest.url() == null) {
                config.getRequestBuilder().url(currentRequest.url());
            }

            if (newRequest.method() == null) {
                config.getRequestBuilder().method(currentRequest.method(), currentRequest.body());
            }
            currentRequest = config.getRequestBuilder().build();
        }
        return currentRequest;
    }
}
