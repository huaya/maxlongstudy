package com.maxlong.study.Util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities for StringUtil
 */
public class StringUtil {

    public final static String ENCODE_UTF8 = "UTF-8";

    public final static String ENCODE_DEFAULT = ENCODE_UTF8;

	/**
	 * 参见:<br/>
	 * https://github.com/VincentSit/ChinaMobilePhoneNumberRegex
	 */
	public final static String PHONE_REG = "^(?=\\d{11}$)^1(?:3\\d|4[57]|5[^4\\D]|7[^249\\D]|8\\d)\\d{8}$";
	public final static String PHONE_REG_ALLOW_EMPTY = "(^" + PHONE_REG + ")?";
	public final static Pattern PHONE_PATTERN = Pattern.compile(PHONE_REG);
	public final static Pattern PHONE_PATTERN_ALLOW_EMPTY = Pattern.compile(PHONE_REG_ALLOW_EMPTY);

    public static String genUUIDString() {
        return UUID.randomUUID().toString();
    }

    public static UUID genUUID(String uuid) {
        return UUID.fromString(uuid);
    }

    public static String toDateString(Date date, String pattern) {
        String dateString = null;
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }
    public static String nowDate() {
        return toDateString(new Date(), "yyyyMMdd");
    }
    public static String nowDatetime () {
        return toDateString(new Date(), "yyyyMMddHHmmss");
    }

	public static String nowTime () {
		return toDateString(new Date(), "HHmmss");
	}

