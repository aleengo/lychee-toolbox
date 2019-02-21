package com.aleengo.peach.toolbox.commons.factory;

import com.aleengo.peach.toolbox.commons.util.ClassUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by CK_ALEENGO on 21/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class Singleton {

    private Singleton() {}

    private static Object instance;

    private static class LazyHolder {
        private static final Object INSTANCE = instance;
    }

    public static <T> T of(Class<T> cls) {
        if (instance == null) {
            instance = ISingleton.newInstance(cls);
        }
        return (T) LazyHolder.INSTANCE;
    }

    interface ISingleton {
        static Object newInstance(Class<?> cls) {
            try {
                return ClassUtil.newInstance(cls);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
