package com.royalnu.kgkx.exhibition.imp;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import org.apache.commons.lang.math.NumberUtils;

import com.royalnu.common.service.JdbcRepositoryWrapper;
import com.royalnu.kgkx.exhibition.Register;
import com.royalnu.kgkx.exhibition.RegisterService;

/**
 * JDBC implementation of {@link AccountService}.
 *
 * @author Eric Zhao
 */
public class JdbcRegisterServiceImpl extends JdbcRepositoryWrapper implements RegisterService {

  public JdbcRegisterServiceImpl(Vertx vertx, JsonObject config) {
    super(vertx, config);
  }

  @Override
  public RegisterService initializePersistence(Handler<AsyncResult<Void>> resultHandler) {
    client.getConnection(connHandler(resultHandler, connection -> {
      connection.execute(CREATE_STATEMENT, r -> {
        resultHandler.handle(r);
        connection.close();
      });
    }));
    return this;
  }

  @Override
  public RegisterService addRegister(Register register, Handler<AsyncResult<Void>> resultHandler) {
    JsonArray params = new JsonArray().add(register.getGroup_name())
      .add(NumberUtils.toInt(register.getGroup_number(), 0) )
      .add(register.getGroup_leader())
      .add(register.getGroup_member())
      .add(register.getMo_phone())
      .add(register.getEmail())
      .add(register.getSchool())
      .add(register.getSchool_profession());

    this.executeNoResult(params, INSERT_STATEMENT, resultHandler);
    return this;
  }
  
  
  @Override
  public RegisterService retrieveByPhone(String phone, Handler<AsyncResult<Register>> resultHandler) {
    this.retrieveOne(phone, FETCH_BY_PHONE_STATEMENT)
      .map(option -> option.map(Register::new).orElse(null))
      .setHandler(resultHandler);
    return this;
  }

  // SQL statement

  private static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS `exhibition_register` (\n" +
    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
    "  `group_name` varchar(30),\n" +
    "  `group_number` int(11) DEFAULT 0,\n" +
    "  `group_leader` varchar(20) NOT NULL,\n" +
    "  `group_member` varchar(100) NOT NULL,\n" +
    "  `mo_phone` varchar(20) NOT NULL,\n" +
    "  `email` varchar(100) NOT NULL,\n" +
    "  `school` varchar(100) NOT NULL,\n" +
    "  `school_profession` varchar(100) NOT NULL,\n" +
    "  `create_time` datetime NOT NULL,\n" +
    "  PRIMARY KEY (`id`),\n" +
    "  UNIQUE KEY `mophone_UNIQUE` (`mo_phone`) )";
  
  private static final String INSERT_STATEMENT = "INSERT INTO exhibition_register(group_name,group_number,group_leader,group_member,mo_phone,email,school,school_profession,create_time) VALUES (?, ?, ?, ?, ?,?,?,?,now())";
  private static final String FETCH_BY_PHONE_STATEMENT = "SELECT * FROM exhibition_register WHERE mo_phone = ?";

}
