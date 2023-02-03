/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package loginform;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author biniyamk
 */
public class ApprovedPayment extends javax.swing.JFrame {

    /**
     * Creates new form PendingPayment
     */
     Connection con;
     Statement st;
     DahsboardPage dp;
     
     ArrayList<Payment> payments = new ArrayList<>();
    public ApprovedPayment() {
        initComponents();
        fetch();
        setTitle("");
      add(new JLabel("", SwingConstants.CENTER),                BorderLayout.CENTER);
      //setSize(550, 450);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
      setVisible(true);
    }
   
    
    private void fetch() {
            //subscriptions.clear();
        try {
            
            String stat = "Approved";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
            String sql = "select * from payment WHERE status='" + stat + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               Payment pay = new Payment(rs.getInt("share_id_paid"),rs.getDouble("amount_paid"),rs.getDouble("weighted_amount"),rs.getString("payment_date"),rs.getString("status"),rs.getInt("payment_date"));
                payments.add(pay);
            }
            DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
            for (Payment pay : payments) {
                Object[] row = new Object[4];
                row[0] = pay.getShareid();
                row[1] = pay.getPayment();
                row[2] = pay.getPayDate();
                row[3] = pay.getStatus();
                //row[2] = this.add( new JButton("+"));

                model.addRow(row);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //handles delete button action
    
    public double GetTotalPaid(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(weighted_amount) as total from payment where `share_id_paid` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     double total_paid = rs.getInt("total");
                     return total_paid;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManagePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0; 
    }
    // get total number of days
   public int GetTotalShareWeighted(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(weighted_amount) as totalweight from payment where `share_id_paid` = '" + id + "'";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     int totalweight = rs.getInt("totalweight");
                     return totalweight;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManagePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
   
   
   // get total payment existance
   public double TotalWeighted() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select SUM(weighted_amount) as Total_weighted from payment";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     int Total_Weighted = rs.getInt("Total_weighted");
                     return Total_Weighted;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManagePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
   // get profit
   public int GetProfit() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select * from budget_year";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     int Profit = rs.getInt("profit");
                     //int ratio = rs.getInt("profit");
                     return Profit;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManagePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
   public int GetDividendRatio() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
        String sql = "select * from budget_year";
        st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
        ResultSet rs = st.executeQuery(sql);
                //ResultSet rs2 = st.executeQuery(sql2);
                if (rs.first()) {
                     //int Profit = rs.getInt("profit");
                     int ratio = rs.getInt("dividend_ratio");
                     return ratio;
                }
        
       
        }
        catch(SQLException  | ClassNotFoundException ex) {
                    Logger.getLogger(ManagePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
   
     //delete details in the db
    public void Approve(int id) {
        try {
            String new_status = "Approved";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
            String sql = "UPDATE `payment`SET status='" + new_status + "' WHERE payment_id='" + id + "'";
            st = con.createStatement();
            st.execute(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);
        }
//        fetch();
    }
  //method to show an info alert
    public void alert(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnApprove = new javax.swing.JButton();
        btnget_ratio = new javax.swing.JButton();
        txt_profit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Approved  Payments");

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Share ID", "Amount Paid", "Paid Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudents);

        jLabel3.setBackground(new java.awt.Color(51, 51, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 102, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Approved Paymet For Dividend Calculation");

        btnApprove.setBackground(new java.awt.Color(153, 255, 153));
        btnApprove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApprove.setText("Calculate Dividend");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnget_ratio.setBackground(new java.awt.Color(102, 102, 255));
        btnget_ratio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnget_ratio.setForeground(new java.awt.Color(51, 51, 51));
        btnget_ratio.setText("Get Ratio");
        btnget_ratio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnget_ratioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Before Calculating Dividend input Profit and Get Dividend Ratio");

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Back to Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Enter Budget Year Profit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnget_ratio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_profit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(620, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(165, 165, 165)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txt_profit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnget_ratio)
                        .addGap(52, 52, 52)
                        .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(263, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked
        // TODO add your handling code here:
        //        int i = tblStudents.getSelectedRow();
        //        TableModel model = tblStudents.getModel();
        //        txtFname.setText(model.getValueAt(i, 0).toString());
        //        txtLname.setText(model.getValueAt(i, 1).toString());
        //        txtId.setText(model.getValueAt(i, 2).toString());
    }//GEN-LAST:event_tblStudentsMouseClicked
   public void SaveDivdend(int share_id, double dividend_amount) {
        try {
            String stat = "Pending";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
            String sql = "INSERT INTO `dividend`(`share_id`, `dividend_amount`, `status`) "
                    + "VALUES ('" + share_id + "','" + dividend_amount + "','" + stat + "')";
            st = con.createStatement();
            st.execute(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageShareholder.class.getName()).log(Level.SEVERE, null, ex);
        }

//        fetch();
    }
    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        // TODO add your handling code here:
       int i = tblStudents.getSelectedRow();
        if (i >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to Approve Payment?", "Delete confirmation", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                TableModel model = tblStudents.getModel();

                String id = model.getValueAt(i, 0).toString();
                int pay_id = Integer.valueOf(id);
                double ratio = GetDividendRatio();
                double weighted = GetTotalShareWeighted(pay_id);
                double d = weighted*(ratio/100);
                
                if (tblStudents.getSelectedRows().length == 1) {
                    
                    SaveDivdend(pay_id,d);
                    alert("Divendend Disbursed Succeffully");
                    DefaultTableModel model1 = (DefaultTableModel) tblStudents.getModel();
                    model1.setRowCount(0);
                    fetch();
                    //clear();
                }
            }
        } else {
            alert("Please select a Payment to Approve");
        } 
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnget_ratioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnget_ratioActionPerformed
        // TODO add your handling code here:
        String input_profit = txt_profit.getText();
        double profit = Double.parseDouble(input_profit);       
        GetRatio(profit);
    }//GEN-LAST:event_btnget_ratioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //System.exit(0);
        new DahsboardPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
     public void GetRatio(double year_profit) {
        double weighted = TotalWeighted();
        double profit = year_profit;
        double ratio = (profit/weighted)*100;
        
        try {
            //String new_status = "Approved";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
            String sql = "UPDATE `budget_year`SET profit ='" + profit + "',dividend_ratio ='" + ratio + "'";
             alert(weighted+"Dividend Ratio Calculated Succesfully");
            st = con.createStatement();
            st.execute(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);
        }
//        fetch();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApprovedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApprovedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApprovedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApprovedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApprovedPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnget_ratio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txt_profit;
    // End of variables declaration//GEN-END:variables
}
