
package com.quesa.mybootproject.common.util;

import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 文件处理工具类
 *
 * @author
 */
public class FileUtil {
    private static final Log logger = LogFactory.getLog("FileUtil");
    public static final int KEY_FILE_IMAGE = 0;// 是图片
    public static final int KEY_FILE_RECORDS = 1;// 是录音
    public static final int KEY_FILE_VIDEO = 2;// 是视频
    public static final int KEY_FILE_DOC = 3;// 是文档
    public static final int KEY_FILE_ERROR = -1; // 不支持的文件格式

    /**
     * 将img保存到目标路径下面
     *
     * @param img
     * @param destPath
     * @throws Exception
     */
    public static void save(File img, String destPath) throws Exception {
        FileInputStream fis = new FileInputStream(img);
        FileOutputStream fos = new FileOutputStream(new File(destPath));
        byte[] data = new byte[1024];
        int length = 0;
        while ((length = fis.read(data)) != -1) {
            fos.write(data, 0, length);
        }
        fos.flush();
        fis.close();
        fos.close();
    }

    /**
     * 得到文件的后缀
     *
     * @param fileName
     * @return
     */
    public static String suffix(String fileName) {
        if (!StringUtil.isEmpty(fileName)) {
            if (fileName.contains(".")) {
                return fileName.substring(fileName.lastIndexOf("."));
            }
            return null;
        }
        return null;
    }

    /**
     * 根据后缀判断当前文件类型
     *
     * @param suffix
     * @return 0:图片、1:录音、2:视频、3:文档、-1:其它文件
     */
    public static int whichFile(String suffix) {
        // 该后缀是带"."的
        suffix = suffix.substring(1);
        if ("jpg".equals(suffix) || "png".equals(suffix) || "bmp".equals(suffix) || "gif".equals(suffix)) {
            return KEY_FILE_IMAGE;
        } else if ("3gp".equals(suffix) || "mp3".equals(suffix) || "amr".equals(suffix)) {
            return KEY_FILE_RECORDS;
        } else if ("mp4".equals(suffix) || "avi".equals(suffix) || "rmvb".equals(suffix)) {
            return KEY_FILE_VIDEO;
        } else if ("txt".equals(suffix) || "doc".equals(suffix) || "docx".equals(suffix) || "xls".equals(suffix) || "xlsx".equals(suffix) || "ppt".equals(suffix) || "pdf".equals(suffix)) {
            return KEY_FILE_DOC;
        }
        return KEY_FILE_ERROR;
    }

    /**
     * 去掉最后一个","
     *
     * @param str
     * @return
     */
    public static String removeLastDot(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        if (str.contains(",")) {
            return str.substring(0, str.length() - 1);
        }
        return null;
    }

    /**
     * 生成文件名
     *
     * @param oldFileName 原文件名
     * @return 生成的文件名称(含后缀)
     */
    public static String generateFileName(String oldFileName) {
        StringBuilder sb = new StringBuilder(UUID.randomUUID().toString().replaceAll("-", ""));
        if (oldFileName.contains(".")) {// 如果原文件名包含后缀，则取得后缀
            sb.append(oldFileName.substring(oldFileName.lastIndexOf(".")));
        }
        return sb.toString();
    }

