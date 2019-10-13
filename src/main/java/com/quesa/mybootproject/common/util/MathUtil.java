package com.quesa.mybootproject.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 一般运算处理工具类
 *
 */
public class MathUtil {
    /**
     * 功能：将字符串转换为BigDecimal，一般用于数字运算时
     *
     * @param str 字符串
     * @return BigDecimal, str为empty时返回null
     */
    public static BigDecimal toBigDecimal(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        return new BigDecimal(str);
    }

    /**
     * 功能：将字符串转换为double，如果失败返回默认值。
     *
     * @param str          字符串
     * @param defaultValue 失败时返回的默认值
     * @return double
     */
    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 功能：将字符串转换为float，如果失败返回默认值。
     *
     * @param str          字符串
     * @param defaultValue 失败时返回的默认值
     * @return float
     */
    public static float toFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 功能：将字符串转换为long，如果失败返回默认值。
     *
     * @param str          字符串
     * @param defaultValue 失败时返回的默认值
     * @return long
     */
    public static long toLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 功能描述:将字符串转换为int，如果失败返回默认值。
     *
     * @param str          字符串
     * @param defaultValue 失败时返回的默认值
     * @return int
     */
    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 得到两个double值中最大的一个.
     *
     * @param a 值 1
     * @param b 值 2
     * @return 最大的值
     */
    public static float getMax(float a, float b) {
        if (Float.isNaN(a)) {
            return b;
        } else if (Float.isNaN(b)) {
            return a;
        } else {
            return Math.max(a, b);
        }
    }

    /**
     * 得到数组中最大的一个.
     *
     * @param array 数组不能为null，也不能为空。
     * @return 得到数组中最大的一个.
     * @throws IllegalArgumentException
     * @throws IllegalArgumentException
     */
    public static float getMax(float[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        // Finds andreturns max
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            max = getMax(array[j], max);
        }
        return max;
    }

