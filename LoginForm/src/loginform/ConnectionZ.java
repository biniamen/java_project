/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;
import com.sun.org.apache.bcel.internal.generic.RET;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author biniyamk
 */
public class ConnectionZ {
   static Connection con;
   
   public static Connection getConnection(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
       }catch(Exception ex) {
           System.out.println(""+ex);
       }
   return con;
}
   
}
