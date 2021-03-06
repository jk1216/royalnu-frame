package com.royalnu.kgkx.account;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ProxyHelper;

import static com.royalnu.kgkx.account.AccountService.SERVICE_ADDRESS;
import static com.royalnu.kgkx.account.AccountService.SERVICE_NAME;

import com.royalnu.common.BaseMicroserviceVerticle;
import com.royalnu.kgkx.account.api.RestUserAccountAPIVerticle;
import com.royalnu.kgkx.account.imp.JdbcAccountServiceImpl;


/**
 * A verticle publishing the user service.
 *
 * @author Eric Zhao
 */
public class UserAccountVerticle extends BaseMicroserviceVerticle {

  private AccountService accountService;

  @Override
  public void start(Future<Void> future) throws Exception {
    super.start();

    // create the service instance
    accountService = new JdbcAccountServiceImpl(vertx, config());
	accountService.initializePersistence(resultHandler->{
	    if(resultHandler.succeeded())
	    {
	    		System.out.println("初始化用户表成功");
	    }
    	
    });
    // register the service proxy on event bus
    ProxyHelper.registerService(AccountService.class, vertx, accountService, SERVICE_ADDRESS);
    // publish the service and REST endpoint in the discovery infrastructure
    publishEventBusService(SERVICE_NAME, SERVICE_ADDRESS, AccountService.class)
      .compose(servicePublished -> deployRestVerticle())
      .setHandler(future.completer());
  }

  private Future<Void> deployRestVerticle() {
    Future<String> future = Future.future();
    vertx.deployVerticle(new RestUserAccountAPIVerticle(accountService),
      new DeploymentOptions().setConfig(config()),
      future.completer());
    return future.map(r -> null);
  }
}
