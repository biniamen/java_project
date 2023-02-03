/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author biniyamk
 */

public class Validation {
    Connection con;
     Statement st;
     //Getting Paid Amount
    public double GetPaidAmount(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(amount_paid) as TotalPaid from payment where `share_id_paid` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     double total_paid = rs.getInt("TotalPaid");
                     return total_paid;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
    // get total Subscribed
    
    public int GetTotalSubscription(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(num_share) as TotalSub from subscription where `share_id` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     int num_of_share = rs.getInt("TotalSub");
                     return num_of_share*1000;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
    
    public double GetTotalDividend(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(dividend_amount) as TotalDividend from dividend where `share_id` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     double total_dividend = rs.getInt("TotalDividend");
                     return total_dividend;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
    
    // for capitalization 
    public double GetDividendPaid(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(dividend_amount) as TotalDividend from dividend where `share_id` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     double total_dividend_paid = rs.getInt("TotalDividend");
                     return total_dividend_paid;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
    
    
}
