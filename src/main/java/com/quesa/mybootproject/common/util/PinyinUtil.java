package com.quesa.mybootproject.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音工具类
 */
public class PinyinUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PinyinUtil.class);

    /**
     * 汉字转拼音
     *
     * @param chineseLanguage
     * @return
     */
    public static String toPinyin(String chineseLanguage) {
        char[] chars = chineseLanguage.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < chars.length; i++) {
                if (String.valueOf(chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0]);
                } else {// 如果字符不是中文,则不转换
                    sb.append(chars[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            LOGGER.error(e.getMessage());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toPinyin("测试"));
    }
}
