package template;

import com.quesa.mybootproject.common.util.FileUtil;
import com.quesa.mybootproject.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ViewJS extends Constants {
    public static void main(String[] args) throws TemplateException {
        Constants.projectOutPath = "E:/Works/JavaWebWork/quesa/src/Main/java/";//项目路径
        //-------------------------------   需要使用者设置-start  设置完成后运行即可---------------------------
        String modulePackageOutPath = "com.quesa.mybootproject.module.compact";//主模块路径
        Constants.tableName = "student";//数据库表名
        Constants.entityName = "student";//实体类名
        Constants.authorName = "quesa";//作者名字
        //-------------------------------   需要使用者设置-end  ---------------------------
        Constants.jsPackageOutPath = modulePackageOutPath + ".js";//构建实体路径-不建议修改
        //获取指定数据表信息
        new MYSQLConn().getMYSQLTable();
        new ViewJS().createJSController();
        new ViewJS().createJSApiController();
    }

    /**
     * 构建实体数据 控制层层 Controller
     *
     * @throws IOException
     */
    public void createJSController() throws TemplateException {
        try {
            String outClassUrl = projectOutPath + jsPackageOutPath.replace(".", "/") + "/";//生成实体类路径

            //1.创建配置实例Cofiguration
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(Constants.projectOutPath.replace("java", "resources")));
            //获取模板（template）
            Template template = cfg.getTemplate("template/ftl/ViewJS.ftl");
            //建立数据模型（Map）
            Map<String, String> root = new HashMap<String, String>();
            root.put("EntityName", StringUtil.toPascalCase(entityName));
            root.put("entityName", StringUtil.toCamelCase(entityName));
            //获取前端实体参数
            StringBuffer sbParameter = new StringBuffer();

            StringBuffer entityData = new StringBuffer();// ${entityData}
            StringBuffer setParams = new StringBuffer(); //${setParams}
            int size = columns.size();
            for (int i = 0; i < size; i++) {
                //entityData
                entityData.append("\r\n");
                /*if(i == size-1){
                    entityData.append("\""+StringUtil.toCamelCase(this.columns.get(i))+"\":\"\"//" + this.comments.get(i));
                }else{
                    entityData.append("\""+StringUtil.toCamelCase(this.columns.get(i))+"\":\"\",//" + this.comments.get(i));
                }*/
                entityData.append(StringUtil.toCamelCase(columns.get(i)) + ":\"\",//" + comments.get(i));
                //定义参数
                //sbParameter.append("\r\n");
                //sbParameter.append(this.processType(this.types.get(i)) + " " + StringUtil.toCamelCase(this.columns.get(i)) + " = null; //"+this.comments.get(i));
                //获取参数
                setParams.append("\r\n");
                setParams.append("viewData." + StringUtil.toCamelCase(columns.get(i)) + " = $(\"#" + StringUtil.toCamelCase(columns.get(i)) + "\").val()" + ";//" + comments.get(i));
            }
            root.put("entityData", entityData.toString().length() == 0 ? "" : entityData.toString().substring(0, entityData.toString().length() - 1));
            root.put("setParams", setParams.toString());
            //判断生成文件是否存在，不存在则创建
            FileUtil.writeFile((outClassUrl + Constants.entityName + ".js"), "");//创建一个指定的空文件
            //数据与模板合并（数据+模板=输出）
            Writer out = new OutputStreamWriter(new FileOutputStream((outClassUrl + Constants.entityName + ".js")), "UTF-8");
            template.process(root, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建实体数据 控制层层 Controller
     *
     * @throws IOException
     */
    public void createJSApiController() throws TemplateException {
        try {
            String outClassUrl = projectOutPath + jsPackageOutPath.replace(".", "/") + "/";//生成实体类路径

            //1.创建配置实例Cofiguration
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(Constants.projectOutPath.replace("java", "resources")));
            //获取模板（template）
            Template template = cfg.getTemplate("template/ftl/ViewJSApi.ftl");
            //建立数据模型（Map）
            Map<String, String> root = new HashMap<String, String>();
            root.put("EntityName", StringUtil.toPascalCase(entityName));
            root.put("entityName", StringUtil.toCamelCase(entityName));
            //获取前端实体参数
            StringBuffer sbParameter = new StringBuffer();

            StringBuffer entityData = new StringBuffer();// ${entityData}
            StringBuffer setParams = new StringBuffer(); //${setParams}
            int size = columns.size();
            for (int i = 0; i < size; i++) {
                //entityData
                entityData.append("\r\n");
                /*if(i == size-1){
                    entityData.append("\""+StringUtil.toCamelCase(this.columns.get(i))+"\":\"\"//" + this.comments.get(i));
                }else{
                    entityData.append("\""+StringUtil.toCamelCase(this.columns.get(i))+"\":\"\",//" + this.comments.get(i));
                }*/
                entityData.append(StringUtil.toCamelCase(columns.get(i)) + ":\"\",//" + comments.get(i));
                //定义参数
                //sbParameter.append("\r\n");
                //sbParameter.append(this.processType(this.types.get(i)) + " " + StringUtil.toCamelCase(this.columns.get(i)) + " = null; //"+this.comments.get(i));
                //获取参数
                setParams.append("\r\n");
                setParams.append("viewData." + StringUtil.toCamelCase(columns.get(i)) + " = $(\"#" + StringUtil.toCamelCase(columns.get(i)) + "\").val()" + ";//" + comments.get(i));
            }
            //root.put("entityData",entityData.toString().length()==0 ? "" : entityData.toString().substring(0,entityData.toString().length()-1));
            //root.put("setParams",setParams.toString());
            //判断生成文件是否存在，不存在则创建
            FileUtil.writeFile((outClassUrl + Constants.entityName + "Api.js"), "");//创建一个指定的空文件
            //数据与模板合并（数据+模板=输出）
            Writer out = new OutputStreamWriter(new FileOutputStream((outClassUrl + Constants.entityName + "Api.js")), "UTF-8");
            template.process(root, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
