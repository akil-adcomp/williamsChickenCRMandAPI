/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Akil Mahimwala
 */
public class LogUtils {

    @Autowired
    public static Logger systemLogger = Logger.getLogger("systemLogging");
    public static Logger debugLogger = Logger.getLogger("debugLogging");

    public static String buildStringForSystemLog(Exception e) {
        StringWriter sWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(sWriter));
        return (new StringBuilder().append(sWriter.toString()).toString());
    }

    public static String buildStringForSystemLog(String sqlString, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder().append(" -- ").append(sqlString).append(" -- parameters(");
        boolean firstRun = true;
        for (Map.Entry tmpEntry : params.entrySet()) {
            if (firstRun) {
                firstRun = false;
                sb.append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            } else {
                sb.append(", ").append(tmpEntry.getKey()).append(":").append(tmpEntry.getValue());
            }
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String sqlString, Object[] params) {
        StringBuilder sb = new StringBuilder().append(") -- ").append(sqlString).append(" -- parameters(");
        boolean firstRun = true;
        for (Object tmpParam : params) {
            if (firstRun) {
                firstRun = false;
                sb.append(tmpParam);
            } else {
                sb.append(", ").append(tmpParam);
            }
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String sqlString, List<Object[]> paramList) {
        StringBuilder sb = new StringBuilder().append(sqlString).append(" -- parameters( ");
        boolean firstRun = true;
        for (Object params[] : paramList) {
            sb.append(" (");
            firstRun = true;
            for (Object tmpParam : params) {
                if (firstRun) {
                    firstRun = false;
                    sb.append(tmpParam);
                } else {
                    sb.append(", ").append(tmpParam);
                }
            }
            sb.append(")");
        }
        sb.append(")");
        return (sb.toString());
    }

    public static String buildStringForSystemLog(String msg) {
        StringBuilder sb = new StringBuilder().append(msg);
        return sb.toString();
    }
}
