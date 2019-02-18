package com.aleengo.peach.toolbox.commons.net;

import lombok.Getter;
import okhttp3.Callback;
import okhttp3.RequestBody;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class RequestConfig {

    @Getter
    private String method;
    @Getter
    private String url;
    @Getter
    private RequestBody requestBody;
    @Getter
    private Callback callback;

    private RequestConfig(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.callback = builder.callback;
        this.requestBody = builder.requestBody;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String method;
        private String url;
        private RequestBody requestBody;
        private Callback callback;


        public Builder() {}

        public Builder(RequestConfig config) {
            this.method = config.method;
            this.url = config.url;
            this.callback = config.callback;
            this.requestBody = config.requestBody;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }
        public Builder requestBody(RequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public RequestConfig build() {
            if (method == null) {
                throw new IllegalArgumentException("method may not be null. Method correspond to HTTP methods");
            }

            if (url == null) {
                throw new IllegalArgumentException("url may not be null.");
            }

            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }

            return new RequestConfig(this);
        }
    }
}
