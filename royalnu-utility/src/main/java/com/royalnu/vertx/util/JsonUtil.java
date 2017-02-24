package com.royalnu.vertx.util;

import com.google.common.base.Preconditions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
   public static String toJsonString(Object object) {
      return Json.encode(Preconditions.checkNotNull(object));
   }

   public static <T> T toObject(String text, Class<T> c) {
      return (T) Json.decodeValue((String)Preconditions.checkNotNull(text), (Class)Preconditions.checkNotNull(c));
   }

   public static <T> T toObject(JsonObject json, Class<T> c) {
      return (T) Json.decodeValue(((JsonObject)Preconditions.checkNotNull(json)).toString(), (Class)Preconditions.checkNotNull(c));
   }

   public static <T> List<T> toArray(String text, Class<T> c) {
      JsonArray jsonArray = new JsonArray((String)Preconditions.checkNotNull(text));
      ArrayList list = new ArrayList();
      jsonArray.forEach((obj) -> {
         Json.decodeValue(obj.toString(), c);
      });
      return list;
   }

   public static JsonObject toJSONObject(Object obj) {
      String jsonStr = Json.encode(Preconditions.checkNotNull(obj));
      return new JsonObject(jsonStr);
   }
}