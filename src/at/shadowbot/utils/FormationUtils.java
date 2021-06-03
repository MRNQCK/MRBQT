package at.shadowbot.utils;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

public class FormationUtils {
    public static String translateDate(OffsetDateTime offsetDateTime) {
        Date then = Date.from(offsetDateTime.toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(then);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        then = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        return formatter.format(then);
    }
}
