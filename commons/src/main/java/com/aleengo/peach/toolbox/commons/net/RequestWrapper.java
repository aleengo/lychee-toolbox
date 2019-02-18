package com.aleengo.peach.toolbox.commons.net;

import lombok.Getter;
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

    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String PUT_METHOD = "PUT";
    public static final String DELETE_METHOD = "DELETE";

    private static final OkHttpClient DEFAULT_CLIENT = OkHttpSingleton.getInstance().getClient();

    @Getter
    private OkHttpClient client;
    @Getter
    private RequestConfig config;

    @Getter
    private Request request;

    public RequestWrapper(RequestConfig config) {
        this(DEFAULT_CLIENT, config);
    }

    public RequestWrapper(OkHttpClient client, RequestConfig config) {
        this.client = client;
        this.config = config;

        this.request = getRequestBuilder()
                .method(config.getMethod(), config.getRequestBody())
                .build();
    }

    /*public void get(final String endPoint, Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(GET_METHOD).endPoint(endPoint)
                .callback(callback)
                .build();
        execute(config);
    }

    public void post(final String endPoint, RequestBody requestBody, Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(POST_METHOD).endPoint(endPoint)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }

    public void put(final String endPoint, final RequestBody requestBody, final Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(PUT_METHOD).endPoint(endPoint)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }

    public void delete(final String endPoint, final RequestBody requestBody, final Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(DELETE_METHOD).endPoint(endPoint)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }*/

    /**
     * Get a request with default configurations.
     * @return A {@link RequestWrapper} object
     */
    /*public static RequestWrapper getDefault() {
        return new RequestWrapper.Builder().build();
    }*/

   /* public Builder newBuilder() {
        return new Builder(this);
    }*/

    private void execute(final RequestConfig config) {
        this.request = getRequestBuilder()
                .method(config.getMethod(), config.getRequestBody())
                .build();

        final Call call = this.client.newCall(this.request);
        call.enqueue(config.getCallback());
    }

    private Request.Builder getRequestBuilder() {

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
        Request.Builder defaultConfig = new Request.Builder().url(_url.toString());

        if (config.getRequestBuilder() != null) {
            final Request oldRequest = defaultConfig.build();
            config.getRequestBuilder().url(oldRequest.url());

            defaultConfig = config.getRequestBuilder();
        }

        return defaultConfig;
    }

/*    public static class Builder {
        private OkHttpClient client;
        private RequestConfig config;

        public Builder() {
            client = OkHttpSingleton.getInstance().getClient();
        }

        public Builder(OkHttpClient client) {
            this.client = client;
        }

        public Builder(RequestWrapper request) {
            this.client = request.client;
            this.config = request.config;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Builder requestConfig(RequestConfig config) {
            this.config = config;
            return this;
        }

        public RequestWrapper build() {
            return new RequestWrapper(this);
        }
    }*/
}
