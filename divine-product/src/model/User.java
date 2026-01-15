package model;

public class User {
    private int id;
    private String fname;
    private String lname;
    private int age;

    // Constructor, Getters, and Setters
    public User(int id, String fname, String lname, int age) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }

    public int getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s %s | Age: %d", id, fname, lname, age);
    }
}
