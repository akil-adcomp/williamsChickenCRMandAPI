/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.test;

import cc.altius.utils.DateUtils;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author altius
 */
public class NewClass {

    public static void main(String[] args) {
//        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, 6);
        System.out.println("time == "+c.getTime());
    }
}
