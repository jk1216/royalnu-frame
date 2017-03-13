package com.royalnu.kgkx.demo.server.todolist.consumer;

import com.royalnu.kgkx.common.util.KgkxConsumerAddressUtil;
import com.royalnu.kgkx.demo.server.todolist.service.TodoService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class TodoConsumer extends AbstractVerticle {
	private TodoService todoService;

	public void start(Future<Void> startFuture) throws Exception {
		try {
			this.initConsumer();
			startFuture.complete();
		} catch (Exception e) {
			startFuture.fail(e);
			startFuture.complete();
		}
	}

	public void stop(Future<Void> stopFuture) throws Exception {
		try {
			this.stopConsumer();
			stopFuture.complete();
		} catch (Exception e) {
			stopFuture.fail(e);
			stopFuture.complete();
		}
	}

	// 查找用户资料
	private Handler<Message<JsonObject>> createTodoHandler = handler -> {
		JsonObject body = handler.body();

		System.out.println("来自:" + KgkxConsumerAddressUtil.TODO_CREATE + "" + body.toString());

		handler.reply(new JsonObject().put("code", "good ok").toString());
	};

	public void initConsumer() throws Exception {
		// todoService = new JdbcTodoService(vertx, config());

		// logger.debug("[initConsumer]----start-------");
		// 新增用户信息
		vertx.eventBus().<JsonObject> consumer(KgkxConsumerAddressUtil.TODO_CREATE).handler(createTodoHandler);

		// logger.debug("[initConsumer]----end-------");
	}

	public void stopConsumer() throws Exception {
		// logger.debug("---------stopConsumer--------");
	}

	public static void main(String[] args) {
		VertxOptions vOpts = new VertxOptions();
		DeploymentOptions options = new DeploymentOptions().setInstances(1).setWorker(true)
				.setConfig(new JsonObject().put("local", true));
		vOpts.setClustered(true);
		Vertx.clusteredVertx(vOpts, cluster -> {
			if (cluster.succeeded()) {
				final Vertx result = cluster.result();
				result.deployVerticle(TodoConsumer.class.getName(), options, handle -> {

				});
			}
		});
	}

}