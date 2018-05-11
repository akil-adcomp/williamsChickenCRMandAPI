/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.test;

import cc.altius.framework.GlobalConstants;
import cc.altius.utils.IPUtils;


/**
 *
 * @author akil
 */
public class IPTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] allowedIpRangeString = GlobalConstants.ALLOWED_IP_RANGE;
        String ipToCheck = "127.0.0.0";
        for (String curRange : allowedIpRangeString) {
            IPUtils curIpRange = new IPUtils(curRange);
        }
    }
}
