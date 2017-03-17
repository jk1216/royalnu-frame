package com.royalnu.kgkx.exhibition;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
/**
 * 学生报名类
 * @author jk
 */
@DataObject(generateConverter = true)
public class Register {
	private long id;
	private String group_name; // 小组名字
	private String group_number; // 小组人数
	private String group_leader; // 组长
	private String group_member; // 组员名字
	private String mo_phone; // 联系方式
	private String email; // 邮件地址
	private String school; // 所在学校
	private String school_profession; // 学院专业

	public Register() {
		// Empty constructor
	}

	public Register(JsonObject json) {
		 RegisterConverter.fromJson(json, this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		RegisterConverter.toJson(this, json);
		return json;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_number() {
		return group_number;
	}

	public void setGroup_number(String group_number) {
		this.group_number = group_number;
	}

	public String getGroup_leader() {
		return group_leader;
	}

	public void setGroup_leader(String group_leader) {
		this.group_leader = group_leader;
	}

	public String getGroup_member() {
		return group_member;
	}

	public void setGroup_member(String group_member) {
		this.group_member = group_member;
	}

	public String getMo_phone() {
		return mo_phone;
	}

	public void setMo_phone(String mo_phone) {
		this.mo_phone = mo_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchool_profession() {
		return school_profession;
	}

	public void setSchool_profession(String school_profession) {
		this.school_profession = school_profession;
	}

	@Override
	public String toString() {
		return toJson().encodePrettily();
	}
}
