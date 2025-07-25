/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJadwal.java
 *
 * Created on May 22, 2010, 10:25:16 PM
 */

package kepegawaian;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgKehadiran2 extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private final Connection koneksi=koneksiDB.condb();
    private final sekuel Sequel=new sekuel();
    private final validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private String pilihan = "";
    private String dateString,dayOfWeek,hari,h1="",h2="",h3="",h4="",h5="",h6="",h7="",h8="",h9="",h10="",h11="",h12="",h13="",
                   h14="",h15="",h16="",h17="",h18="",h19="",h20="",h21="",h22="",h23="",h24="",h25="",h26="",h27="",h28="",h29="",h30="",h31="",
                   sqlps2="select rekap_presensi.shift from rekap_presensi where rekap_presensi.id=? and  rekap_presensi.jam_datang like ? ";
    private Date date = null;
    private DlgJamMasuk jammasuk=new DlgJamMasuk(null,false);
    private int i=0;

    /** Creates new form DlgJadwal
     * @param parent
     * @param modal */
    public DlgKehadiran2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tbJadwal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJadwal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Valid.LoadTahun(ThnCari);
        Valid.loadCombo(Departemen,"nama","departemen");
        Departemen.addItem("Semua");
        Departemen.setSelectedItem("Semua");

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
        
        jammasuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(jammasuk.getTable().getSelectedRow()!= -1){    
                    if(tbJadwal.getSelectedColumn()>4){
                        tabMode.setValueAt(jammasuk.getTable().getValueAt(jammasuk.getTable().getSelectedRow(),1).toString(),tbJadwal.getSelectedRow(),tbJadwal.getSelectedColumn());
                    }                    
                }   
                tbJadwal.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        jammasuk.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    jammasuk.dispose();
                }   
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJadwal = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        label11 = new widget.Label();
        ThnCari = new widget.ComboBox();
        BlnCari = new widget.ComboBox();
        label12 = new widget.Label();
        Departemen = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Kehadiran & Tambahan Jaga ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJadwal.setName("tbJadwal"); // NOI18N
        tbJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJadwalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJadwal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(57, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(68, 23));
        panelGlass8.add(LCount);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('m');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tahun & Bulan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(label11);

        ThnCari.setName("ThnCari"); // NOI18N
        ThnCari.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass7.add(ThnCari);

        BlnCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        BlnCari.setName("BlnCari"); // NOI18N
        BlnCari.setPreferredSize(new java.awt.Dimension(62, 23));
        panelGlass7.add(BlnCari);

        label12.setText("Departemen :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(77, 23));
        panelGlass7.add(label12);

        Departemen.setName("Departemen"); // NOI18N
        Departemen.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass7.add(Departemen);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(66, 23));
        jLabel6.setRequestFocusEnabled(false);
        panelGlass7.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

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
        panelGlass7.add(BtnCari);

        jPanel3.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().isEmpty()){
            BtnCariActionPerformed(evt);
        }
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        }else if(tabMode.getRowCount()!=0){
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            int row=tabMode.getRowCount();
            for(int r=0;r<row;r++){  
                Sequel.menyimpan("temporary","'"+r+"','"+
                                tabMode.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,3).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,4).toString().replaceAll("'","`")+"','"+
                                tabMode.getValueAt(r,5).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,6).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,7).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,8).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,9).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,10).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,11).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,12).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,13).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,14).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,15).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,16).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,17).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,18).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,19).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,20).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,21).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,22).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,23).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,24).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,25).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,26).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,27).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,28).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,29).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,30).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,31).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,32).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,33).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,34).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','"+
                                tabMode.getValueAt(r,35).toString().replaceAll("Midle ","").replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replaceAll("agi","").replaceAll("iang","").replaceAll("alam","").replaceAll(" ","")+"','','','"+akses.getalamatip()+"'","Rekap Presensi"); 
            }
            
            Map<String, Object> param = new HashMap<>();   
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("departemen",Departemen.getSelectedItem().toString().replaceAll("Semua",""));
                param.put("periode","01 - 31 BULAN "+BlnCari.getSelectedItem()+" TAHUN "+ThnCari.getSelectedItem());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                param.put("jd1","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),1)+")");
                param.put("jd2","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),2)+")");
                param.put("jd3","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),3)+")");
                param.put("jd4","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),4)+")");
                param.put("jd5","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),5)+")");
                param.put("jd6","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),6)+")");
                param.put("jd7","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),7)+")");
                param.put("jd8","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),8)+")");
                param.put("jd9","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),9)+")");
                param.put("jd10","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),10)+")");
                param.put("jd11","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),11)+")");
                param.put("jd12","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),12)+")");
                param.put("jd13","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),13)+")");
                param.put("jd14","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),14)+")");
                param.put("jd15","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),15)+")");
                param.put("jd16","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),16)+")");
                param.put("jd17","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),17)+")");
                param.put("jd18","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),18)+")");
                param.put("jd19","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),19)+")");
                param.put("jd20","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),20)+")");
                param.put("jd21","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),21)+")");
                param.put("jd22","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),22)+")");
                param.put("jd23","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),23)+")");
                param.put("jd24","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),24)+")");
                param.put("jd25","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),25)+")");
                param.put("jd26","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),26)+")");
                param.put("jd27","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),27)+")");
                param.put("jd28","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),28)+")");
                param.put("jd29","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),29)+")");
                param.put("jd30","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),30)+")");
                param.put("jd31","("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),31)+")");
                try{
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih model cetak..!","Jadwal",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Tampilkan Semua", "Tanpa departemen & jabatan"},"Tampilkan Semua");
                    switch (pilihan) {
                        case "Tampilkan Semua":
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            Valid.MyReportqry("rptRekapKehadiran.jasper","report","::[ Rekap Kehadiran Non Jadwal Tambahan ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);            
                            this.setCursor(Cursor.getDefaultCursor());
                            break;
                        case "Tanpa departemen & jabatan":
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            Valid.MyReportqry("rptRekapKehadiran2.jasper","report","::[ Rekap Kehadiran Non Jadwal Tambahan ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);            
                            this.setCursor(Cursor.getDefaultCursor());
                            break;
                    }
                }catch(Exception e){
                    System.out.println(e);
                }                 
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            //Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void tbJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJadwalKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                jammasuk.isCek();
                jammasuk.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight());
                jammasuk.setLocationRelativeTo(internalFrame1);
                jammasuk.setVisible(true);
            }else if(evt.getKeyCode()==KeyEvent.VK_DELETE){
                tabMode.setValueAt("",tbJadwal.getSelectedRow(),tbJadwal.getSelectedColumn());
            }
        }
    }//GEN-LAST:event_tbJadwalKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
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
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKehadiran2 dialog = new DlgKehadiran2(new javax.swing.JFrame(), true);
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
    private widget.ComboBox BlnCari;
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ComboBox Departemen;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.ComboBox ThnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.Label label11;
    private widget.Label label12;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.Table tbJadwal;
    // End of variables declaration//GEN-END:variables

    private void tampil() {        
        Object[] row={"No","ID","Nama","Pendidikan","Departemen",
            "1("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),1)+")",
            "2("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),2)+")",
            "3("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),3)+")",
            "4("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),4)+")",
            "5("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),5)+")",
            "6("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),6)+")",
            "7("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),7)+")",
            "8("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),8)+")",
            "9("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),9)+")",
            "10("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),10)+")",
            "11("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),11)+")",
            "12("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),12)+")",
            "13("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),13)+")",
            "14("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),14)+")",
            "15("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),15)+")",
            "16("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),16)+")",
            "17("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),17)+")",
            "18("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),18)+")",
            "19("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),19)+")",
            "20("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),20)+")",
            "21("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),21)+")",
            "22("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),22)+")",
            "23("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),23)+")",
            "24("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),24)+")",
            "25("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),25)+")",
            "26("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),26)+")",
            "27("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),27)+")",
            "28("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),28)+")",
            "29("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),29)+")",
            "30("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),30)+")",
            "31("+konversi(Integer.parseInt(ThnCari.getSelectedItem().toString()),Integer.parseInt(BlnCari.getSelectedItem().toString()),31)+")"
        };
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbJadwal.setModel(tabMode);
        
        for (int i = 0; i < 36; i++) {
            TableColumn column = tbJadwal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(50);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(120);
            }else{
                column.setPreferredWidth(75);
            }
        }
        tbJadwal.setDefaultRenderer(Object.class, new WarnaTable());
        
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select pegawai.id,pegawai.nik,pegawai.nama,pegawai.pendidikan,departemen.nama as departemen from pegawai inner join departemen "+
                    "on pegawai.departemen=departemen.dep_id where  pegawai.stts_aktif<>'KELUAR' and departemen.nama like ? and pegawai.nik like ? or "+
                    " pegawai.stts_aktif<>'KELUAR' and departemen.nama like ? and pegawai.nama like ? order by pegawai.nama");
            try {
                ps.setString(1,"%"+Departemen.getSelectedItem().toString().replaceAll("Semua","")+"%");
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                ps.setString(3,"%"+Departemen.getSelectedItem().toString().replaceAll("Semua","")+"%");
                ps.setString(4,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                i=1;
                while(rs.next()){   
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h1="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-01%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h1=h1+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h2="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-02%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h2=h2+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    					ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h3="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-03%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h3=h3+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h4="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-04%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h4=h4+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h5="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-05%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h5=h5+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h6="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-06%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h6=h6+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h7="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-07%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h7=h7+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h8="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-08%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h8=h8+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h9="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-09%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h9=h9+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h10="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-10%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h10=h10+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h11="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-11%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h11=h11+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h12="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-12%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h12=h12+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h13="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-13%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h13=h13+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h14="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-14%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h14=h14+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h15="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-15%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h15=h15+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h16="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-16%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h16=h16+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h17="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-17%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h17=h17+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h18="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-18%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h18=h18+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h19="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-19%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h19=h19+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h20="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-20%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h20=h20+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h21="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-21%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h21=h21+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h22="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-22%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h22=h22+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h23="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-23%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h23=h23+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h24="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-24%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h24=h24+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h25="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-25%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h25=h25+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h26="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-26%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h26=h26+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h27="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-27%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h27=h27+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h28="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-28%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h28=h28+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h29="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-29%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h29=h29+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h30="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-30%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h30=h30+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement(sqlps2);
                    try {   
                        h31="";
                        ps2.setString(1,rs.getString("id"));
                        ps2.setString(2,"%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"-31%");
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            h31=h31+rs2.getString(1)+" ";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }

                    tabMode.addRow(new Object[]{
                        " "+i+".",rs.getString("id"),rs.getString("nama"),rs.getString("pendidikan"),rs.getString("departemen"),
                        h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16,h17,h18,h19,h20,h21,h22,h23,h24,h25,h26,h27,h28,h29,h30,h31 
                    });
                    i++;
                }
            } catch (Exception e) {
                System.out.println();
            } finally{
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
    
    String konversi(int year, int month, int day){
        dateString = String.format("%d-%d-%d", year, month, day);        
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (Exception ex) {
            Logger.getLogger(DlgKehadiran2.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Then get the day of week from the Date based on specific locale.
        dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        switch (dayOfWeek) {
            case "Monday":
                hari="Senin";
                break;
            case "Tuesday":
                hari="Selasa";
                break;
            case "Wednesday":
                hari="Rabu";
                break;
            case "Thursday":
                hari="Kamis";
                break;
            case "Friday":
                hari="Jumat";
                break;
            case "Saturday":
                hari="Sabtu";
                break;
            case "Sunday":
                hari="Minggu";
                break;
        }
        return hari;
    }
    
}
