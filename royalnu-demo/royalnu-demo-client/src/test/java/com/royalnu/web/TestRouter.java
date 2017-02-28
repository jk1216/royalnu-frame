package com.royalnu.web;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class TestRouter {
	private Vertx vertx;
	private int port;
	
	@Before
	public void setUp(TestContext context)throws IOException 
	{
		
		 vertx = Vertx.vertx();
		 port=8080;
	}
	
	@After
	public void tearDown(TestContext context) {
	    vertx.close(context.asyncAssertSuccess());
	 }
	
	@Test
	public void checkThatTheIndexPageIsServed(TestContext context) {
		
		Async async= context.async();
		vertx.createHttpClient().getNow(port, "localhost","/test?key=111", response->{
		    response.bodyHandler(body->{
		    	System.out.println(body.toString());
		    	
		    	async.complete();
		    });
		});
	
	
	}
}
