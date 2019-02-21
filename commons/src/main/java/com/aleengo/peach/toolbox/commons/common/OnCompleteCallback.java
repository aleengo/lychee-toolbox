package com.aleengo.peach.toolbox.commons.common;

@FunctionalInterface
public interface OnCompleteCallback<E> {
    void onComplete(Response<E> response);
}