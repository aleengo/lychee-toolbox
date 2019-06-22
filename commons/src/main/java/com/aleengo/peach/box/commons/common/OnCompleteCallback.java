package com.aleengo.peach.box.commons.common;

import com.aleengo.peach.box.commons.model.Response;

@FunctionalInterface
public interface OnCompleteCallback {
    void onComplete(Response response);
}