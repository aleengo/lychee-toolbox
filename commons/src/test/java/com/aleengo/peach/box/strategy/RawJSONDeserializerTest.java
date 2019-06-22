package com.aleengo.peach.box.strategy;

import com.aleengo.peach.box.commons.model.RawJSON;
import com.aleengo.peach.box.commons.strategy.RawJSONDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class RawJSONDeserializerTest {

    private static Gson gson;
    private static String singleJson;
    private static String longJson;

    @BeforeClass
    public static void setup() {
        gson = new GsonBuilder()
                .registerTypeAdapter(RawJSON.class, new RawJSONDeserializer())
                .setPrettyPrinting()
                .create();

        singleJson = "{\n" +
                        "\"USD\": \"us dollar\"\n" +
                    "}";

        longJson = "{\n" +
                "\"USD\": \"us dollar\",\n" +
                "\"XAF\": \"CFA Franc\"\n" +
                "}";

    }

    @AfterClass
    public static void clean() {
        gson = null;
        singleJson = null;
        longJson = null;
    }

    @Test
    public void testIfListIsNullWhenJsonIsEmpty() {
        final Type pairType = new TypeToken<List<RawJSON>>(){}.getType();

        final List<RawJSON> pairList = gson.fromJson("", pairType);
        Assert.assertNull("List cannot be not null", pairList);
    }

    @Test
    public void testIfPairSizeIsEqualsToOne() {
        RawJSON pair = gson.fromJson(new StringReader(singleJson), RawJSON.class);
        Assert.assertNotNull("List cannot be null", pair);

        Assert.assertTrue(pair instanceof RawJSON);

        Assert.assertTrue(pair.getItems().size() == 1);
    }

    @Test
    public void testIfPairSizeIsGreaterThanOne() {
        RawJSON pair = gson.fromJson(new StringReader(longJson), RawJSON.class);
        Assert.assertNotNull("List cannot be null", pair);

        Assert.assertTrue(pair.getItems().size() == 2);
    }


}
