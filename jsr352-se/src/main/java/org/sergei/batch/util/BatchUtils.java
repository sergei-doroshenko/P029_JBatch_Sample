package org.sergei.batch.util;

import java.text.SimpleDateFormat;

/**
 * Created by Sergei_Doroshenko on 11/7/2016.
 */
public class BatchUtils {
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm:ss:SSS");
    public static String SEPARATOR = "******************************************************************";
    public static String STR_FORMAT = "**%30s: %-30s**\n";
    public static String NUM_FORMAT = "**%30s: %-30d**\n";
}
