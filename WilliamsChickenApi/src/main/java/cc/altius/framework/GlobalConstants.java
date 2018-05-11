/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.framework;

/**
 *
 * @author shrutika
 */
public class GlobalConstants {

    /**
     * sets the value for array of ALLOWED_IP_RANGE={"127.0.0.1",
     * "10.1.2.1-10.1.2.254","10.1.3.1-10.1.3.254"}
     *
     */
    public static String[] ALLOWED_IP_RANGE = new String[]{"127.0.0.1", "10.1.2.1-10.1.2.254", "10.1.3.1-10.1.3.254"};
    public static int SMS_TEMPLATE_ID_FOR_OTP = 1;
    public static int SMS_TEMPLATE_ID_FOR_PASSWORD = 2;
    public static final String version = "v1";
    public static String LOG_TAG_SYSTEM;
}
