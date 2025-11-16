package com.hostel;

public class ScholarStudents extends Students {

    public ScholarStudents(int id, String name, String gender) {
        super(id, name, gender);
    }

    @Override
    public double calculateMess(int days) {
        return (days * 50) * 0.5; // 50% discount
    }
}
