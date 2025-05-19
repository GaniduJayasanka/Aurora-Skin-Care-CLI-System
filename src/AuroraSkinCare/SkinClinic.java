package AuroraSkinCare;

import java.util.ArrayList;
import java.util.List;

// Represents the Aurora Skin Care Clinic, Managing appointments, dermatologists,and clinic information, including the clinic name, location, and available doctors. 
// The clinic also provides methods for adding, updating, and searching appointments.
public class SkinClinic {
    private String clinicName; // Clinic name
    private String location; // Clinic Location
    private List<Appointment> appointments; // List of store all clinic appointments
    private List<Dermatologist> availableDoctors; // List to store available dermatologists
    private int appointmentCounter = 1; // Counter to generate unique appointment IDS
        
    // Color Codes for text color and format
    public static final String ANSI_BG_WHITE_color = "\033[47m";
    public static final String Bold = "\033[1m";
    public static final String ANSI_BLUE_color = "\033[34m";
    public static final String reset = "\u001B[0m";
    
    // Constructor that accepts clinicName and location
    public SkinClinic(String clinicName, String location) {
        this.clinicName = clinicName;
        this.location = location;
        this.appointments = new ArrayList<>();
        this.availableDoctors = new ArrayList<>();
           
        // Adding dermatologists to the list with their respective schedules
        List<String> doctor1Days = List.of("Monday", "Wednesday");
        Dermatologist doctor1 = new Dermatologist("Dr. Chandana", doctor1Days);

        List<String> doctor2Days = List.of("Friday", "Saturday");
        Dermatologist doctor2 = new Dermatologist("Dr. Samarasinghe", doctor2Days);

        availableDoctors.add(doctor1);
        availableDoctors.add(doctor2);
    }
  
    // Getter methods for clinic name and location
    public String getClinicName() {
        return clinicName;
    }

    public String getLocation() {
        return location;
    }

 // Method to generate the next appointment ID
    public int getNextAppointmentID() {
        return appointmentCounter++;
    }
    
    // Methods for managing appointments and doctors (implementation based on your requirements)
    public List<Appointment> getAppointmentsByDate(String date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                appointmentsOnDate.add(appointment);
            }
        }
        return appointmentsOnDate;
    }

    // Adds a new appointment to the clinic's appointment list.
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Displays and retrives the list of available dermatologist along with their schedules
    public List<Dermatologist> getAvailableDoctors() {
    	System.out.println("");
		System.out.println(ANSI_BG_WHITE_color + ANSI_BLUE_color + Bold+ "[Dr. Chandana-------Available on Monday & Wednesday]"+reset);
		System.out.println(ANSI_BG_WHITE_color + ANSI_BLUE_color + Bold+ "[Dr. Samarasinghe----Available on Friday & Saturday]"+reset);
		System.out.println("");
        return availableDoctors;
    }

    // Updates an appointment's date time based on its unique ID
    public void updateAppointment(int appointmentId, String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentId) {
                appointment.setDate(date);
                appointment.setTime(time);
                break;
            }
        }
    }

    // Searches for an appointment based on the patient's name or appointment ID
    public Appointment searchAppointment(String searchTerm) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(searchTerm) ||
                String.valueOf(appointment.getAppointmentID()).equals(searchTerm)) {
                return appointment;
            }
        }
        return null;
    }

	 // Method to check if an appointment exists by appointment ID
    public boolean doesAppointmentExist(int appointmentID ) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                return true;
            }
        }
        return false;
    }

    public Dermatologist getDermatologistByAppointmentId(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentId) {
                return appointment.getDermatologist(); // Return associated dermatologist
            }
        }
        return null; // Return null if appointment ID not found
    }
}
