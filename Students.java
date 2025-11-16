package com.hostel;

public class Students {
    private int id;
    private String name;
    private String gender;
    private String room = "None";
    protected double dues = 0;

    public Students(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getRoom() { return room; }
    public double getDues() { return dues; }

    public void setName(String n) { name = n; }
    public void setGender(String g) { gender = g; }
    public void setRoom(String r) { room = r; }
    public void resetDues() { dues = 0; }

    public double calculateMess(int days) {
        return days * 50; // normal rate
    }
}
