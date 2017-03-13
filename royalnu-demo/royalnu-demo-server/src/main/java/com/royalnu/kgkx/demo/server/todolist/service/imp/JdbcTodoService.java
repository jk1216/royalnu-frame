package com.royalnu.kgkx.demo.server.todolist.service.imp;

import java.util.List;
import java.util.Optional;

import com.royalnu.kgkx.demo.server.todolist.entity.Todo;
import com.royalnu.kgkx.demo.server.todolist.service.TodoService;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

public class JdbcTodoService implements TodoService {

	private final Vertx vertx;
	private final JsonObject config;
	private final JDBCClient client;

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS `todo` (\n"
			+ "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" + "  `title` varchar(255) DEFAULT NULL,\n"
			+ "  `completed` tinyint(1) DEFAULT NULL,\n" + "  `order` int(11) DEFAULT NULL,\n"
			+ "  `url` varchar(255) DEFAULT NULL,\n" + "  PRIMARY KEY (`id`) )";

	private static final String SQL_INSERT = "INSERT INTO `todo` "
			+ "(`id`, `title`, `completed`, `order`, `url`) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_QUERY = "SELECT * FROM todo WHERE id = ?";
	private static final String SQL_QUERY_ALL = "SELECT * FROM todo";
	private static final String SQL_UPDATE = "UPDATE `todo`\n" + "SET\n" + "`id` = ?,\n" + "`title` = ?,\n"
			+ "`completed` = ?,\n" + "`order` = ?,\n" + "`url` = ?\n" + "WHERE `id` = ?;";
	private static final String SQL_DELETE = "DELETE FROM `todo` WHERE `id` = ?";
	private static final String SQL_DELETE_ALL = "DELETE FROM `todo`";

	public JdbcTodoService(JsonObject config) {
		this(Vertx.vertx(), config);
	}

	public JdbcTodoService(Vertx vertx, JsonObject config) {
		this.vertx = vertx;
		this.config = config;
		this.client = JDBCClient.createShared(vertx, config);
	}

	private Handler<AsyncResult<SQLConnection>> connHandler(Future future, Handler<SQLConnection> handler) {
		return conn -> {
			if (conn.succeeded()) {
				final SQLConnection connection = conn.result();
				handler.handle(connection);
			} else {
				future.fail(conn.cause());
			}
		};
	}

	@Override
	public Future<Boolean> initData() {
		Future<Boolean> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.execute(SQL_CREATE, create -> {
				if (create.succeeded()) {
					result.complete(true);
				} else {
					result.fail(create.cause());
				}

				connection.close();
			});
		}));
		return result;
	}

	@Override
	public Future<Boolean> insert(Todo todo) {
		Future<Boolean> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.updateWithParams(SQL_INSERT, new JsonArray().add(todo.getId()).add(todo.getTitle())
					.add(todo.getCompleted()).add(todo.getOrder()).add(todo.getUrl()), r -> {
						if (r.succeeded()) {
							result.complete(true);
						} else {
							result.fail(r.cause());
						}
					});
		}));
		
		return result;
	}

	@Override
	public Future<List<Todo>> getAll() {
		Future<Boolean> result = Future.future();
		
		return null;
	}

	@Override
	public Future<Optional<Todo>> getCertain(String todoID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Todo> update(String todoId, Todo newTodo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Boolean> delete(String todoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Boolean> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