    /**
     * 得到数组中最大的一个.
     *
     * @param array 数组不能为null，也不能为空。
     * @return 得到数组中最大的一个.
     * @throws IllegalArgumentException
     * @throws IllegalArgumentException
     */
    public static double getMax(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        // Finds andreturns max
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            max = getMax(array[j], max);
        }
        return max;
    }

    /**
     * 得到两个 double值中最大的一个
     *
     * @param a 值 1
     * @param b 值 2
     * @return 最大的值
     */
    public static double getMax(double a, double b) {
        if (Double.isNaN(a)) {
            return b;
        } else if (Double.isNaN(b)) {
            return a;
        } else {
            return Math.max(a, b);
        }
    }

    /**
     * 得到两个float中最小的一个。
     *
     * @param a 值 1
     * @param b 值 2
     * @return double值最小的
     */
    public static float getMin(float a, float b) {
        if (Float.isNaN(a)) {
            return b;
        } else if (Float.isNaN(b)) {
            return a;
        } else {
            return Math.min(a, b);
        }
    }

    /**
     * 返回数组中最小的数值。
     *
     * @param array 数组不能为null，也不能为空。
     * @return 数组里面最小的float
     * @throws IllegalArgumentException
     * @throws IllegalArgumentException
     */
    public static float getMin(float[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("数组不能为null。");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("数组不能为空。");
        }
        // Finds andreturns min
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = getMin(array[i], min);
        }
        return min;
    }

    /**
     * 返回数组中最小的double。
     *
     * @param array 数组不能为null，也不能为空。
     * @return 数组里面最小的double
     * @throws IllegalArgumentException
     * @throws IllegalArgumentException
     */
    public static double getMin(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("数组不能为null。");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("数组不能为空。");
        }
        // Finds andreturns min
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = getMin(array[i], min);
        }
        return min;
    }

    /**
     * 得到两个double中最小的一个。
     *
     * @param a 值 1
     * @param b 值 2
     * @return double值最小的
     */
    public static double getMin(double a, double b) {
        if (Double.isNaN(a)) {
            return b;
        } else if (Double.isNaN(b)) {
            return a;
        } else {
            return Math.min(a, b);
        }
    }

    /**
     * 返回两个double的商 first除以second。
     *
     * @param first  第一个double
     * @param second 第二个double
     * @return double
     */
    public static double divideDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.divide(b2).doubleValue();
    }

    /**
     * 返回两个double的商 first除以second。 四舍五入保留decimals位小数
     *
     * @param first  第一个double
     * @param second 第二个double
     * @param decimals 保留小数位
     * @return double
     */
    public static double divideDouble(double first, double second ,int decimals) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.divide(b2,decimals,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回两个double的乘积 first*second
     *
     * @param first  第一个double
     * @param second 第二个double
     * @return double
     */
    public static double multiplyDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 返回两个double的乘积 first*second 四舍五入,保留decimals小数
     *
     * @param first    第一个double
     * @param second   第二个double
     * @param decimals 保留小数位
     * @return double
     */
    public static double multiplyDouble(double first, double second, int decimals) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.multiply(b2).setScale(decimals,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回两个double的差值 first-second。
     *
     * @param first  第一个double
     * @param second 第二个double
     * @return double
     */
    public static double subtractDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 返回两个double的和值 first+second。
     *
     * @param first  第一个double
     * @param second 第二个double
     * @return double
     */
    public static double sumDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(String.valueOf(first));
        BigDecimal b2 = new BigDecimal(String.valueOf(second));
        return b1.add(b2).doubleValue();
    }

    /**
     * 格式化double指定位数小数。例如将11.123格式化为11.1。
     *
     * @param value    原double数字。
     * @param decimals 小数位数。
     * @return 格式化后的double，注意为硬格式化不存在四舍五入。
     */
    public static String formatDouble(double value, int decimals) {
        String doubleStr = "" + value;
        int index = doubleStr.indexOf(".") != -1 ? doubleStr.indexOf(".") : doubleStr.indexOf(",");
        // Decimal point can not be found...
        if (index == -1) {
            return doubleStr;
        }
        // Truncate all decimals
        if (decimals == 0) {
            return doubleStr.substring(0, index);
        }
        int len = index + decimals + 1;
        if (len >= doubleStr.length()) {
            len = doubleStr.length();
        }
        double d = Double.parseDouble(doubleStr.substring(0, len));
        return String.valueOf(d);
    }

    /**
     * 生成一个指定位数的随机数，并将其转换为字符串作为函数的返回值。
     *
     * @param numberLength 随机数的位数。
     * @return String 注意随机数可能以0开头。
     */
    public static String randomNumber(int numberLength) {
        // 记录生成的每一位随机数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberLength; i++) {
            // 每次生成一位,随机生成一个0-10之间的随机数,不含10。
            Double randouble = Math.floor(Math.random() * 10);
            sb.append(randouble.intValue());
        }
        return sb.toString();
    }

    /**
     * 功能：生成一个在最大数和最小数之间的随机数。会出现最小数，但不会出现最大数。
     *
     * @param minNum 最小数
     * @param maxNum 最大数
     * @return int
     */
    public static int randomNumber(int minNum, int maxNum) {
        if (maxNum <= minNum) {
            throw new RuntimeException("maxNum必须大于minNum!");
        }
        // 计算出来差值
        int subtract = maxNum - minNum;
        Double randouble = Math.floor(Math.random() * subtract);
        return randouble.intValue() + minNum;
    }


    /**
     * 保留两位小数
     */
    public static DecimalFormat df = new DecimalFormat("######0.00");


    /**
     * 保留一位小数
     */
    public static DecimalFormat dfOne = new DecimalFormat("######0.0");


    //计算比率
    public static String accuracy(double num, double total, int scale) {
        DecimalFormat df = new DecimalFormat();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        //去掉逗号
        df.setGroupingUsed(false);
        double accuracy_num = 0.00;
        if (total > 0) {
            accuracy_num = num / total * 100;
        } else {
            accuracy_num = 0.00;
        }
        return df.format(accuracy_num) + "%";
    }


    public static String keepTwoBit(String src){
        try {
            BigDecimal bd = new BigDecimal(src);
            double money = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            DecimalFormat df = new DecimalFormat("#.00");
            src = df.format(money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * 原始价格为单位为元，微信单位为分，所以需要转换
     *
     * @param money
     * @return
     */
    public static String YuanTofen(String money) {
        BigDecimal sourcePrice = new BigDecimal(money);//原始价格 单位元
        BigDecimal b2 = new BigDecimal("100");//将元换算为分的单位 100
        String tfee = Double.toString(sourcePrice.multiply(b2).doubleValue());
        // 订单金额
        String total_fee = tfee.substring(0, tfee.indexOf("."));
        return total_fee;
    }


    public static void main(String[] args) {

        String s = accuracy(5, 9,2);
        System.out.println(s);
    }
}
