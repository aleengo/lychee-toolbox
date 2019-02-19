package com.aleengo.peach.toolbox.commons.common;

import java.util.List;

@FunctionalInterface
public interface OnCompleteCallback {
    void onComplete(List<Result> results, Throwable throwable);
}