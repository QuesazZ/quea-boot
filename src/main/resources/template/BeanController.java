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
public class BeanController extends Constants {
    public BeanController(Boolean flag) {
        Constants.cover = flag;//是否允许强制覆盖已存在文件
    }

    /**
     * 构建实体数据 控制层层 Controller
     *
     * @throws IOException
     */
    public void createBeanController() throws TemplateException {
        try {
            String outClassUrl = projectOutPath + controllerPackageOutPath.replace(".", "/") + "/";//生成实体类路径

            //1.创建配置实例Cofiguration
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(Constants.projectOutPath.replace("java", "resources")));
            //获取模板（template）
            Template template = cfg.getTemplate("template/ftl/BeanController.ftl");
            //建立数据模型（Map）
            Map<String, String> root = new HashMap<String, String>();
            root.put("package", controllerPackageOutPath);
            root.put("servicePage", servicePackageOutPath);
            root.put("ServiceName", serviceName);
            root.put("serviceName", StringUtil.toCamelCase(serviceName));
            root.put("entityPage", entityPackageOutPath);
            root.put("EntityName", entityName);
            root.put("entityName", StringUtil.toCamelCase(entityName));
            root.put("tableComment", tableComment);
            root.put("modulePage", modulePage);
            root.put("moduleName", StringUtil.toLowerCaseWithUnderscores(entityName));
            //获取前端实体参数
            StringBuffer sbParameter = new StringBuffer();
            StringBuffer sbSetParameter = new StringBuffer();
            StringBuffer sbApiParams = new StringBuffer();//api参数
            int size = columns.size();
            for (int i = 0; i < size; i++) {
                //定义参数
                //sbParameter.append("\r\n");
                //sbParameter.append(this.processType(this.types.get(i)) + " " + StringUtil.toCamelCase(this.columns.get(i)) + " = null; //"+this.comments.get(i));
                //获取参数
                sbSetParameter.append("\r\n");
                //如果是double或float，获取的时候需要设置保留几位小数点。
                if ("Double".equalsIgnoreCase(processType(types.get(i))) || "Float".equalsIgnoreCase(processType(types.get(i)))) {
                    sbSetParameter.append(StringUtil.toCamelCase(entityName) + ".set" + StringUtil.toPascalCase(columns.get(i)) + "(RequestUtil.getParameterTo" + processType(types.get(i)) + "(\"" + StringUtil.toCamelCase(columns.get(i)) + "\",2))" + ";//" + comments.get(i));
                } else {
                    sbSetParameter.append(StringUtil.toCamelCase(entityName) + ".set" + StringUtil.toPascalCase(columns.get(i)) + "(RequestUtil.getParameterTo" + processType(types.get(i)) + "(\"" + StringUtil.toCamelCase(columns.get(i)) + "\"))" + ";//" + comments.get(i));
                }
                sbApiParams.append("\\" + "\"" + StringUtil.toCamelCase(columns.get(i)) + "\\" + "\":" + "\\" + "\"" + comments.get(i) + "\\" + "\",");

            }
            // root.put("parameter",sbParameter.toString());
            root.put("setParameter", sbSetParameter.toString());
            root.put("apiParams", sbApiParams.toString().length() == 0 ? "" : sbApiParams.toString().substring(0, sbApiParams.toString().length() - 1));
            //判断生成文件是否存在，不存在则创建
            if (!FileUtil.isExists(outClassUrl + controllerName + ".java") || Constants.cover) {
                FileUtil.writeFile((outClassUrl + Constants.controllerName + ".java"), "");//创建一个指定的空文件
            } else {
                System.out.print(controllerName + ".java文件已存在，不允许直接覆盖！\n");
                return;
            }
            //数据与模板合并（数据+模板=输出）
            Writer out = new OutputStreamWriter(new FileOutputStream((outClassUrl + Constants.controllerName + ".java")), "UTF-8");
            template.process(root, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
