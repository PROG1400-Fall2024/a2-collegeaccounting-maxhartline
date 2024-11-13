import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Declare variables
        String name;
        String address;
        int year;

        // Create lists to track entered students and staff
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Staff> staffList = new ArrayList<>();

        while (true) { // Wrap entire application in a while loop
            // Application start
            Object[] myButtons = {"Student", "Staff", "Finish"}; // Array of objects with the button options hardcoded
            int userSelect = JOptionPane.showOptionDialog(null, "Select Student or Staff.", "Accounting App",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, myButtons, myButtons[0]); //myButtons[0] is which option is selected by default upon opening

            if (userSelect == 0) {
                while (true) {
                    // Enter student year
                    String studentYear = JOptionPane.showInputDialog(null, "Enter student year (1-4)", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (studentYear.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }
                    if (!studentYear.matches("-?[0-9]+")) { // Regex checking for numbers, including negatives
                        JOptionPane.showMessageDialog(null, "Please enter a number.");
                        continue;
                    }
                    year = Integer.parseInt(studentYear); // Parse the year string into an integer
                    if (year > 4 || year < 1) {
                        JOptionPane.showMessageDialog(null, "Number outside of range.");
                        continue;
                    }
                    break; // Exit the loop if input is valid
                }

                // Enter student name
                while (true) {
                    name = JOptionPane.showInputDialog(null, "Enter student name", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }
                    break; // Exit the loop if input is valid
                }

                // Enter student address
                while (true) {
                    address = JOptionPane.showInputDialog(null, "Enter student address", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }
                    break;
                }

                // Create new Student object and add to list
                Student student = new Student(name, address, year);
                studentList.add(student);

            } else if (userSelect == 1) {
                while (true) {
                    // Enter staff name
                    name = JOptionPane.showInputDialog(null, "Enter staff name", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    // Enter staff address
                    address = JOptionPane.showInputDialog(null, "Enter staff address", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    // Enter staff years of service
                    String staffYears = JOptionPane.showInputDialog(null, "Enter staff years of service", JOptionPane.QUESTION_MESSAGE);

                    // Validate input
                    if (staffYears.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter valid information.");
                        continue;
                    }

                    if (!staffYears.matches("-?[0-9]+")) { // Regex checking for numbers, including negatives
                        JOptionPane.showMessageDialog(null, "Please enter a number.");
                        continue;
                    }

                    year = Integer.parseInt(staffYears); // Parse the year string into an integer
                    if (year > 30 || year < 0) {
                        JOptionPane.showMessageDialog(null, "Number outside of range.");
                        continue;
                    }
                    break; // Exit the loop if input is valid
                }

                // Create new Staff object and add to list
                Staff staff = new Staff(name, address, year);
                staffList.add(staff);

            } else if (userSelect == 2) { // Generate report once user selects "Finish"
                // Create report for students
                StringBuilder studentReport = new StringBuilder();
                studentReport.append("Students:[Total: ").append(studentList.size()).append("]\n");

                int studentCounter = 1;
                for (Student student : studentList) {
                    studentReport.append(studentCounter++).append(". ").append(student.toString()).append("\n");
                }

                // Create report for staff
                StringBuilder staffReport = new StringBuilder();
                staffReport.append("Staff:[Total: ").append(staffList.size()).append("]\n");

                int staffCounter = 1;
                for (Staff staff : staffList) {
                    staffReport.append(staffCounter++).append(". ").append(staff.toString()).append("\n");
                }

                double[] results = calculateTotal(studentList, staffList);
                double incomingAmount = results[0];
                double outgoingAmount = results[1];
                double totalAmount = incomingAmount - outgoingAmount;

                // Add results to the end of the report
                StringBuilder reportResults = new StringBuilder();
                reportResults.append(studentReport);
                reportResults.append("\n").append(staffReport);
                reportResults.append("\nResults:\n");
                reportResults.append("Outgoing: $").append(String.format("%.2f", outgoingAmount)).append("\n");
                reportResults.append("Incoming: $").append(String.format("%.2f", incomingAmount)).append("\n");
                reportResults.append("Total: $").append(String.format("%.2f", totalAmount)).append("\n");

                // Display report
                JOptionPane.showMessageDialog(null, reportResults, "Report", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    // Method to calculate total - incoming minus outgoing
    public static double[] calculateTotal(ArrayList<Student> studentList, ArrayList<Staff> staffList) {
        double totalIncoming = 0;
        double totalOutgoing = 0;

        for (Student student : studentList) {
            totalIncoming += student.calculateIncomingAmount(studentList); // Sum incoming amount using the calculated incomingAmount in Student
        }
        for (Staff staff : staffList) {
            totalOutgoing += staff.calculateOutgoingAmount(staffList);  // Sum outgoing amount using the calculated outgoingAmount in Staff
        }
        return new double[]{totalIncoming, totalOutgoing};
    }
}