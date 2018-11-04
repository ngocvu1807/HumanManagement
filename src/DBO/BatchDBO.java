package DBO;

import Model.Batch;
import dbConnect.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchDBO {
    public void insertBatch(Batch bat) throws SQLException, ClassNotFoundException {
        String sql ="insert into batch (btcName) values (?)";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,bat.btcName);
        stmt.execute();
        stmt.close();
        conn.close();
        }
    public void viewAllBatch() throws SQLException, ClassNotFoundException {
        String sql ="select * from batch ";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            System.out.println("--------------------------");
            System.out.println("Batch ID: " + rs.getInt(1) + " | Batch name: " + rs.getString(2));
        }
        stmt.close();
        conn.close();
    }
    public void searchBatchByName(String btcName) throws SQLException, ClassNotFoundException {
        String sql = "select * from batch where btcName like ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + btcName + "%");
        ResultSet rs = stmt.executeQuery();
        int count = 0;
        while (rs.next()){
            count++;
            System.out.println("---------------------------------------------------------------");
            System.out.println("Batch ID: " + rs.getInt(1) + " | Batch name: " + rs.getString(2));
        }
        System.out.println("Found " + count + "batch(es)");
        if (count == 0){
            throw null;
        }
        stmt.close();
        conn.close();
    }
    public void editBatch(Batch bat) throws SQLException, ClassNotFoundException {
        String sql ="update batch set btcName = ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bat.btcName);
        int rs = stmt.executeUpdate();
        if (rs >0){
            System.out.println("Update success!!");
        }else{
            System.out.println("ID not found");
        }
        stmt.close();
        conn.close();
    }
    public void  deleteBatchByID(int btcID) throws SQLException, ClassNotFoundException {
        String sql ="delete from batch where btcID = ?";
        Connection conn = new dbConnection().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,btcID);
        int rs = stmt.executeUpdate();
        if (rs >0) {
            System.out.println("deleted!!");
        }else System.out.println("ID not found.");
        stmt.close();
        conn.close();
    }
    public void deleteBatchByName () throws SQLException, ClassNotFoundException {
        System.out.println("Please enter batch name to delete!!");
        Scanner scanner = new Scanner(System.in);
        int btcID = scanner.nextInt();
        deleteBatchByID(btcID);
    }
}
