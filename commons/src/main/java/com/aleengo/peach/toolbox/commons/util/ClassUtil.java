package com.aleengo.peach.toolbox.commons.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * Created by CK_ALEENGO on 21/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class ClassUtil {

    private ClassUtil() {
    }

    // call default contstructor
    public static Object newInstance(final String className) {
        try {
            final Class clazz = Class.forName(className);
            return newInstance(clazz);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // call default contstructor
    public static Object newInstance(Class<?> clazz)
            throws NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        return newInstance(clazz, null, null);
    }

    // constructor with parameters
    public static Object newInstance(Class clazz, Class[] parameterTypes, Object[] args)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        final Constructor ctor = getConstructor(clazz, parameterTypes);
        // creates object of a class as constructor is accessible now.
        final Object o = ctor.newInstance(args);
        // change the accessibility of constructor
        ctor.setAccessible(Modifier.PUBLIC == ctor.getModifiers());
        return o;
    }

    private static Constructor getConstructor(Class clazz, Class[] parameterTypes)
            throws NoSuchMethodException {

        final Constructor ctor = clazz.getDeclaredConstructor(parameterTypes);
        // change the accessibility of constructor for outside a class object creation.
        ctor.setAccessible(true);
        return ctor;
    }
}
