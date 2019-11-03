package second.task;

public class Person {
    String name;
    String address;
    long phone;
    String email;

    public Person() {
        name = "Edward";
        address = "70 The Pond Road";
        phone = 4164915050L;
        email = "evuong1@myseneca.ca";
    }
    public String toString() {
        return "Person " + name + " lives at " + address + " with contact information of " + phone + " and " + email;
    }
}
