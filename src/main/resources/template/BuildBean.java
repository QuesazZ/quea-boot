package template;

import com.quesa.mybootproject.common.util.FileUtil;
import com.quesa.mybootproject.common.util.StringUtil;

import java.io.IOException;

/**
 *
 */
public class BuildBean extends Constants {
    public BuildBean(Boolean flag) {
        Constants.cover = flag;//是否允许强制覆盖已存在文件
    }

    /**
     * 生成实体类文件
     */
    public void createEntity() throws IOException {
        String outClassUrl = projectOutPath + entityPackageOutPath.replace(".", "/") + "/";//生成实体类路径
        StringBuffer sb = new StringBuffer();
        sb.append("package " + entityPackageOutPath.replace("/", ".") + ";");//实体类路径
        sb.append("\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import com.alibaba.fastjson.JSONObject;\r\n");
        sb.append("import com.alibaba.fastjson.JSONException;\r\n");
        sb.append("import java.util.Date;\r\n");
        sb.append("import com.quesa.mybootproject.common.util.DateUtil;\r\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;\r\n");
        sb.append("import com.quesa.mybootproject.common.persistence.DataEntity;\r\n");
        sb.append("import javax.persistence.Column;\r\n");
        sb.append("import javax.persistence.Table;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("import lombok.experimental.Accessors;\r\n");
        createClassComment(sb, tableComment);//生成实体类注释
        sb.append("\r\n@Data\r\n");
        sb.append("@Accessors(chain = true)\r\n");
        sb.append("@Table(name = \"" + tableName + "\")\r\n");
        sb.append("public class " + entityName + " extends DataEntity<" + entityName + ">{");
        sb.append("\r\n");
        sb.append("\r\n");
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            sb.append("\t/**" + comments.get(i) + "**/\r\n");
            sb.append("\t@Column(name = \"" + columns.get(i) + "\")\r\n");
            sb.append("\tprivate " + processType(types.get(i)) + " " + StringUtil.toCamelCase(columns.get(i)) + ";");
            sb.append("\r\n");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        /*for ( int i = 0 ; i < size ; i++ ) {
            tempType = this.processType(this.types.get(i));
            _tempField = StringUtil.toCamelCase(this.columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            sb.append("\r\n");
            sb.append("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            sb.append("\r\n");
            sb.append("\t\tthis." + _tempField + " = " + _tempField + ";");
            sb.append("\r\n");
            sb.append("\t}");
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("\tpublic " + tempType + " get" + tempField + "(){");
            sb.append("\r\n");
            sb.append("\t\treturn this." + _tempField + ";");
            sb.append("\r\n");
            sb.append("\t}");
            sb.append("\r\n");
        }*/
        //生成返回到前端页面数据方法
        sb.append("\r\n");
        sb.append("\t@Override");
        sb.append("\r\n");
        sb.append("\tpublic JSONObject toJSONObjcet() throws JSONException{");
        sb.append("\r\n");
        sb.append("\t\tJSONObject json = new JSONObject();");
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = StringUtil.toCamelCase(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            if ("Date".equalsIgnoreCase(tempType)) {
                //如果是日期需要做一个日期格式转换
                sb.append("\r\t\tjson.put(\"" + _tempField + "\", null == " + _tempField + " ? \"\" : DateUtil.getDateToString(" + _tempField + "));");
            } else {
                sb.append("\r\t\tjson.put(\"" + _tempField + "\", null == " + _tempField + " ? \"\" : " + _tempField + ");");
            }
        }
        sb.append("\r\n");
        sb.append("\t\treturn json;");
        sb.append("\r\n");
        sb.append("\t}\n");
        sb.append("}");
        //判断生成文件是否存在，不存在则创建
        if (!FileUtil.isExists(outClassUrl + entityName + ".java") || Constants.cover) {
            FileUtil.writeFile((outClassUrl + entityName + ".java"), sb.toString());
        } else {
            System.out.print(entityName + ".java文件已存在，不允许直接覆盖！\n");
            return;
        }

    }
}