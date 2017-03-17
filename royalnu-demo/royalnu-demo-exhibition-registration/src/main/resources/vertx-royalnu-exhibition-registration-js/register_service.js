/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module vertx-royalnu-exhibition-registration-js/register_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JRegisterService = com.royalnu.kgkx.exhibition.RegisterService;
var Register = com.royalnu.kgkx.exhibition.Register;

/**
 A service interface managing user accounts.
 <p>
 This service is an event bus service (aka. service proxy).
 </p>

 @class
*/
var RegisterService = function(j_val) {

  var j_registerService = j_val;
  var that = this;

  /**
   Initialize the persistence.
   初始化注册表

   @public
   @param resultHandler {function} the result handler will be called as soon as the initialization has been accomplished. The async result indicates whether the operation was successful or not. 
   @return {RegisterService}
   */
  this.initializePersistence = function(resultHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_registerService["initializePersistence(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Add a account to the persistence.
   添加一个用户

   @public
   @param register {Object} 
   @param resultHandler {function} the result handler will be called as soon as the account has been added. The async result indicates whether the operation was successful or not. 
   @return {RegisterService}
   */
  this.addRegister = function(register, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_registerService["addRegister(com.royalnu.kgkx.exhibition.Register,io.vertx.core.Handler)"](register != null ? new Register(new JsonObject(JSON.stringify(register))) : null, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Retrieve the user account with certain `id`.
   根据手机号码获取报名数据

   @public
   @param phone {string} 
   @param resultHandler {function} the result handler will be called as soon as the user has been retrieved. The async result indicates whether the operation was successful or not. 
   @return {RegisterService}
   */
  this.retrieveByPhone = function(phone, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_registerService["retrieveByPhone(java.lang.String,io.vertx.core.Handler)"](phone, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_registerService;
};

// We export the Constructor function
module.exports = RegisterService;