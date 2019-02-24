package com.example.demo2.archtechture.utils;

/**
 * @author lihuaqing
 * @create 2019-02-22 15:48
 **/
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.Util;

public final class MyStringUtils {
    private static final char DELIM_START = '{';
    private static final char DELIM_STOP = '}';
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';
    private static final int DOUBLE_ESCAPED_INDEX = 2;
    private static final int DEFAULT_COLLECTION_CAPACITY = 10;

    public MyStringUtils() {
    }

    public static String unicodeToString(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

            char ch;
            for(Matcher matcher = pattern.matcher(str); matcher.find(); str = str.replace(matcher.group(1), ch + "")) {
                ch = (char)Integer.parseInt(matcher.group(2), 16);
            }

            return str;
        }
    }

    public static String formatMessage(String messagePattern, Object[] argArray) {
        if (messagePattern == null) {
            return null;
        } else if (argArray == null) {
            return messagePattern;
        } else {
            int i = 0;
            StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);

            for(int inx = 0; inx < argArray.length; ++inx) {
                int j = messagePattern.indexOf("{}", i);
                if (j == -1) {
                    if (i == 0) {
                        return messagePattern;
                    }

                    sbuf.append(messagePattern, i, messagePattern.length());
                    return sbuf.toString();
                }

                if (isEscapedDelimeter(messagePattern, j)) {
                    if (!isDoubleEscaped(messagePattern, j)) {
                        --inx;
                        sbuf.append(messagePattern, i, j - 1);
                        sbuf.append('{');
                        i = j + 1;
                    } else {
                        sbuf.append(messagePattern, i, j - 1);
                        deeplyAppendParameter(sbuf, argArray[inx], new HashMap(10));
                        i = j + 2;
                    }
                } else {
                    sbuf.append(messagePattern, i, j);
                    deeplyAppendParameter(sbuf, argArray[inx], new HashMap(10));
                    i = j + 2;
                }
            }

            sbuf.append(messagePattern, i, messagePattern.length());
            return sbuf.toString();
        }
    }

    static final boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex == 0) {
            return false;
        } else {
            char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
            return potentialEscape == '\\';
        }
    }

    static final boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
        return delimeterStartIndex >= 2 && messagePattern.charAt(delimeterStartIndex - 2) == '\\';
    }

    private static void deeplyAppendParameter(StringBuilder sbuf, Object o, Map<Object[], Object> seenMap) {
        if (o == null) {
            sbuf.append("null");
        } else {
            if (!o.getClass().isArray()) {
                safeObjectAppend(sbuf, o);
            } else if (o instanceof boolean[]) {
                booleanArrayAppend(sbuf, (boolean[])((boolean[])o));
            } else if (o instanceof byte[]) {
                byteArrayAppend(sbuf, (byte[])((byte[])o));
            } else if (o instanceof char[]) {
                charArrayAppend(sbuf, (char[])((char[])o));
            } else if (o instanceof short[]) {
                shortArrayAppend(sbuf, (short[])((short[])o));
            } else if (o instanceof int[]) {
                intArrayAppend(sbuf, (int[])((int[])o));
            } else if (o instanceof long[]) {
                longArrayAppend(sbuf, (long[])((long[])o));
            } else if (o instanceof float[]) {
                floatArrayAppend(sbuf, (float[])((float[])o));
            } else if (o instanceof double[]) {
                doubleArrayAppend(sbuf, (double[])((double[])o));
            } else {
                objectArrayAppend(sbuf, (Object[])((Object[])o), seenMap);
            }

        }
    }

    private static void safeObjectAppend(StringBuilder sbuf, Object o) {
        try {
            String oAsString = o.toString();
            sbuf.append(oAsString);
        } catch (Throwable var3) {
            Util.report("SLF4J: Failed toString() invocation on an object of type [" + o.getClass().getName() + "]", var3);
            sbuf.append("[FAILED toString()]");
        }

    }

    private static void objectArrayAppend(StringBuilder sbuf, Object[] a, Map<Object[], Object> seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, (Object)null);
            int len = a.length;

            for(int i = 0; i < len; ++i) {
                deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i != len - 1) {
                    sbuf.append(", ");
                }
            }

            seenMap.remove(a);
        } else {
            sbuf.append("...");
        }

        sbuf.append(']');
    }

    private static void booleanArrayAppend(StringBuilder sbuf, boolean[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void byteArrayAppend(StringBuilder sbuf, byte[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void charArrayAppend(StringBuilder sbuf, char[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void shortArrayAppend(StringBuilder sbuf, short[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void intArrayAppend(StringBuilder sbuf, int[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void longArrayAppend(StringBuilder sbuf, long[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void floatArrayAppend(StringBuilder sbuf, float[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }

    private static void doubleArrayAppend(StringBuilder sbuf, double[] a) {
        sbuf.append('[');
        int len = a.length;

        for(int i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }

        sbuf.append(']');
    }
}
