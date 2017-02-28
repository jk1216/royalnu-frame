package com.royalnu.kgkx.client.web;

import java.util.Arrays;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.spring.VertxSpring;

public class MultiVerticleServer {
	 public static void main(String[] args) {
	        Vertx vertx = Vertx.vertx();
	        CompositeFuture.all(
	            Arrays.asList(
	                deploy(vertx)
	                )
	        ).setHandler((res) -> {
	            if (res.failed()) {
	                res.cause().printStackTrace();
	            }
	        });
	    }

	    private static Future<String> deploy(Vertx vertx) {
	        Future<String> future = Future.future();
	        VertxSpring.deploy(vertx,
	            "spring/config/test-context.xml",
	            new HttpServerOptions()
	                .setPort(8080),
	            future.completer());
	        return future;
	    }
}
