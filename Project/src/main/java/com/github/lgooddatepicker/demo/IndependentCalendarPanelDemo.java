package com.github.lgooddatepicker.demo;

import com.github.lgooddatepicker.datepicker.DatePickerSettings;
import com.github.lgooddatepicker.calendarpanel.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.DateSelectionListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.DateSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.InternalUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * IndependentCalendarPanelDemo,
 *
 * Programmers who only wish to create a DatePicker, a TimePicker, or a DateTimePicker, should not
 * use this demo. See the "BasicDemo" or "FullDemo" classes instead.
 *
 * The CalendarPanel class is normally created (automatically), and used by the DatePicker
 * component. This demo shows how to instantiate a CalendarPanel as an -independent component-. This
 * demo only applies to programmers with specialized requirements. The majority of programmers would
 * never need to directly instantiate the CalendarPanel class.
 *
 * Note: Using the CalendarPanel class is not required to create a DatePicker or a DateTimePicker.
 * The DatePicker component automatically creates its own CalendarPanel instances whenever they are
 * needed.
 */
public class IndependentCalendarPanelDemo {

    /**
     * frame, This is our main frame.
     */
    static JFrame frame = new JFrame();

    /**
     * informationLabel, This is a label for displaying messages to the user.
     */
    static JLabel informationLabel = new JLabel();

    /**
     * container, This is a container panel for laying out the components.
     */
    static JPanel container = new JPanel();

    /**
     * main, This is the entry point for the demo.
     */
    public static void main(String[] args) {
        // Initialize the form components.
        initializeComponents();

        // Create a calendar panel.
        DatePickerSettings settings = new DatePickerSettings();
        CalendarPanel calendarPanel = new CalendarPanel(settings);

        // Add a date selection listener to the CalendarPanel.
        calendarPanel.addDateSelectionListener(new SampleDateSelectionListener());

        // Add the CalendarPanel to the form.
        container.add(calendarPanel);

        // Display the frame.
        frame.setVisible(true);
    }

    /**
     * initializeComponents, This will set up our form, panels, and the layout managers. Most of
     * this code only exists to make the demo program look pretty.
     */
    private static void initializeComponents() {
        // Set up our main frame.
        frame.setTitle("LGoodDatePicker Independent Calendar Panel Demo "
                + InternalUtilities.getProjectVersionString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set up our panels and layouts. 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(mainPanel);
        container.setLayout(new GridBagLayout());
        mainPanel.add(informationLabel);
        mainPanel.add(new JLabel(" "));
        mainPanel.add(new JLabel(" "));
        mainPanel.add(container);

        // Set up an information label to display the selected date.
        informationLabel.setOpaque(true);
        informationLabel.setBackground(Color.white);
        informationLabel.setBorder(new CompoundBorder(
                new LineBorder(Color.black), new EmptyBorder(2, 4, 2, 4)));
        informationLabel.setText("The selected date will be displayed here.");
        informationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pack and resize the frame, and center it on the screen.
        frame.pack();
        frame.validate();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
    }

    /**
     * SampleDateSelectionListener, A date selection listener provides a way for a class to receive
     * notifications whenever a date has been selected in an independent CalendarPanel.
     */
    private static class SampleDateSelectionListener implements DateSelectionListener {

        /**
         * dateSelected, This function will be called each time that a date is selected in the
         * applicable CalendarPanel. The new and old selected dates are supplied in the event
         * object. These parameters may contain null, which represents a cleared or empty date.
         */
        @Override
        public void dateSelected(DateSelectionEvent event) {
            LocalDate oldDate = event.getOldDate();
            LocalDate newDate = event.getNewDate();
            String oldDateString = PickerUtilities.localDateToString(oldDate, "(null)");
            String newDateString = PickerUtilities.localDateToString(newDate, "(null)");
            String messageStart = "The selected date has changed from: ";
            String fullMessage = messageStart + oldDateString + " to: " + newDateString + ".";
            informationLabel.setText(fullMessage);
        }
    }
}
