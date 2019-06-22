package com.aleengo.peach.box.commons.factory;

import com.aleengo.peach.box.commons.util.ClassUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by CK_ALEENGO on 21/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class Singleton {

    private Singleton() {}

    public static <T> T of(Class<T> cls, T instance) {
        if (instance == null) {
            synchronized (cls) {
                if (instance == null) {
                    instance = (T) Singleton.ISingleton.instance(cls);
                }
            }
        }
        return instance;
    }

    interface ISingleton {
        static Object instance(Class cls) {
            try {
                return ClassUtil.newInstance(cls);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
