package com.royalnu.kgkx.account;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;

/**
 * A service interface managing user accounts.
 * <p>
 * This service is an event bus service (aka. service proxy).
 * </p>
 *
 * @author Eric Zhao
 */
@VertxGen
@ProxyGen
public interface AccountService {

  /**
   * The name of the event bus service.
   */
  String SERVICE_NAME = "user-account-eb-service";

  /**
   * The address on which the service is published.
   */
  String SERVICE_ADDRESS = "service.user.account";

  /**
   * Initialize the persistence.
   * 初始化用户表
   *
   * @param resultHandler the result handler will be called as soon as the initialization has been accomplished. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService initializePersistence(Handler<AsyncResult<Void>> resultHandler);

  /**
   * Add a account to the persistence.
   * 添加一个用户
   * @param account       a account entity that we want to add
   * @param resultHandler the result handler will be called as soon as the account has been added. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService addAccount(Account account, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Retrieve the user account with certain `id`.
   * 根据ID，获取一个用户
   * @param id            user account id
   * @param resultHandler the result handler will be called as soon as the user has been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService retrieveAccount(String id, Handler<AsyncResult<Account>> resultHandler);

  /**
   * Retrieve the user account with certain `username`.
   * 根据用户名称，获取一个用户
   * @param username      username
   * @param resultHandler the result handler will be called as soon as the user has been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService retrieveByUsername(String username, Handler<AsyncResult<Account>> resultHandler);

  /**
   * Retrieve all user accounts.
   * 获取所有用户
   * @param resultHandler the result handler will be called as soon as the users have been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService retrieveAllAccounts(Handler<AsyncResult<List<Account>>> resultHandler);

  /**
   * Update user account info.
   * 更新一个用户
   * @param account       a account entity that we want to update
   * @param resultHandler the result handler will be called as soon as the account has been added. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService updateAccount(Account account, Handler<AsyncResult<Account>> resultHandler);

  /**
   * Delete a user account from the persistence
   * 根据ID，删除用户
   * @param id            user account id
   * @param resultHandler the result handler will be called as soon as the user has been removed. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService deleteAccount(String id, Handler<AsyncResult<Void>> resultHandler);

  /**
   * Delete all user accounts from the persistence
   * 清空所有用户
   * @param resultHandler the result handler will be called as soon as the users have been removed. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  AccountService deleteAllAccounts(Handler<AsyncResult<Void>> resultHandler);

}
