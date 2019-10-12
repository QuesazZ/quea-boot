package template;

import com.quesa.mybootproject.common.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException, TemplateException {

        Constants.projectOutPath = "F:/QuesaGithub/quesa-boot/src/main/java/";//项目路径
//        Constants.projectOutPath = "c:/qypms/qypms/src/main/java/";//项目路径
        String rootSrc = "com.quesa.mybootproject.module.";//主模块路径D:\IdeaProjects\qypms-boot
        //-------------------------------   需要使用者设置-start  设置完成后运行即可---------------------------
        Constants.modulePage = "score";
        Constants.tableName = "score";//数据库表名
        Constants.entityName = StringUtil.toPascalCase(Constants.tableName);//实体类名
        Constants.authorName = "quesa";//作者名字
        //-------------------------------   需要使用者设置-end  ---------------------------
        String modulePackageOutPath = rootSrc + Constants.modulePage;
        Constants.entityPackageOutPath = modulePackageOutPath + ".entity";//构建实体路径-不建议修改
        Constants.mapperPackageOutPath = modulePackageOutPath + ".dao";//构建数据层路径-不建议修改
        Constants.mapperXmlOutPath = modulePackageOutPath + ".dao.mapping";//构建xml映射路径-不建议修改
        Constants.servicePackageOutPath = modulePackageOutPath + ".service";//构建业务逻辑层路径-不建议修改
        Constants.controllerPackageOutPath = modulePackageOutPath + ".controller";//构建控制层路径-不建议修改

        Constants.mapperName = Constants.entityName + "Mapper";//构建数据层类名-不建议修改
        Constants.xmlName = Constants.entityName + "Mapper";//构建xml文件名-不建议修改
        Constants.serviceName = Constants.entityName + "Service";//构建业务逻辑层类名-不建议修改
        Constants.controllerName = Constants.entityName + "Controller";//构建业务逻辑层类名-不建议修改

        //获取指定数据表信息
        new MYSQLConn().getMYSQLTable();
        //构建实体
        new BuildBean(true).createEntity();
        //构建mapperDao
        new MapperDao(true).createMapperDao();
        //构建mapperXml
        new MapperXml(true).createMapperXml();
        //构建beanService
        new BeanService(true).createBeanService();
        //构建Controller
        new BeanController(true).createBeanController();
    }
}
