package AuroraSkinCare;


// Represent a treatment offered by the Aurora Skin Care Clinic System
public class Treatment {
	
    private String treatmentName; // Name of the treatment
    private double price; // Cost of the treatment

    
    // Constructor a Treatment object with the specified ID, name, and price.
    public Treatment(int treatmentID, String treatmentName, double price) {
        this.treatmentName = treatmentName;
        this.price = price;
    }

    public String getTreatmentName() {
        return treatmentName; // Added getter for treatment name
    }

    public double getPrice() {
        return price;
    }
}
