import java.util.ArrayList;

public class Student extends Person {

    // Constructor
    public Student(String name, String address, int year) {
        super(name, address, year);
    }

    // Method to calculate student fees
    public double calculateStudentFee() {
        double baseFee = 3000;
        return baseFee + (getYear() - 1) * 100;
    }

    // Method to calculate incoming amount - student fees added together and divided by 2
    public double calculateIncomingAmount(ArrayList<Student> students) {
        double incomingAmount = 0;
        for (Student student : students) { // Iterate through the list of students
            incomingAmount += student.calculateStudentFee();
        }
        return incomingAmount / 2;
    }

    // Override toString() method to return a string for report
    @Override
    public String toString() {
        return "name = " + getName() + ", address = " + getAddress() + ", year = " + getYear() + ", fee = $" + String.format("%.2f", calculateStudentFee());
    }

}
