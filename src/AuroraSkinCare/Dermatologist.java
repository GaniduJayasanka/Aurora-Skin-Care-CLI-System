package AuroraSkinCare;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

// The Dermatologist class manages the schedule and appointments for a specific dermatologist
public class Dermatologist {
    private String name;
    private List<String> schedule;  // List of available slots (days and times)
    private List<Appointment> appointments; // List of scheduled appointments
    private List<String> assignedDays; // Days assigned to this dermatologist (ex:- Monday, Wednesday)

    private Map<String, Boolean> schedule1 = new HashMap<>(); // Alternative schedule map
    
     // Color Codes for text color and format
    public static final String BG_GREEN_color = "\033[42m";
    public static final String BRIGHT_WHITE_color ="\033[97m";   // WHITE
    public static final String Bold = "\033[1m"; 
    public static final String BRIGHT_RED_color = "\033[91m";
        
    // Constructs a new Dermatologist instance with a name and assigned work days.
    public Dermatologist(String name, List<String> assignedDays) {
        this.name = name;
        this.schedule = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.assignedDays = assignedDays;
        populateSchedule(assignedDays);  // Initialize schedule based on assigned days
    }

    // Initializes the schedule with time slots based on assigned days.
    private void populateSchedule(List<String> assignedDays) {
        for (String day : assignedDays) {
            switch (day) {
                case "Monday":
                    addTimeSlots(day, LocalTime.of(10, 0), LocalTime.of(13, 0));
                    break;
                case "Wednesday":
                    addTimeSlots(day, LocalTime.of(14, 0), LocalTime.of(17, 0));
                    break;
                case "Friday":
                    addTimeSlots(day, LocalTime.of(16, 0), LocalTime.of(20, 0));
                    break;
                case "Saturday":
                    addTimeSlots(day, LocalTime.of(9, 0), LocalTime.of(13, 0));
                    break;
                default:
                    break;
            }
        }
    }
    
    // Adds 15 minute intervals to the schedule between a specified start and end time.
    private void addTimeSlots(String day, LocalTime startTime, LocalTime endTime) {
        while (startTime.isBefore(endTime)) {
            String slot = day + " " + startTime.toString();
            schedule.add(slot);
            startTime = startTime.plusMinutes(15);  // Increment by 15 minutes
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getSchedule() {
        return schedule;
    }

 // Method to Checks the availability of a slot based on the provided date and time
    public boolean isAvailable(String date, String time) throws Exception {
        	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date parsedDate = dateFormat.parse(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String day = dayFormat.format(parsedDate); // Get day from date

        // Check if the selected day is valid
        if (!assignedDays.contains(day)) {
            System.out.println(BRIGHT_RED_color + Bold+"Invalid day. The appointment can only be scheduled on: "+ BRIGHT_WHITE_color+ assignedDays);
            return false;
        }

        // Create the slot to check
        String slotToCheck = day + " " + time;

        // Check if the slot is free in the schedule
        if (!schedule.contains(slotToCheck)) {
            System.out.println(BRIGHT_RED_color+ Bold+"The selected time slot is not available.");
            return false;
        }

        // Check if there are existing appointments for the same date and time
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) && appointment.getTime().equals(time)) {
                System.out.println(BRIGHT_RED_color + Bold+"The selected date and time are already booked.");
                return false; // Slot is not available
            }
        }
        return true; 
    }

   // Schedules a session for a patient and removes the booked slot from the schedule.
    public void scheduleSession(int appointmentID, Patient patient, String date, String time) {
        try {
            // Use the correct DateTimeFormatter to parse the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate currentDate = LocalDate.parse(date, formatter); // Parsing date with the specified format

            // Schedule the appointment for the current date and time
            Appointment appointment = new Appointment(appointmentID, patient, this, date, time);
            appointments.add(appointment);
            
            // Extract day from the date string and remove the booked slot
            String day = getDayFromDate(date); 
            String slotToRemove = day + " " + time;
            schedule.remove(slotToRemove); 

            // Calculate the next week's date (same day and time)
            LocalDate nextWeekDate = currentDate.plusWeeks(1);
            String nextWeekDay = getDayFromDate(nextWeekDate.format(formatter));
            String nextWeekSlot = nextWeekDay + " " + time;

            // Check if the next week's slot is available
            if (!schedule.contains(nextWeekSlot)) {
                
                schedule.add(nextWeekSlot); // Change this line to add the next week's slot
           
            } else {
                System.out.println("The same time slot for next week is not available.");
            }
        } catch (DateTimeParseException e) {
            System.out.println(BRIGHT_RED_color + Bold+"Invalid date format. Please use 'yyyy/MM/dd'.");
        }
    }

    // Extracts and returns the day of the week from a date string.
    private String getDayFromDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return parsedDate.getDayOfWeek().name(); 
        } catch (DateTimeParseException e) {
            return "";
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    // Displays the available slots, with a bertical gap between different days.
    public void displayAvailableSlots() {
        String previousDay = ""; 
        for (String slot : schedule) {
           
            String currentDay = slot.split(" ")[0];
            if (!currentDay.equals(previousDay)) {
                if (!previousDay.isEmpty()) {
                    System.out.println(); 
                }
                previousDay = currentDay;
            }
            System.out.println(slot); 
        }
    }

}

