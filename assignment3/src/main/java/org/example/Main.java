package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    // Class variables 
    public static String url = "jdbc:postgresql://localhost:5432/Assignment3";
    public static String user = "postgres";
    public static String password = "SQLpass";
    public static Connection conn;

    static void getAllStudents(){
        try { 
            String[] tableHeaders = {"student_id", "first_name", "last_name", "email", "enrollment_date"};
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            // Print all headers            
            for(int i = 0; i<tableHeaders.length; i++){
                System.out.print(tableHeaders[i] + "\t");
            }

            // Print the "students" table line by line
            System.out.println();
            while(rs.next()){
                for(int i = 0; i<tableHeaders.length; i++){
                    System.out.print(rs.getString(tableHeaders[i]) + "\t");
                }
                System.out.println();
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        try {
            Statement stmt = conn.createStatement();

            // Fill out the INSERT statement using the user input
            String insertSQL = " INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (";
            insertSQL += "'" + first_name + "',";
            insertSQL += "'" + last_name + "',";
            insertSQL += "'" + email + "',";
            insertSQL += "'" + enrollment_date + "')";

            int rowsInserted = stmt.executeUpdate(insertSQL);
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateStudentEmail(int student_id, String new_email){
        try {
            Statement stmt = conn.createStatement();

            // Fill out the UPDATE statement using the user input
            String insertSQL = " UPDATE students SET email = '";
            insertSQL += new_email + "' WHERE student_id = ";
            insertSQL += student_id;

            int studId = stmt.executeUpdate(insertSQL);
            if(studId > 0){
                System.out.println("Email was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteStudent(int student_id){
        try {
            Statement stmt = conn.createStatement();

            // Fill out the DELETE statement using the user input
            String insertSQL = " DELETE FROM students WHERE student_id = ";
            insertSQL += student_id;

            int studId = stmt.executeUpdate(insertSQL);
            if(studId > 0){
                System.out.println("Student was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                // Test 1: Add Students
                /*
                getAllStudents();
                addStudent("Annie", "Zhang", "annie.zhang@example.com", "2023-11-06");
                addStudent("Ashe", "Eijin", "ashe.eijin@example.com", "2023-03-16");
                getAllStudents(); 
                */

                // Test 2: Update Emails
                /*
                getAllStudents();
                updateStudentEmail(1, "john.doe@newemail.com");
                updateStudentEmail(2, "jane.smith@newemail.com");
                getAllStudents();
                */

                // Test 3: Delete Students
                /*
                getAllStudents();
                deleteStudent(4);
                deleteStudent(5);
                getAllStudents();
                */

            } else {
                System.out.println("Failed to establish connection.");
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
