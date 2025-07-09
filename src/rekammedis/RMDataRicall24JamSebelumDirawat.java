/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgDataSkriningGiziLanjut.java
 * Kontribusi Haris Rochmatullah RS Bhayangkara Nganjuk
 * Created on 11 November 2020, 20:19:56
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public class RMDataRicall24JamSebelumDirawat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private String finger="";
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMDataRicall24JamSebelumDirawat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Umur","JK","Tanggal","Makan Pagi","gr","URT",
            "Selingan Pagi","gr","URT","Makan Siang","gr","URT","Selingan Siang","gr","URT","Makan Sore","gr","URT",
            "Selingan Sore","gr","URT","Energi","Karbohidrat","Protein",
            "Lemak","NIP","Petugas","Tgl.Lahir"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 31; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(35);
            }else if(i==4){
                column.setPreferredWidth(20);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(20);
            }else if(i==8){
                column.setPreferredWidth(30);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(20);
            }else if(i==11){
                column.setPreferredWidth(30);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(20);
            }else if(i==14){
                column.setPreferredWidth(30);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setPreferredWidth(20);
            }else if(i==17){
                column.setPreferredWidth(30);
            }else if(i==18){
                column.setPreferredWidth(100);
            }else if(i==19){
                column.setPreferredWidth(20);
            }else if(i==20){
                column.setPreferredWidth(30);
            }else if(i==21){
                column.setPreferredWidth(100);
            }else if(i==22){
                column.setPreferredWidth(20);
            }else if(i==23){
                column.setPreferredWidth(30);
                 }else if(i==24){
                column.setPreferredWidth(80);
            }else if(i==25){
                column.setPreferredWidth(80);
            }else if(i==26){
                column.setPreferredWidth(80);
                 }else if(i==27){
                column.setPreferredWidth(80);
                }else if(i==28){
                column.setPreferredWidth(100);
                }else if(i==29){
                column.setPreferredWidth(100);
                }else if(i==30){
                column.setPreferredWidth(100);
            }else{
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdPetugas.setDocument(new batasInput((byte)20).getKata(KdPetugas));
//        MSoregr.setDocument(new batasInput((byte)5).getKata(MSoregr));
//        SSore.setDocument(new batasInput((byte)5).getKata(SSore));
////        Alergi.setDocument(new batasInput((byte)25).getKata(Alergi));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                KdPetugas.requestFocus();
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
        
        SSore.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
        });
        
        MSoregr.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isBMI();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isBMI();
            }
        });
        
        ChkInput.setSelected(false);
        isForm();
        
        jam();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSkriningGizi = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel12 = new widget.Label();
        SSore = new widget.TextBox();
        jLabel13 = new widget.Label();
        MSoregr = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        MPagi = new widget.TextBox();
        SPagi = new widget.TextBox();
        SSiang = new widget.TextBox();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        MSiang = new widget.TextBox();
        SSoreurt = new widget.TextBox();
        Lemak = new widget.TextBox();
        MPagigr = new widget.TextBox();
        MSianggr = new widget.TextBox();
        MSiangurt = new widget.TextBox();
        MPagiurt = new widget.TextBox();
        SPagigr = new widget.TextBox();
        SSianggr = new widget.TextBox();
        SSoregr = new widget.TextBox();
        SPagiurt = new widget.TextBox();
        SSiangurt = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        MSoreurt = new widget.TextBox();
        Energi = new widget.TextBox();
        Karbohidrat = new widget.TextBox();
        Protein = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        MSore = new widget.TextBox();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSkriningGizi.setBackground(new java.awt.Color(255, 255, 254));
        MnSkriningGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSkriningGizi.setForeground(new java.awt.Color(50, 50, 50));
        MnSkriningGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSkriningGizi.setText("Formulir Skrining Gizi Lanjut");
        MnSkriningGizi.setName("MnSkriningGizi"); // NOI18N
        MnSkriningGizi.setPreferredSize(new java.awt.Dimension(250, 26));
        MnSkriningGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSkriningGiziActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSkriningGizi);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Ricall 24 Jam Sebelum Dirawat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50)));
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 244));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(200, 325));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 75, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(79, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2025" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(79, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 75, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(173, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(238, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(303, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(368, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setHighlighter(null);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(474, 40, 94, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(570, 40, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(761, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel12.setText("Lemak :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(620, 180, 75, 23);

        SSore.setFocusTraversalPolicyProvider(true);
        SSore.setName("SSore"); // NOI18N
        SSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SSoreActionPerformed(evt);
            }
        });
        SSore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSoreKeyPressed(evt);
            }
        });
        FormInput.add(SSore);
        SSore.setBounds(510, 140, 130, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("gr");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(690, 80, 30, 23);

        MSoregr.setFocusTraversalPolicyProvider(true);
        MSoregr.setName("MSoregr"); // NOI18N
        MSoregr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSoregrKeyPressed(evt);
            }
        });
        FormInput.add(MSoregr);
        MSoregr.setBounds(240, 140, 40, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("1.  Selingan Pagi");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(420, 80, 90, 23);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("2. Selingan Siang");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(420, 110, 110, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("3.  Selingan Sore");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(420, 140, 100, 23);

        jLabel27.setText("Rata - rata Sehari ");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(10, 180, 120, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("1. Makan Pagi");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(20, 80, 120, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("2. Makan Siang");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(20, 110, 110, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("3. Makan Sore");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(20, 140, 130, 23);

        MPagi.setFocusTraversalPolicyProvider(true);
        MPagi.setName("MPagi"); // NOI18N
        MPagi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MPagiKeyPressed(evt);
            }
        });
        FormInput.add(MPagi);
        MPagi.setBounds(110, 80, 130, 23);

        SPagi.setFocusTraversalPolicyProvider(true);
        SPagi.setName("SPagi"); // NOI18N
        SPagi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPagiKeyPressed(evt);
            }
        });
        FormInput.add(SPagi);
        SPagi.setBounds(510, 80, 130, 23);

        SSiang.setFocusTraversalPolicyProvider(true);
        SSiang.setName("SSiang"); // NOI18N
        SSiang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSiangKeyPressed(evt);
            }
        });
        FormInput.add(SSiang);
        SSiang.setBounds(510, 110, 130, 23);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("gr");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(750, 180, 30, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("URT");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(360, 80, 30, 23);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("URT");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(360, 140, 50, 23);

        MSiang.setFocusTraversalPolicyProvider(true);
        MSiang.setName("MSiang"); // NOI18N
        MSiang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSiangKeyPressed(evt);
            }
        });
        FormInput.add(MSiang);
        MSiang.setBounds(110, 110, 130, 23);

        SSoreurt.setFocusTraversalPolicyProvider(true);
        SSoreurt.setName("SSoreurt"); // NOI18N
        SSoreurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSoreurtKeyPressed(evt);
            }
        });
        FormInput.add(SSoreurt);
        SSoreurt.setBounds(710, 140, 40, 23);

        Lemak.setFocusTraversalPolicyProvider(true);
        Lemak.setName("Lemak"); // NOI18N
        Lemak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LemakKeyPressed(evt);
            }
        });
        FormInput.add(Lemak);
        Lemak.setBounds(700, 180, 40, 23);

        MPagigr.setFocusTraversalPolicyProvider(true);
        MPagigr.setName("MPagigr"); // NOI18N
        MPagigr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MPagigrKeyPressed(evt);
            }
        });
        FormInput.add(MPagigr);
        MPagigr.setBounds(240, 80, 40, 23);

        MSianggr.setFocusTraversalPolicyProvider(true);
        MSianggr.setName("MSianggr"); // NOI18N
        MSianggr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSianggrKeyPressed(evt);
            }
        });
        FormInput.add(MSianggr);
        MSianggr.setBounds(240, 110, 40, 23);

        MSiangurt.setFocusTraversalPolicyProvider(true);
        MSiangurt.setName("MSiangurt"); // NOI18N
        MSiangurt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MSiangurtActionPerformed(evt);
            }
        });
        MSiangurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSiangurtKeyPressed(evt);
            }
        });
        FormInput.add(MSiangurt);
        MSiangurt.setBounds(310, 110, 40, 23);

        MPagiurt.setFocusTraversalPolicyProvider(true);
        MPagiurt.setName("MPagiurt"); // NOI18N
        MPagiurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MPagiurtKeyPressed(evt);
            }
        });
        FormInput.add(MPagiurt);
        MPagiurt.setBounds(310, 80, 40, 23);

        SPagigr.setFocusTraversalPolicyProvider(true);
        SPagigr.setName("SPagigr"); // NOI18N
        SPagigr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPagigrKeyPressed(evt);
            }
        });
        FormInput.add(SPagigr);
        SPagigr.setBounds(640, 80, 40, 23);

        SSianggr.setFocusTraversalPolicyProvider(true);
        SSianggr.setName("SSianggr"); // NOI18N
        SSianggr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSianggrKeyPressed(evt);
            }
        });
        FormInput.add(SSianggr);
        SSianggr.setBounds(640, 110, 40, 23);

        SSoregr.setFocusTraversalPolicyProvider(true);
        SSoregr.setName("SSoregr"); // NOI18N
        SSoregr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSoregrKeyPressed(evt);
            }
        });
        FormInput.add(SSoregr);
        SSoregr.setBounds(640, 140, 40, 23);

        SPagiurt.setFocusTraversalPolicyProvider(true);
        SPagiurt.setName("SPagiurt"); // NOI18N
        SPagiurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPagiurtKeyPressed(evt);
            }
        });
        FormInput.add(SPagiurt);
        SPagiurt.setBounds(710, 80, 40, 23);

        SSiangurt.setFocusTraversalPolicyProvider(true);
        SSiangurt.setName("SSiangurt"); // NOI18N
        SSiangurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SSiangurtKeyPressed(evt);
            }
        });
        FormInput.add(SSiangurt);
        SSiangurt.setBounds(710, 110, 40, 23);

        jLabel20.setText("Energi :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(100, 180, 75, 23);

        jLabel22.setText("Karbohidrat :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(290, 180, 75, 23);

        jLabel25.setText("Protein :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(450, 180, 75, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("gr");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(690, 140, 30, 23);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Kkl");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(230, 180, 30, 23);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("gr");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(420, 180, 30, 23);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("gr");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(580, 180, 30, 23);

        MSoreurt.setFocusTraversalPolicyProvider(true);
        MSoreurt.setName("MSoreurt"); // NOI18N
        MSoreurt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSoreurtKeyPressed(evt);
            }
        });
        FormInput.add(MSoreurt);
        MSoreurt.setBounds(310, 140, 40, 23);

        Energi.setFocusTraversalPolicyProvider(true);
        Energi.setName("Energi"); // NOI18N
        Energi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnergiKeyPressed(evt);
            }
        });
        FormInput.add(Energi);
        Energi.setBounds(180, 180, 40, 23);

        Karbohidrat.setFocusTraversalPolicyProvider(true);
        Karbohidrat.setName("Karbohidrat"); // NOI18N
        Karbohidrat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KarbohidratKeyPressed(evt);
            }
        });
        FormInput.add(Karbohidrat);
        Karbohidrat.setBounds(370, 180, 40, 23);

        Protein.setFocusTraversalPolicyProvider(true);
        Protein.setName("Protein"); // NOI18N
        Protein.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProteinKeyPressed(evt);
            }
        });
        FormInput.add(Protein);
        Protein.setBounds(530, 180, 40, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("gr");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(290, 80, 30, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("gr");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(290, 110, 30, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("gr");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(290, 140, 30, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("gr");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(690, 110, 30, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("URT");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(760, 80, 50, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("URT");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(760, 110, 50, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("URT");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(760, 140, 50, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("URT");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(360, 110, 50, 23);

        MSore.setFocusTraversalPolicyProvider(true);
        MSore.setName("MSore"); // NOI18N
        MSore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MSoreKeyPressed(evt);
            }
        });
        FormInput.add(MSore);
        MSore.setBounds(110, 140, 130, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
                      
    if (TNoRw.getText().trim().isEmpty() || TPasien.getText().trim().isEmpty()) {
        Valid.textKosong(TNoRw, "pasien");
    } else if (KdPetugas.getText().trim().isEmpty() || NmPetugas.getText().trim().isEmpty()) {
        Valid.textKosong(KdPetugas, "Petugas");
    } else if (MPagi.getText().trim().isEmpty()) {
        Valid.textKosong(MPagi, "Makan Pagi");
    } else if (MSiang.getText().trim().isEmpty()) {
        Valid.textKosong(MSiang, "Makan Siang");
    } else if (MSore.getText().trim().isEmpty()) {
        Valid.textKosong(MSore, "Makan Sore");
//    } else if (MPagigr.getText().trim().isEmpty()) {
//        Valid.textKosong(MPagigr, "Gram Pagi");
//    } else if (MSianggr.getText().trim().isEmpty()) {
//        Valid.textKosong(MSianggr, "Gram Siang");
//    } else if (MSoregr.getText().trim().isEmpty()) {
//        Valid.textKosong(MSoregr, "Gram Sore");
//    } else if (MPagiurt.getText().trim().isEmpty()) {
//        Valid.textKosong(MPagiurt, "URT Pagi");
//    } else if (MSiangurt.getText().trim().isEmpty()) {
//        Valid.textKosong(MSiangurt, "URT Siang");
//    } else if (MSoreurt.getText().trim().isEmpty()) {
//        Valid.textKosong(MSoreurt, "URT Sore");
//    } else if (SPagi.getText().trim().isEmpty()) {
//        Valid.textKosong(SPagi, "Snack Pagi");
//    } else if (SSiang.getText().trim().isEmpty()) {
//        Valid.textKosong(SSiang, "Snack Siang");
//    } else if (SSore.getText().trim().isEmpty()) {
//        Valid.textKosong(SSore, "Snack Sore");
//    } else if (SPagigr.getText().trim().isEmpty()) {
//        Valid.textKosong(SPagigr, "Gram Snack Pagi");
//    } else if (SSianggr.getText().trim().isEmpty()) {
//        Valid.textKosong(SSianggr, "Gram Snack Siang");
//    } else if (SSoregr.getText().trim().isEmpty()) {
//        Valid.textKosong(SSoregr, "Gram Snack Sore");
//    } else if (SPagiurt.getText().trim().isEmpty()) {
//        Valid.textKosong(SPagiurt, "URT Snack Pagi");
//    } else if (SSiangurt.getText().trim().isEmpty()) {
//        Valid.textKosong(SSiangurt, "URT Snack Siang");
//    } else if (SSoreurt.getText().trim().isEmpty()) {
//        Valid.textKosong(SSoreurt, "URT Snack Sore");
    } else if (Energi.getText().trim().isEmpty()) {
        Valid.textKosong(Energi, "Energi");
    } else if (Karbohidrat.getText().trim().isEmpty()) {
        Valid.textKosong(Karbohidrat, "Karbohidrat");
    } else if (Protein.getText().trim().isEmpty()) {
        Valid.textKosong(Protein, "Protein");
    } else if (Lemak.getText().trim().isEmpty()) {
        Valid.textKosong(Lemak, "Lemak");
    } else {
        // Menyimpan data ke database
        if (Sequel.menyimpantf(
                "recall24jam_sebelumdirawat",
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                "Data",
                25,
                new String[]{
                    TNoRw.getText(),
                    Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                    MPagi.getText(),
                    MSiang.getText(),
                    MSore.getText(),
                    MPagigr.getText(),
                    MSianggr.getText(),
                    MSoregr.getText(),
                    MPagiurt.getText(),
                    MSiangurt.getText(),
                    MSoreurt.getText(),
                    SPagi.getText(),
                    SSiang.getText(),
                    SSore.getText(),
                    SPagigr.getText(),
                    SSianggr.getText(),
                    SSoregr.getText(),
                    SPagiurt.getText(),
                    SSiangurt.getText(),
                    SSoreurt.getText(),
                    Energi.getText(),
                    Karbohidrat.getText(),
                    Protein.getText(),
                    Lemak.getText(),
                    KdPetugas.getText()
                })) {
            tampil();
            emptTeks();
        }
    }


       


//if(TNoRw.getText().trim().isEmpty()||TPasien.getText().trim().isEmpty()){
//            Valid.textKosong(TNoRw,"pasien");
//        }else if(KdPetugas.getText().trim().isEmpty()||NmPetugas.getText().trim().isEmpty()){
//            Valid.textKosong(KdPetugas,"Petugas");
//        }else if(SSore.getText().trim().isEmpty()){
//            Valid.textKosong(SSore,"Berat Badan");
//        }else if(MSoregr.getText().trim().isEmpty()){
//            Valid.textKosong(MSoregr,"Tinggi Badan");
////        }else if(Alergi.getText().trim().isEmpty()){
////            Valid.textKosong(Alergi,"Alergi");
//        }else if(Skor1.getText().trim().isEmpty()){
//            Valid.textKosong(Skor1,"Skor 1");
//        }else if(Skor2.getText().trim().isEmpty()){
//            Valid.textKosong(Skor2,"Skor 2");
//        }else if(Skor3.getText().trim().isEmpty()){
//            Valid.textKosong(Skor1,"Skor 3");
//        }else{
////            isCombo1();
////            isCombo2();
////            isCombo3();
////            isjml();
////            isHitung();
//            if(Sequel.menyimpantf("skrining_gizi","?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",14,new String[]{
//                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
//                SSore.getText(),MSoregr.getText(),Alergi.getText(),
//                cmbSkor1.getSelectedItem().toString(),Skor1.getText(),cmbSkor2.getSelectedItem().toString(),Skor2.getText(),
//                cmbSkor3.getSelectedItem().toString(),Skor3.getText(),TotalSkor.getText(),ParameterSkor.getText(),KdPetugas.getText()
//            })==true){
//                tampil();
//                emptTeks();
//            }   
//        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,cmbSkor3,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().isEmpty()||TPasien.getText().trim().isEmpty()){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KdPetugas.getText().trim().isEmpty()||NmPetugas.getText().trim().isEmpty()){
            Valid.textKosong(KdPetugas,"Petugas");
        }else if(SSore.getText().trim().isEmpty()){
            Valid.textKosong(SSore,"Berat Badan");
//        }else if(MSoregr.getText().trim().isEmpty()){
//            Valid.textKosong(MSoregr,"Tinggi Badan");
//        }else if(Alergi.getText().trim().isEmpty()){
//            Valid.textKosong(Alergi,"Alergi");
//        }else if(Skor1.getText().trim().isEmpty()){
//            Valid.textKosong(Skor1,"Skor 1");
//        }else if(Skor2.getText().trim().isEmpty()){
//            Valid.textKosong(Skor2,"Skor 2");
//        }else if(Skor3.getText().trim().isEmpty()){
//            Valid.textKosong(Skor1,"Skor 3");
        }else{   
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            } 
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            
            if(TCari.getText().trim().isEmpty()){
                Valid.MyReportqry("rptDataSkriningGiziLanjut.jasper","report","::[ Data Skrining Gizi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
                    "pasien.jk,skrining_gizi.tanggal,skrining_gizi.skrining_bb,skrining_gizi.skrining_tb,skrining_gizi.alergi,"+
                    "skrining_gizi.parameter_imt,skrining_gizi.skor_imt,skrining_gizi.parameter_bb,skrining_gizi.skor_bb,skrining_gizi.parameter_penyakit,skrining_gizi.skor_penyakit,"+
                    "skrining_gizi.skor_total,skrining_gizi.parameter_total,skrining_gizi.nip,petugas.nama,date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir "+
                    "from skrining_gizi inner join reg_periksa on skrining_gizi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on skrining_gizi.nip=petugas.nip where "+
                    "skrining_gizi.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' order by skrining_gizi.tanggal ",param);
            }else{
                Valid.MyReportqry("rptDataSkriningGiziLanjut.jasper","report","::[ Data Skrining Gizi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
                    "pasien.jk,skrining_gizi.tanggal,skrining_gizi.skrining_bb,skrining_gizi.skrining_tb,skrining_gizi.alergi,"+
                    "skrining_gizi.parameter_imt,skrining_gizi.skor_imt,skrining_gizi.parameter_bb,skrining_gizi.skor_bb,skrining_gizi.parameter_penyakit,skrining_gizi.skor_penyakit,"+
                    "skrining_gizi.skor_total,skrining_gizi.parameter_total,skrining_gizi.nip,petugas.nama,date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir "+
                    "from skrining_gizi inner join reg_periksa on skrining_gizi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on skrining_gizi.nip=petugas.nip "+
                    "where skrining_gizi.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and "+
                    "(reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%'  or pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' "+
                    "or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or skrining_gizi.alergi like '%"+TCari.getText().trim()+"%' "+
                    "or skrining_gizi.parameter_total like '%"+TCari.getText().trim()+"%' or skrining_gizi.nip like '%"+TCari.getText().trim()+"%' or petugas.nama like ?) "+
                    "order by skrining_gizi.tanggal ",param);
            }  
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Detik.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            Alergi.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Detik,SSore);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnSkriningGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSkriningGiziActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),18).toString()+"\nID "+(finger.isEmpty()?tbObat.getValueAt(tbObat.getSelectedRow(),17).toString():finger)+"\n"+Tanggal.getSelectedItem()); 
            param.put("diagnosa",Sequel.cariIsi("select diagnosa_awal from kamar_inap where diagnosa_awal<>'' and no_rawat=? ",TNoRw.getText()));
            Valid.MyReportqry("rptFormulirSkriningGizi.jasper","report","::[ Formulir Monitoring & Evaluasi Asuhan Gizi Pasien ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur, "+
                    "pasien.jk,pasien.tgl_lahir,skrining_gizi.tanggal,skrining_gizi.alergi,skrining_gizi.skrining_bb,skrining_gizi.skrining_tb, "+
                    "skrining_gizi.parameter_imt,skrining_gizi.skor_imt,skrining_gizi.parameter_bb,skrining_gizi.skor_bb, "+
                    "skrining_gizi.parameter_penyakit,skrining_gizi.skor_penyakit,skrining_gizi.skor_total,skrining_gizi.parameter_total, "+
                    "skrining_gizi.nip,petugas.nama "+
                    "from skrining_gizi inner join reg_periksa on skrining_gizi.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on skrining_gizi.nip=petugas.nip where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' and skrining_gizi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"'",param);
        }
    }//GEN-LAST:event_MnSkriningGiziActionPerformed

    private void SSoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSoreKeyPressed
        Valid.pindah(evt,btnPetugas,MSoregr);
    }//GEN-LAST:event_SSoreKeyPressed

    private void MSoregrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSoregrKeyPressed
