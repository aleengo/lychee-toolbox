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
    private final CompletableFuture<String> future;

    public DefaultCallback() {
        this.future = new CompletableFuture<>();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        future.completeExceptionally(e.getCause());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if (response.isSuccessful()) {
            final String body = response.body().string();
            future.complete(body);
        } else {
            future.completeExceptionally(new RuntimeException(response.toString()));
        }
    }
}
