package com.aleengo.peach.toolbox.commons.net;

import com.aleengo.peach.toolbox.commons.concurrent.ResponseCallback;

import lombok.Getter;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class RequestConfig {

    @Getter
    private String method;
    @Getter
    private String endPoint;
    @Getter
    private String baseUrl;
    @Getter
    private RequestBody requestBody;
    @Getter
    private Request.Builder requestBuilder;
    @Getter
    private ResponseCallback callback;

    private RequestConfig(Builder builder) {
        this.method = builder.method;
        this.endPoint = builder.endPoint;
        this.baseUrl = builder.baseUrl;
        this.requestBody = builder.requestBody;
        this.requestBuilder = builder.requestBuilder;
        this.callback = builder.callback;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String method;
        private String endPoint;
        private String baseUrl;
        private RequestBody requestBody;
        private Request.Builder requestBuilder;
        private ResponseCallback callback;

        public Builder(ResponseCallback callback) {
            this(RequestMethod.GET, callback);
        }

        public Builder(String method, ResponseCallback callback) {
            this.method = method;
            this.callback = callback;
        }

        public Builder(RequestConfig config) {
            this.method = config.method;
            this.endPoint = config.endPoint;
            this.baseUrl = config.baseUrl;
            this.requestBuilder = config.requestBuilder;
            this.requestBody = config.requestBody;
            this.callback = config.callback;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder endPoint(String endpoint) {
            this.endPoint = endpoint;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder requestBody(RequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        /**
         * add new configuration to {@link Request.Builder} object
         * @param requestBuilder The new Request Builder
         * @return A builder
         */
        public Builder requestBuilder(Request.Builder requestBuilder) {
            this.requestBuilder = requestBuilder;
            return this;
        }

        public Builder callback(ResponseCallback callback) {
            this.callback = callback;
            return this;
        }

        public RequestConfig build() {
            if (method == null) {
                throw new IllegalArgumentException("method may not be null. MethodType correspond to HTTP methods");
            }
            if (endPoint == null && baseUrl == null) {
                throw new IllegalArgumentException("endPoint or baseUrl may not be null.");
            }
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            return new RequestConfig(this);
        }
    }
}
