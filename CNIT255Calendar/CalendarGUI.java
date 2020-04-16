package CNIT255Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Class for the calendar GUI. Uses CalendarSetup to get initial Calendar data and CalendarUpdate to get current month/year
when user interacts with month or year buttons.
*/
public class CalendarGUI extends Frame {

    // Create new instances of calendars
    CalendarSetup calSetup = new CalendarSetup();       // New implementation to setup initial calendar in GUI
    UpdateCalendar calUpdate = new UpdateCalendar();    // New implementation to maintain and update calendar in GUI

    // Values of starting month and year
    String startingMonth = calSetup.getMonth();
    int startingYear = calSetup.getYear();

    // Create dynamic elements of GUI (Outside of constructor scope to allow access to other methods)
    JLabel lblMonth = new JLabel(calSetup.getMonth(), SwingConstants.CENTER);      // Create Month Label
    JLabel lblYear = new JLabel(String.valueOf(calSetup.getYear()));               // Create Year Label
    JPanel calDates =  new JPanel(new GridLayout(0,7,3,3)); // Create dates of current month/year

    // GUI Constructor (Initialize and modify all GUI elements)
    public CalendarGUI() {
        // Build Root Window
        JFrame frame = new JFrame("Calendar"); // Main frame where main panels are added to
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Make window not resizeable to keep display constant
        frame.setSize(600, 290);
        frame.setLayout(new BorderLayout());

        // Create Menu Bar
        JMenuBar menuBar = new JMenuBar();
        frame.add(menuBar, BorderLayout.NORTH); // add menu bar to frame
        // Create MenuBar Components
        JMenu File = new JMenu("File"); // File menu item
        JMenu Help = new JMenu("Help"); // Help menu item
        JMenuItem Quit = new JMenuItem("Quit"); // Quit menu item
        JMenuItem About = new JMenuItem("About"); // About menu item
        // Add Components to MenuBar panel
        menuBar.add(File);
        menuBar.add(Help);
        Help.add(About);
        File.add(Quit);

        // Create Top Panel
        JPanel TopInfo = new JPanel(new BorderLayout());
        frame.add(TopInfo, BorderLayout.CENTER); // add Top info to frame
        JPanel MonAndYear = new JPanel(new FlowLayout()); // Panel to display month, year, and arrow buttons
        MonAndYear.setBackground(Color.red);
        TopInfo.add(MonAndYear, BorderLayout.NORTH);
        JPanel daysOfWeek = new JPanel(new GridLayout(0, 7, 3, 0)); // Panel grid for the days of the week
        TopInfo.add(daysOfWeek, BorderLayout.CENTER);
        // Create Top Panel Components
        JButton monthLeft = new JButton("<"); // Month Left Arrow
        formatArrowButton(monthLeft); // Set button size, text, color, etc...
        JButton monthRight = new JButton(">"); // Month Right Arrow
        formatArrowButton(monthRight);
        lblMonth.setFont(new Font("ARIAL", Font.PLAIN, 18)); // Increase month font size
        lblMonth.setForeground(Color.white);
        lblMonth.setPreferredSize(new Dimension(90, 30)); // Increase label size so buttons dont move when month changes
        JButton yearLeft = new JButton("<"); // Year Left Arrow
        formatArrowButton(yearLeft);
        JButton yearRight = new JButton(">"); // Year Right Arrow
        formatArrowButton(yearRight);
        lblYear.setFont(new Font("ARIAL", Font.PLAIN, 18)); // Increase year font size
        lblYear.setForeground(Color.white);
        JLabel emptySpace = new JLabel(); // Empty label to create space between month and year
        emptySpace.setPreferredSize(new Dimension(100, 15)); // Increase distance between month and year
        JLabel lblSun = new JLabel("Sun", SwingConstants.CENTER); // Centered label for each day of week
        JLabel lblMon = new JLabel("Mon", SwingConstants.CENTER);
        JLabel lblTue = new JLabel("Tue", SwingConstants.CENTER);
        JLabel lblWed = new JLabel("Wed", SwingConstants.CENTER);
        JLabel lblThu = new JLabel("Thu", SwingConstants.CENTER);
        JLabel lblFri = new JLabel("Fri", SwingConstants.CENTER);
        JLabel lblSat = new JLabel("Sat", SwingConstants.CENTER);
        // Add Components to Top Panel
        MonAndYear.add(monthLeft);
        MonAndYear.add(lblMonth);
        MonAndYear.add(monthRight);
        MonAndYear.add(emptySpace);
        MonAndYear.add(yearLeft);
        MonAndYear.add(lblYear);
        MonAndYear.add(yearRight);
        daysOfWeek.add(lblSun);
        daysOfWeek.add(lblMon);
        daysOfWeek.add(lblTue);
        daysOfWeek.add(lblWed);
        daysOfWeek.add(lblThu);
        daysOfWeek.add(lblFri);
        daysOfWeek.add(lblSat);

        // Create Dates Panel
        frame.add(calDates, BorderLayout.SOUTH);
        // Add Components to Dates Panel
        setDaysOfMonth(calUpdate.getEmptyDays());

        // Action listener for About button to display data about the calendar
        About.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"This is a basic calendar app");
            }});
        // Action listener for Quit button to quit the GUI
        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
        // Action listener for monthRight button to increment the month
        monthRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calUpdate.changeMonth("RIGHT"); // Call changeMonth() from calUpdate with button direction
                setDaysOfMonth(calUpdate.getEmptyDays()); // Set the days of the month for the current month/year
                setMonthYear(); // Set current month and year
            }});
        // Action listener for monthLeft button to decrement the month
        monthLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calUpdate.changeMonth("LEFT");
                setDaysOfMonth(calUpdate.getEmptyDays());
                setMonthYear();
            }});
        // Action listener for YearRight button to increment the year
        yearRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calUpdate.changeYear("RIGHT"); // Call changeMonth() from calUpdate with button direction
                setDaysOfMonth(calUpdate.getEmptyDays()); // Set the days of the month for the current month/year
                setMonthYear(); // Set current month and year
            }});
        // Action listener for YearLeft button to decrement the year
        yearLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calUpdate.changeYear("LEFT");
                setDaysOfMonth(calUpdate.getEmptyDays());
                setMonthYear();
            }});

        frame.setVisible(true); // Make the frame visible
    }

    // Method to format each arrow button to a certain size, color, font, etc...
    public void formatArrowButton(JButton button) {
        button.setBackground(Color.red); // Set background color
        button.setForeground(Color.white); // Set text color
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        button.setMargin(new Insets(0, 0, 0, 0)); // Reset margins to allow for reduced size
        button.setPreferredSize(new Dimension(15, 15)); // Set size
        button.setFont(new Font("CALIBRE", Font.BOLD, 18)); // Increase button font size
    }

    // Method to set the current days of the month
    public void setDaysOfMonth(int emptyDays) {
        calDates.removeAll(); // Clear dates panel
        calDates.revalidate();
        calDates.repaint();
        JLabel[] emptyDates = new JLabel[7]; // Create an array of labels for empty dates (invisible)
        for (int x = 0; x < emptyDays; x++) { // Create a new empty label for length of emptyDays
            emptyDates[x] = new JLabel();
            calDates.add(emptyDates[x]); // Add the label to the dates panel
        }
        JButton[] buttons = new JButton[31]; // Create an array of buttons for the actual dates (visible)
        for (int x = 0; x < calUpdate.getDays(); x++) { // Create a grid of buttons for the length of the month
            buttons[x] = new JButton(String.valueOf(x + 1)); // Start button dates at 1 instead of 0
            calDates.add(buttons[x]); // Add the button to the dates panel
        }
        // Highlight the current day
        if (calUpdate.getMonth().equals(startingMonth) && calUpdate.getYear() == startingYear) { highlightCurrentDay(buttons); }
    }

    // Method to set the current month and year of the main calendar
    public void setMonthYear() {
        lblMonth.setText(calUpdate.getMonth()); // Set text of month label to current month
        lblYear.setText(String.valueOf(calUpdate.getYear())); // Set text of year label to current year
    }

    // Method for finding the current date time and highlighting the current day
    public void highlightCurrentDay(JButton[] dates) {
        String currTime = String.valueOf(calSetup.regCal.getTime()); // convert current date to string
        String currDay = currTime.replaceAll(".* (\\d+) .*", "$1"); // regex to find only day of month
        int currCalDay = Integer.parseInt(currDay) - 1; // subtract 1 for array value to match actual day
        dates[currCalDay].setBackground(Color.RED); // set background color
        dates[currCalDay].setForeground(Color.white); // Set text color
        //System.out.println(currDay);
    }

    // RUN MAIN
    public static void main (String[] args)
    {
        new CalendarGUI(); // Create GUI object
    }
}
