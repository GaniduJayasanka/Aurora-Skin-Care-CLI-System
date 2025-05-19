package AuroraSkinCare;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class UserInterface {

	// SkinClinic instance to manage appointments and dermatologists
    private SkinClinic clinic;
    private Scanner scanner;  // Scanner for reading user inputs from the console
	    
     // Color Codes for Text Colors
    
    public static final String White_Color = "\u001B[37m";
    public static final String Green_Color = "\033[0;32m";
    public static final String Yellow_Color = "\033[0;33m";
    public static final String Blue_Color = "\033[0;34m";
    public static final String cyan_Color = "\033[36m";
    public static final String Magenta_Color = "\033[35m";

    // Color Codes for  Bright Text Colors
    
    public static final String BRIGHT_WHITE_Color = "\033[97m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHTYELLOW = "\033[93m";
    public static final String BRIGHT_BLUE = "\033[94m";
    public static final String ANSI_BRIGHT_MAGENTA = "\033[95m";
    public static final String BRIGHT_CYAN = "\033[96m";

    
    // Color Codes for Text Background
    
    public static final String BLUE_BG = "\u001B[44m";
    public static final String CYAN_BG = "\033[46m";

    // Color Codes for Bright Background Text Colors
    
    public static final String BG_BRIGHT_MAGENTA = "\033[105m";
    public static final String BG_BRIGHT_WHITE = "\033[107m";
    public static final String BG_GREEN = "\033[42m";

   // Color Codes for Text Style
    
    public static final String Bold = "\033[1m";
    public static final String reset = "\u001B[0m";

    
    //Constructor that initialize the UserInerface with a SkinClinic instance
    public UserInterface(SkinClinic clinic) {
        this.clinic = clinic;
        this.scanner = new Scanner(System.in); 
    }
             

    //Starts the main menu loop, allow the user to select option for managing appointments
    public void start() {
        int choice;

        do {
        	
        	// Display welocome banner and main menu options
            System.out.println(BLUE_BG + White_Color + "♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦"+ reset);
            System.out.println(BLUE_BG + White_Color +"♦                                                                                                         ♦"+ reset);
            System.out.println(BLUE_BG + BRIGHT_WHITE_Color + Bold+ "♦                            ♥ WELCOME TO AURORA SKIN CARE APPOINTMENT SYSTEM ♥                           ♦"+ reset);
            System.out.println(BLUE_BG + White_Color +"♦                                                                                                         ♦"+ reset);
            System.out.println(BLUE_BG + White_Color + "♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦"+ reset);
            
            // Display the menu options with consistent background color
            System.out.println( Yellow_Color + Bold );
            System.out.println("                                          1. Book an Appointment                                           ");
            System.out.println("                                          2. Update Appointment Details                                    ");
            System.out.println("                                          3. View Appointments by Date                                     ");
            System.out.println("                                          4. Search Appointment by Name or ID                              ");
            System.out.println("                                          5. Exit                                                          ");

            // Prompt for user input with consistent background
            System.out.println();
            System.out.println("*****************************************************************************");
            System.out.print(BRIGHT_GREEN +  "Please choose an option: " + BRIGHTYELLOW);
            
                    	
        	// Handle user's choice with repective methods
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();
            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    updateAppointment();
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                    searchAppointment();
                    break;
                case 5:
                	System.out.print(BRIGHT_GREEN + "Are you sure you want to quit the system? (Y/N): " + BRIGHTYELLOW);
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("Y")) {
                        System.out.println(BLUE_BG + White_Color + "Thank you for using the system. Goodbye!" );
                        choice = 5; // Exit the loop
                    } else {
                        choice = -1; // Reset to main menu
                        System.out.println(BRIGHT_GREEN + "Returning to the main menu..." );
                        System.out.println("");
                    }
                    break;
                default:
                	System.out.println(BRIGHT_RED + "Invalid choice. Please try again." + reset);
            }
        } while (choice != 5); // Loop untill the user chooses to exit
    }
    
 // Validation methods    
    private boolean validateNIC(String nic) {
        String oldNicPattern = "^[0-9]{9}[VXvx]$";
        String newNicPattern = "^[0-9]{12}$";
        return nic.matches(oldNicPattern) || nic.matches(newNicPattern);
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[a-z]+@(gmail|yahoo|outlook)\\.com$";
        return email.matches(emailPattern);
    }

    private boolean validatePhone(String phone) {
        String phonePattern = "^(070|071|072|074|075|076|077|078|011|037)[0-9]{7}$";
        return phone.matches(phonePattern);
    }
    
    // Initiates the booking of a new appointment by prompting user for details.
    private void bookAppointment() {
    	
    	// Generate the next appointment ID and Display it to the user
        int nextAppointmentId = clinic.getNextAppointmentID();
        System.out.println(BRIGHT_WHITE_Color + "Generated Appointment ID: " + BRIGHTYELLOW + nextAppointmentId );
        System.out.println("");
        
        
        System.out.println(BRIGHT_CYAN + Bold + "---------------Available Dermatologists Consultation Schedules---------------" + BRIGHT_WHITE_Color);
        System.out.println("");
        
       // Display available dermatologists for user selection
        List<Dermatologist> doctors = clinic.getAvailableDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No available dermatologists at the moment.");
            return;
        }

        // Display dermatologist options
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println(BRIGHT_WHITE_Color + Bold+(i + 1) + "." + doctors.get(i).getName()+ BRIGHT_WHITE_Color);
        }

        // Select dermatologist, date, and time for the appointment
        int doctorChoice = selectDoctor(doctors);
        Dermatologist selectedDoctor = doctors.get(doctorChoice - 1);
        
     // Show available time slots before selecting date
        System.out.println("Available Time Slots for "  + selectedDoctor.getName() + ":");
        System.out.println(""  + BG_BRIGHT_MAGENTA + BRIGHT_WHITE_Color + Bold);
        selectedDoctor.displayAvailableSlots();
        System.out.println("" + reset);
        
        
        String date = selectDate();
        String time = selectTime(selectedDoctor, date);

        // Collect patient details and complete booking
        Patient patient = collectPatientDetails();
        Appointment appointment = new Appointment(nextAppointmentId, patient, selectedDoctor, date, time);

        // Process payment and complete the appointment
        if (appointment.acceptRegistrationFee()) {
            selectedDoctor.scheduleSession(nextAppointmentId, patient, date, time);
            clinic.addAppointment(appointment);
            System.out.println("Appointment Booked Successfully!");

            // Select treatment after booking
            selectTreatment(appointment);
        } else {
            System.out.println("Payment failed. Appointment not booked.");
        }
    }

    // Method to select a dermatologist from the list
    private int selectDoctor(List<Dermatologist> doctors) {
        int doctorChoice;
        do {
        	System.out.println("");
            System.out.print(BRIGHT_GREEN + Bold +"Select Dermatologist (1 or 2): "+ BRIGHTYELLOW);
            doctorChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (doctorChoice < 1 || doctorChoice > doctors.size()) {
                System.out.println(BRIGHT_RED + Bold +"Invalid selection. Please select a valid dermatologist.");
            }
        } while (doctorChoice < 1 || doctorChoice > doctors.size());
        
        return doctorChoice;
    }

 // Method to select appointment date
    private String selectDate() {
        String date;
        do {
            System.out.println("");
            System.out.print(BRIGHT_WHITE_Color + Bold + "Enter Appointment Date (yyyy/MM/dd): " + BRIGHTYELLOW);
            date = scanner.nextLine();

            if (!isValidDate(date)) {
                System.out.println(BRIGHT_RED + Bold + "Invalid date format. Please enter a valid date.");
            } else if (!isCurrentOrFutureDate(date)) {
                System.out.println(BRIGHT_RED + Bold + "The date must be today or in the future. Please try again.");
            }
        } while (!isValidDate(date) || !isCurrentOrFutureDate(date)); // Repeat if any validation fails

        return date; // Return the valid date
    }

    // Method to check if the provided date string follows the "yyyy/MM/dd" format
    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true; // Date is valid
        } catch (ParseException e) {
            return false; // Date is not valid
        }
    }

    //Method to Check if the date provided is today or a future date
    private boolean isCurrentOrFutureDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date enteredDate = dateFormat.parse(date);

            // Get today's date with time set to 00:00:00 to compare just the dates
            Calendar currentDate = Calendar.getInstance();
            currentDate.set(Calendar.HOUR_OF_DAY, 0);
            currentDate.set(Calendar.MINUTE, 0);
            currentDate.set(Calendar.SECOND, 0);
            currentDate.set(Calendar.MILLISECOND, 0);

            // Check if the entered date is the same as or after the current date
            return !enteredDate.before(currentDate.getTime());
        } catch (ParseException e) {
            return false; // If parsing fails, return false
        }
    }

    // Method to allow select an appointment time slot for a given dermatologist and date
    private String selectTime(Dermatologist selectedDoctor, String date) {
        String time;
        boolean validSlot;
        Pattern timePattern = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d$"); // Regex for "HH:mm" 24-hour format

        do {
            System.out.println("");
            System.out.print(BRIGHT_WHITE_Color + Bold + "Enter Appointment Time (e.g [ 10:00 - 13:00] 24 hours format): " + BRIGHTYELLOW);
            time = scanner.nextLine();

            // Validate time format
            if (!timePattern.matcher(time).matches()) {
                System.out.println(BRIGHT_RED + Bold + "Invalid time format. Please enter time in 24-hour format (e.g., 10:00).");
                validSlot = false; // Continue loop until a valid format is entered
                continue;
            }

            // Check if the selected time slot is available
            try {
                validSlot = selectedDoctor.isAvailable(date, time);
            } catch (Exception e) {
                System.out.println(BRIGHT_RED + Bold + "Error checking slot availability.");
                validSlot = false;
            }

        } while (!validSlot); // Repeat until a valid slot is selected

        return time; // Return the valid time slot
    }    

    // Method to collect patient details from user input with validation checks for ecach detail
    private Patient collectPatientDetails() {
    System.out.println("");
    System.out.println(BRIGHT_CYAN + Bold + "----------------Fill the Patient Detail---------------");
    System.out.println("");
    
    // Ensure name contains only Letters and spaces
    String name;
    // Proceed with collecting other details after valid NIC 
   do {
        System.out.print(BRIGHT_WHITE_Color + Bold  + "Enter Patient Name: " + BRIGHTYELLOW);
        name = scanner.nextLine();

        //Validate Name Can include only letters
        if (!name.matches("[a-zA-Z\\s]+")) {
            System.out.println(BRIGHT_RED + "Invalid name format. Please enter alphabetic characters only." );
        }
       } while (!name.matches("[a-zA-Z\\s]+")); // Repeat until valid name
   
    // Validate NIC format
    String NIC;
   do {
        System.out.print(BRIGHT_WHITE_Color + Bold + "Enter Patient NIC: " + BRIGHTYELLOW);
        NIC = scanner.nextLine();

        if (!validateNIC(NIC)) {
            System.out.println(BRIGHT_RED + Bold+ "Invalid NIC format. Please try again." );
        }
   } while (!validateNIC(NIC));  // Repeat until NIC is valid

   // Collect and validate email Address 
    String email;
   
    do {
    System.out.print(BRIGHT_WHITE_Color + Bold  + "Enter Patient Email: " + BRIGHTYELLOW);
      email = scanner.nextLine();

     //Validate email format
    if (!validateEmail(email)) {
       System.out.println(BRIGHT_RED + Bold+ "Invalid email format. Please try again." + reset);
        
    }
    } while((!validateEmail(email)));
    
    
 // Collect and Validate phone number (Should be 10 digits and follow Sri Lankan prefixes)
    String phone;
    do {
        System.out.print(BRIGHT_WHITE_Color + Bold + "Enter Patient Phone: " + BRIGHTYELLOW);
        phone = scanner.nextLine();
        System.out.println("");

        if (phone.length() != 10) {
            System.out.println(BRIGHT_RED + Bold + "Invalid phone number. Phone number must be exactly 10 digits." + reset);
        } else if (!phone.matches("^(070|071|072|074|075|076|077|078|011|037).*")) {
            System.out.println(BRIGHT_RED + Bold + "Invalid prefix. Phone number must start with one of the following prefixes: 070, 071, 072, 074, 075, 076, 077, 078, 011, 037." + reset);
        } else if (!validatePhone(phone)) {
            System.out.println(BRIGHT_RED + Bold + "Invalid phone number. Please enter a valid Sri Lankan phone number." + reset);
        }
        
    } while (!validatePhone(phone));  // Repeat until valid phone number is entered
   
    // Create Patient object with the collected details
    Patient patient = new Patient( NIC, name, email, phone);
	return patient;
   }
  
   // Method to display available treatments and allow user to select one for an appointment
   private void selectTreatment(Appointment appointment) {
    // Create a list of available treatments using the treatment classes
    List<Treatment> treatments = List.of(
        new AcneTreatment(),
        new SkinWhitening(),
        new MoleRemoval(),
        new LaserTreatment()
    );

    System.out.println(BRIGHT_CYAN + Bold + "-+-+-+-+-+-+-+-+-+-+-Available Treatments:-+-+--+-+-+-+-+-+-+-+-" + BRIGHT_WHITE_Color);
    System.out.println("");

    // Display all available treatments with their respective prices
    for (int i = 0; i < treatments.size(); i++) {
        System.out.println(BRIGHT_WHITE_Color + Bold + (i + 1) + ". " + treatments.get(i).getTreatmentName() + " (LKR " + treatments.get(i).getPrice() + ")" );
    }
    System.out.println("");

    int treatmentChoice = -1; // Initialize to an invalid choice
    boolean validChoice = false; // Flag to determine if the choice is valid

    // Allow user to select a treatment and validate the choice
    do {
        System.out.print(BRIGHT_GREEN + Bold + "Select Treatment (1-" + treatments.size() + "): " + BRIGHTYELLOW);
        String input = scanner.nextLine().trim(); // Read the input and trim whitespace

        if (input.isEmpty()) {
            // If the user presses Enter without input
            System.out.println(BRIGHT_WHITE_Color + Bold + "No input detected. Please enter a number between 1 and " + treatments.size() + "." + reset);
        } else if (input.matches("\\d+")) {
            // Check if the input is a number
            treatmentChoice = Integer.parseInt(input);

            // Validate treatment choice is within the range
            validChoice = (treatmentChoice >= 1 && treatmentChoice <= treatments.size());

            if (!validChoice) {
                System.out.println(BRIGHT_WHITE_Color + Bold + "Invalid selection. Please select a valid treatment." + reset);
            }
        } else {
            // If input is not a valid number
            System.out.println(BRIGHT_WHITE_Color + Bold + "Invalid input. Please enter a number between 1 and " + treatments.size() + "." + reset);
        }

    } while (!validChoice); // Repeat until a valid choice is made

    // Once a valid choice is selected, process it
    Treatment selectedTreatment = treatments.get(treatmentChoice - 1);
    appointment.setTreatmentFee(selectedTreatment); // Set the treatment fee using the selected treatment

    System.out.println(cyan_Color + Bold + "Treatment selected: " + selectedTreatment.getTreatmentName() + " (LKR " + selectedTreatment.getPrice() + ")" + reset);        
    System.out.println("");

    // Generate invoice after successful booking
    appointment.generateInvoice(selectedTreatment); // Generate the invoice with the selected treatment
   }
     
