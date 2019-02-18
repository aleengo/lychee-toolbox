package com.aleengo.peach.toolbox.commons.net;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */

public final class PeachRequest {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";

    private OkHttpClient client;
    private String baseUrl;
    private Request.Builder config;

    private PeachRequest(Builder builder) {
        this.client = builder.client;
        this.baseUrl = builder.baseUrl;
        this.config = builder.config;
    }

    public void get(final String url, Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(GET_METHOD).url(url)
                .callback(callback)
                .build();
        execute(config);
    }

    public void post(final String url, RequestBody requestBody, Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(POST_METHOD).url(url)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }

    public void put(final String url, final RequestBody requestBody, final Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(PUT_METHOD).url(url)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }

    public void delete(final String url, final RequestBody requestBody, final Callback callback) {

        final RequestConfig config = new RequestConfig.Builder()
                .method(DELETE_METHOD).url(url)
                .requestBody(requestBody)
                .callback(callback)
                .build();
        execute(config);
    }

    /**
     * Get a request with default configurations.
     * @return A {@link PeachRequest} object
     */
    /*public static PeachRequest getDefault() {
        return new PeachRequest.Builder().build();
    }*/

    public Builder newBuilder() {
        return new Builder(this);
    }

    private void execute(final RequestConfig config) {
        final Request request = getRequestBuilder(config.getUrl())
                .method(config.getMethod(), config.getRequestBody())
                .build();

        final Call call = this.client.newCall(request);
        call.enqueue(config.getCallback());
    }

    private Request.Builder getRequestBuilder(final String url) {

        final StringBuilder sb = new StringBuilder();

        // if baseUrl == null, then url is an absolute url
        // otherwise, url is a relative url, and it is concatenated with the baseUrl
        if (this.baseUrl == null) {
            this.baseUrl = url;
            sb.append(baseUrl);
        } else {
            sb.append(baseUrl).append(url);
        }

        final HttpUrl _url = HttpUrl.parse(sb.toString());
        Request.Builder defaultConfig = new Request.Builder().url(_url.toString());

        if (this.config != null) {
            final Request oldRequest = defaultConfig.build();
            this.config.url(oldRequest.url());

            defaultConfig = this.config;
        }

        return defaultConfig;
    }

    public static class Builder {
        private String baseUrl;
        private OkHttpClient client;
        private Request.Builder config;

        public Builder() {
            client = OkHttpSingleton.getInstance().getClient();
        }

        public Builder(OkHttpClient client) {
            this.client = client;
        }

        public Builder(PeachRequest request) {
            this.client = request.client;
            this.baseUrl = request.baseUrl;
            this.config = request.config;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * add new configuration to {@link Request.Builder} object
         * @param config The new Request Builder
         * @return A builder
         */
        public Builder addNewConfig(Request.Builder config) {
            this.config = config;
            return this;
        }

        public PeachRequest build() {
            return new PeachRequest(this);
        }
    }
}
