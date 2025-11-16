package com.hostel;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Students[] students = new Students[50];
        Room[] rooms = new Room[20];
        int scount = 0, rcount = 0, nextId = 1;

        while (true) {
            System.out.println("\n1 Add Student");
            System.out.println("2 Add Room");
            System.out.println("3 Allocate Room");
            System.out.println("4 Mess Fees");
            System.out.println("5 View Students");
            System.out.println("6 Delete Student");
            System.out.println("7 View Room Occupancy");
            System.out.println("8 Save & Exit");
            System.out.print("Choice: ");

            String c = sc.nextLine();

            try {
                switch (c) {

                    case "1":
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Gender (M/F): ");
                        String g = sc.nextLine();
                        System.out.print("1 Normal 2 Scholar: ");
                        boolean sch = sc.nextLine().equals("2");

                        if (sch)
                            students[scount++] = new ScholarStudents(nextId++, name, g);
                        else
                            students[scount++] = new Students(nextId++, name, g);

                        break;

                    case "2":
                        System.out.print("Room ID: ");
                        String id = sc.nextLine();
                        System.out.print("Gender (M/F/Any): ");
                        String gl = sc.nextLine();
                        System.out.print("Capacity: ");
                        int cap = Integer.parseInt(sc.nextLine());

                        rooms[rcount++] = new Room(id, gl, cap);
                        break;

                    case "3":
                        for (int i = 0; i < scount; i++)
                            System.out.println(i + ") " + students[i].getName());
                        System.out.print("Student index: ");
                        int si = Integer.parseInt(sc.nextLine());

                        for (int i = 0; i < rcount; i++)
                            System.out.println(i + ") " + rooms[i].getId());
                        System.out.print("Room index: ");
                        int ri = Integer.parseInt(sc.nextLine());

                        rooms[ri].allocate(students[si]);
                        break;

                    case "4":
                        System.out.print("Student index: ");
                        int mi = Integer.parseInt(sc.nextLine());
                        System.out.print("Days: ");
                        int d = Integer.parseInt(sc.nextLine());

                        double amt = students[mi].calculateMess(d);
                        students[mi].dues += amt;

                        System.out.println("Added: " + amt);
                        break;

                    case "5":
                        for (int i = 0; i < scount; i++) {
                            Students s = students[i];
                            System.out.println(s.getId() + " - " + s.getName() +
                                    " | Room: " + s.getRoom() + " | Dues: " + s.getDues());
                        }
                        break;

                    case "6":
                        System.out.print("Index: ");
                        int di = Integer.parseInt(sc.nextLine());

                        for (int r = 0; r < rcount; r++)
                            rooms[r].remove(students[di]);

                        for (int i = di; i < scount - 1; i++)
                            students[i] = students[i + 1];

                        students[--scount] = null;
                        break;

                    case "7":
                        for (int i = 0; i < rcount; i++) {
                            Room r = rooms[i];
                            System.out.print(r.getId() + ": ");
                            boolean e = true;
                            for (Students x : r.getStudents()) {
                                if (x != null) {
                                    System.out.print(x.getName() + " ");
                                    e = false;
                                }
                            }
                            if (e) System.out.print("(empty)");
                            System.out.println();
                        }
                        break;

                    case "8":
                        save(students, scount);
                        System.out.println("Saved!");
                        return;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void save(Students[] s, int n) {
        try (PrintWriter p = new PrintWriter("students.txt")) {
            for (int i = 0; i < n; i++)
                p.println(s[i].getId() + "," + s[i].getName() + "," +
                        s[i].getGender() + "," + s[i].getRoom() + "," + s[i].getDues());
        } catch (Exception e) {}
    }
}
