package com.aleengo.peach.toolbox.commons.strategy;

import com.aleengo.peach.toolbox.commons.model.RawJSON;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class RawJSONDeserializer implements JsonDeserializer<RawJSON> {

    @Override
    public RawJSON deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jo = json.getAsJsonObject();
        final Map<String, String> items = new LinkedHashMap<>();
        jo.entrySet().forEach(entry -> items.put(entry.getKey(), entry.getValue().getAsString()));
        return new RawJSON(items);
    }
}
