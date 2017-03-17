package com.royalnu.demo.client.web.todolist;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.royalnu.kgkx.demo.client.web.todolist.Constants;
import com.royalnu.kgkx.demo.client.web.todolist.verticles.TodoVerticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class TodoVerticleTest {
	private Vertx vertx;
	private Integer port;

	@Before
	public void setUp(TestContext context) throws IOException {
		vertx = Vertx.vertx();
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}
	
	@Test
	public void postTest(TestContext context) {
		
		/*final Async async= context.async();
		 
		for(int i=0;i<100;i++)
		{
			String json="{\"firstName\": \""+i+"Brett\", \"lastName\":\"McLaughlin\", \"email\": \"aaaa\" }";	
			
		vertx.createHttpClient().post(8082,"localhost",Constants.API_CREATE)
		.putHeader("content-type", "application/json")
        .putHeader("content-length", Integer.toString(json.length())).handler(response->{
        	response.bodyHandler(body -> {
        		System.out.println("序号"+response.hashCode()+":"+body.toString());
                async.complete();
              });
        }).write(json)
        .end();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
/*		vertx.createHttpClient().post(8082, "localhost",Constants.API_CREATE, response->{
			
		    response.bodyHandler(body->{
		    	
		    	
		    	System.out.println(body.toString());
		    	
		    	async.complete();
		    });
		    
		    
		}).write(json)
        .end();
*/		
	}

/*	@Test
	public void checkThatTheIndexPageIsServed(TestContext context) {
		
		Async async= context.async();
		vertx.createHttpClient().getNow(8082, "localhost","/api/users", response->{
			context.assertEquals(response.statusCode(), 200);
		    context.assertEquals(response.headers().get("content-type"), "text/html");
		    response.bodyHandler(body->{
		    	System.out.println(body.toString());
		    	
		    	async.complete();
		    });
		    
		    
		});
		
	}*/

}