//        Valid.pindah(evt,SSore,Alergi);
    }//GEN-LAST:event_MSoregrKeyPressed

    private void MPagiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MPagiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPagiKeyPressed

    private void SPagiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPagiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SPagiKeyPressed

    private void SSiangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSiangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSiangKeyPressed

    private void MSiangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSiangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSiangKeyPressed

    private void SSoreurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSoreurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSoreurtKeyPressed

    private void LemakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LemakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LemakKeyPressed

    private void MPagigrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MPagigrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPagigrKeyPressed

    private void MSianggrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSianggrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSianggrKeyPressed

    private void MSiangurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSiangurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSiangurtKeyPressed

    private void MPagiurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MPagiurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPagiurtKeyPressed

    private void SPagigrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPagigrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SPagigrKeyPressed

    private void SSianggrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSianggrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSianggrKeyPressed

    private void SSoregrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSoregrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSoregrKeyPressed

    private void SPagiurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPagiurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SPagiurtKeyPressed

    private void SSiangurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SSiangurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSiangurtKeyPressed

    private void MSoreurtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSoreurtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSoreurtKeyPressed

    private void EnergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnergiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnergiKeyPressed

    private void KarbohidratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KarbohidratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KarbohidratKeyPressed

    private void ProteinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProteinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProteinKeyPressed

    private void MSiangurtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MSiangurtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSiangurtActionPerformed

    private void SSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SSoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSoreActionPerformed

    private void MSoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MSoreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSoreKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataRicall24JamSebelumDirawat dialog = new RMDataRicall24JamSebelumDirawat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.TextBox Energi;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox Jam;
    private widget.TextBox Karbohidrat;
    private widget.TextBox KdPetugas;
    private widget.Label LCount;
    private widget.TextBox Lemak;
    private widget.TextBox MPagi;
    private widget.TextBox MPagigr;
    private widget.TextBox MPagiurt;
    private widget.TextBox MSiang;
    private widget.TextBox MSianggr;
    private widget.TextBox MSiangurt;
    private widget.TextBox MSore;
    private widget.TextBox MSoregr;
    private widget.TextBox MSoreurt;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnSkriningGizi;
    private widget.TextBox NmPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Protein;
    private widget.TextBox SPagi;
    private widget.TextBox SPagigr;
    private widget.TextBox SPagiurt;
    private widget.TextBox SSiang;
    private widget.TextBox SSianggr;
    private widget.TextBox SSiangurt;
    private widget.TextBox SSore;
    private widget.TextBox SSoregr;
    private widget.TextBox SSoreurt;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel25;
    private widget.Label jLabel27;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().isEmpty()){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, reg_periksa.umurdaftar, reg_periksa.sttsumur," +
                "pasien.jk, recall24jam_sebelumdirawat.tanggal, recall24jam_sebelumdirawat.mpagi, recall24jam_sebelumdirawat.msiang," +
                "recall24jam_sebelumdirawat.msore, recall24jam_sebelumdirawat.mpagigr, recall24jam_sebelumdirawat.msianggr," +
                "recall24jam_sebelumdirawat.msoregr, recall24jam_sebelumdirawat.mpagiurt, recall24jam_sebelumdirawat.msiangurt," +
                "recall24jam_sebelumdirawat.msoreurt, recall24jam_sebelumdirawat.spagi, recall24jam_sebelumdirawat.ssiang," +
                "recall24jam_sebelumdirawat.ssore, recall24jam_sebelumdirawat.spagigr, recall24jam_sebelumdirawat.ssianggr," +
                "recall24jam_sebelumdirawat.ssoregr, recall24jam_sebelumdirawat.spagiurt, recall24jam_sebelumdirawat.ssiangurt," +
                "recall24jam_sebelumdirawat.ssoreurt, recall24jam_sebelumdirawat.energi, recall24jam_sebelumdirawat.karbohidrat," +
                "recall24jam_sebelumdirawat.protein, recall24jam_sebelumdirawat.lemak, recall24jam_sebelumdirawat.nip," +
                "petugas.nama, date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir " +
                "from recall24jam_sebelumdirawat " +
                "inner join reg_periksa on recall24jam_sebelumdirawat.no_rawat = reg_periksa.no_rawat " +
                "inner join pasien on reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                "inner join petugas on recall24jam_sebelumdirawat.nip = petugas.nip " +
                "where recall24jam_sebelumdirawat.tanggal between ? and ? " +
                "order by recall24jam_sebelumdirawat.tanggal");
