package com.appcomponent.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jzy
 * @ClassName: StringUtil
 * @Description: (字符串工具类)
 * @date 2012-9-11 下午01:48:36
 */
public class StringUtil {
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean listIsEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return (s != null && s.trim().length() > 0);
    }

    public static String readAssetsCity(Context context, String fileName) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        if (fileName == null || fileName.equals("")) {
            return null;
        }
        try {
            is = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));

            sb.append(br.readLine());

            String line;
            while ((line = br.readLine()) != null) {
                // temp+=line;
                sb.append(line);
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }

    public static String formatString(int maxLen, String textValue) {
        if (!isEmpty(textValue)) {
            if (textValue.length() > maxLen) {
                return textValue.substring(0, maxLen) + " ...";
            }
        }
        return textValue;
    }

    public static String formatMoney(long moeny) {
        String res;
        NumberFormat format = NumberFormat.getCurrencyInstance();
        res = format.format(moeny);
        return res.substring(1, res.length()).trim();
    }

    public static String hideLastStr(String content) {
        if (StringUtil.isEmpty(content)) {
            return "";
        }
        String result = "";
        int len = content.length();
        result = content.substring(0, 1);
        for (int i = 1; i < len; i++) {
            result += "*";
        }
        return result;
    }

    public static String leastTwoStr(String content) {
        if (StringUtil.isEmpty(content)) {
            return "";
        }
        String result = "";
        result = content.substring(0, 1);
        for (int i = 1; i < 3; i++) {
            result += "*";
        }
        return result;
    }

    public static String readFromFile(Context context, String fileName) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();

        // String temp = null;
        File file;
        if (fileName == null || fileName.equals("")) {
            return null;
        }
        try {
            file = context.getFileStreamPath(fileName);
            is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            int c;
            char[] charStr = new char[1024];
            while ((c = isr.read(charStr)) != -1) {
                sb.append(charStr, 0, c);
            }

            is.close();
        } catch (Exception e) {
            return null;
        }
        return sb.toString();

    }

    public static void writeCityToFile(Context context, String fileName) {
        InputStream is = null;
        if (fileName == null || fileName.equals("")) {
            return;
        }
        try {
            // ew
            is = context.getAssets().open(fileName);
            File file = context.getFileStreamPath(fileName);
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = is.read(buffer))) {
                output.write(buffer, 0, n);
            }
            output.close();
            is.close();
        } catch (Exception e) {
        }
    }

    public static void writeNewCityToFile(Context context, String fileName,
                                          String str) {
        File file = context.getFileStreamPath(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.close();
            fos.close();
        } catch (Exception e) {
        }
    }

    /**
     * 通过code取得乘机人类型
     **/
    public static String getPTypeByPCode(String code) {
        String type = "成人";
        if ("01".equals(code)) {
            type = "成人";
        } else if ("02".equals(code)) {
            type = "儿童";
        }
        return type;
    }

    /**
     * 通过code取得乘机人类型
     **/
    public static String getPCodeByPType(String type) {
        String typeCode = "01";
        if ("成人".equals(type)) {
            typeCode = "01";
        } else if ("儿童".equals(type)) {
            typeCode = "02";
        }
        return typeCode;
    }

    /**
     * 通过code取得证件名称
     **/
    public static String getCertNameByCode(String code) {
        String cert = "";
        if ("0".equals(code)) {
            cert = "身份证";
        } else if ("1".equals(code)) {
            cert = "护照";
        } else if ("2".equals(code)) {
            cert = "军官证";
        } else if ("3".equals(code)) {
            cert = "港澳通行证";
        } else if ("4".equals(code)) {
            cert = "回乡证";
        } else if ("5".equals(code)) {
            cert = "台胞证";
        } else if ("6".equals(code)) {
            cert = "国际海员证";
        } else if ("7".equals(code)) {
            cert = "外国人永久居留证";
        } else if ("9".equals(code)) {
            cert = "其他";
        }
        return cert;
    }

    //
    public static String getCodeByCertName(String certName) {
        String cert = "";
        if ("身份证".equals(certName.trim())) {
            cert = "0";
        } else if ("护照".equals(certName.trim())) {
            cert = "1";
        } else if ("军官证".equals(certName.trim())) {
            cert = "2";
        } else if ("港澳通行证".equals(certName.trim())) {
            cert = "3";
        } else if ("回乡证".equals(certName.trim())) {
            cert = "4";
        } else if ("台胞证".equals(certName.trim())) {
            cert = "5";
        } else if ("国际海员证".equals(certName.trim())) {
            cert = "6";
        } else if ("外国人永久居留证".equals(certName.trim())) {
            cert = "7";
        } else if ("其他".equals(certName)) {
            cert = "9";
        }
        return cert;
    }

    public static int getChineseLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static int getAirDegree(double ax, double ay, double bx, double by) {
        double degree = Math.atan(Math.abs((by - ay) / (bx - ax))) * 180
                / Math.PI;
        double dLo = by - ay;
        double dLa = bx - ax;
        if (dLo > 0 && dLa <= 0) {
            degree = (90 - degree) + 90;
        } else if (dLo <= 0 && dLa < 0) {
            degree = degree + 180;
        } else if (dLo < 0 && dLa >= 0) {
            degree = (90 - degree) + 270;
        }
        return (int) degree;
    }

    public static String selectTime(String planTime, String realTime) {
        if (!"-".equals(realTime.trim())) {
            return realTime;
        }
        return planTime;
    }

    public static String getStringValue(Object str, String def) {
        String s = (String) str;
        if (null == s || isEmpty(s) || "false".equals(s)) {
            s = def;
        }
        return s;
    }

    public static String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    public static String setHtmlFont(String html, int fontSize) {
        if (isEmpty(html)) {
            return html;
        }
        String font = "<font size=\"" + fontSize + "\">";
        String fontEnd = "</font>";
        html = font + html + fontEnd;
        return html;
    }

    /**
     * 判断字符串是否是英文
     *
     * @param str
     * @return true：是 false：不是
     */
    public static boolean isABC(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String c = str.substring(0, 1);
        Pattern pattern = Pattern.compile("[a-z|A-Z]");
        Matcher m = pattern.matcher(c);
        return m.find();
    }

    /**
     * 判断字符串是否是英文
     *
     * @param str
     * @return true：是 false：不是
     */
    public static boolean isNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String c = str.substring(0, 1);
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher m = pattern.matcher(c);
        return m.find();
    }

    /**
     * 判断字符串是否是邮箱
     *
     * @param str
     * @return true：是 false：不是
     */
    public static boolean isEmail(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(check);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    /**
     * 判断是不是密码
     */
    public static boolean isPwd(String loginPassword) {
        // Pattern mPattern = Pattern.compile("^[0-9a-zA-Z]{6,16}$");
        Pattern mPattern = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$).{6,16}$");
        Matcher mMatcher = mPattern.matcher(loginPassword);
        return mMatcher.matches();
    }

    /**
     * 判断是不是手机验证码全是数字
     *
     * @param @param  identifyingCode
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isVCode
     * @Description: (这里用一句话描述这个方法的作用)
     */
    public static boolean isVCode(String identifyingCode) {
        Pattern mPattern = Pattern.compile("^\\d{6}$");
        Matcher mMatcher = mPattern.matcher(identifyingCode);
        return mMatcher.matches();
    }

    /**
     * 判断是不是手机号
     *
     * @param @param  loginPhoneNumber
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isPhoneNumber
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * 增加 运营商最新的号段 188 199 166
     */
    public static boolean isPhoneNumber(String loginPhoneNumber) {
        Pattern mPattern = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");
        Matcher mMatcher = mPattern.matcher(loginPhoneNumber);
        return mMatcher.matches();
    }

    /**
     * 判断是不是身份证号
     *
     * @param @param  loginPhoneNumber
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isPhoneNumber
     * @Description: (这里用一句话描述这个方法的作用)
     */
    public static boolean isIdCard(String idCard) {
        //TODO 身份证最后一位是否强制为正确的大写字母
        Pattern mPattern = Pattern
                .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
        Matcher mMatcher = mPattern.matcher(idCard);
        return mMatcher.matches();
    }

    /**
     * @param @param  idCard
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isIdCard
     * @Description: (判断是不是银行卡号
     */
    public static boolean isBankCard(String BankCard) {
        Pattern mPattern = Pattern.compile("^\\d{12,}$");
        Matcher mMatcher = mPattern.matcher(BankCard);
        return mMatcher.matches();
    }

    public static String db2Mb(String value) {
        float dbValue = Long.valueOf(value);
        float mbValue = dbValue / (1024 * 1024);
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        return String.valueOf(dcmFmt.format(mbValue));

    }

    /**
     * 指定字体大小和颜色
     *
     * @param str   指定字符串
     * @param start begin
     * @param end   end
     * @param xp    字体大小单位像素
     * @param color 字体颜色（#000000）
     * @return 设置好的SpannableString
     */
    public static SpannableString makeAprStr(String str, int start, int end, int xp, String color) {
        SpannableString sbs = new SpannableString(str);
        AbsoluteSizeSpan sizeSpan;
        ForegroundColorSpan colorSpan;

        if (xp != 0) {
            sizeSpan = new AbsoluteSizeSpan(xp, true);
            sbs.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (!StringUtil.isEmpty(color)) {
            colorSpan = new ForegroundColorSpan(Color.parseColor(color));
            sbs.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return sbs;
    }

    @Deprecated  //颜色不起作用
    public static SpannableString makeAprStr(String str, int start, int end, int xp, int color) {
        SpannableString sbs = new SpannableString(str);
        AbsoluteSizeSpan sizeSpan;
        ForegroundColorSpan colorSpan;

        if (start - end >= 0) {
            return sbs;
        }

        if (xp != 0) {
            sizeSpan = new AbsoluteSizeSpan(xp, true);
            sbs.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (color != 0) {
            colorSpan = new ForegroundColorSpan(color);
            sbs.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return sbs;
    }

    public static String inputLongReturnDateString(long data) {
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(data);
        return sf.format(date);
    }

    /**
     * 转化long格式的数据为金额格式
     *
     * @param longStr 以分为单位的long类型数据
     * @param len     保留的位数
     * @return
     */
    public static String formatMoney(long longStr, int len) {
        String s = "" + ((double) longStr) / 100;
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = Double.parseDouble(s);
        if (len == 0) {
            formater = new DecimalFormat("###,###");

        } else {
            StringBuffer buff = new StringBuffer();
            buff.append("###,###.");
            for (int i = 0; i < len; i++) {
                buff.append("#");
            }
            formater = new DecimalFormat(buff.toString());
        }
        String result = formater.format(num);
        if (result.indexOf(".") == -1) {
            result = result + ".00";
        }
        return result;
    }

    public static String cutPdfString(String loadUrlStr) {
        String resultStr = "";
        if (loadUrlStr.endsWith(".pdf")) {
            int startInt = loadUrlStr.lastIndexOf("/");
            resultStr = loadUrlStr.substring(startInt + 1, loadUrlStr.length() - 4);
        }
        return resultStr;
    }


    public static String delphoneString(String phone) {
        String resultStr = "";
        if (StringUtil.isNotEmpty(phone)) {
            resultStr = phone.replace(phone.substring(3, 7)," **** ");
        }
        return resultStr;
    }

    /**
     * 隐藏身份证中间部分
     *
     * @param old
     * @return
     */
    public static String dealIdCard2(String old) {
        if (StringUtil.isEmpty(old))
            return "";
        return old.replace(old.substring(6, 14), "********");
    }

    /**
     * 隐藏姓名部分
     *
     * @param
     * @return
     */
    public static String dealRealname(String realName) {
        if (StringUtil.isEmpty(realName))
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(realName.charAt(0));
        for (int i = 0; i < realName.length() - 1; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    public static String insertDotToStringFromIndex(String string) {
        int length = string.length();
        StringBuffer buffer = new StringBuffer(string);
        if (length > 0 && length > 4) {
            buffer.insert(4, ".");
        }
        if (length > 6) {
            buffer.insert(7, ".");
        }
        return buffer.toString();
    }

    /**
     * 金额单位格式化（该方法具体效果见当前版本的需求文档总则）
     * 暂时接收以分为单位的金额
     *
     * @param string
     * @return
     */
    public static String translateMoneyToDouble(String string) {
        int length = string.length();
        if (string != null && string.length() > 2 && string.indexOf(".") == -1) {
            StringBuffer buffer = new StringBuffer(string);
            boolean endsWith = string.endsWith("00");
            if (endsWith) {
                return string.substring(0, length - 2);
            } else {
                boolean endsWith2 = string.endsWith("0");
                if (endsWith2) {
                    buffer.insert(length - 2, ".");
                    buffer.deleteCharAt(buffer.length() - 1);
                } else {
                    buffer.insert(length - 2, ".");
                }
            }
            string = buffer.toString();
        } else if (length <= 2 && !string.startsWith("0") && string.indexOf(".") == -1) {
            StringBuffer buffer = new StringBuffer("0.");
            buffer.append(string);
            String newString = buffer.toString();
            boolean endsWith = newString.endsWith("0");
            if (endsWith) {
                string = newString.substring(0, newString.length() - 1);
            } else {
                string = newString;
            }
        }

        if (string.endsWith(".0")) {
            string = string.substring(0, string.length() - 2);
        } else if (string.endsWith(".00")) {
            string = string.substring(0, string.length() - 3);
        }
        return string;
    }

    /**
     * @param string
     * @return
     */
    public static String translateMoneyToKeepDouble(String string) {
        int length = string.length();
        if (string != null && string.length() > 2 && string.indexOf(".") == -1) {
            StringBuffer buffer = new StringBuffer(string);
            buffer.insert(length - 2, ".");
            string = buffer.toString();
        } else if (string != null && length <= 2 && !string.startsWith("0") && string.indexOf(".") == -1) {
            StringBuffer buffer = new StringBuffer("0.");
            buffer.append(string);
            String newString = buffer.toString();
            boolean endsWith = newString.endsWith("0");
            boolean startsWith = newString.startsWith("0");
            if (!startsWith && endsWith) {
                string = newString.substring(0, newString.length() - 1);
            } else {
                string = newString;
            }
        }

        if (string.endsWith(".0")) {
            string = string.substring(0, string.length() - 2);
        } else if (string.endsWith(".00")) {
            string = string.substring(0, string.length() - 3);
        }
        return string;
    }

    /**
     * 动态设置Editer hint
     *
     * @param textValue
     * @param textSize
     * @return
     */

    public static SpannableString setHintSize(String textValue, int textSize) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(textValue);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
// 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
// 设置hint
        return ss;
    }

    /*
     * Add color to a given text
     */
    public static SpannableStringBuilder addColor(CharSequence text, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (color != 0) {
            builder.setSpan(new ForegroundColorSpan(color), 0, text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


}
