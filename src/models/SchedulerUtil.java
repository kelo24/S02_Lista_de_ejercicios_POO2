package models;

import java.util.Calendar;
import java.util.Date;


public class SchedulerUtil {
    
    // Methods
    /* Converts a string in a {@link Date} */
    public static Date getDateFromString(String dateStr) {
        
        Calendar date = Calendar.getInstance();
        
        // mm/dd/yyyy
        // 0123456789 }-> index
        date.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(6,10)));
        date.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(3,5)));
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(0,2)));
        
        return date.getTime();
    }
    
}
