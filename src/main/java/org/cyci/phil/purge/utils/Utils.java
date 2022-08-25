package org.cyci.phil.purge.utils;

import java.math.BigDecimal;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Tue - 23/Aug/2022 - 11:36 PM
 */
public class Utils {

    // modified from https://stackoverflow.com/a/6118964
    public static String splitToComponentTimes(BigDecimal biggy)
    {
        long longVal = biggy.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        String total = "";
        if (hours > 0) total = total.concat(hours +"hr ");
        if (mins > 0) total = total.concat(mins + "m ");
        if (secs > 0) total = total.concat(secs + "s");

        return total;
    }
}
