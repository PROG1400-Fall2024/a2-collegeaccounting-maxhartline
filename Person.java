public class Person {

    // Properties
    private String name;
    private String address;
    private int year;

    // Constructor
    public Person(String name, String address, int year) {
        this.name = name;
        this.address = address;
        this.year = year;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }
}
