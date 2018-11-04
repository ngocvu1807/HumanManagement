package DBO;

import Model.Student;
import dbConnect.dbConnection;

import java.sql.*;
import java.util.Scanner;

public class StudentDBO {
    public void insertStudent(Student student) throws SQLException, ClassNotFoundException {
        String sql = "Insert into student (stdID, stdName, stdDoB, stdEmail, stdAddress, stdBatchID) "
                + "values (?,?,?,?,?,?)";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,student.stdId);
        stmt.setString(2,student.stdName);
        stmt.setString(3, student.stdDoB);
        stmt.setString(4,student.stdEmail);
        stmt.setString(5,student.stdAddress);
        stmt.setInt(6, student.stdBatch);
        stmt.execute();
        stmt.close();
        conn.close();
    }
    public void selectAllStudent() throws SQLException, ClassNotFoundException {
        String sql = "Select * from student";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Student's ID: " + rs.getString(1) + " | Student's name: " + rs.getString(2)
                    + " | Student's DoB: " + rs.getString(3) + " | Student's Phone: " + rs.getString(4) + " | Student's Email: "
                    + rs.getString(5) + " | Student's address: " + rs.getString(6) + " | Student's Batch ID: " + rs.getInt(7));

        }
        stmt.close();
        conn.close();
    }

    public void searchStudentByName(String stdName) throws SQLException, ClassNotFoundException {
        String sql = "Select * from student where stdName like ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,"%" + stdName + "%");
        ResultSet rs = stmt.executeQuery();
        int count = 0;

        while (rs.next()){

            count++;
            System.out.println("Student ID: " + rs.getString(1) + " | Student name: " + rs.getString(2));
        }
        System.out.println("Found " + count + " Student(s)");
        stmt.close();
        conn.close();
    }
    public void deleteStudentByID(String stdID) throws SQLException, ClassNotFoundException {
        String sql = "Delete from Student where stdID = ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,stdID);
        int rs = stmt.executeUpdate();
        if (rs > 0){
            System.out.println("Deleted");
        }else{
            System.out.println("ID not found!!");
        }
        stmt.close();
        conn.close();
    }

    public void DeleteStudentByName() throws SQLException, ClassNotFoundException {
        System.out.println("Please Insert Student Id to Delete");
        Scanner sc = new Scanner(System.in);
        String stdID = sc.nextLine();
        deleteStudentByID(stdID);
    }

    public void updateStudent(Student student) throws SQLException, ClassNotFoundException {
        String sql = "update Student set stdName = ? , stdDoB =? , stdPhone=?, stdEmail =?, stdAddress = ?, stdBatchID= ? where stdID = ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,student.stdName);
        stmt.setString(2,student.stdDoB);
        stmt.setString(3,student.stdPhone);
        stmt.setString(4,student.stdEmail);
        stmt.setString(5,student.stdAddress);
        stmt.setInt(6,student.stdBatch);
        stmt.setString(7, student.stdId);
        int rs = stmt.executeUpdate();
        if (rs >0){
            System.out.println("Update success!!");
        }else{
            System.out.println("ID not found");
        }
        stmt.close();
        conn.close();
    }
}
