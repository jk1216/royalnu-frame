package com.royalnu.kgkx.client.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class HttpServerVerticle extends AbstractVerticle {
	
	@Override
	public void start(Future<Void> fut) throws Exception {
		int port = config().getInteger("http.port", 8080);		
		Router router=Router.router(vertx);
		
		
		
		vertx.createHttpServer().requestHandler(router::accept).listen(port,result->
		{
			if(result.succeeded())
			{
				fut.complete();
			}else{
				fut.fail(result.cause());
			}
		});
		
	}
}
