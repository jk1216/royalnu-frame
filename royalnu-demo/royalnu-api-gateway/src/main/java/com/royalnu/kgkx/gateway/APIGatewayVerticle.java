package com.royalnu.kgkx.gateway;


import com.royalnu.common.RestAPIVerticle;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.UserSessionHandler;

public class APIGatewayVerticle extends RestAPIVerticle {
	
	  private static final int DEFAULT_CHECK_PERIOD = 60000;
	  private static final int DEFAULT_PORT = 8787;

	  private static final Logger logger = LoggerFactory.getLogger(APIGatewayVerticle.class);
	
	  private OAuth2Auth oauth2;
	  
	  @Override
	  public void start(Future<Void> future) throws Exception {
	    super.start();
	    // get HTTP host and port from configuration, or use default value
	    String host = config().getString("api.gateway.http.address", "localhost");
	    int port = config().getInteger("api.gateway.http.port", DEFAULT_PORT);
	    
	    Router router = Router.router(vertx);
	    
	    // body handler
	    router.route().handler(BodyHandler.create());
	    
	    // version handler
	    router.get("/api/v").handler(this::apiVersion);
	    
	    // create OAuth 2 instance for Keycloak
	    oauth2 = OAuth2Auth.createKeycloak(vertx, OAuth2FlowType.AUTH_CODE, config());
	    
	    router.route().handler(UserSessionHandler.create(oauth2));
	    String hostURI = buildHostURI();

	    router.get("/login").handler(this::loginEntryHandler);
	    
	    // enable HTTPS
/*	    HttpServerOptions httpServerOptions = new HttpServerOptions()
	      .setSsl(true)
	      .setKeyStoreOptions(new JksOptions().setPath("server.jks").setPassword("123456"));
*/	    
	    // create http server
	    vertx.createHttpServer()
	      .requestHandler(router::accept)
	      .listen(port, host, ar -> {
	        if (ar.succeeded()) {
	          publishApiGateway(host, port);
	          future.complete();
	          logger.info("API Gateway is running on port " + port);
	          // publish log
	          publishGatewayLog("api_gateway_init_success:" + port);
	        } else {
	          future.fail(ar.cause());
	        }
	      });
	    
	  }
	  
	  private void loginEntryHandler(RoutingContext context) {
		    context.response()
		      .putHeader("Location", generateAuthRedirectURI(buildHostURI()))
		      .setStatusCode(302)
		      .end();
		  }
	  
	  private String generateAuthRedirectURI(String from) {
		    return oauth2.authorizeURL(new JsonObject()
		      .put("redirect_uri", from + "/callback?redirect_uri=" + from)
		      .put("scope", "")
		      .put("state", ""));
		  }
	  
	  
	  private void apiVersion(RoutingContext context) {
		    context.response()
		      .end(new JsonObject().put("version", "v1").encodePrettily());
		  }
	  
	  // log methods
	  private void publishGatewayLog(String info) {
	    JsonObject message = new JsonObject()
	      .put("info", info)
	      .put("time", System.currentTimeMillis());
	    publishLogEvent("gateway", message);
	  }
	  
	  private String buildHostURI() {
		    int port = config().getInteger("api.gateway.http.port", DEFAULT_PORT);
		    final String host = config().getString("api.gateway.http.address.external", "localhost");
		    return String.format("https://%s:%d", host, port);
		  }
	  

}
