package com.royalnu.kgkx.exhibition;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class RegisterVerticleTest {
	
	private Vertx vertx;
	private Integer port=8081;
	private String host="localhost";

	@Before
	public void setUp(TestContext context) throws IOException {
		vertx = Vertx.vertx();
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	/**
	 * 添加用户
	 * 
	 * @param context
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void add(TestContext context) throws ParseException, UnsupportedEncodingException {
		final Async async  = context.async(); 
      
		long birthDate = 0;  
		Date date = DateUtils.parseDateStrictly("1979-12-16", new String[] { "yyyy-MM-dd" });
		birthDate = date.getTime();
 
		Register addRegister=new Register(); 
		JsonObject addRegisterJsonObject=new JsonObject();
		addRegister.setId(1);
		addRegister.setGroup_name("梦想队");
		addRegister.setGroup_number("4");
		addRegister.setGroup_leader("王明"); 
		addRegister.setGroup_member("王明、张蓝、刘红、李艳"); 
		addRegister.setMo_phone("11133156767111111");
		addRegister.setEmail("816666@qq.com");
		addRegister.setSchool("广州大学");
		addRegister.setSchool_profession("会展经济系");

		RegisterConverter.toJson(addRegister,addRegisterJsonObject);

		System.out.println(addRegisterJsonObject.toString());
		String encoderJson = URLEncoder.encode(addRegisterJsonObject.toString(), "UTF-8");
		
		System.out.println(encoderJson);
		/*final String addUserJson = "{'username':'jk','phone':'13533727030','email':'81555166@qq.com','birthDate':'"
				+ birthDate + "'}";*/
		
		vertx.createHttpClient().post(port, host,"/api/register")
		.putHeader("content-type", "application/json;charset=utf-8")
        .putHeader("content-length", Integer.toString(encoderJson.length())).handler(response->{
        	response.bodyHandler(body->{
        		System.out.println(body.toString());
        		async.complete();
        	});
        }).write(encoderJson).end();
		
	}
	
	@Test
	public void getByPhone(TestContext context){
		
		final Async async = context.async(); 
		String phone="124324324";
		vertx.createHttpClient().getNow(port,host,"/api/register/"+phone,response->
		{
			response.bodyHandler(bodyHandler->{
				System.out.println(bodyHandler.toString());
				 
			     async.complete();
			});
		});
	}
	
}
