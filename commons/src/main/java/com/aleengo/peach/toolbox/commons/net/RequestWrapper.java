package com.aleengo.peach.toolbox.commons.net;

import com.aleengo.peach.toolbox.commons.factory.Singleton;

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

    private static final OkHttpClient DEFAULT_CLIENT = Singleton.of(HttpClient.class).get();

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

        this.request = buildRequest();
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
                .method(config.getMethod().get(), config.getRequestBody());

        Request currentRequest = builder.build();

        if (config.getRequestBuilder() != null) {
            Request newRequest = config.getRequestBuilder().build();
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


    public enum Method {
        GET("GET"),
        POST("POST"),
        PUT("DELETE"),
        DELETE("DELETE");

        private String method;
        Method(String method) {
            this.method = method;
        }
        public String get() {
            return method;
        }
    }

/*    public static class Builder {
        private OkHttpClient client;
        private RequestConfig config;

        public Builder() {
            client = HttpClient.getInstance().getClient();
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
