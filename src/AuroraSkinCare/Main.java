package AuroraSkinCare;


public class Main {
    
	public static void main(String[] args) {
     	
    	
    	SkinClinic clinic = new SkinClinic("Aurora Skin Care", "Colombo"); 
        
    	UserInterface ui = new UserInterface(clinic);    // Create new instant for passing the Clinic instance to manage operations 
        
    	ui.start(); // Start the user interface to allow users to interact with the system. 
    }
}
