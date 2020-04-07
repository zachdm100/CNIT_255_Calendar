package CNIT255Calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// Custom interface to align first day of the month to the correct day of the week
interface DisplayData {
    public int getEmptyDays();
}

/*
UpdateCalendar is used to update the main calendar. Adjusts month and year when GUI interactions happen. Also finds the
correct days in each month and what day of the week each month starts with. Inherits CalendarSetup and implements
DisplayData.
*/
public class UpdateCalendar extends CalendarSetup implements DisplayData {
    // Method to get the dates of the month (28 - 31)
    public int getDates(String month, int year) {
        int days = 0;
        if (month.matches("January|March|May|July|August|October|December")) { days = 31; } // Use regex to match month
        else if (month.matches("April|June|Septemeber|November")) { days = 30; }
        else { if (gCal.isLeapYear(year)) { days = 29; } else { days = 28; } }
        return days;
    }

    // Input month and year and return first day of the month as an integer
    public int getDayOfWeek (String month, int year) {
        int dayOfWeek = 0;
        try { // Try catch block for potential parse errors
            String dateString =  month + " 01 " + year; // Create custom date string to convert to real date object
            System.out.println(dateString); // print statement to show current month/year
            SimpleDateFormat localDateFormat = new SimpleDateFormat("MMMM dd yyyy"); // Setup a date object in the correct format
            Date formattedDate = localDateFormat.parse(dateString); // Create real date object from custom date string
            //System.out.println(formattedDate);
            String dayOfWeekStr = String.valueOf(formattedDate).replaceAll("(\\S*).*", "$1"); // use regex to return only shorthand month
            switch (dayOfWeekStr) { // Switch case to match each day of week to an integer value (Sun = 0)
                case "Mon":
                    dayOfWeek = 1;
                    break;
                case "Tue":
                    dayOfWeek = 2;
                    break;
                case "Wed":
                    dayOfWeek = 3;
                    break;
                case "Thu":
                    dayOfWeek = 4;
                    break;
                case "Fri":
                    dayOfWeek = 5;
                    break;
                case "Sat":
                    dayOfWeek = 6;
                    break;
                default:
                    dayOfWeek = 0;
            }
        }
        catch (Exception e) { System.out.println("Get first day of week failed"); }
        return dayOfWeek; // return new integer value corresponding to day of the week
    }

    // Method to change the current month on the main calendar
    public void changeMonth(String direction) {
        setDays(getDates(getMonth(), getYear())); // Set days for current month/year
        if (direction.equals("RIGHT")) { regCal.add(Calendar.MONTH, 1); } // Increment month if right button clicked
        if (direction.equals("LEFT")) { regCal.add(Calendar.MONTH, -1); } // Decrement month if left button clicked
        setMonth(regCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())); // Set current month
        setYear(regCal.get(Calendar.YEAR)); // Set current year
        //System.out.println(getMonth());
    }

    // Method to change the current year on the main calendar
    public void changeYear(String direction) {
        setDays(getDates(getMonth(), getYear())); // Set days for current month/year
        if (direction.equals("RIGHT")) { regCal.add(Calendar.YEAR, 1); } // Increment year if right button clicked
        if (direction.equals("LEFT")) { regCal.add(Calendar.YEAR, -1); } // Decrement year if left button clicked
        setMonth(regCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())); // Set current month
        setYear(regCal.get(Calendar.YEAR)); // Set current year
    }

    // Implement interface to produce empty labels on GUI to match first day of month with correct day of the week
    public int getEmptyDays() {
        setDays(getDates(getMonth(), getYear())); // Set days for current month/year
        return getDayOfWeek(getMonth(), getYear()); // Return integer value of day of the week
    }
}
