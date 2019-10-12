package template;


import com.quesa.mybootproject.common.util.FileUtil;

import java.io.IOException;

/**
 *
 */
public class BeanService extends Constants {
    public BeanService(Boolean flag) {
        Constants.cover = flag;//是否允许强制覆盖已存在文件
    }

    /**
     * 构建实体业务逻辑层 service
     *
     * @throws IOException
     */
    public void createBeanService() {
        try {
            String outClassUrl = projectOutPath + servicePackageOutPath.replace(".", "/") + "/";//生成实体类路径
            StringBuffer sb = new StringBuffer();
            sb.append("package " + servicePackageOutPath + ";");
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("import " + entityPackageOutPath + "." + entityName + ";");
            sb.append("\r\n");
            sb.append("import " + mapperPackageOutPath + "." + mapperName + ";");
            sb.append("\r\n");
            sb.append("import org.springframework.stereotype.Service;");
            sb.append("\r\n");
            sb.append("import com.quesa.mybootproject.common.service.BaseService;");
            createClassComment(sb, tableComment + "-业务逻辑处理层");
            sb.append("\r\n");
            sb.append("@Service");
            sb.append("\r\n");
            sb.append("public class " + serviceName + " extends BaseService<" + mapperName + "," + entityName + "> {");
            sb.append("\r\n");
            sb.append("\r\n");
            // ----------定义Service中的方法Begin----------

            // ----------定义Service中的方法End----------
            sb.append("\r\n");
            sb.append("}");
            //判断生成文件是否存在，不存在则创建
            if (!FileUtil.isExists(outClassUrl + serviceName + ".java") || Constants.cover) {
                FileUtil.writeFile((outClassUrl + serviceName + ".java"), sb.toString());//创建一个指定的空文件
            } else {
                System.out.print(serviceName + ".java文件已存在，不允许直接覆盖！\n");
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
