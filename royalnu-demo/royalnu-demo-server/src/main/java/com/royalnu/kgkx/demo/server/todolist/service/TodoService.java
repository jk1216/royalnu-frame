package com.royalnu.kgkx.demo.server.todolist.service;

import java.util.List;
import java.util.Optional;

import com.royalnu.kgkx.demo.server.todolist.entity.Todo;

import io.vertx.core.Future;

public interface TodoService {
	Future<Boolean> initData(); // 初始化数据（或数据库）

	Future<Boolean> insert(Todo todo);

	Future<List<Todo>> getAll();

	Future<Optional<Todo>> getCertain(String todoID);

	Future<Todo> update(String todoId, Todo newTodo);

	Future<Boolean> delete(String todoId);

	Future<Boolean> deleteAll();
}
