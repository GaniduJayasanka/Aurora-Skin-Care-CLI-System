package AuroraSkinCare;

import java.util.ArrayList;
import java.util.List;

// Represents a patient in the Aurora Skin Care Clinic System.
public class Patient {
    private String NIC; //National Identity Card number of the patient
    private String name; // Name of the patient
    private String email; // Email address of the patient
    private String phone; // Phone number of the patient
    private List<Appointment> appointments; // List to store appointments associated with patient

// Constructs a new Patient with the specified NIC, name, email, and phone   
    public Patient( String NIC, String name, String email, String phone) {
        
        this.NIC = NIC;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.appointments = new ArrayList<>(); // Initialize the appointments list
    }

    // Getters for patient attributes
    public String getNIC() {
        return NIC;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }

    // Method to Book a new appointment for the patient and adds it to the list of appointments.
    public boolean bookAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment); // Add the appointment to the list
            System.out.println("Appointment booked for " + name + " on " + appointment.getDate() + " at " + appointment.getTime());
            System.out.println("");
            return true;
        }
        System.out.println("Failed to book appointment.");
        return false;
    }

    // Method to View details of appointments  scheduled on a specific date.
    public List<Appointment> viewAppointmentDetails(String date) {
        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments; // Return appointments on that date
    }

    // Method to Searches for an appointment based on the provided appointment ID
    public Appointment searchAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null; // Return null if appointment not found
    }

    //  Method to get all appointments for the patient
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // String representation for printing patient details
    @Override
    public String toString() {
        return ", Name: " + name +
               ", NIC: " + NIC +
               ", Email: " + email +
               ", Phone: " + phone;
    }
}
