package com.hostel;

public class Room {
    private String id;
    private String genderLimit;
    private Students[] occ;
    private int count = 0;

    public Room(String id, String g, int cap) {
        this.id = id;
        this.genderLimit = g;
        occ = new Students[cap];
    }

    public String getId() { return id; }

    public Students[] getStudents() { return occ; }

    public void allocate(Students s) throws Exception {
        if (count == occ.length) throw new Exception("Room Full!");
        if (!genderLimit.equalsIgnoreCase("Any") &&
            !genderLimit.equalsIgnoreCase(s.getGender()))
            throw new Exception("Gender mismatch!");

        occ[count++] = s;
        s.setRoom(id);
    }

    public void remove(Students s) {
        for (int i = 0; i < count; i++) {
            if (occ[i] == s) {
                for (int j = i; j < count - 1; j++)
                    occ[j] = occ[j + 1];
                occ[--count] = null;
                s.setRoom("None");
            }
        }
    }
}
