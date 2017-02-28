package com.royalnu.kgkx.client.web;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.spring.annotation.RouterHandler;
import io.vertx.ext.spring.annotation.VertxRouter;
import io.vertx.ext.web.RoutingContext;


@VertxRouter
public class UserRouter {
	 
		//用户注册
	    @RouterHandler(method = HttpMethod.GET, value = "/add")
	    public void add(RoutingContext context)
	    {	
	    	 String key = context.request().params().get("key");
		     context.response().end(key);
	    }
	  
	  
	  
}
