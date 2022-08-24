package com.example.hutool;

public final class StringUtils {

    private static final String QUOT = "&quot;";
    private static final String AMP = "&amp;";
    private static final String APOS = "&apos;";
    private static final String GT = "&gt;";
    private static final String LT = "&lt;";

    private StringUtils() {
    }

    public static void main(String[] args) {
        System.out.println(isEmpty(null));
        System.out.println(isEmpty(""));
        System.out.println(isEmpty("   "));
        System.out.println(isEmpty("%"));

        System.out.println(leftPad("ABC", 12, '0'));
        System.out.println(rightPad("ABC", 12, '0'));
        System.out.println(leftPad("ABCDEFABCDEF", 12, '0'));
        System.out.println(leftPad("", 12, '0'));
//		System.out.println(leftPad("ABCDEFABCDEFABCDEF", 12, '0'));

        System.out.println(areNotEmpty("123", null));
        System.out.println(areNotEmpty("123", ""));
        System.out.println(areNotEmpty("123", " "));
        System.out.println(areNotEmpty("123", " 456 "));

        System.out.println(isNumeric("-1"));
    }

    public static boolean notEquals(String s1, String s2) {
        return !equals(s1, s2);
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null))
            return false;

        return s1.equals(s2);
    }

    /**
     * XML字符转义包括(<,>,',&,")五个字符
     *
     * @param value
     * @return
     */
    public static final String escapeXml(String value) {
        StringBuilder writer = new StringBuilder();
        char[] chars = value.trim().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '<':
                    writer.append(LT);
                    break;
                case '>':
                    writer.append(GT);
                    break;
                case '\'':
                    writer.append(APOS);
                    break;
                case '&':
                    writer.append(AMP);
                    break;
                case '\"':
                    writer.append(QUOT);
                    break;
                default:
                    //非emoji字符
                    if ((c == 0x9) || (c == 0xA) || (c == 0xD) || ((c >= 0x20) && (c <= 0xD7FF))
                            || ((c >= 0xE000) && (c <= 0xFFFD)) || ((c >= 0x10000) && (c <= 0x10FFFF)))
                        writer.append(c);
            }
        }
        return writer.toString();
    }

    /**
     * 判断是否为数值，包含负数，不包含小数
     *
     * @param string
     * @return
     */
    public static final boolean isNumeric(String string) {
//        Asserts.notNull(string);

        char[] charArray = string.toCharArray();
        if (charArray.length < 1) {
            return false;
        }
        int i = 0;
        if (charArray.length > 1 && charArray[0] == '-') {
            i = 1;
        }
        for (; i < charArray.length; i++) {
            if (!Character.isDigit(charArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串为空
     * isEmpty(null) -> true
     * isEmpty("") -> true
     * isEmpty(" ") -> true
     * isEmpty("aaa") -> false
     *
     * @param string
     * @return
     */
    public static final boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    /**
     * 判断字符串非空
     * 等价于!isEmpty(string)
     *
     * @param string
     * @return
     */
    public static final boolean notEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 判断多个字符串非空
     *
     * @param strings
     * @return
     */
    public static final boolean areNotEmpty(String... strings) {
        if (strings == null || strings.length == 0) {
            throw new IllegalArgumentException("input param length must be great than zero");
        }
        for (String string : strings) {
            if (isEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 头部追加至长度
     *
     * @param string       原字符串
     * @param dstLength    目标长度
     * @param toAppendChar 需要添加的字符
     * @return
     * @throws NullPointerException     原字符串为空
     * @throws IllegalArgumentException 原字符串长度大于目标长度
     */
    public static final String leftPad(String string, int dstLength, char toAppendChar) {
        return pad(PadDirection.LEFT, string, dstLength, toAppendChar);
    }

    /**
     * 尾部追加至长度
     *
     * @param string       原字符串
     * @param dstLength    目标长度
     * @param toAppendChar 需要添加的字符
     * @return
     * @throws NullPointerException     原字符串为空
     * @throws IllegalArgumentException 原字符串长度大于目标长度
     */
    public static final String rightPad(String string, int dstLength, char toAppendChar) {
        return pad(PadDirection.RIGHT, string, dstLength, toAppendChar);
    }

    private static String pad(PadDirection direction, String string, int dstLength, char toAppendChar) {
//        Asserts.notNull(string);

        if (string.length() > dstLength) {
            throw new IllegalArgumentException("source string length great than dst length");
        }
        StringBuilder builder = new StringBuilder(string);
        if (string.length() < dstLength) {
            switch (direction) {
                case LEFT:
                    while (builder.length() < dstLength) {
                        builder.insert(0, String.valueOf(toAppendChar));
                    }
                    break;
                case RIGHT:
                    while (builder.length() < dstLength) {
                        builder.append(String.valueOf(toAppendChar));
                    }
                    break;
            }
        }
        return builder.toString();
    }

    private static enum PadDirection {
        LEFT, RIGHT;
    }

}
