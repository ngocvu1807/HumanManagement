package DBO;

import Model.Lecturer;
import dbConnect.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LecturerDBO {
    public void insertLecturer(Lecturer lec) throws SQLException, ClassNotFoundException {
        String sql = " insert into lecturer (lecID,lecName,lecDoB, lecEmail,lecPhone,lecAddress, lecDeptID) " +
                "values (?,?,?,?,?,?,?)";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,lec.lecID);
        stmt.setString(2,lec.lecName);
        stmt.setString(3, lec.lecDoB);
        stmt.setString(4,lec.lecEmail);
        stmt.setString(5,lec.lecPhone);
        stmt.setString(6, lec.lecAddress);
        stmt.setInt(7,lec.lecDeptID);
        stmt.execute();
        stmt.close();
        conn.close();

    }
    public void viewAllLecturer() throws SQLException, ClassNotFoundException {
        String sql = "select * from lecturer";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Lecturer's ID: " + rs.getString(1) + " | Lecturer's name: " + rs.getString(2)
                    + " | Lecturer's DoB: " + rs.getString(3) + " | Lecturer's Phone: " + rs.getString(4) + " | Lecturer's Email: "
                    + rs.getString(5) + " | Lecturer's address: " + rs.getString(6) + " | Lecturer's department ID: " + rs.getInt(7));
        }
        stmt.close();
        conn.close();
    }
    public void searchLecturerByName(String lecName) throws SQLException, ClassNotFoundException {
        String sql = "select * from lecturer where lecName like ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt  = conn.prepareStatement(sql);
        stmt.setString(1,"%" + lecName + "%");
        ResultSet rs = stmt.executeQuery();
        int count = 0;

        while (rs.next()){
            count++;
            System.out.println("--------------------------");
            System.out.println("Lecturer's ID: " + rs.getString(1) + " | Lecturer's name: " + rs.getString(2)
                    + " | Lecturer's DoB: " + rs.getString(3) + " | Lecturer's Phone: " + rs.getString(4) + " | Lecturer's Email: "
                    + rs.getString(5) + " | Lecturer's address: " + rs.getString(6) + " | Lecturer's department ID: " + rs.getInt(7));
        }
        System.out.println("Found " + count + " lecturer(s)");
        if (count == 0){
            throw null;
        }
        stmt.close();
        conn.close();
    }
    public void deleteLecturerByID(String lecID) throws SQLException, ClassNotFoundException {
        String sql = "delete from lecturer where lecID =? ";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, lecID);
        int rs = stmt.executeUpdate();
        if (rs >0) {
            System.out.println("deleted!!");
        }else System.out.println("ID now found.");
            stmt.close();
            conn.close();
    }
    public void deleteLecturerByName() throws SQLException, ClassNotFoundException {
        System.out.println("Please Insert lecturer Id to Delete");
        Scanner sc = new Scanner(System.in);
        String lecID = sc.nextLine();
        deleteLecturerByID(lecID);
    }
    public void updateLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        String sql = "update lecturer set lecName = ? , lecDoB =? , lecEmail=?, lecPhone =?, lecAddress = ?, lecDeptID= ? where lecID = ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,lecturer.lecName);
        stmt.setString(2,lecturer.lecDoB);
        stmt.setString(3,lecturer.lecEmail);
        stmt.setString(4,lecturer.lecPhone);
        stmt.setString(5,lecturer.lecAddress);
        stmt.setInt(6,lecturer.lecDeptID);
        stmt.setString(7, lecturer.lecID);
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
