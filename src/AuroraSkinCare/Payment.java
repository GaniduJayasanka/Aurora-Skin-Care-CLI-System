package AuroraSkinCare;


// Represents the payment process within the Aurora Skin Care Clinic System

public class Payment {
	
	    private double registrationFee = 500; // Fixed registration fee for each Patient
	    private double treatmentCost; // Cost of the selected treatment
	    private double taxRate = 0.025; // Tax rate applied to the treatment cost

	    public double getRegistrationFee() {
	        return registrationFee;
	    }

	    // Calculates the total payment amount for a specified treatment
	    public double calculateTotal(Treatment treatment) {
	        treatmentCost = treatment.getPrice();
	        double total = treatmentCost + (treatmentCost * taxRate);
	        return Math.round(total * 100.0) / 100.0; // Round to two decimal places
	    }

	   // Generates a formatted invoice for a specified treatment,
	    public String generateInvoice(Treatment treatment) {
	        double total = calculateTotal(treatment);
	        return String.format("Invoice:\nTreatment: %s\nTreatment Cost: %.2f\nRegistration Fee: %.2f\nTotal (with tax): %.2f",
	                treatment.getPrice(), treatment.getPrice(), registrationFee, total);
	    }

}
