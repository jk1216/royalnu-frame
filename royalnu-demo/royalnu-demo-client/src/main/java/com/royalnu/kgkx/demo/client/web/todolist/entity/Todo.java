package com.royalnu.kgkx.demo.client.web.todolist.entity;

import java.util.concurrent.atomic.AtomicInteger;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Todo {
	private static final AtomicInteger acc = new AtomicInteger(0); // counter

	private int id;
	private String title;
	private Boolean completed=false;
	private Integer order;
	private String url;

	public Todo() {
	}

	public Todo(Todo other) {
		this.id = other.id;
		this.title = other.title;
		this.completed = other.completed;
		this.order = other.order;
		this.url = other.url;
	}

	public Todo(int id, String title, Boolean completed, Integer order, String url) {
		this.id = id;
		this.title = title;
		this.completed = completed;
		this.order = order;
		this.url = url;
	}

	public Todo(JsonObject obj) {
		TodoConverter.fromJson(obj, this); // 还未生成Converter的时候需要先注释掉，生成过后再取消注释
	}

	public Todo(String jsonStr) {
		TodoConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		TodoConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static AtomicInteger getAcc() {
		return acc;
	}
	
	 @Override
	  public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Todo todo = (Todo) o;

	    if (id != todo.id) return false;
	    if (!title.equals(todo.title)) return false;
	    if (completed != null ? !completed.equals(todo.completed) : todo.completed != null) return false;
	    return order != null ? order.equals(todo.order) : todo.order == null;

	  }

	  @Override
	  public int hashCode() {
	    int result = id;
	    result = 31 * result + title.hashCode();
	    result = 31 * result + (completed != null ? completed.hashCode() : 0);
	    result = 31 * result + (order != null ? order.hashCode() : 0);
	    return result;
	  }
	
	  private <T> T getOrElse(T value, T defaultValue) {
		    return value == null ? defaultValue : value;
		  }
	
	  public Todo merge(Todo todo) {
		    return new Todo(id,
		      getOrElse(todo.title, title),
		      getOrElse(todo.completed, completed),
		      getOrElse(todo.order, order),
		      url);
		  }
	  public void setIncId() {
		    this.id = acc.incrementAndGet();
		  }
	  
	  public static int getIncId() {
		    return acc.get();
		  }

		  public static void setIncIdWith(int n) {
		    acc.set(n);
		  }

}
