package com.aleengo.peach.toolbox.commons.concurrent;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import lombok.Getter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by CK_ALEENGO on 18/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class DefaultCallback implements Callback {

    @Getter
    private final CompletableFuture<String> completableFuture;

    public DefaultCallback() {
        this.completableFuture = new CompletableFuture<>();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        completableFuture.completeExceptionally(e.getCause());
        throw new RuntimeException(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if (response.isSuccessful()) {
            final String body = response.body().string();
            completableFuture.complete(body);
        } else {
            final Throwable ex = new Throwable("Reason : " + response.code() + " - " + response.message());
            completableFuture.completeExceptionally(ex);
            throw new RuntimeException(ex);
        }
    }
}
