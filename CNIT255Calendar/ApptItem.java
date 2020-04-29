package CNIT255Calendar;


public class ApptItem {
    //////////////////////////////////////////////////////////////////
    //**** Private classes ***
    
        String title;
        String day;
        String month;
        String year;
        
        ApptItem(String t, String d, String m, String y)
        {
            title = new String(t);
            day = new String(d);
            month = new String(m);
            year = new String(y);
            
        }
    
}
