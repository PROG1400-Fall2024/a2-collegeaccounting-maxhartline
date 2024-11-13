import java.util.ArrayList;

public class Staff extends Person {

    // Constructor
    public Staff(String name, String address, int year) {
        super(name, address, year);
    }

    // Method to calculate staff pay
    public double calculateStaffPay() {
        double basePay = 50000;
        return basePay + (getYear() * 500);
    }

    // Method to calculate outgoing amount - staff pays added together and divided by 26
    public double calculateOutgoingAmount(ArrayList<Staff> staffList) {
        double outgoingAmount = 0;
        for (Staff staff : staffList) {// Iterate through the list of staff
            outgoingAmount += staff.calculateStaffPay();
        }
        return outgoingAmount / 26;
    }

    // Override toString() method to return a string for report
    @Override
    public String toString() {
        return "name = " + getName() + ", address = " + getAddress() + ", years = " + getYear() + ", pay = $" + String.format("%.2f", calculateStaffPay());
    }
}
