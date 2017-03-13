package com.royalnu.kgkx.demo.client.web.todolist.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

public class DeployVertx extends AbstractVerticle {

	public static void main(String[] args)
	{
		    VertxOptions vOpts = new VertxOptions();
	        DeploymentOptions options = new DeploymentOptions().setInstances(1).setConfig(new JsonObject().put("local", true));
	        vOpts.setClustered(true);
	        Vertx.clusteredVertx(vOpts, cluster -> {
	            if (cluster.succeeded()) {
	                final Vertx result = cluster.result();
	                result.deployVerticle(TodoVerticle.class.getName(), options, handle -> {

	                });
	            }
	        });
	}
}