    public static Date toDate(String dateString, String pattern) {
        Date date = null;
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String padStart(String str, int max, String pad) {
        String string = str;
        int len = str.length();
        if (len < max) {
            for (; string.length() < max; ) {
                string = pad + string;
            }
        } else {
            throw new RuntimeException("data is too long, str len:" + len + ", but max length:" + max);
        }
        return string;
    }

    /**
     * 左、右、两边填充字符
     *
     * @param str      待填充的字符串，可以为null
     * @param fillChar 填充的字符
     * @param fillSide ['left','right','both']
     *                 填充的方向，
     * @param size     输出字符串的固定byte长度。
     * @return String
     */
    public static String charFill(String str, char fillChar, String fillSide, int size) {
        str = (str == null) ? "" : str;
        StringBuilder sb = new StringBuilder(str);
        int len = str.length();
        if (len >= size)
            return (("left".equals(fillSide)) ? str.substring(len - size) : str.substring(0, size));
        int n = size - len;
        if ("left".equals(fillSide))
            for (int i = 0; i < n; ++i)
                sb.insert(0, fillChar);
        else if ("right".equals(fillSide))
            for (int i = 0; i < n; ++i)
                sb.append(fillChar);
        else if ("both".equals(fillSide)) {
            for (int i = 0; i < n; ++i) {
                if (i % 2 == 0)
                    sb.insert(0, fillChar);
                else
                    sb.append(fillChar);
            }
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean notEmpty(String str) {
        if (str != null && str.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * setCardNoCHeckCode:设置卡号校验码. <br/>
     * 卡号校验位置默认第8位.<br/>
     *
     * @param typeMark   卡标识
     * @param cardSerial 卡序号
     * @return String 卡号
     */
    public static String setCardNoCHeckCode(String typeMark, String cardSerial) {

        final int checkOffset = 8;
        /**卡标识和卡序号不能为空**/
        if (typeMark == null || cardSerial == null || typeMark.length() == 0 || cardSerial.length() == 0) {
            return null;
        }
        String cardNo = typeMark + cardSerial;
        int j = 1, k = 0, m = 0, n = 0, t = 0;

        /**无校验位卡号从右朝左逐步计算**/
        for (int i = cardSerial.length() + typeMark.length(); i > 0; i--) {
            if ((j % 2) == 1) { /**第奇数位**/
                m = 0;
                n = (cardNo.charAt(i - 1) - '0') * 2;
                t = n;
                if (n > 9) {
                    t = n / 10 + n % 10;
                }
            } else {   /**第偶数位**/
                m = cardNo.charAt(i - 1) - '0';
                t = 0;
            }
            k = k + t + m;
            j++;
        }
        int check = (10 - (k % 10)) % 10;
        return cardNo.substring(0, checkOffset - 1) + check + cardNo.substring(checkOffset - 1);
    }

    /**
     * 连接字符串的时候注意null值会跳过
     * @param separator
     * @param strs
     * @return
     */
    public static String unionString(String separator, String... strs) {
        return Joiner.on(separator).skipNulls().join(strs);
    }

    public static String unionStringWithSuffix(String separator,String suffix, String... strs) {
        return Joiner.on(separator).skipNulls().join(strs)+suffix;
    }
    public static String unionStringWithSuffixNotSkipNull(String separator,String suffix, String... strs) {
    	//对null转换为"";
	    int index = 0;
    	for(String str : strs){
    		if(str == null){
			    strs[index] = "";
    		}
		    index ++;
    	}
        return Joiner.on(separator).join(strs)+suffix;
    }

    public static List<String> splitToListWithTrimAndOmitEmpty(String separator, String sequence) {
        return Splitter.on(separator).trimResults().omitEmptyStrings().splitToList(sequence);
    }

    public static List<String> splitToList(String separator, String sequence) {
        return Splitter.on(separator).splitToList(sequence);
    }

    public static String[] splitToArray(String separator, String sequence) {
        List<String>  list = splitToList(separator,sequence);
        String[] data = new String[list.size()];
        return list.toArray(data);
    }

    public static String unionStringWithAint(String... strs) {
        return Joiner.on("&").skipNulls().join(strs);
    }


    public static boolean between(String str, String beginStr, String endStr) {
        boolean between = false;
        if (beginStr != null && endStr != null && str != null && str.compareTo(beginStr) >= 0 && str.compareTo(endStr) <= 0) {
            between = true;
        }
        return between;
    }

    /**
     * 校验手机号
     *
     * @param phone
     * @return
     */
    public static boolean regexMobile(String phone) {
       return regexMobile(phone, false);
    }
	public static boolean regexMobile(String phone, boolean allowEmpty) {
		try {
			Matcher m;
			if(allowEmpty){
				m = PHONE_PATTERN_ALLOW_EMPTY.matcher(phone);
			} else {
				m = PHONE_PATTERN.matcher(phone);
			}
			return m.matches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

    /**
     * 校验邮箱
     *
     * @param mail
     * @return
     */
    public static boolean regexMail(String mail) {
        try {
            String regexExpress = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(regexExpress);
            Matcher matcher = regex.matcher(mail);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取代换行符
     *
     * @param string
     * @return
     */
    public static String regexReplaceLine(String string) {
        if (string != null) {
            String temp = string.replaceAll("\r\n", " ");
            return string.replaceAll("\n", " ");
        }
        return null;
    }

    /**
     * 取代竖线分割符
     *
     * @param string
     * @return
     */
    public static String regexReplaceVert(String string) {
        if (string != null) {
            return string.replaceAll("\\|", "");
        }
        return null;
    }

    /**
     * 取代竖线分割符
     *
     * @param string
     * @return
     */
    public static String regexReplaceAnt(String string) {
        if (string != null) {
            return string.replaceAll("&", "");
        }
        return null;
    }

    /**
     * 组合取代
     *
     * @param string
     * @param regexs
     * @return
     */
    public static String  regexReplace(String string,String... regexs){
        if (string != null && regexs != null &&  regexs.length > 0) {
            for (String regex:regexs) {
                string = string.replaceAll(regex, "");
            }
        }
        return string;
    }

    /**
     * 截取字符串
     *
     * @param string
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static String sub(String string, int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (string != null && string.length() <= endIndex)
            return string;
        else if (string != null && string.length() > endIndex)
            return string.substring(beginIndex, endIndex);
        else
            return null;
    }


    public static void main(String[] args) {
        System.out.println(StringUtil.setCardNoCHeckCode("04900001", "1705052"));
//        System.out.println(StringUtil.regexMobile("4545"));
//        System.out.println(StringUtil.regexReplaceLine("213132131\n" + "eqeqeqeqe"));
//        System.out.println(StringUtil.unionStringWithSuffix("|","|",new String[]{"a","b","c",""}));
//        System.out.println(StringUtil.regexReplaceVert("aaaa|bbb"));
//        System.out.println(StringUtil.regexReplaceAnt("aaaa&bbb"));
//        System.out.println(StringUtil.regexReplace("aaaa|bbb&cccc\r\nddd","\\|","&","\r\n"));
    	
//    	System.out.println(StringUtil.unionStringWithSuffixNotSkipNull("|","|","1","2","3","4","5","6","7","8"));

	    System.out.println(nowTime());
    }
}