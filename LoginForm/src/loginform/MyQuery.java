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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author biniyamk
 */
public class MyQuery {
    
    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/sharedb", "root","");
        } catch (SQLException ex) {
           // Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

   public HashMap<String, Integer> populateCombo(){
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      Connection con = getConnection();
      Statement st;
      ResultSet rs;
      
       try {
           st = con.createStatement();
           rs = st.executeQuery("SELECT `id`, `shareholder_name` FROM `shareholders`");
           comboItem cmi;
           
           while(rs.next()){
               cmi = new comboItem(rs.getInt(1), rs.getString(2));
               map.put(cmi.getCatName(), cmi.getCatId());
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
      
       return map;
   }
}
