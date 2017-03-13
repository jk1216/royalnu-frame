package com.royalnu.kgkx.demo.client.web.todolist.verticles;

import java.util.HashSet;
import java.util.Set;

import com.royalnu.kgkx.common.util.KgkxConsumerAddressUtil;
import com.royalnu.kgkx.demo.client.web.todolist.Constants;
import com.royalnu.kgkx.demo.client.web.todolist.service.TodoService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class TodoVerticle extends AbstractVerticle {

	private static final String HOST = "0.0.0.0";
	private static final int PORT = 8082;
	private TodoService service;

	private void initData() {
		/*
		 * service = new JdbcTodoService(vertx, config());
		 * 
		 * service.initData().setHandler(res -> { if (res.failed()) { //
		 * LOGGER.error("Persistence service is not running!");
		 * res.cause().printStackTrace(); } });
		 */
	}

	@Override
	public void start(Future<Void> future) throws Exception {
		initData();
		Router router = Router.router(vertx);
		// CORS support
		Set<String> allowHeaders = new HashSet<>();
		allowHeaders.add("x-requested-with");
		allowHeaders.add("Access-Control-Allow-Origin");
		allowHeaders.add("origin");
		allowHeaders.add("Content-Type");
		allowHeaders.add("accept");
		Set<HttpMethod> allowMethods = new HashSet<>();
		allowMethods.add(HttpMethod.GET);
		allowMethods.add(HttpMethod.POST);
		allowMethods.add(HttpMethod.DELETE);
		allowMethods.add(HttpMethod.PATCH);

		router.route().handler(BodyHandler.create());
		router.route().handler(CorsHandler.create("*").allowedHeaders(allowHeaders).allowedMethods(allowMethods));
		// routes
		router.post(Constants.API_CREATE).handler(this::handleCreateTodo);

		/*
		 * router.get(Constants.API_LIST_ALL).handler(this::handleGetAll);
		 * router.patch(Constants.API_UPDATE).handler(this::handleUpdateTodo);
		 * router.delete(Constants.API_DELETE).handler(this::handleDeleteOne);
		 * router.delete(Constants.API_DELETE_ALL).handler(this::handleDeleteAll
		 * );
		 */

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", PORT),
				config().getString("http.address", HOST), result -> {
					if (result.succeeded())
						future.complete();
					else
						future.fail(result.cause());
				});
	}

	private void handleCreateTodo(RoutingContext context) {
		JsonObject newTodo = context.getBodyAsJson();

		vertx.eventBus().<String> send(KgkxConsumerAddressUtil.TODO_CREATE, newTodo, reply -> {
			if (reply.succeeded()) {
				String result = reply.result().body();
				System.out.println("回复:"+result);
				context.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
				context.response().end(result);
			} else {
				
				
			}
		});


	}

	private void defaultResponse(RoutingContext ctx, AsyncResult<Message<String>> responseHandler) {
		if (responseHandler.failed()) {
			ctx.fail(500);
		} else {
			final Message<String> result = responseHandler.result();
			ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			ctx.response().end(result.body());
		}
	}

	private void sendError(int statusCode, HttpServerResponse response) {
		response.setStatusCode(statusCode).end();
	}

	private void notFound(RoutingContext context) {
		context.response().setStatusCode(404).end();
	}

	private void badRequest(RoutingContext context) {
		context.response().setStatusCode(400).end();
	}

	private void serviceUnavailable(RoutingContext context) {
		context.response().setStatusCode(503).end();
	}

	public static void main(String[] args) {
		VertxOptions vOpts = new VertxOptions();
		vOpts.setEventLoopPoolSize(100);
		DeploymentOptions options = new DeploymentOptions().setInstances(1)
				.setConfig(new JsonObject().put("local", true));
		vOpts.setClustered(true);
		Vertx.clusteredVertx(vOpts, cluster -> {
			if (cluster.succeeded()) {
				final Vertx result = cluster.result();
				result.deployVerticle(TodoVerticle.class.getName(), options, handle -> {

				});
			}
		});
	}
}
