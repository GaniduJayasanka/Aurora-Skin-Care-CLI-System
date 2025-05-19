package AuroraSkinCare;

import java.util.List;

public class Appointment {
 	
	// Fields for appointment details	
    private int appointmentID; // Unique identifier for the appointment
    private Patient patient;   // Patient associated with the appointment
    private Dermatologist dermatologist; // Dermatologist assigned for the appointment
    private String date; // Appointment date in String format
    private String time; // Appointment time in String format
    private String status; // Status of the appointment (Scheduled, Completed, Cancelled)
    private double registrationFee = 500.00; // Fixed registration fee
    private double treatmentFee = 0.0; // Variable treatment fee based on treatment type
    private double taxRate = 0.025; // Tax rate (2.5%) applied to total fees

    // Color Codes for  Bright Text Colors
    public static final String BRIGHT_RED_Color = "\033[91m"; // RED
    public static final String BRIGHT_GREEN_Color = "\033[92m"; // GREEN
    public static final String BRIGHT_YELLOW_Color = "\033[93m"; // YELLOW
    public static final String BRIGHT_BLUE_Color = "\033[94m"; // BLUE
    public static final String BRIGHT_CYAN_Color = "\033[96m"; // CYAN
    public static final String BRIGHT_MAGENTA_Color = "\033[95m"; // MAGENTA
    public static final String BRIGHT_WHITE_Color = "\033[97m"; // WHITE
    
    // Color Codes for Text Background    
    public static final String BG_BRIGHT_BLUE_Color = "\033[104m"; // BLUE
    public static final String BG_GREEN_Color = "\033[42m"; // GREEN
    
    // Color Codes for Text Style
    public static final String ANSI_BOLD = "\033[1m"; // Bold
    public static final String reset = "\u001B[0m"; //RESET
    

    // Constructor to initialize the appointment with basic details
    public Appointment(int appointmentID,  Patient patient, Dermatologist dermatologist, String date, String time) {
        this.appointmentID = appointmentID;  
    	this.patient = patient;
        this.dermatologist = dermatologist;
        this.date = date; 
        this.time = time;
        this.status = "Scheduled"; // Default status upon creation
    }

    // Getter methods for retrieving appointment details
    public int getAppointmentID() {
          	
    	return appointmentID;
   } 
    public Patient getPatient() {
        return patient;
    }
    public Dermatologist getDermatologist() {
        return dermatologist;
    }
    public String getDate() { 
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }

 // Setters methods for updating appointment details
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Method to Update the appointment details with a new date and time.
    public void updateDetails(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
        this.status = "Updated"; 
    }
    
    // Accept the Registration Fee From the Patientc
    public boolean acceptRegistrationFee() {
        System.out.println(" ");
        System.out.println(BRIGHT_BLUE_Color + "*****************************************************************************");
        System.out.println(BRIGHT_CYAN_Color + ANSI_BOLD + "Registration fee: LKR " + registrationFee);
        String confirmation;

        // Loop until a valid response ("Y" or "N") is entered
        do {
            System.out.print(BRIGHT_GREEN_Color + ANSI_BOLD + "Please confirm payment (Y/N): " + BRIGHT_YELLOW_Color);
            confirmation = System.console().readLine();

            // Validate the input
            if (confirmation.equalsIgnoreCase("Y")) {
                System.out.println(BRIGHT_MAGENTA_Color + ANSI_BOLD + "Payment Accepted. Appointment Confirmed.");
                return true;  // Confirmed, exit method with true
            } else if (confirmation.equalsIgnoreCase("N")) {
                System.out.println(BRIGHT_RED_Color + "Payment not confirmed. Appointment not completed.");
                return false;  // Not confirmed, exit method with false
            } else {
                // Display error message for invalid input
                System.out.println(BRIGHT_RED_Color + "Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }

        } while (!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N")); // Repeat until valid input

        return false;  // Default return in case loop exits unexpectedly
    }

    // Sets the treatment fee based on selected treatment type
    public void setTreatmentFee(Treatment treatment) {
        this.treatmentFee = treatment.getPrice(); // Assuming Treatment class has getPrice method
    }

    // Calculates the total appointment cost, including registration fee, treatment  fee, and tax
    public double calculateTotalFee() {
        double subtotal = registrationFee + treatmentFee;
        double tax = subtotal * taxRate; // Calculate tax
        return Math.round((subtotal + tax) * 100.0) / 100.0; // Rounding to 2 decimal places
    }

    // Generate an invoice for the appointment, displaying fees and total cost 
    public void generateInvoice(Treatment treatment) {
        double totalFee = calculateTotalFee(); // Calculate the total fee
        double subtotal = registrationFee + treatmentFee;
        double tax = subtotal * taxRate; // Calculate tax for the invoice

        // Define the width to ensure proper formatting and alignment
        int width = 70;

        System.out.println(BG_BRIGHT_BLUE_Color + BRIGHT_WHITE_Color + ANSI_BOLD + "################# $ Aurora Skin Care Clinic Invoice $ #################");
        System.out.println(BRIGHT_WHITE_Color + ANSI_BOLD + " ".repeat(width)); 
        System.out.println(centerAlign("Appointment ID: " + appointmentID, width));
        System.out.println(centerAlign("Patient Name: " + patient.getName(), width));
        System.out.println(centerAlign("Dermatologist: " + dermatologist.getName(), width));
        System.out.println(centerAlign("Treatment: " + treatment.getTreatmentName(), width));
        System.out.println(centerAlign("Registration Fee: LKR " + String.format("%.2f", registrationFee), width));
        System.out.println(centerAlign("Treatment Fee: LKR " + String.format("%.2f", treatmentFee), width));
        System.out.println(centerAlign("Tax (2.5%): LKR " + String.format("%.2f", tax), width)); // Use calculated tax
        System.out.println(centerAlign("Total Amount: LKR " + String.format("%.2f", totalFee), width));
        System.out.println(" ".repeat(width)); 
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(reset);
    }

    // Helper method to center-align text within a specified width
    private String centerAlign(String text, int width) {
        int padding = (width - text.length()) / 2;
        String spaces = " ".repeat(Math.max(0, padding));
        return spaces + text + spaces;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID +
                ", Patient: " + patient.getName() +
                ", Dermatologist: " + dermatologist.getName() +
                ", Date: " + date +
                ", Time: " + time +
                ", Status: " + status;
    }	
}
