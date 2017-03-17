package com.royalnu.kgkx.account;

import java.io.IOException;
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
public class UserAccountVerticleTest {

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
	 */
	@Test
	public void add(TestContext context) throws ParseException {
		final Async async = context.async();

		long birthDate = 0;
		Date date = DateUtils.parseDateStrictly("1979-12-16", new String[] { "yyyy-MM-dd" });
		birthDate = date.getTime();

		Account addUser=new Account();
		JsonObject addUserJsonObject=new JsonObject();
		addUser.setId("2");
		addUser.setUsername("jk"); 
		addUser.setPhone("13318090957");
		addUser.setEmail("81555166@qq.com");
		addUser.setBirthDate(birthDate);
		AccountConverter.toJson(addUser,addUserJsonObject);

		System.out.println(addUserJsonObject.toString());
		/*final String addUserJson = "{'username':'jk','phone':'13533727030','email':'81555166@qq.com','birthDate':'"
				+ birthDate + "'}";*/
		
		vertx.createHttpClient().post(port, host,"/user")
		.putHeader("content-type", "application/json")
        .putHeader("content-length", Integer.toString(addUserJsonObject.toString().length())).handler(response->{
        	response.bodyHandler(body->{
        		System.out.println(body.toString());
        		async.complete();
        	});
        }).write(addUserJsonObject.toString()).end();
		
	}

}
