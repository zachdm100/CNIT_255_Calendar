package CNIT255Calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/*
CalendarSetup creates a regular calendar and gregorian calendar (to check for leap year). Also has setters and getters
for the current days, month, and year of the regular calendar.
*/
public class CalendarSetup {
    public GregorianCalendar gCal = (GregorianCalendar) GregorianCalendar.getInstance(); //Gregorian calendar to check leap year
    public Calendar regCal = Calendar.getInstance(); // Main calendar used throughout the program
    // Month setup
    private String currMonth = regCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    public String getMonth() { return currMonth; } // Month Getter
    public void setMonth(String currMonth) { this.currMonth = currMonth; } // Month Setter
    // Year setup
    private int currYear  = regCal.get(Calendar.YEAR); // Current year
    public int getYear() { return currYear; } // Year Getter
    public void setYear(int currYear) { this.currYear = currYear; } // Year Setter
    // Days setup
    private int currDays = 0;
    public int getDays() { return currDays; } // Day Getter
    public void setDays(int currDays) { this.currDays = currDays; } // Day Setter
}
