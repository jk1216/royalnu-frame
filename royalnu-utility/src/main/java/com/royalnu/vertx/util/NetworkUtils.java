package com.royalnu.vertx.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class NetworkUtils {
   public static String getInterface() {
      try {
         return Inet4Address.getLocalHost().getHostAddress();
      } catch (UnknownHostException arg0) {
         return "127.0.0.1";
      }
   }
}