    public static String generateName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 拷贝资源到目标地址
     *
     * @param srcFilePath 源文件路径
     * @param tarFilePath 目标文件路径
     */
    public static void copySrc2Tar(String srcFilePath, String tarFilePath) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            File desFile = new File(tarFilePath);
            if (!desFile.getParentFile().exists()) {
                desFile.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(tarFilePath);
            fis = new FileInputStream(srcFilePath);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            System.out.println("异常url：" + srcFilePath);
            logger.error("上传保存文件异常" + StringUtil.getStackMsg(e));
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭文件输出流异常" + e.getStackTrace());
                }
                fos = null;
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("关闭文件输入流异常" + e.getStackTrace());
                }
                fis = null;
            }
        }
    }

    /**
     * 根据含后缀的文件名得到不含后缀的文件名
     *
     * @param fileName 原来的文件名 例：xxxx.doc
     * @return 不含后缀的文件名 例：xxxx
     */
    public static String getFileName(String fileName) {
        String newFileName = null;
        if (fileName.contains(".")) {
            newFileName = fileName.substring(0, fileName.indexOf("."));
        }
        return newFileName;
    }

    /**
     * 将输入流is写入到输出流os当中
     *
     * @param is
     * @param os
     */
    public static void handleStream(InputStream is, OutputStream os) {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);

        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
        } catch (Exception e) {
            logger.error("FileUtil.handleStream.exc1:" + e.getMessage());
        } finally {
            try {
                bos.close();
                bis.close();
            } catch (Exception e) {
                logger.error("FileUtil.handleStream.exc2:" + e.getMessage());
            }
        }

    }

    /**
     * 保存文件
     *
     * @param file
     * @param data
     * @throws Exception
     */
    public static void save(File file, byte[] data) throws Exception {

        FileOutputStream outStream = new FileOutputStream(file);
        System.out.println(file.getAbsolutePath());
        outStream.write(data);
        System.out.println(file.getAbsolutePath() + "success!");
        outStream.close();
    }

    public static void save(byte[] bfile, String filePath, String fileName) throws Exception {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static String readLine(InputStream is) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static String readLine(PushbackInputStream in) throws IOException {
        char[] buf = new char[128];
        int room = buf.length;
        int offset = 0;
        int c;
        loop:
        while (true) {
            switch (c = in.read()) {
                case -1:
                case '\n':
                    break loop;
                case '\r':
                    int c2 = in.read();
                    if ((c2 != '\n') && (c2 != -1)) {
                        in.unread(c2);
                    }
                    break loop;
                default:
                    if (--room < 0) {
                        char[] lineBuffer = buf;
                        buf = new char[offset + 128];
                        room = buf.length - offset - 1;
                        System.arraycopy(lineBuffer, 0, buf, 0, offset);
                    }
                    buf[offset++] = (char) c;
                    break;
            }
        }
        if ((c == -1) && (offset == 0)) {
            return null;
        }
        return String.copyValueOf(buf, 0, offset);
    }

    /**
     * 读取流
     *
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    /**
     * 写文件
     */
    public static void writeFile(String filePath, String string) throws IOException {
        File tarFileDir = new File(filePath).getParentFile();
        if (!tarFileDir.exists()) {
            tarFileDir.mkdirs();
        }
        // FileWriter fw = new FileWriter(filePath);
        // PrintWriter out = new PrintWriter(fw);
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
        PrintWriter out = new PrintWriter(write);
        out.write(string);
        out.println();
        write.close();
        out.close();

        // FileOutputStream fos = new FileOutputStream(filePath);
        // OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        // osw.write(string);
        // osw.flush();
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean isExists(String filePath) {

        File tarFileDir = new File(filePath);
        if (!tarFileDir.exists()) {
            //不存在
            tarFileDir = null;
            return false;
        }
        tarFileDir = null;
        return true;
    }

    /**
     * 读文件
     */
    public static String ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        return dirFile.delete();
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        if (null != sPath && !"".equals(sPath)) {
            File file = new File(sPath);
            // 路径为文件且不为空则进行删除
            if (file.exists()) {
                file.delete();
                flag = true;
            }
            file = null;
        }
        return flag;
    }

    /**
     * 拷贝文件夹，包括子文件和子文件夹
     */
    public static void copyDir(String src, String des) {
        File file1 = new File(src);
        File[] fs = file1.listFiles();
        File file2 = new File(des);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (File f : fs) {
            if (f.isFile()) {
                copyFile(f.getPath(), des + "\\" + f.getName()); // 调用文件拷贝的方法
            } else if (f.isDirectory()) {
                copyDir(f.getPath(), des + "\\" + f.getName()); // 递归
            }
        }

    }

    /**
     * 拷贝文件
     */
    public static void copyFile(String src, String des) {
        BufferedReader br = null;
        PrintStream ps = null;
        try {
            File desFile = new File(des);
            if (!desFile.getParentFile().exists()) {
                desFile.getParentFile().mkdirs();
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
            ps = new PrintStream(new FileOutputStream(des));
            String s = null;
            while ((s = br.readLine()) != null) {
                ps.println(s);
                ps.flush();
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getStackTrace());
            logger.error(StringUtil.getStackMsg(e));
        } catch (IOException e) {
            logger.error(e.getStackTrace());
            logger.error(StringUtil.getStackMsg(e));
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (IOException e) {
                logger.error(e.getStackTrace());
                logger.error(StringUtil.getStackMsg(e));
            }
        }
    }

    /**
     * 根据URL判断文件是否存在
     */
    public static boolean isFileExists(String realFilePath) {
        try {
            File realFile = new File(realFilePath);
            return realFile.exists();
        } catch (Exception e) {
            logger.error(StringUtil.getStackMsg(e));
        }
        return false;
    }

    public static boolean isImage(File file) {
        boolean flag = false;
        try {
            ImageInputStream is = ImageIO.createImageInputStream(file);
            if (null == is) {
                return flag;
            }
            is.close();
            flag = true;
        } catch (Exception e) {
            // logger.error(e.getStackTrace());
        }
        return flag;
    }

    /**
     * 将inFilePath文件逐行读出字符串，用map中值替换键，再写到outFilePath
     *
     * @param inFilePath
     * @param replaceMap
     * @param outFilePath
     * @author lzm
     * @date 2013-07-13
     */
    public static void replaceFileContent(String inFilePath, Map<String, String> replaceMap, String outFilePath) {
        File inFile = new File(inFilePath);
        File outFile = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        // 读到一个字符串
        try {
            br = new BufferedReader(new FileReader(inFile));
            // 替换得到新串
            StringBuilder sb = new StringBuilder();
            String buff;
            String key = null;
            String value = null;
            while ((buff = br.readLine()) != null) {
                Iterator<String> iter = replaceMap.keySet().iterator();
                while (iter.hasNext()) {
                    key = iter.next();
                    if (buff.contains(key)) {
                        value = replaceMap.get(key);
                        buff = buff.replaceAll(key, value);
                    }
                }
                sb.append(buff).append("\n");
            }

            outFile = new File(outFilePath);
            bw = new BufferedWriter(new FileWriter(outFile));
            bw.write(sb.toString());
            sb = null;
            buff = null;
            System.gc();
            // 写回
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                logger.error(e);
            }
            br = null;
            bw = null;
            inFile = null;
            outFile = null;
        }
    }

    /**
     * 将文件转换为byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        if (!StringUtil.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                buffer = bos.toByteArray();
            } catch (FileNotFoundException e) {
                logger.error(e);
                // e.printStackTrace();
            } catch (IOException e) {
                logger.error(e);
                // e.printStackTrace();
            }
        }
        return buffer;
    }
    public static void tranformMethod(String inFile,String outFile) throws IOException {
        String fileName="";
        BufferedReader bs  = null;
        BufferedWriter bw = null;
        Map<String,String> cityCodeMap = Maps.newHashMap();
        try{
            bs = new BufferedReader(new FileReader(new File(inFile)));
            String line = null;
            while((line = bs.readLine()) != null){
                String[] split = line.split(" ");
                if (split.length < 2) {
                    System.out.println(line);
                }
                String code = split[0];
                String city = split[1];
                if (code.length() != 4) {
                    continue;
                }
                cityCodeMap.put(code,city);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(bs != null){
                bs.close();
            }
            if(bw != null){
                bw.close();
            }
        }
        String s = cityCodeMap.toString();
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        tranformMethod("/Users/yang/Desktop/cardCode","");
    }
}
