/*
 * JDialogAPropos.java
 *
 * Created on 13 mars 2005, 23:04
 */

package P2S.UI.View.JDialog;

/**
 *
 * @author  Conde Mike K
 */
public class JDialogAPropos extends javax.swing.JDialog {
    
    /** Creates new form JDialogAPropos */
    public JDialogAPropos (java.awt.Frame parent, boolean modal) {
	super (parent, modal);
	initComponents ();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        memberPanel = new javax.swing.JPanel();
        members = new javax.swing.JPanel();
        m1Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        l3Panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        logo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        GNUPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gnu = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("P2S - Project Supervisor System");
        setModal(true);
        setResizable(false);
        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(new javax.swing.border.EtchedBorder());
        jTabbedPane1.setFocusable(false);
        memberPanel.setLayout(new java.awt.GridLayout(1, 2));

        memberPanel.setBackground(new java.awt.Color(255, 255, 255));
        members.setLayout(new java.awt.GridLayout(2, 1));

        m1Panel.setLayout(new java.awt.GridLayout(5, 1, 5, 10));

        m1Panel.setBackground(new java.awt.Color(255, 255, 255));
        m1Panel.setBorder(new javax.swing.border.TitledBorder("Groupe M1"));
        jLabel1.setText("SOUYRIS Matthieu : chef de projet");
        m1Panel.add(jLabel1);

        jLabel2.setText("BOUYSSOU Fabien : Sp\u00e9cialiste outils");
        m1Panel.add(jLabel2);

        jLabel3.setText("LAFFARGUE Nicolas : Webmaster - Sp\u00e9cialiste IHM");
        m1Panel.add(jLabel3);

        jLabel4.setText("LAROCHE Guillaume : Architecte ");
        m1Panel.add(jLabel4);

        jLabel5.setText("DEL COL Nicolas : Sp\u00e9cialiste IHM");
        m1Panel.add(jLabel5);

        members.add(m1Panel);

        l3Panel.setLayout(new java.awt.GridLayout(5, 1, 5, 10));

        l3Panel.setBackground(new java.awt.Color(255, 255, 255));
        l3Panel.setBorder(new javax.swing.border.TitledBorder("Groupe L3"));
        jLabel6.setText("CONDE Mickael K. : Responsable L3");
        l3Panel.add(jLabel6);

        jLabel7.setText("BADAOUI Kassem : D\u00e9veloppeur");
        l3Panel.add(jLabel7);

        jLabel8.setText("CANAYE Kurvin : D\u00e9veloppeur");
        l3Panel.add(jLabel8);

        jLabel9.setText("GUENATRI Kamil : D\u00e9veloppeur");
        l3Panel.add(jLabel9);

        jLabel10.setText("TAYAC Julie : D\u00e9veloppeur");
        l3Panel.add(jLabel10);

        members.add(l3Panel);

        memberPanel.add(members);

        logo.setLayout(new java.awt.GridLayout(1, 1));

        logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/P2S/Resources/Logo.png")));
        jLabel11.setIconTextGap(0);
        logo.add(jLabel11);

        memberPanel.add(logo);

        jTabbedPane1.addTab("Equipes de d\u00e9veloppement", memberPanel);

        GNUPanel.setLayout(new java.awt.BorderLayout());

        gnu.setEditable(false);
        gnu.setText("Project Supervisor System\n\nCopyright (C) 2004-2005 IUP ISI\n\nThis program is free software; you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation; either version 2 of the License, or\n(at your option) any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.");
        jScrollPane1.setViewportView(gnu);

        GNUPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Licence GNU", GNUPanel);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        buttonPanel.setBorder(new javax.swing.border.EtchedBorder());
        jButton1.setLabel("Fermer");
        jButton1.setName("bouton");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonPanel.add(jButton1);
        jButton1.getAccessibleContext().setAccessibleName("closeButton");

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	this.dispose() ;
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main (String args[]) {
	java.awt.EventQueue.invokeLater (new Runnable () {
	    public void run () {
		new JDialogAPropos (new javax.swing.JFrame (), true).setVisible (true);
	    }
	});
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GNUPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextArea gnu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel l3Panel;
    private javax.swing.JPanel logo;
    private javax.swing.JPanel m1Panel;
    private javax.swing.JPanel memberPanel;
    private javax.swing.JPanel members;
    // End of variables declaration//GEN-END:variables
    
}