//                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
//                    "pasien.jk,"
//                            + "recall24jam_sebelumdirawat.tanggal,\n" +
//                            "recall24jam_sebelumdirawat.mpagi,\n" +
//                            "recall24jam_sebelumdirawat.msiang,\n" +
//                            "recall24jam_sebelumdirawat.msore,\n" +
//                            "recall24jam_sebelumdirawat.mpagigr,\n" +
//                            "recall24jam_sebelumdirawat.msianggr,\n" +
//                            "recall24jam_sebelumdirawat.msoregr,\n" +
//                            "recall24jam_sebelumdirawat.mpagiurt,\n" +
//                            "recall24jam_sebelumdirawat.msiangurt,\n" +
//                            "recall24jam_sebelumdirawat.msoreurt,\n" +
//                            "recall24jam_sebelumdirawat.spagi,\n" +
//                            "recall24jam_sebelumdirawat.ssiang,\n" +
//                            "recall24jam_sebelumdirawat.ssore,\n" +
//                            "recall24jam_sebelumdirawat.spagigr,\n" +
//                            "recall24jam_sebelumdirawat.ssianggr,\n" +
//                            "recall24jam_sebelumdirawat.ssoregr,\n" +
//                            "recall24jam_sebelumdirawat.spagiurt,\n" +
//                            "recall24jam_sebelumdirawat.ssiangurt,\n" +
//                            "recall24jam_sebelumdirawat.ssoreurt,\n" +
//                            "recall24jam_sebelumdirawat.energi,\n" +
//                            "recall24jam_sebelumdirawat.karbohidrat,\n" +
//                            "recall24jam_sebelumdirawat.protein,\n" +
//                            "recall24jam_sebelumdirawat.lemak,\n" +
//                            "recall24jam_sebelumdirawat.nip,"
//                            + "petugas.nama,date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir "+
//                    "from recall24jam_sebelumdirawat inner join reg_periksa on recall24jam_sebelumdirawat.no_rawat=reg_periksa.no_rawat "+
//                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "inner join petugas on recall24jam_sebelumdirawat.nip=petugas.nip where "+
//                    "recall24jam_sebelumdirawat.tanggal between ? and ? order by recall24jam_sebelumdirawat.tanggal ");
            
