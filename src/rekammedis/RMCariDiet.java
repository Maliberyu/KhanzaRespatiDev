/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class RMCariDiet extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String norawat="";
    private int z=0,i=0,jml=0,index=0;
    private boolean[] pilih;
    private String[] tanggal,jam,diet;
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public RMCariDiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

//        Object[] row={"Tanggal","Waktu","Diet"};
//        tabMode=new DefaultTableModel(null,row){
//              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
//        };
//        tbKamar.setModel(tabMode);
        tabMode=new DefaultTableModel(null,new Object[]{
                "P","Tanggal","Jam","Diet"
            }){
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
               boolean a = false;
               if (colIndex==0) {
                 a=true;
               }
               return a;
             }
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbKamar.setModel(tabMode);
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (z= 0; z < 4; z++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(z);
            if(z==0){
                column.setPreferredWidth(15);
            }else if(z==1){
                column.setPreferredWidth(65);
            }else if(z==2){
                column.setPreferredWidth(50);
            }else if(z==3){
                column.setPreferredWidth(750);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    Popup = new javax.swing.JPopupMenu();
    ppBersihkan = new javax.swing.JMenuItem();
    ppSemua = new javax.swing.JMenuItem();
    internalFrame1 = new widget.InternalFrame();
    Scroll = new widget.ScrollPane();
    tbKamar = new widget.Table();
    panelisi3 = new widget.panelisi();
    label9 = new widget.Label();
    TCari = new widget.TextBox();
    BtnCari = new widget.Button();
    BtnAll = new widget.Button();
    label10 = new widget.Label();
    LCount = new widget.Label();
    BtnKeluar = new widget.Button();

    Popup.setName("Popup"); // NOI18N

    ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
    ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
    ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
    ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
    ppBersihkan.setText("Bersihkan Pilihan");
    ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    ppBersihkan.setName("ppBersihkan"); // NOI18N
    ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
    ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ppBersihkanActionPerformed(evt);
      }
    });
    Popup.add(ppBersihkan);

    ppSemua.setBackground(new java.awt.Color(255, 255, 254));
    ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
    ppSemua.setForeground(new java.awt.Color(50, 50, 50));
    ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
    ppSemua.setText("Pilih Semua");
    ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    ppSemua.setName("ppSemua"); // NOI18N
    ppSemua.setPreferredSize(new java.awt.Dimension(200, 25));
    ppSemua.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ppSemuaActionPerformed(evt);
      }
    });
    Popup.add(ppSemua);

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setUndecorated(true);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowActivated(java.awt.event.WindowEvent evt) {
        formWindowActivated(evt);
      }
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Riwayat Diet Diberikan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
    internalFrame1.setName("internalFrame1"); // NOI18N
    internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

    Scroll.setName("Scroll"); // NOI18N
    Scroll.setOpaque(true);

    tbKamar.setAutoCreateRowSorter(true);
    tbKamar.setComponentPopupMenu(Popup);
    tbKamar.setName("tbKamar"); // NOI18N
    tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tbKamarMouseClicked(evt);
      }
    });
    tbKamar.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        tbKamarKeyPressed(evt);
      }
    });
    Scroll.setViewportView(tbKamar);

    internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

    panelisi3.setName("panelisi3"); // NOI18N
    panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
    panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

    label9.setText("Key Word :");
    label9.setName("label9"); // NOI18N
    label9.setPreferredSize(new java.awt.Dimension(68, 23));
    panelisi3.add(label9);

    TCari.setName("TCari"); // NOI18N
    TCari.setPreferredSize(new java.awt.Dimension(300, 23));
    TCari.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        TCariKeyPressed(evt);
      }
    });
    panelisi3.add(TCari);

    BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
    BtnCari.setMnemonic('1');
    BtnCari.setToolTipText("Alt+1");
    BtnCari.setName("BtnCari"); // NOI18N
    BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
    BtnCari.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnCariActionPerformed(evt);
      }
    });
    BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        BtnCariKeyPressed(evt);
      }
    });
    panelisi3.add(BtnCari);

    BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
    BtnAll.setMnemonic('2');
    BtnAll.setToolTipText("2Alt+2");
    BtnAll.setName("BtnAll"); // NOI18N
    BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
    BtnAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnAllActionPerformed(evt);
      }
    });
    BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        BtnAllKeyPressed(evt);
      }
    });
    panelisi3.add(BtnAll);

    label10.setText("Record :");
    label10.setName("label10"); // NOI18N
    label10.setPreferredSize(new java.awt.Dimension(60, 23));
    panelisi3.add(label10);

    LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    LCount.setText("0");
    LCount.setName("LCount"); // NOI18N
    LCount.setPreferredSize(new java.awt.Dimension(50, 23));
    panelisi3.add(LCount);

    BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
    BtnKeluar.setMnemonic('4');
    BtnKeluar.setToolTipText("Alt+4");
    BtnKeluar.setName("BtnKeluar"); // NOI18N
    BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
    BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnKeluarActionPerformed(evt);
      }
    });
    panelisi3.add(BtnKeluar);

    internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

    getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbKamar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if(tabMode.getRowCount()!=0){
            if(evt.getClickCount()==2){
                dispose();
            }
        }
}//GEN-LAST:event_tbKamarMouseClicked

    private void tbKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamarKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbKamarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

  private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    for(i=0;i<tbKamar.getRowCount();i++){
      tbKamar.setValueAt(false,i,0);
    }
  }//GEN-LAST:event_ppBersihkanActionPerformed

  private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
    for(i=0;i<tbKamar.getRowCount();i++){
      tbKamar.setValueAt(true,i,0);
    }
  }//GEN-LAST:event_ppSemuaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCariDiet dialog = new RMCariDiet(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private widget.Button BtnAll;
  private widget.Button BtnCari;
  private widget.Button BtnKeluar;
  private widget.Label LCount;
  private javax.swing.JPopupMenu Popup;
  private widget.ScrollPane Scroll;
  private widget.TextBox TCari;
  private widget.InternalFrame internalFrame1;
  private widget.Label label10;
  private widget.Label label9;
  private widget.panelisi panelisi3;
  private javax.swing.JMenuItem ppBersihkan;
  private javax.swing.JMenuItem ppSemua;
  private widget.Table tbKamar;
  // End of variables declaration//GEN-END:variables

    public void tampil() {
        jml=0;
        for(i=0;i<tabMode.getRowCount();i++){
            if(tabMode.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }

        pilih=null;
        pilih=new boolean[jml]; 
        tanggal=null;
        tanggal=new String[jml];
        jam=null;
        jam=new String[jml];
        diet=null;
        diet=new String[jml];
        
        index=0;        
        for(i=0;i<tabMode.getRowCount();i++){
            if(tabMode.getValueAt(i,0).toString().equals("true")){
                pilih[index]=true;
                tanggal[index]=tabMode.getValueAt(i,1).toString();
                jam[index]=tabMode.getValueAt(i,2).toString();
                diet[index]=tabMode.getValueAt(i,3).toString();
                index++;
            }
        }       

        Valid.tabelKosong(tabMode);

        for(i=0;i<jml;i++){
            tabMode.addRow(new Object[] {
                pilih[i],tanggal[i],jam[i],diet[i]
            });
        }
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select detail_beri_diet.tanggal, detail_beri_diet.waktu, diet.nama_diet "+
                    "from detail_beri_diet INNER JOIN diet ON detail_beri_diet.kd_diet=diet.kd_diet where "+
                    "detail_beri_diet.no_rawat=? and (detail_beri_diet.tanggal like ? or diet.nama_diet like ?) "+
                    "order by detail_beri_diet.tanggal,detail_beri_diet.waktu");
            try{
                ps.setString(1,norawat);
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[] {
                        false,rs.getString(1),rs.getString(2),rs.getString(3)
                    });
                }
            }catch(Exception ex){
                System.out.println(ex);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {   
        TCari.requestFocus();
    }
    
    public void setNoRawat(String norawat){
        this.norawat=norawat;
    }

    public JTable getTable(){
        return tbKamar;
    }
    
}
