package com.palm.vertx.util.url;

public class EventBusUriUtil {
   public static final String ADD_USER = "P2P.oauth.server.userService.addUser";
   public static final String UPDATE_USER_PASSWORD = "P2P.oauth.server.userService.updateUserPassword";
   public static final String UPDATE_USER_PHONE_NUM = "P2P.oauth.server.userService.updatePhoneNum";
   public static final String DELETE_USER = "P2P.oauth.server.userService.deleteUser";
   public static final String FIND_ONE_USER = "P2P.oauth.server.userService.findOneUserByQuery";
   public static final String FIND_USER = "P2P.oauth.server.userService.findUserByQuery";
   public static final String FIND_USER_BY_IDS = "P2P.oauth.server.userService.findUserByQuery.byIds";
   public static final String LOCAL_LOGIN = "P2P.oauth.server.loginService.localLogin";
   public static final String MESSAGE_LOGIN = "P2P.oauth.server.loginService.messageTokenLogin";
   public static final String LOGOUT = "P2P.oauth.server.loginService.logout";
   public static final String OAUTH_LOGIN = "P2P.oauth.server.loginService.oauthTokenLogin";
   public static final String AUTHORIZE_CODE = "P2P.oauth.server.loginService.oauthAuthorize";
   public static final String IS_REMEMBER = "P2P.com.palm.vertx.auth.server.helper.isRemembered";
   public static final String IS_AUTHENTICATED = "P2P.com.palm.vertx.auth.server.helper.isAuthenticated";
   public static final String LOGIN_PAGE = "P2P.com.palm.vertx.auth.client.consumer.UserWebConsumer.loginPageHandler";
   public static final String SESSION_TOUCH = "P2P.oauth.server.loginService.sessionTouch";
   public static final String SMS_CONSUMER_ADRRESS = "P2P.com.palm.vertx.sms.consumer.sendSmsConsumer";
   public static final String COUNT_USER = "P2P.com.palm.vertx.auth.server.service.count";
   public static final String UPDATE_USER = "P2P.com.palm.vertx.auth.server.service.updateUser";
   public static final String UPDATE_FORGET_PASSWORD = "P2P.oauth.server.userService.updateForgetUserPassword";
   public static final String SET_PASSWORD = "P2P.oauth.server.userService.setPassword";
   public static final String FIND_NO_CONTAIN_PASSWD_ONE_USER = "P2P.oauth.server.userService.findOneOptions";
   public static final String UPDATE_OAUTH_USER = "P2P.com.palm.vertx.auth.server.consumer.updateOauthUserIdHandler";
   public static final String FIND_OAUTH_CLIENT_BY_USER_ID = "P2P.com.palm.vertx.auth.server.consumer.findOauthUserIdHandler";
   public static final String UPDATE_EMAIL = "P2P.com.palm.vertx.auth.server.service.updateEmail";
   public static final String GET_OAUTH_INFO = "P2P.com.palm.vertx.auth.server.consumer.getOauthInfoHandler";
   public static final String GET_OAUTH_USER_FROM_REDIS = "P2P.com.palm.vertx.auth.server.consumer.getOauthInfoFromRedisHandler";
   public static final String GET_RAMDOM_CIPHERTEXT = "P2P.com.palm.vertx.auth.server.consumer.getRamdomCiphertextHandler";
   public static final String DECRYPT_BY_PRIVATEKEY = "P2P.com.palm.vertx.auth.server.consumer.decryptByPrivateKeyHandler";
   public static final String CHECK_CERTIFICATE = "P2P.com.palm.vertx.auth.server.consumer.checkCertificateHandler";
}