//            else{
//                ps=koneksi.prepareStatement(
//                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,reg_periksa.sttsumur,"+
//                    "pasien.jk,"
//                            + "recall24jam_sebelumdirawat.tanggal,\n" +
//                            "recall24jam_sebelumdirawat.mpagi,\n" +
//                            "recall24jam_sebelumdirawat.msiang,\n" +
//                            "recall24jam_sebelumdirawat.msore,\n" +
//                            "recall24jam_sebelumdirawat.mpagigr,\n" +
//                            "recall24jam_sebelumdirawat.msianggr,\n" +
//                            "recall24jam_sebelumdirawat.msoregr,\n" +
//                            "recall24jam_sebelumdirawat.mpagiurt,\n" +
//                            "recall24jam_sebelumdirawat.msiangurt,\n" +
//                            "recall24jam_sebelumdirawat.msoreurt,\n" +
//                            "recall24jam_sebelumdirawat.spagi,\n" +
//                            "recall24jam_sebelumdirawat.ssiang,\n" +
//                            "recall24jam_sebelumdirawat.ssore,\n" +
//                            "recall24jam_sebelumdirawat.spagigr,\n" +
//                            "recall24jam_sebelumdirawat.ssianggr,\n" +
//                            "recall24jam_sebelumdirawat.ssoregr,\n" +
//                            "recall24jam_sebelumdirawat.spagiurt,\n" +
//                            "recall24jam_sebelumdirawat.ssiangurt,\n" +
//                            "recall24jam_sebelumdirawat.ssoreurt,\n" +
//                            "recall24jam_sebelumdirawat.energi,\n" +
//                            "recall24jam_sebelumdirawat.karbohidrat,\n" +
//                            "recall24jam_sebelumdirawat.protein,\n" +
//                            "recall24jam_sebelumdirawat.lemak,\n" +
//                            "recall24jam_sebelumdirawat.nip,"
//                            + "petugas.nama,date_format(pasien.tgl_lahir,'%d-%m-%Y') as lahir "+
//                    "from recall24jam_sebelumdirawat inner join reg_periksa on recall24jam_sebelumdirawat.no_rawat=reg_periksa.no_rawat "+
//                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "inner join petugas on recall24jam_sebelumdirawat.nip=petugas.nip "+
//                    "where recall24jam_sebelumdirawat.tanggal between ? and ? and "+
//                    "(reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or recall24jam_sebelumdirawat.alergi like ? or skrining_gizi.parameter_total like ? or skrining_gizi.nip like ? or petugas.nama like ?) "+
//                    "order by recall24jam_sebelumdirawat.tanggal ")
;
            }
                
            try {
                if(TCari.getText().trim().isEmpty()){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }
                else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                    ps.setString(10,"%"+TCari.getText()+"%");
                    ps.setString(11,"%"+TCari.getText()+"%");
                    ps.setString(12,"%"+TCari.getText()+"%");
                    ps.setString(13,"%"+TCari.getText()+"%");
                    ps.setString(14,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur"),
                        rs.getString("jk"),
                        rs.getString("tanggal"),
                        rs.getString("mpagi"),
                        rs.getString("msiang"),
                        rs.getString("msore"),
                        rs.getString("mpagigr"),
                        rs.getString("msianggr"),
                        rs.getString("msoregr"),
                        rs.getString("mpagiurt"),
                        rs.getString("msiangurt"),
                        rs.getString("msoreurt"),
                        rs.getString("spagi"),
                        rs.getString("ssiang"),
                        rs.getString("ssore"),
                        rs.getString("spagigr"),
                        rs.getString("ssianggr"),
                        rs.getString("ssoregr"),
                        rs.getString("spagiurt"),
                        rs.getString("ssiangurt"),
                        rs.getString("ssoreurt"),
                        rs.getString("energi"),
                        rs.getString("karbohidrat"),
                        rs.getString("protein"),
                        rs.getString("lemak"),
                        rs.getString("nip"),
                        rs.getString("nama"),
                        rs.getString("lahir")
//                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
//                        rs.getString("umurdaftar")+" "+rs.getString("sttsumur"),rs.getString("jk"),
//                        rs.getString("tanggal"),rs.getString("skrining_bb"),rs.getString("skrining_tb"),rs.getString("alergi"),
//                        rs.getString("parameter_imt"),rs.getString("skor_imt"),rs.getString("parameter_bb"),rs.getString("skor_bb"),
//                        rs.getString("parameter_penyakit"),rs.getString("skor_penyakit"),rs.getString("skor_total"),rs.getString("parameter_total"),
//                        rs.getString("nip"),rs.getString("nama"),rs.getString("lahir")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    private void isCombo1(){
//        if(cmbSkor1.getSelectedItem().equals("IMT 18,5-20/-2 =< z score =< 2")){
//            Skor1.setText("1");
//        }else if(cmbSkor1.getSelectedItem().equals("IMT < 18,5/z score < -2")){
//            Skor1.setText("2");
//        }else{
//            Skor1.setText("0");
//        }
    }
    
    private void isCombo2(){
//        if(cmbSkor2.getSelectedItem().equals("BB Hilang 5 - 10 %")){
//            Skor2.setText("1");
//        }else if(cmbSkor2.getSelectedItem().equals("BB Hilang > 10 %")){
//            Skor2.setText("2");
//        }else{
//            Skor2.setText("0");
//        }
    }
    
    private void isCombo3(){
//        if(cmbSkor3.getSelectedItem().equals("Tidak ada asupan nutrisi > 5 hari")){
//            Skor3.setText("2");
//        }else{
//            Skor3.setText("0");
//        }
    } 
    
    private void isjml(){
//        if((!Skor1.getText().isEmpty())&&(!Skor2.getText().isEmpty())&&(!Skor3.getText().isEmpty())){
//            TotalSkor.setText(Valid.SetAngka2(
//                    Double.parseDouble(Skor1.getText().trim())+
//                    Double.parseDouble(Skor2.getText().trim())+
//                    Double.parseDouble(Skor3.getText().trim())
//            ));
//        }
    }
    
    private void isHitung(){
//        if(TotalSkor.getText().equals("0")){
//            ParameterSkor.setText("Beresiko rendah, ulangi 7 hari");
//        }else if(TotalSkor.getText().equals("1")){
//            ParameterSkor.setText("Beresiko menengah, monitoring asupan selama 3 hari");
//        }else{
//            ParameterSkor.setText("Beresiko tinggi, bekerja sama dengan tim dukungan gizi upayakan peningkatan asupan gizi dan memberikan makanan sesuai dengan daya terima");
//        }
    }
    
    public void emptTeks() {
        
        SSore.setText("");
        MSoregr.setText("");
       
        Tanggal.setDate(new Date());
//        cmbSkor1.setSelectedIndex(0);
//        Skor1.setText("0");
//        cmbSkor2.setSelectedIndex(0);
//        Skor2.setText("0");
//        cmbSkor3.setSelectedIndex(0);
//        Skor3.setText("0");
//        TotalSkor.setText("0");
//        ParameterSkor.setText("Beresiko rendah, ulangi 7 hari");
        SSore.requestFocus();
        MPagi.setText("");
        MSiang.setText("");
        MSore.setText("");
        MPagigr.setText("");
        MSianggr.setText("");
        MSoregr.setText("");
        MPagiurt.setText("");
        MSiangurt.setText("");
        MSoreurt.setText("");

        SPagi.setText("");
        SSiang.setText("");
        SSore.setText("");
        SPagigr.setText("");
        SSianggr.setText("");
        SSoregr.setText("");
        SPagiurt.setText("");
        SSiangurt.setText("");
        SSoreurt.setText("");

        Energi.setText("");
        Karbohidrat.setText("");
        Protein.setText("");
        Lemak.setText("");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());  
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,15));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
//            MPagi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
//            MSoregr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            MPagi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            MSiang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            MSore.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            MPagigr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            MSianggr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            MSoregr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            MPagiurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            MSiangurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            MSoreurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());

            SPagi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            SSiang.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            SSore.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            SPagigr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            SSianggr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            SSoregr.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            SPagiurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            SSiangurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            SSoreurt.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());

            Energi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString());
            Karbohidrat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());
            Protein.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
            Lemak.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
        Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=? ",TglLahir,TNoRM.getText());
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,244));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getskrining_gizi());
        BtnHapus.setEnabled(akses.getskrining_gizi());
        BtnEdit.setEnabled(akses.getskrining_gizi());
        BtnPrint.setEnabled(akses.getskrining_gizi()); 
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            btnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if(NmPetugas.getText().isEmpty()){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    private void isBMI(){
        if((Valid.SetAngka(MSoregr.getText())>0)&&(Valid.SetAngka(SSore.getText())>0)){
            try {
                MSore.setText(Valid.SetAngka8(Valid.SetAngka(SSore.getText())/((Valid.SetAngka(MSoregr.getText())/100)*(Valid.SetAngka(MSoregr.getText())/100)),1)+"");
            } catch (Exception e) {
                MSore.setText("");
            }
        }
    }

    private void ganti() {
//        isCombo1();
//        isCombo2();
//        isCombo3();
//        isjml();
//        isHitung();
        Sequel.mengedit("skrining_gizi","tanggal=? and no_rawat=?","no_rawat=?,tanggal=?,skrining_bb=?,skrining_tb=?,alergi=?,parameter_imt=?,skor_imt=?,"+
            "parameter_bb=?,skor_bb=?,parameter_penyakit=?,skor_penyakit=?,skor_total=?,parameter_total=?,nip=?",16,new String[]{
            TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
//            SSore.getText(),MSoregr.getText(),Alergi.getText(),cmbSkor1.getSelectedItem().toString(),Skor1.getText(),cmbSkor2.getSelectedItem().toString(),Skor2.getText(),
//            cmbSkor3.getSelectedItem().toString(),Skor3.getText(),TotalSkor.getText(),ParameterSkor.getText(),KdPetugas.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        });
        if(tabMode.getRowCount()!=0){tampil();}
        emptTeks();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from recall24jam_sebelumdirawat where tanggal=? and no_rawat=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
}
