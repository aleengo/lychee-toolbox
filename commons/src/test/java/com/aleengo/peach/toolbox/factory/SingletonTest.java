package com.aleengo.peach.toolbox.factory;

import com.aleengo.peach.toolbox.commons.factory.Singleton;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class SingletonTest {


    @Test
    public void testIfTheInstanceIsCorrect() {
        final SingletonTest singletonTest = Singleton.of(SingletonTest.class);
        Assert.assertTrue("It is not instance of", singletonTest instanceof SingletonTest);
    }

    @Test
    public void testIfObjectsAreEquals() {
        final SingletonTest singletonTest = Singleton.of(SingletonTest.class);
        final SingletonTest singletonTest2 = Singleton.of(SingletonTest.class);

        Assert.assertTrue("Object are not equals", singletonTest.equals(singletonTest2));
    }

    @Test
    public void testIfHashCodeAreEquals() {
        final SingletonTest singletonTest = Singleton.of(SingletonTest.class);
        final SingletonTest singletonTest2 = Singleton.of(SingletonTest.class);

        Assert.assertTrue("Object are not equals", singletonTest.hashCode() == singletonTest2.hashCode());
    }
}
