package com.royalnu.kgkx.exhibition;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;
import static com.royalnu.kgkx.exhibition.RegisterService.SERVICE_ADDRESS;
import static com.royalnu.kgkx.exhibition.RegisterService.SERVICE_NAME;
import com.royalnu.common.BaseMicroserviceVerticle;
import com.royalnu.kgkx.exhibition.api.RestExhibitionRegisterAPIVerticle;
import com.royalnu.kgkx.exhibition.imp.JdbcRegisterServiceImpl;
/**
 * A verticle publishing the user service.
 *
 * @author Eric Zhao
 */
public class ExhibitionRegisterVerticle extends BaseMicroserviceVerticle {

  private RegisterService registerService;

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    // create the service instance
    registerService = new JdbcRegisterServiceImpl(vertx, config());
    registerService.initializePersistence(resultHandler->{
	    if(resultHandler.succeeded())
	    {
	    		System.out.println("初始化用户表成功");
	    }
    	
    });
    // register the service proxy on event bus
    ProxyHelper.registerService(RegisterService.class, vertx, registerService, SERVICE_ADDRESS);
    // publish the service and REST endpoint in the discovery infrastructure
    publishEventBusService(SERVICE_NAME, SERVICE_ADDRESS, RegisterService.class)
      .compose(servicePublished -> deployRestVerticle())
      .setHandler(future.completer());
  }

  private Future<Void> deployRestVerticle() {
    Future<String> future = Future.future();
    vertx.deployVerticle(new RestExhibitionRegisterAPIVerticle(registerService),
      new DeploymentOptions().setConfig(config()),
      future.completer());
    return future.map(r -> null);
  }
}
