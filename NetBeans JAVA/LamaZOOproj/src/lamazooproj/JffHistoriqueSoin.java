/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lamazooproj;

import accesseBDD.Connexion;
import accesseBDD.LogIn;
import accesseBDD.sql.CheckAccesse;
import accesseBDD.sql.VeterinaireSQL;
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
public class JffHistoriqueSoin extends javax.swing.JFrame {
    private JFrame jFrame;
    private LogIn accesse;
    private int animalId;
    private VeterinaireSQL veterinaireSQL;
    private Connexion lamazoo;
    private CheckAccesse accesseChecker;
    private HashMap<Integer, String[]> listDesSoins;
    private int histId;
            
    /**
     * Creates new form JffHistoriqueSoin
     */
    public JffHistoriqueSoin() {
        initComponents();
    }
    
    public JffHistoriqueSoin(LogIn accesse, int animalId, VeterinaireSQL veterinaireSQL, Connexion lamazoo, CheckAccesse accesseChecker) {
        initComponents();
        jFrame = this;
        this.accesse = accesse;
        this.animalId = animalId;
        this.veterinaireSQL = veterinaireSQL;
        this.lamazoo = lamazoo;
        this.accesseChecker = accesseChecker;
        if(accesseChecker.check()) {
            listDesSoins = veterinaireSQL.getSoin(animalId);
            remplirTableau(listDesSoins);
        } else {
            badToken();
        }
        System.out.println("opachki");
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
                    jTHist.changeSelection(jTHist.getSelectedRow(), jTHist.getSelectedColumn(), true, false);
                    jTFTitreHist.setText("");
                    jTPDiscriptionHist.setText("");
                    jFrame.dispose();
                }
            }
        });
    }
    
    private void badToken() {
        JffAuthentification jffAuthentification = new JffAuthentification();
        jffAuthentification.setVisible(true);;
        this.dispose();
    }
    
    private void remplirTableau(HashMap<Integer, String[]> listDesSoins) {
        DefaultTableModel model = (DefaultTableModel) jTHist.getModel();
        listDesSoins.forEach((key, value) -> {
            model.addRow(new Object[] {key,value[0], value[5] + " " + value[6], value[2], value[3], value[4]});
        }); 
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
        jTHist = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFTitreHist = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTPDiscriptionHist = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTHist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Titre", "Nom de Soignant", "Date prescrire", "Date realisation", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTHist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTHistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTHist);
        if (jTHist.getColumnModel().getColumnCount() > 0) {
            jTHist.getColumnModel().getColumn(0).setMinWidth(0);
            jTHist.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTHist.getColumnModel().getColumn(0).setMaxWidth(0);
            jTHist.getColumnModel().getColumn(1).setResizable(false);
            jTHist.getColumnModel().getColumn(2).setResizable(false);
            jTHist.getColumnModel().getColumn(3).setResizable(false);
            jTHist.getColumnModel().getColumn(4).setResizable(false);
            jTHist.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Titre:");

        jLabel2.setText("Description:");

        jScrollPane2.setViewportView(jTPDiscriptionHist);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTitreHist))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFTitreHist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTHistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTHistMouseClicked
        histId = (int)jTHist.getValueAt(jTHist.getSelectedRow(), 0);
        jTFTitreHist.setText(listDesSoins.get(histId)[0]);
        jTPDiscriptionHist.setText(listDesSoins.get(histId)[1]);
    }//GEN-LAST:event_jTHistMouseClicked

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
            java.util.logging.Logger.getLogger(JffHistoriqueSoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JffHistoriqueSoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JffHistoriqueSoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JffHistoriqueSoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JffHistoriqueSoin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFTitreHist;
    private javax.swing.JTable jTHist;
    private javax.swing.JTextPane jTPDiscriptionHist;
    // End of variables declaration//GEN-END:variables
}