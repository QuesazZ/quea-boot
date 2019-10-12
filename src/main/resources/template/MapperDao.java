package template;

import com.quesa.mybootproject.common.util.FileUtil;

import java.io.IOException;

/**
 *
 */
public class MapperDao extends Constants {
    public MapperDao(Boolean flag) {
        Constants.cover = flag;//是否允许强制覆盖已存在文件
    }

    /**
     * 构建实体数据接口层 dao
     *
     * @throws IOException
     */
    public void createMapperDao() {
        try {
            String outClassUrl = projectOutPath + mapperPackageOutPath.replace(".", "/") + "/";//生成实体类路径
            StringBuffer sb = new StringBuffer();
            sb.append("package " + mapperPackageOutPath + ";");
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("import " + entityPackageOutPath + "." + entityName + ";");
            sb.append("\r\n");
            sb.append("import org.apache.ibatis.annotations.Param;");
            sb.append("\r\n");
            sb.append("import org.apache.ibatis.annotations.Mapper;");
            sb.append("\r\n");
            sb.append("import org.springframework.stereotype.Repository;\r\n");
            sb.append("import com.quesa.mybootproject.common.persistence.BaseDao;");
            sb.append("\r\n");
            createClassComment(sb, tableComment + "-数据库操作接口层");
            sb.append("\r\n");
            sb.append("@Mapper\r\n");
            sb.append("@Repository\r\n");
            sb.append("public interface " + mapperName + " extends BaseDao<" + entityName + "> {");
            sb.append("\r\n");
            sb.append("\r\n");
            // ----------定义Mapper中的方法Begin----------
           /* this.createMethodComment(sb,"查询（根据主键ID查询）");
            sb.append("\r\n");
            sb.append("\t" + this.entityName + "  "+this.getById+"( @Param(\"id\") String id );");
            sb.append("\r\n");
            this.createMethodComment(sb, "删除（根据主键ID删除）");
            sb.append("\r\n");
            sb.append("\t" + "int "+this.deleteById+"( @Param(\"id\") String id );");
            sb.append("\r\n");

                this.createMethodComment(sb,"添加 （匹配有值的字段）");

            sb.append("\r\n");
            sb.append("\t" + "int "+this.insert+"( " + this.entityName + " entity );");
            sb.append("\r\n");
            this.createMethodComment(sb,"修改 （匹配有值的字段）");
            sb.append("\r\n");
            sb.append("\t" + "int "+this.update+"( " + this.entityName + " entity );");
            sb.append("\r\n");*/

            // ----------定义Mapper中的方法End----------
            sb.append("\r\n");
            sb.append("}");
            //判断生成文件是否存在，不存在则创建
            if (!FileUtil.isExists(outClassUrl + mapperName + ".java") || Constants.cover) {
                FileUtil.writeFile((outClassUrl + mapperName + ".java"), sb.toString());//创建一个指定的空文件
            } else {
                System.out.print(mapperName + ".java文件已存在，不允许直接覆盖！\n");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
