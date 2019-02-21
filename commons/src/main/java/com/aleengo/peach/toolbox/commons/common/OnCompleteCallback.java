package com.aleengo.peach.toolbox.commons.common;

@FunctionalInterface
public interface OnCompleteCallback {
    void onComplete(Response response);
}