package com.royalnu.vertx.util;

import io.vertx.core.http.HttpServerRequest;

public class IPUtil {
   public static String getIpAddr(HttpServerRequest request) {
      String ip = request.getHeader("x-forwarded-for");
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
      }

      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }

      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.remoteAddress().host();
      }

      if(ip.equals("0:0:0:0:0:0:0:1")) {
         ip = "127.0.0.1";
      }

      return ip;
   }
}
