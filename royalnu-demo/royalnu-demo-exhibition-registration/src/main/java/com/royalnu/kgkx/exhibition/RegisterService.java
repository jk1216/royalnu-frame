package com.royalnu.kgkx.exhibition;


import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
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
public interface RegisterService {

  /**
   * The name of the event bus service.
   */
  String SERVICE_NAME = "exhibition-register-eb-service";

  /**
   * The address on which the service is published.
   */
  String SERVICE_ADDRESS = "service.exhibition.register";

  /**
   * Initialize the persistence.
   * 初始化注册表
   *
   * @param resultHandler the result handler will be called as soon as the initialization has been accomplished. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  RegisterService initializePersistence(Handler<AsyncResult<Void>> resultHandler);

  /**
   * Add a account to the persistence.
   * 添加一个用户
   * @param account       a account entity that we want to add
   * @param resultHandler the result handler will be called as soon as the account has been added. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  RegisterService addRegister(Register register, Handler<AsyncResult<Void>> resultHandler);
  
  /**
   * Retrieve the user account with certain `id`.
   * 根据手机号码获取报名数据
   * @param id            user account id
   * @param resultHandler the result handler will be called as soon as the user has been retrieved. The async result indicates
   *                      whether the operation was successful or not.
   */
  @Fluent
  RegisterService retrieveByPhone(String phone, Handler<AsyncResult<Register>> resultHandler);
  
}
