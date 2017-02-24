package com.royalnu.vertx.util;

public final class CheckUtil {
   public static boolean checkNull(Object reference) {
      boolean isNull = false;
      if(null == reference || "".equals(reference)) {
         isNull = true;
      }

      return isNull;
   }

   public static boolean checkNotNull(Object reference) {
      boolean isNull = false;
      if(null != reference && !"".equals(reference)) {
         isNull = true;
      }

      return isNull;
   }
}