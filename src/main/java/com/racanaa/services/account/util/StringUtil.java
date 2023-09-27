package com.racanaa.services.account.util;

import com.racanaa.services.account.constant.ApiConstant;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for string manipulation.
 *
 * @author Manohar
 * @since 23/Sep/2023
 *
 */
@Slf4j
public class StringUtil {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // private static final String NOT_ALLOWED_CHARS_FOR_XML = "[^-0-9a-zA-Z\\(\\)_,\\.\\/'\\[\\]]+";
    private static final String NOT_ALLOWED_CHARS_FOR_H2H = "[^0-9a-zA-Z_,]+";

    private static final String INTEGER_NUMBER_PATTERN = "-?\\d+";
    private static final String DECIMAL_NUMBER_PATTERN = INTEGER_NUMBER_PATTERN + "(\\.\\d+)?";
    private static final byte[] HEX_CHAR_TABLE = {
            (byte) '0', (byte) '1', (byte) '2', (byte) '3',
            (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
            (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
    };
    private static final NumberFormat twoDigitNumber = NumberFormat.getInstance();
    private static final NumberFormat threeDigitNumber = NumberFormat.getInstance();
    private static final NumberFormat fourDigitNumber = NumberFormat.getInstance();

    static {
        twoDigitNumber.setGroupingUsed(false);
        twoDigitNumber.setMinimumIntegerDigits(2);

        threeDigitNumber.setGroupingUsed(false);
        threeDigitNumber.setMinimumIntegerDigits(3);

        fourDigitNumber.setGroupingUsed(false);
        fourDigitNumber.setMinimumIntegerDigits(4);
    }

    /**
     * Private constructor to avoid new()
     */
    private StringUtil() {
        throw new IllegalStateException(ApiConstant.UTILITY_CLASS_NO_CONSTRUCTOR);
    }

    /**
     * Replaces all the characters not allowed for H2H format with " "(single space).
     *
     * @param input the input string
     * @return the escaped string
     */
    public static String escapeForH2H(String input) {
        input = input.replaceAll(" ?([,.])", "$1 ");
        return input.replaceAll(NOT_ALLOWED_CHARS_FOR_H2H, " ");
    }

    /**
     * Replaces all the multiple spaces in a string with a single space.
     *
     * @param input the input string
     * @return the space cleaned string
     */
    public static String cleanSpaces(String input) {
        if (StringUtil.isNotEmpty(input)) {
            input = input.replaceAll("\\s+", " ");
            return input;
        }
        return null;
    }

    /**
     * Converts a string containing a number to double. Handles for null and non-numeric strings also.
     *
     * @param doubleVal the string to be converted to double
     * @return the extracted double value
     */
    public static double getDoubleValue(String doubleVal) {
        if (isEmpty(doubleVal) || !Pattern.matches(DECIMAL_NUMBER_PATTERN, doubleVal)) {
            return 0;
        }
        return Double.parseDouble(doubleVal);
    }

    /**
     * Converts a string containing a number to integer. Handles for null and non-numeric strings also.
     *
     * @param integerVal the string to be converted to integer
     * @return the extracted integer value
     */
    public static int getIntegerValue(String integerVal) {
        if (isEmpty(integerVal) || !Pattern.matches(INTEGER_NUMBER_PATTERN, integerVal)) {
            return 0;
        }
        return Integer.parseInt(integerVal);
    }

    /**
     * Converts a string containing a number to long. Handles for null and non-numeric strings also.
     *
     * @param longVal the string to be converted to long
     * @return the extracted long value
     */
    public static long getLongValue(String longVal) {
        if (isEmpty(longVal) || !Pattern.matches(INTEGER_NUMBER_PATTERN, longVal)) {
            return 0;
        }
        return Long.parseLong(longVal);
    }

    /**
     * Escapes characters for text appearing as data in the
     * <a href='http://www.json.org/'>Javascript Object Notation</a>
     * (JSON) data interchange format.
     * <p/>
     * <pre>The following commonly used control characters are escaped :
     * <table border='1' cellpadding='3' cellspacing='0'>
     * <tr><th> Character </th><th> Escaped As </th></tr>
     * <tr><td> " </td><td> \" </td></tr> Do not need to do this as browser itself is escaping this
     * <tr><td> \ </td><td> \\ </td></tr>
     * <tr><td> / </td><td> \/ </td></tr>
     * <tr><td> back space </td><td> \b </td></tr>
     * <tr><td> form feed </td><td> \f </td></tr>
     * <tr><td> line feed </td><td> \n </td></tr>
     * <tr><td> carriage return </td><td> \r </td></tr>
     * <tr><td> tab </td><td> \t </td></tr>
     * </table>
     * </pre>
     */
    public static String skipSpecialCharacterForJson(String jsonString) {

        if (isEmpty(jsonString)) {
            return jsonString;
        }

        final StringBuilder result = new StringBuilder();
        StringCharacterIterator iterator = new StringCharacterIterator(jsonString);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            /*if (character == '\"') {
                result.append("\\\"");
            } else*/
            if (character == '\\') {
                result.append("\\\\");
            } else if (character == '/') {
                result.append("\\/");
            } else if (character == '\b') {
                result.append("\\b");
            } else if (character == '\f') {
                result.append("\\f");
            } else if (character == '\n') {
                result.append("\\n");
            } else if (character == '\r') {
                result.append("\\r");
            } else if (character == '\t') {
                result.append("\\t");
            } else {
                //the char is not a special one
                //add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString().replaceAll("[\u0000-\u001f]", "");
    }

    /**
     * Checks if the given string is null or only spaces.
     *
     * @param str the string to be tested
     * @return {@code true} if the string is null or has only spaces, {@code false} otherwise
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return "".equals(str.trim());
    }

    /**
     * Checks if the given string is neither null nor has only spaces.
     *
     * @param str the string to be tested
     * @return {@code true} if the string is non-null and contains non-space characters, {@code false} otherwise
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Replaces all commas with spaces.
     *
     * @param str the input string
     * @return the string after removing all commas by spaces
     */
    public static String commaToSpace(String str) {
        return str.replaceAll(",", " ");
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param raw the input byte array
     * @return the hex string received after converting the byte array
     */
    public static String getHexString(byte[] raw) {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, StandardCharsets.US_ASCII);
    }

    /**
     * Converts a string array to a single string.
     *
     * @param strings   an array of strings
     * @param separator the separator between each array element
     * @return a single string after joining the elements of the array, separated by the {@code separator}
     */
    public static String arrayToString(String[] strings, String separator) {
        if (strings == null || strings.length < 1) {
            return "";
        }
        if (strings.length == 1) {
            return strings[0];
        }
        if (isEmpty(separator)) {
            separator = "";
        }
        return String.join(separator, strings);
    }

    /**
     * Converts an integer array to a single string.
     *
     * @param integers  an array of integers
     * @param separator the separator between each array element
     * @return a single string after joining the elements of the array, separated by the {@code separator}
     */
    public static String arrayToString(int[] integers, String separator) {
        StringBuilder result = new StringBuilder();
        if (integers.length > 0) {
            result.append(integers[0]);
            for (int i = 1; i < integers.length; i++) {
                result.append(separator);
                result.append(integers[i]);
            }
        }
        return result.toString();
    }

    public static String to2Digits(int value) {
        return twoDigitNumber.format(value);
    }

    public static String to3Digits(int value) {
        return threeDigitNumber.format(value);
    }

    public static String to4Digits(int value) {
        return fourDigitNumber.format(value);
    }

    /**
     * Converts the given string to a 4 digit integer. If the number is less than the {@code minValue}, {@code
     * defaultValue is used} for the operation.
     *
     * @param integerString the string containing the integer
     * @param minValue      the minimum value of the output
     * @param defaultValue  the default value
     * @return the 4 digit string of the integer value
     */
    public static String to4Digits(String integerString, int minValue, int defaultValue) {
        int v = getIntegerValue(integerString);
        if (v < minValue) {
            v = defaultValue;
        }
        return to4Digits(v);
    }

    /**
     * Checks if input starts with substring.
     *
     * @param input     the input string
     * @param substring the substring to be checked for at the beginning of the main string
     * @return {@code true} if the string starts with the given substring, {@code false} otherwise
     */
    public static boolean startsWith(String input, String substring) {
        return isNotEmpty(input) && isNotEmpty(substring) && input.startsWith(substring);
    }

    /**
     * Replaces all the line breaks in the given string with spaces.
     *
     * @param input the input string
     * @return the modified string
     */
    public static String replaceLineBreaks(String input) {
        if (input.contains("\r")) {
            input = input.replaceAll("\\r", " ");
        } else if (input.contains("\n")) {
            input = input.replaceAll("\\n", " ");
        } else if (input.contains("\r\n")) {
            input = input.replaceAll("\\r\\n", " ");
        }
        return input;
    }

    /**
     * <p>Find the Levenshtein distance between two Strings.</p>
     * <p/>
     * <p>This is the number of changes needed to change one String into
     * another, where each change is a single character modification (deletion,
     * insertion or substitution).</p>
     * <p/>
     * <p>The previous implementation of the Levenshtein distance algorithm
     * was from <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
     * <p/>
     * <p>Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError
     * which can occur when my Java implementation is used with very large strings.<br>
     * This implementation of the Levenshtein distance algorithm
     * is from <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a></p>
     * <p/>
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance("","")               = 0
     * StringUtils.getLevenshteinDistance("","a")              = 1
     * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
     * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
     * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
     * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
     * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
     * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
     * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
     * </pre>
     *
     * @param s the first String, must not be null
     * @param t the second String, must not be null
     * @return result distance
     * @throws IllegalArgumentException if either String input <code>null</code>
     */
    public static int getLevenshteinDistance(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        /*
         The difference between this impl. and the previous is that, rather
         than creating and retaining a matrix of size s.length()+1 by t.length()+1,
         we maintain two single-dimensional arrays of length s.length()+1.  The first, costArray,
         is the 'current working' distance array that maintains the newest distance cost
         counts as we iterate through the characters of String s.  Each time we increment
         the index of String t we are comparing, costArray is copied to previousCostArray, the second int[].  Doing so
         allows us to retain the previous cost counts as required by the algorithm (taking
         the minimum of the cost count to the left, up one, and diagonally up and to the left
         of the current cost count being calculated).  (Note that the arrays aren't really
         copied anymore, just switched...this is clearly much better than cloning an array
         or doing a System.arraycopy() each time  through the outer loop.)

         Effectively, the difference between the two implementations is this one does not
         cause an out of memory condition when calculating the LD over two very large strings.
        */

        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            String tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int[] previousCostArray = new int[n + 1]; //'previous' cost array, horizontally
        int[] costArray = new int[n + 1]; // cost array, horizontally
        int[] placeHolder; //placeholder to assist in swapping previousCostArray and costArray

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char jthCharOfT; // jth character of t

        int cost; // cost

        for (i = 0; i <= n; i++) {
            previousCostArray[i] = i;
        }

        for (j = 1; j <= m; j++) {
            jthCharOfT = t.charAt(j - 1);
            costArray[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == jthCharOfT ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                costArray[i] = Math.min(Math.min(costArray[i - 1] + 1, previousCostArray[i] + 1),
                        previousCostArray[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            placeHolder = previousCostArray;
            previousCostArray = costArray;
            costArray = placeHolder;
        }

        // our last action in the above loop was to switch costArray and previousCostArray, so previousCostArray now
        // actually has the most recent cost counts
        return previousCostArray[n];
    }

    /**
     * Checks if the given email is a valid email or not.
     *
     * @param email the input email id
     * @return {@code true} if given email is valid, {@code false} otherwise
     */
    public static boolean isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    /**
     * Trims all the spaces from the beginning and the end of the string. Handles null and empty strings.
     *
     * @param str the input string
     * @return the trimmed string
     */
    public static String trim(String str) {
        if (isNotEmpty(str)) {
            return str.trim();
        }
        return "";
    }

    /**
     * Generates a random number string of maximum {@code digCount} digits.
     *
     * @param digCount the maximum number of digits
     * @return a random number string
     */
    public static String getRandomNumber(int digCount) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++) {
            sb.append((char) ('0' + rnd.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * Splits the text by the given regex and return the item in specified position.
     *
     * @param text     the input text
     * @param regex    the regex used for splitting the text
     * @param position the position for which the string is needed
     * @return if available, the string at the given {@code position} after splitting. Otherwise, an empty string
     */
    public static String getSplitItem(String text, String regex, int position) {
        String[] items = StringUtil.isNotEmpty(text) ? text.split(regex) : null;
        return items != null && items.length > position ? items[position] : "";
    }

    public static boolean isEmptyOrUnknown(String s) {
        return isEmpty(s) || "unknown".equalsIgnoreCase(s);
    }

    // Making this method private as it's use is not clear and could not add javadoc for it.
    private static String[] splitString(String s, int blockSize, int maxBlocks) {
        String[] res = new String[maxBlocks];

        int mod = s.length() / blockSize;
        int rem = s.length() % blockSize;

        for (int i = 0; i < maxBlocks; i++) {
            res[i] = "";
            if (mod < i) {
                continue;
            }
            if (mod == i || (mod == i + 1 && rem == 0)) {
                res[i] = s.substring(i * blockSize);
            } else {
                res[i] = s.substring(i * blockSize, (i + 1) * blockSize);
            }
        }

        return res;
    }

    /**
     * Cleans the given mobile number string and returns the last ten characters of the string.
     *
     * @param mobile the input obile number
     * @return the cleaned up mobile number string
     */
    public static String cleanMobileNo(String mobile) {
        mobile = cleanupFullStopAndSpaces(mobile);
        if (isNotEmpty(mobile) && mobile.length() > 10) {
            return mobile.substring(mobile.length() - 10);
        }
        return mobile;
    }

    private static String cleanupFullStopAndSpaces(String input) {
        if (StringUtil.isNotEmpty(input)) {
            input = input.replaceAll(" ?([,.])", "$1 ");
            return input.replaceAll("\\s{2,}", " ");
        }
        return null;
    }

    /**
     * This method truncates address if length greater than max length by removing chars after last suitable comma or
     * space.
     *
     * @param address   the input address string
     * @param maxLength maximum length of the address needed
     * @return the truncated address
     */
    public static String truncateAddress(String address, int maxLength) {
        if (address.length() > maxLength) {
            String truncatedAddress = address.substring(0, maxLength);
            int commaIdx = truncatedAddress.lastIndexOf(',');
            int spaceIdx = truncatedAddress.lastIndexOf(' ');
            int splitIdx = commaIdx > 0 ? commaIdx : spaceIdx;
            address = truncatedAddress.substring(0, splitIdx);
        }
        return address;
    }

    public static void main(String[] args) {
        System.out.println(cleanupFullStopAndSpaces("00    13    , 4. "));
    }
}
