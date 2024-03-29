/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lamazooproj;

import accesseBDD.Connexion;
import accesseBDD.LogIn;
import accesseBDD.sql.CheckAccesse;
import accesseBDD.sql.VeterinaireSQL;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class JffCommentaire extends javax.swing.JFrame {
    private JFrame jFrame;
    private LogIn accesse;
    private int animalId;
    private VeterinaireSQL veterinaireSQL;
    private Connexion lamazoo;
    private CheckAccesse accesseChecker;
    private HashMap<Integer, String[]> listDesComm;
    private int commId;
    private JPanel jPComm;
    private JTextField jTFComm;
    
    /**
     * Creates new form JffCommentaire
     */
    public JffCommentaire() {
        initComponents();
    }
    public JffCommentaire(LogIn accesse, int animalId, VeterinaireSQL veterinaireSQL, Connexion lamazoo, CheckAccesse accesseChecker, JPanel jPComm, JTextField jTFComm) {
        initComponents();
        this.accesse = accesse;
        this.animalId = animalId;
        this.veterinaireSQL = veterinaireSQL;
        this.lamazoo = lamazoo;
        this.accesseChecker = accesseChecker;
        this.jPComm = jPComm;
        this.jTFComm = jTFComm;
        jFrame = this;
        if(accesseChecker.check()) {
            listDesComm = veterinaireSQL.getCommentaires(animalId);
            remplirTableau(listDesComm);
        } else {
            badToken();
        } 
        eventCloseWindows();
    }
    
    private void eventCloseWindows() {
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(jFrame,
                    "Fermer des Commentaires?", "Exit", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    jTComm.changeSelection(jTComm.getSelectedRow(), jTComm.getSelectedColumn(), true, false);
                    jTFTitreComm.setText("");
                    jTPDiscriptionComm.setText("");
                    jBCheck.setEnabled(true);
                    jFrame.dispose();
                }
            }
        });
    }
    
    private void remplirTableau(HashMap<Integer, String[]> listDesComm) {
        DefaultTableModel model = (DefaultTableModel) jTComm.getModel();
        listDesComm.forEach((key, value) -> {
            model.addRow(new Object[] {key, value[0], value[2], value[4] + " " + value[5], value[3]});
        }); 
    }
    
    private void badToken() {
        JffAuthentification jffAuthentification = new JffAuthentification();
        jffAuthentification.setVisible(true);;
        this.dispose();
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
        jTComm = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFTitreComm = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTPDiscriptionComm = new javax.swing.JTextPane();
        jBCheck = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTComm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Titre", "Date creation", "Nom de soignant", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTComm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTCommMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTComm);
        if (jTComm.getColumnModel().getColumnCount() > 0) {
            jTComm.getColumnModel().getColumn(0).setMinWidth(0);
            jTComm.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTComm.getColumnModel().getColumn(0).setMaxWidth(0);
            jTComm.getColumnModel().getColumn(1).setResizable(false);
            jTComm.getColumnModel().getColumn(2).setResizable(false);
            jTComm.getColumnModel().getColumn(3).setResizable(false);
            jTComm.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Titre:");

        jTFTitreComm.setEditable(false);

        jLabel2.setText("Description:");

        jTPDiscriptionComm.setEditable(false);
        jScrollPane2.setViewportView(jTPDiscriptionComm);

        jBCheck.setText("Check");
        jBCheck.setEnabled(false);
        jBCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTitreComm))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBCheck)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFTitreComm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCheckActionPerformed
        veterinaireSQL.checkComm(commId);
        jBCheck.setEnabled(false);
        Integer commNumb = (Integer.valueOf(jTFComm.getText())) - 1;        
        jTFComm.setText(commNumb.toString());
        if(!jTFComm.getText().equals("0")) {
            jPComm.setBackground(new Color(204, 255, 204));
        } else {
            jPComm.setBackground(new Color(204, 204, 204));
        }
        jTComm.setValueAt("1", jTComm.getSelectedRow(), 4);
    }//GEN-LAST:event_jBCheckActionPerformed

    private void jTCommMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCommMouseClicked
        commId = (int)jTComm.getValueAt(jTComm.getSelectedRow(), 0);
        jTFTitreComm.setText(listDesComm.get(commId)[0]);
        jTPDiscriptionComm.setText(listDesComm.get(commId)[1]);
        if(jTComm.getValueAt(jTComm.getSelectedRow(), 4).equals("0")) {
            jBCheck.setEnabled(true);
        } else {
            jBCheck.setEnabled(false);
        }
    }//GEN-LAST:event_jTCommMouseClicked

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
            java.util.logging.Logger.getLogger(JffCommentaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JffCommentaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JffCommentaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JffCommentaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JffCommentaire().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTComm;
    private javax.swing.JTextField jTFTitreComm;
    private javax.swing.JTextPane jTPDiscriptionComm;
    // End of variables declaration//GEN-END:variables
}
