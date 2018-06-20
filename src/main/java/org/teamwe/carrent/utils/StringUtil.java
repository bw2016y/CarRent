package org.teamwe.carrent.utils;

/**
 * @author FDws
 * Created on 2018/6/11 23:16
 */

public class StringUtil {
    public static String ILLEGAL_EMAIL = "Illegal email address";
    public static String NO_SUCH_USER = "No such user";
    public static String PARAM_ILLEGAL = "Parameters is illegal";
    public static String ERROR_OCCUR = "Some error occur in server";
    public static String ERROR_PAGE = "This is error page, please close this page and send request again!";
    public static String NO_PERMISSION = "Haven't permission";


    /**
     * Judge if the string is null or empty
     *
     * @param string the origin string
     * @return string is null or empty
     */
    public static boolean nullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    /**
     * Judge is the mail address is legal
     *
     * @param mail mail address
     * @return legal or not
     */
    public static boolean isLegalMail(String mail) {
        String MAIL_PATTERN = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return mail.matches(MAIL_PATTERN);
    }

    /**
     * Print String array
     *
     * @param items items
     */
    public static void print(String... items) {
        System.out.print("Items: [");
        for (String item : items) {
            System.out.print(item + ", ");
        }
        System.out.println("]");
    }
}