// Method to update an existing appointment's date and time based on appointment ID
   private void updateAppointment() {
	    System.out.println(BRIGHT_CYAN + Bold + "---------------Update Appointments---------------" + BRIGHT_WHITE_Color);
	    System.out.println("");
	    System.out.print(BRIGHT_GREEN + Bold + "Enter Appointment ID to update: " + BRIGHTYELLOW);	    
	    String input = scanner.nextLine(); // Read input as a string

	    // Check if the input is empty (user pressed Enter without typing an ID)
	    if (input.isEmpty()) {
	        System.out.println(BRIGHT_RED + Bold + "Invalid input. Please enter a valid appointment ID." + reset);
	        return; // Exit if no ID is provided
	    }

	    // Try to parse the input to an integer
	    int appointmentId;
	    try {
	        appointmentId = Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        System.out.println(BRIGHT_RED + Bold + "Invalid input. Please enter a valid numeric appointment ID." + reset);
	        return; // Exit if input is not a valid integer
	    }

	    // Check if the appointment ID exists in the system
	    if (!clinic.doesAppointmentExist(appointmentId)) {
	        System.out.println(BRIGHT_RED + Bold + "Error: Appointment ID " + appointmentId + " does not exist. Please enter a valid ID." + reset);
	        return; // Exit if the appointment ID is not found
	    }

	    // Retrieve the assigned dermatologist for the appointment ID
	    Dermatologist assignedDoctor = clinic.getDermatologistByAppointmentId(appointmentId);

	    // Prompt for and validate the new appointment date
	    String newDate = selectDate();

	    // Prompt for and validate the new appointment time
	    String newTime = selectTime(assignedDoctor, newDate);

	    // Update the appointment details
	    clinic.updateAppointment(appointmentId, newDate, newTime);
	    System.out.println(BG_GREEN + BRIGHT_WHITE_Color + Bold + "Appointment updated successfully!" + reset);
	}


