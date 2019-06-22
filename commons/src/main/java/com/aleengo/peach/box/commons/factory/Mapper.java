package com.aleengo.peach.box.commons.factory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by CK_ALEENGO on 26/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class Mapper {

    private Mapper() {
    }

    public static JsonObject map(String str) {
        final JsonObject jo = new JsonParser()
                .parse(new JsonReader(new StringReader(str)))
                .getAsJsonObject();

        return jo;
    }

    public static <T> T deserialize(Gson gson, String json, Class<T> cls) {
        return gson.fromJson(new JsonReader(new StringReader(json)), cls);
    }
}
