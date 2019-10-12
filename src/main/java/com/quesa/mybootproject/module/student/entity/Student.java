package com.quesa.mybootproject.module.student.entity;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;
import java.util.Date;
import com.quesa.mybootproject.common.util.DateUtil;
import com.quesa.mybootproject.common.persistence.DataEntity;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 
 * 
 **/
@Data
@Accessors(chain = true)
@Table(name = "student")
public class Student extends DataEntity<Student>{

	/****/
	@Column(name = "id_")
	private String id;

	/**姓名**/
	@Column(name = "name_")
	private String name;

	/**性别**/
	@Column(name = "sex_")
	private Integer sex;

	/**创建时间**/
	@Column(name = "ct_")
	private Date ct;



	@Override
	public JSONObject toJSONObjcet() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", null == id ? "" : id);
		json.put("name", null == name ? "" : name);
		json.put("sex", null == sex ? "" : sex);
		json.put("ct", null == ct ? "" : DateUtil.getDateToString(ct));
		return json;
	}
}
