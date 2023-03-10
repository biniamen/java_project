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
public class PendingPayment extends javax.swing.JFrame {

    /**
     * Creates new form PendingPayment
     */
     Connection con;
     Statement st;
     ArrayList<Payment> payments = new ArrayList<>();
    public PendingPayment() {
        initComponents();
        fetch();
        setTitle("Pending Payments");
      add(new JLabel("", SwingConstants.CENTER),                BorderLayout.CENTER);
      //setSize(550, 450);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
      setVisible(true);
    }
    //set the values of a row to the textfields
//    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {                                         
//        // TODO add your handling code here:
//        int i = tblStudents.getSelectedRow();
//        TableModel model = tblStudents.getModel();
//        txtUname.setText(model.getValueAt(i, 1).toString());
//        txtRole.setText(model.getValueAt(i, 2).toString());
//        //txt.setText(model.getValueAt(i, 2).toString());
//    }                                        

    
    private void fetch() {
            payments.clear();
        try {
            String stat = "Pending";
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

                Object[] row = new Object[5];
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
    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        int i = tblStudents.getSelectedRow();
        if (i >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to Approve Payment?", "Approval confirmation", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                TableModel model = tblStudents.getModel();

                String id = model.getValueAt(i, 0).toString();
                int pay_id = Integer.valueOf(id);
                if (tblStudents.getSelectedRows().length == 1) {
                    Approve(pay_id);
                    DefaultTableModel model1 = (DefaultTableModel) tblStudents.getModel();
                    model1.setRowCount(0);
                    fetch();
                    //clear();
                }
            }
        } else {
            alert("Please select payment to Approve");
        }
    }  
     //Approve payment in the db
    public void Approve(int id) {
        try {
            String new_status = "Approved";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharedb", "root", "");
            String sql = "UPDATE `payment`SET status='" + new_status + "' WHERE share_id_paid='" + id + "'";
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pending Payments");

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
        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 102, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pending Paymet");

        btnApprove.setBackground(new java.awt.Color(102, 255, 102));
        btnApprove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApprove.setText("Approve");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Back to Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnApprove)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        // TODO add your handling code here:
        int i = tblStudents.getSelectedRow();
        if (i >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to Approve Payment?", "Approval confirmation", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                TableModel model = tblStudents.getModel();

                String id = model.getValueAt(i, 0).toString();
                int pay_id = Integer.valueOf(id);
                if (tblStudents.getSelectedRows().length == 1) {
                    Approve(pay_id);
                    alert("Payment Approved Succeffully");
                    DefaultTableModel model1 = (DefaultTableModel) tblStudents.getModel();
                    model1.setRowCount(0);
                    new PendingPayment().setVisible(true);
                    this.dispose();
                    //fetch();
                    //clear();
                }
            }
        } else {
            alert("Please select a Payment to Approve");
        }
    }//GEN-LAST:event_btnApproveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //System.exit(0);
        new CheckerPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PendingPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PendingPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PendingPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PendingPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PendingPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudents;
    // End of variables declaration//GEN-END:variables
}
