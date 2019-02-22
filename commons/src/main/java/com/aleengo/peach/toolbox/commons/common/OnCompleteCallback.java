package com.aleengo.peach.toolbox.commons.common;

import com.aleengo.peach.toolbox.commons.model.Response;

@FunctionalInterface
public interface OnCompleteCallback {
    void onComplete(Response response);
}