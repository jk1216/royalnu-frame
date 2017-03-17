package com.royalnu.kgkx.exhibition.api;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.lang.StringUtils;
import com.royalnu.common.RestAPIVerticle;
import com.royalnu.kgkx.exhibition.Register;
import com.royalnu.kgkx.exhibition.RegisterService;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class RestExhibitionRegisterAPIVerticle extends RestAPIVerticle {

	private static final Logger logger = LoggerFactory.getLogger(RestExhibitionRegisterAPIVerticle.class);

	private static final String SERVICE_NAME = "exhibition-register-rest-api";

	private final RegisterService registerService;

	private static final String API_ADD = "/api/register";
	private static final String API_RETRIEVEByPhone = "/api/register/:phone";
	
	public RestExhibitionRegisterAPIVerticle(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@Override
	public void start(Future<Void> future) throws Exception {
		// super.start();
		final Router router = Router.router(vertx);
		
		//静态资料
		router.route("/assets/*").handler(StaticHandler.create("assets"));
		enableCorsSupport(router);
		// api route handler
		
		router.route("/api/register*").handler(BodyHandler.create());
		router.post(API_ADD).handler(this::apiAddRegister);
		router.get(API_RETRIEVEByPhone).handler(this::apiRetrieveRegisterByPhone);

		String host = config().getString("user.account.http.address", "0.0.0.0");
		int port = config().getInteger("user.account.http.port", 8081);

		// create HTTP server and publish REST service
		createHttpServer(router, host, port).compose(serverCreated -> publishHttpEndpoint(SERVICE_NAME, host, port))
				.setHandler(future.completer());
	}
	
	
	private void apiRetrieveRegisterByPhone(RoutingContext context) {
		String id = context.request().getParam("phone");
		registerService.retrieveByPhone(id, resultHandlerNonEmpty(context));		
	}

	private void apiAddRegister(RoutingContext context) {
		String json = context.getBodyAsString("utf-8");
		try {
			json = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonObject jsonObject = new JsonObject(json);
		Register register = new Register(jsonObject);
		
		String phone=register.getMo_phone();
		if(!StringUtils.isNotBlank(phone))
		{
			this.serviceUnavailable(context,"phone is not null");
			return;
		}
		
		registerService.addRegister(register, resultVoidHandler(context,new JsonObject().put("msg","ok"), 201));		
	}
	
}