// Method to view appointments scheduled on a specific day entered by the user
private void viewAppointments() {
    System.out.println(BRIGHT_CYAN + Bold + "----------------View Appointments By Day---------------");
    System.out.println("");

    boolean continueSearching = true;  // To control if the user wants to search again

    while (continueSearching) {
        System.out.print(BRIGHT_GREEN + Bold + "Enter Appointment Date to view appointments (e.g., yyyy/MM/dd): " + BRIGHTYELLOW);
        String date = scanner.nextLine(); // Read date input from the user

        // Validate the date format
        if (!isValidDate(date)) {
            System.out.println(BRIGHT_RED + Bold + "Invalid date format. Please enter the date in 'yyyy/MM/dd' format." + reset);
            continue; // Skip the rest of the loop and prompt for date again
        }

        List<Appointment> appointmentsOnDate = clinic.getAppointmentsByDate(date);

        if (appointmentsOnDate.isEmpty()) {
            System.out.println("");
            System.out.println(BRIGHT_RED + Bold + "No appointments found on " + date + "." + reset);
        } else {
            for (Appointment appointment : appointmentsOnDate) {
                System.out.println("");
                System.out.println(BRIGHTYELLOW + Bold + "-----------Appointment Available:-----------" + reset);
                System.out.println("");
                System.out.println(BRIGHT_WHITE_Color + Bold + "Appointment ID: " + appointment.getAppointmentID());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Name: " + appointment.getPatient().getName());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Contact Number: " + appointment.getPatient().getPhone());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient NIC: " + appointment.getPatient().getNIC());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Email: " + appointment.getPatient().getEmail());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Date: " + appointment.getDate());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Time: " + appointment.getTime());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Dermatologist: " + appointment.getDermatologist().getName());
                System.out.println(BRIGHTYELLOW + Bold + "---------------------------------------------");
                System.out.println("");
            }
        }

        // Ask if the user wants to search for appointments on another day
        System.out.print(BRIGHT_CYAN + Bold + "Would You Like to Search for Another Day's Appointments? (Y/N): " + BRIGHTYELLOW);
        String response = scanner.nextLine().toLowerCase();  
        if (!response.equals("y")) {
            continueSearching = false;  // Stop searching and return to main menu
        }
    }

    // Return to the main menu
    System.out.println(BRIGHT_GREEN + Bold + "Returning to Main Menu...");
    // Call the method to return to the main menu (e.g., mainMenu())
}


    // Method to search for a specific appointment by either patient name or appointment ID    
    private void searchAppointment() {
        System.out.println(BRIGHT_CYAN + Bold + "---------------Search Available Appointments---------------" + BRIGHT_WHITE_Color);
        System.out.println("");

        int attempts = 0;  // Counter for invalid attempts
        boolean continueSearching = true;  // To control if user wants to search again

        while (attempts < 3 && continueSearching) {
            System.out.print(BRIGHT_GREEN + Bold + "Search by Patient Name or Appointment ID: " + BRIGHTYELLOW);
            String searchTerm = scanner.nextLine().toLowerCase();  // Convert to lowercase

            Appointment appointment = clinic.searchAppointment(searchTerm);

            if (appointment != null) {
                // Appointment found, print details
                System.out.println("");
                System.out.println(BRIGHTYELLOW + Bold + "-----------Appointment Available:-----------" + reset);
                System.out.println("");
                System.out.println(BRIGHT_WHITE_Color + Bold + "Appointment ID: " + appointment.getAppointmentID());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Name: " + appointment.getPatient().getName());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Contact Number: " + appointment.getPatient().getPhone());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient NIC: " + appointment.getPatient().getNIC());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Patient Email: " + appointment.getPatient().getEmail());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Date: " + appointment.getDate());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Time: " + appointment.getTime());
                System.out.println(BRIGHT_WHITE_Color + Bold + "Dermatologist: " + appointment.getDermatologist().getName());
                System.out.println(BRIGHTYELLOW + Bold + "---------------------------------------------");
                System.out.println("");

                // Ask if user wants to search for another appointment
                System.out.print(BRIGHT_CYAN + Bold + "Would You Like to Search for Another Appointment? (Y/N): " + BRIGHTYELLOW);
                String response = scanner.nextLine().toLowerCase();  
                if (!response.equalsIgnoreCase("Y")) {
                    continueSearching = false;  // Stop searching and return to main menu
                }
            } else {
                attempts++;
                System.out.println("");
                System.out.println(BRIGHT_RED + Bold + "No Appointments Found Matching the Search AppointmentID or Patient Name." + reset);

                
                }
            }
        

        // After 3 failed attempts, or if user chooses to stop searching, return to main men
        
        System.out.println(BRIGHT_GREEN + Bold + "Returning to Main Menu...");
        
    }
 
}

