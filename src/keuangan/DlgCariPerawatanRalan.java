/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package keuangan;

import bridging.ApiPcare;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class DlgCariPerawatanRalan extends javax.swing.JDialog {
    private final DefaultTableModel TabModeTindakan;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement pstindakan,pstindakan2,pstindakan3,pstindakan4,
            pssimpandokter,pssimpanperawat,pssimpandokterperawat,psset_tarif,psrekening;
    private ResultSet rstindakan,rsset_tarif,rsrekening;
    private String pilihtable="",kd_pj="",kd_poli="",poli_ralan="Yes",cara_bayar_ralan="Yes",aktifpcare="no",nokunjungan="",sql="",requestJson="",URL="",otorisasi,
            Suspen_Piutang_Tindakan_Ralan="",Tindakan_Ralan="",Beban_Jasa_Medik_Dokter_Tindakan_Ralan="",Utang_Jasa_Medik_Dokter_Tindakan_Ralan="",
            Beban_Jasa_Medik_Paramedis_Tindakan_Ralan="",Utang_Jasa_Medik_Paramedis_Tindakan_Ralan="",Beban_KSO_Tindakan_Ralan="",Utang_KSO_Tindakan_Ralan="",
            Beban_Jasa_Sarana_Tindakan_Ralan="",Utang_Jasa_Sarana_Tindakan_Ralan="",HPP_BHP_Tindakan_Ralan="",Persediaan_BHP_Tindakan_Ralan="",
            Beban_Jasa_Menejemen_Tindakan_Ralan="",Utang_Jasa_Menejemen_Tindakan_Ralan="",utc="";
    private boolean[] pilih; 
    private String[] kode,nama,kategori;
    private double[] totaltnd,bagianrs,bhp,jmdokter,jmperawat,kso,menejemen;
    private int jml=0,i=0,index=0;
    public  DlgCariDokter dokter=new DlgCariDokter(null,false);
    public  DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApiPcare api=new ApiPcare();
    private double ttljmdokter=0,ttljmperawat=0,ttlkso=0,ttljasasarana=0,ttlbhp=0,ttlmenejemen=0,ttlpendapatan=0;
    private boolean sukses=false;
    private Jurnal jur=new Jurnal();
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private FileReader myObj;
    
    /**
     * Creates new form DlgPenyakit
     * @param parent
     * @param modal
     */
    public DlgCariPerawatanRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

        Object[] row={"P","Kode","Nama Perawatan","Kategori Perawatan","Tarif/Biaya","Bagian RS","BHP","JM Dokter","JM Perawat","KSO","Menejemen"};
        TabModeTindakan=new DefaultTableModel(null,row){
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,  
                java.lang.Object.class,java.lang.Double.class,java.lang.Double.class,
                java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,
                java.lang.Double.class,java.lang.Double.class
             };
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                 boolean a = false;
                 if (colIndex==0) {
                     a=true;
                 }
                 return a;
             }
             /*Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };*/
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTindakan.setModel(TabModeTindakan);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 11; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(420);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==9){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else{
                column.setPreferredWidth(120);
            }
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        TCariTindakan.setDocument(new batasInput((byte)100).getKata(TCariTindakan));    
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCariTindakan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCariTindakan.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCariTindakan.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCariTindakan.getText().length()>2){
                        tampil2();
                    }
                }
            });
        } 
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    nmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                }   
                kddokter.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){    
                    switch (pilihtable) {
                        case "rawat_jl_pr":
                            kddokter.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                            nmdokter.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                            kddokter.requestFocus();
                            break;
                        case "rawat_jl_drpr":
                            Nip2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                            NmPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());                        
                            Nip2.requestFocus();
                            break;
                    }
                }                  
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
        
        try {
            psset_tarif=koneksi.prepareStatement("select * from set_tarif");
            try {
                rsset_tarif=psset_tarif.executeQuery();
                if(rsset_tarif.next()){
                    poli_ralan=rsset_tarif.getString("poli_ralan");
                    cara_bayar_ralan=rsset_tarif.getString("cara_bayar_ralan");
                }else{
                    poli_ralan="Yes";
                    cara_bayar_ralan="Yes";
                }  
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rsset_tarif != null){
                    rsset_tarif.close();
                }
                if(psset_tarif != null){
                    psset_tarif.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        } 
        
        try {
            psrekening=koneksi.prepareStatement(
                "select set_akun_ralan.Suspen_Piutang_Tindakan_Ralan,set_akun_ralan.Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Medik_Dokter_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Medik_Dokter_Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Medik_Paramedis_Tindakan_Ralan,set_akun_ralan.Utang_Jasa_Medik_Paramedis_Tindakan_Ralan,"+
                "set_akun_ralan.Beban_KSO_Tindakan_Ralan,set_akun_ralan.Utang_KSO_Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Sarana_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Sarana_Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Menejemen_Tindakan_Ralan,set_akun_ralan.Utang_Jasa_Menejemen_Tindakan_Ralan,"+
                "set_akun_ralan.HPP_BHP_Tindakan_Ralan,set_akun_ralan.Persediaan_BHP_Tindakan_Ralan from set_akun_ralan");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Tindakan_Ralan=rsrekening.getString("Suspen_Piutang_Tindakan_Ralan");
                    Tindakan_Ralan=rsrekening.getString("Tindakan_Ralan");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ralan=rsrekening.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ralan=rsrekening.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Beban_Jasa_Medik_Paramedis_Tindakan_Ralan=rsrekening.getString("Beban_Jasa_Medik_Paramedis_Tindakan_Ralan");
                    Utang_Jasa_Medik_Paramedis_Tindakan_Ralan=rsrekening.getString("Utang_Jasa_Medik_Paramedis_Tindakan_Ralan");
                    Beban_KSO_Tindakan_Ralan=rsrekening.getString("Beban_KSO_Tindakan_Ralan");
                    Utang_KSO_Tindakan_Ralan=rsrekening.getString("Utang_KSO_Tindakan_Ralan");
                    Beban_Jasa_Sarana_Tindakan_Ralan=rsrekening.getString("Beban_Jasa_Sarana_Tindakan_Ralan");
                    Utang_Jasa_Sarana_Tindakan_Ralan=rsrekening.getString("Utang_Jasa_Sarana_Tindakan_Ralan");
                    Beban_Jasa_Menejemen_Tindakan_Ralan=rsrekening.getString("Beban_Jasa_Menejemen_Tindakan_Ralan");
                    Utang_Jasa_Menejemen_Tindakan_Ralan=rsrekening.getString("Utang_Jasa_Menejemen_Tindakan_Ralan");
                    HPP_BHP_Tindakan_Ralan=rsrekening.getString("HPP_BHP_Tindakan_Ralan");
                    Persediaan_BHP_Tindakan_Ralan=rsrekening.getString("Persediaan_BHP_Tindakan_Ralan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            otorisasi=koneksiDB.USERPCARE()+":"+koneksiDB.PASSPCARE()+":095";
            URL=koneksiDB.URLAPIPCARE();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
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

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppDokter = new javax.swing.JMenuItem();
        ppPetugas = new javax.swing.JMenuItem();
        ppPetugasDokter = new javax.swing.JMenuItem();
        TNoRw = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTindakan = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCariTindakan = new widget.TextBox();
        BtnCariTindakan = new widget.Button();
        BtnAllTindakan = new widget.Button();
        BtnTambahTindakan = new widget.Button();
        BtnSimpanTindakan = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel6 = new widget.Label();
        Nip2 = new widget.TextBox();
        NmPetugas2 = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel7 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Centang/Tindakan Terpilih");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(250, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppDokter.setBackground(new java.awt.Color(255, 255, 254));
        ppDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDokter.setForeground(new java.awt.Color(50, 50, 50));
        ppDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        ppDokter.setText("Ubah Ke Tindakan Dokter");
        ppDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDokter.setName("ppDokter"); // NOI18N
        ppDokter.setPreferredSize(new java.awt.Dimension(250, 25));
        ppDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDokterActionPerformed(evt);
            }
        });
        Popup.add(ppDokter);

        ppPetugas.setBackground(new java.awt.Color(255, 255, 254));
        ppPetugas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPetugas.setForeground(new java.awt.Color(50, 50, 50));
        ppPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        ppPetugas.setText("Ubah Ke Tindakan Petugas");
        ppPetugas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPetugas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPetugas.setName("ppPetugas"); // NOI18N
        ppPetugas.setPreferredSize(new java.awt.Dimension(250, 25));
        ppPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPetugasActionPerformed(evt);
            }
        });
        Popup.add(ppPetugas);

        ppPetugasDokter.setBackground(new java.awt.Color(255, 255, 254));
        ppPetugasDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPetugasDokter.setForeground(new java.awt.Color(50, 50, 50));
        ppPetugasDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        ppPetugasDokter.setText("Ubah Ke Tindakan Dokter & Petugas");
        ppPetugasDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPetugasDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPetugasDokter.setName("ppPetugasDokter"); // NOI18N
        ppPetugasDokter.setPreferredSize(new java.awt.Dimension(250, 25));
        ppPetugasDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPetugasDokterActionPerformed(evt);
            }
        });
        Popup.add(ppPetugasDokter);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Tarif Tagihan/Perawatan/Tindakan Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTindakan.setAutoCreateRowSorter(true);
        tbTindakan.setToolTipText("");
        tbTindakan.setComponentPopupMenu(Popup);
        tbTindakan.setName("tbTindakan"); // NOI18N
        tbTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanMouseClicked(evt);
            }
        });
        tbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTindakan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCariTindakan.setName("TCariTindakan"); // NOI18N
        TCariTindakan.setPreferredSize(new java.awt.Dimension(450, 23));
        TCariTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariTindakanKeyPressed(evt);
            }
        });
        panelisi3.add(TCariTindakan);

        BtnCariTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariTindakan.setMnemonic('1');
        BtnCariTindakan.setToolTipText("Alt+1");
        BtnCariTindakan.setName("BtnCariTindakan"); // NOI18N
        BtnCariTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariTindakanActionPerformed(evt);
            }
        });
        BtnCariTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariTindakanKeyPressed(evt);
            }
        });
        panelisi3.add(BtnCariTindakan);

        BtnAllTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllTindakan.setMnemonic('2');
        BtnAllTindakan.setToolTipText("Alt+2");
        BtnAllTindakan.setName("BtnAllTindakan"); // NOI18N
        BtnAllTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllTindakanActionPerformed(evt);
            }
        });
        BtnAllTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllTindakanKeyPressed(evt);
            }
        });
        panelisi3.add(BtnAllTindakan);

        BtnTambahTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahTindakan.setMnemonic('3');
        BtnTambahTindakan.setToolTipText("Alt+3");
        BtnTambahTindakan.setName("BtnTambahTindakan"); // NOI18N
        BtnTambahTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahTindakanActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambahTindakan);

        BtnSimpanTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanTindakan.setMnemonic('S');
        BtnSimpanTindakan.setToolTipText("Alt+S");
        BtnSimpanTindakan.setName("BtnSimpanTindakan"); // NOI18N
        BtnSimpanTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSimpanTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanTindakanActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpanTindakan);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(55, 23));
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

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(710, 74));
        FormInput.setLayout(null);

        jLabel5.setText("Dokter :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel5);
        jLabel5.setBounds(3, 10, 55, 23);

        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.setPreferredSize(new java.awt.Dimension(120, 23));
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        FormInput.add(kddokter);
        kddokter.setBounds(61, 10, 99, 23);

        nmdokter.setEditable(false);
        nmdokter.setHighlighter(null);
        nmdokter.setName("nmdokter"); // NOI18N
        nmdokter.setPreferredSize(new java.awt.Dimension(400, 23));
        FormInput.add(nmdokter);
        nmdokter.setBounds(162, 10, 240, 23);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(405, 10, 28, 23);

        jLabel6.setText("Petugas :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel6);
        jLabel6.setBounds(3, 40, 55, 23);

        Nip2.setHighlighter(null);
        Nip2.setName("Nip2"); // NOI18N
        Nip2.setPreferredSize(new java.awt.Dimension(120, 23));
        FormInput.add(Nip2);
        Nip2.setBounds(61, 40, 99, 23);

        NmPetugas2.setEditable(false);
        NmPetugas2.setHighlighter(null);
        NmPetugas2.setName("NmPetugas2"); // NOI18N
        NmPetugas2.setPreferredSize(new java.awt.Dimension(400, 23));
        FormInput.add(NmPetugas2);
        NmPetugas2.setBounds(162, 40, 240, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('4');
        btnPetugas.setToolTipText("ALt+4");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(405, 40, 28, 23);

        jLabel7.setText("Tanggal :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(431, 10, 59, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-02-2022" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(493, 10, 90, 23);

        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(587, 10, 62, 23);

        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(653, 10, 62, 23);

        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(719, 10, 62, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(785, 10, 23, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariTindakanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariTindakanActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbTindakan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCariTindakan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kddokter.requestFocus();
        }
}//GEN-LAST:event_TCariTindakanKeyPressed

    private void BtnCariTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariTindakanActionPerformed
        tampil2();
}//GEN-LAST:event_BtnCariTindakanActionPerformed

    private void BtnCariTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariTindakanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariTindakanActionPerformed(null);
        }else{
            Valid.pindah(evt, TCariTindakan, BtnAllTindakan);
        }
}//GEN-LAST:event_BtnCariTindakanKeyPressed

    private void BtnAllTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllTindakanActionPerformed
        TCariTindakan.setText("");
        tampil();
}//GEN-LAST:event_BtnAllTindakanActionPerformed

    private void BtnAllTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllTindakanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllTindakanActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCariTindakan, TCariTindakan);
        }
}//GEN-LAST:event_BtnAllTindakanKeyPressed

    private void tbTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanMouseClicked
        if(tbTindakan.getRowCount()!=0){
            if(evt.getClickCount()==2){
                dispose();
            }
        }
}//GEN-LAST:event_tbTindakanMouseClicked

    private void tbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanKeyPressed
        if(tbTindakan.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                try {
                    i=tbTindakan.getSelectedColumn();
                    if(i==1){
                        if(tbTindakan.getSelectedRow()>-1){
                          tbTindakan.setValueAt(true,tbTindakan.getSelectedRow(),0);   
                        }                               
                        TCariTindakan.setText("");
                        TCariTindakan.requestFocus();
                    }           
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariTindakan.setText("");
                TCariTindakan.requestFocus();
            }
        }
}//GEN-LAST:event_tbTindakanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
             dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahTindakanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        //perawatan.setModal(true);
        DlgJnsPerawatanRalan perawatan=new DlgJnsPerawatanRalan(null,false);
        perawatan.emptTeks();
        perawatan.isCek();
        perawatan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawatan.setLocationRelativeTo(internalFrame1);
        perawatan.setAlwaysOnTop(false);
        perawatan.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());           
    }//GEN-LAST:event_BtnTambahTindakanActionPerformed

private void BtnSimpanTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanTindakanActionPerformed
        if(TNoRw.getText().trim().isEmpty()||kddokter.getText().trim().isEmpty()||nmdokter.getText().trim().isEmpty()){
            Valid.textKosong(TCariTindakan,"Dokter/Paramedis");            
        }else{   
            Valid.editTable("reg_periksa","no_rawat",TNoRw,"stts='Sudah'");
            try {                
                if(pilihtable.equals("rawat_jl_dr")||pilihtable.equals("rawat_jl_pr")||pilihtable.equals("rawat_jl_drpr")){    
                    Sequel.AutoComitFalse();
                    sukses=true;
                    ttljmdokter=0;ttljmperawat=0;ttlkso=0;ttlpendapatan=0;ttljasasarana=0;ttlbhp=0;ttlmenejemen=0;
                    for(i=0;i<tbTindakan.getRowCount();i++){ 
                        if(tbTindakan.getValueAt(i,0).toString().equals("true")){
                            try {                                    
                                switch (pilihtable) {
                                    case "rawat_jl_dr":
                                        pssimpandokter=koneksi.prepareStatement("insert into rawat_jl_dr values(?,?,?,?,?,?,?,?,?,?,?,'Belum')");
                                        try {
                                            pssimpandokter.setString(1,TNoRw.getText());
                                            pssimpandokter.setString(2,tbTindakan.getValueAt(i,1).toString());
                                            pssimpandokter.setString(3,kddokter.getText());
                                            pssimpandokter.setString(4,Valid.SetTgl(DTPTgl.getSelectedItem()+""));
                                            pssimpandokter.setString(5,cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem());
                                            pssimpandokter.setString(6,tbTindakan.getValueAt(i,5).toString());
                                            pssimpandokter.setString(7,tbTindakan.getValueAt(i,6).toString());
                                            pssimpandokter.setString(8,tbTindakan.getValueAt(i,7).toString());
                                            pssimpandokter.setString(9,tbTindakan.getValueAt(i,9).toString());
                                            pssimpandokter.setString(10,tbTindakan.getValueAt(i,10).toString());
                                            pssimpandokter.setString(11,tbTindakan.getValueAt(i,4).toString());
                                            pssimpandokter.executeUpdate();
                                        } catch (Exception e) {
                                            sukses=false;
                                            System.out.println("Notifikasi : "+e);
                                        } finally{
                                            if(pssimpandokter != null){
                                                pssimpandokter.close();
                                            }
                                        }

                                        if(sukses==true){
                                            ttljmdokter=ttljmdokter+Double.parseDouble(tbTindakan.getValueAt(i,7).toString());
                                            ttlkso=ttlkso+Double.parseDouble(tbTindakan.getValueAt(i,9).toString());
                                            ttlpendapatan=ttlpendapatan+Double.parseDouble(tbTindakan.getValueAt(i,4).toString());
                                            ttljasasarana=ttljasasarana+Double.parseDouble(tbTindakan.getValueAt(i,5).toString());
                                            ttlbhp=ttlbhp+Double.parseDouble(tbTindakan.getValueAt(i,6).toString());
                                            ttlmenejemen=ttlmenejemen+Double.parseDouble(tbTindakan.getValueAt(i,10).toString());

                                            if(aktifpcare.equals("yes")){
                                                simpanTindakanPCare(
                                                    nokunjungan,Sequel.cariIsi("select kd_tindakan_pcare from maping_tindakan_pcare where kd_jenis_prw=?",tbTindakan.getValueAt(i,1).toString()),TNoRw.getText(),
                                                    Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),tbTindakan.getValueAt(i,1).toString(),
                                                    tbTindakan.getValueAt(i,5).toString(),tbTindakan.getValueAt(i,6).toString(),tbTindakan.getValueAt(i,7).toString(),"0",tbTindakan.getValueAt(i,9).toString(),
                                                    tbTindakan.getValueAt(i,10).toString(),tbTindakan.getValueAt(i,4).toString()
                                                );
                                            }
                                        }   
                                        break;
                                    case "rawat_jl_pr":
                                        pssimpanperawat=koneksi.prepareStatement("insert into rawat_jl_pr values(?,?,?,?,?,?,?,?,?,?,?,'Belum')");
                                        try {
                                            pssimpanperawat.setString(1,TNoRw.getText());
                                            pssimpanperawat.setString(2,tbTindakan.getValueAt(i,1).toString());
                                            pssimpanperawat.setString(3,kddokter.getText());
                                            pssimpanperawat.setString(4,Valid.SetTgl(DTPTgl.getSelectedItem()+""));
                                            pssimpanperawat.setString(5,cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem());
                                            pssimpanperawat.setString(6,tbTindakan.getValueAt(i,5).toString());  
                                            pssimpanperawat.setString(7,tbTindakan.getValueAt(i,6).toString());  
                                            pssimpanperawat.setString(8,tbTindakan.getValueAt(i,8).toString());
                                            pssimpanperawat.setString(9,tbTindakan.getValueAt(i,9).toString());
                                            pssimpanperawat.setString(10,tbTindakan.getValueAt(i,10).toString());  
                                            pssimpanperawat.setString(11,tbTindakan.getValueAt(i,4).toString()); 
                                            pssimpanperawat.executeUpdate();
                                        } catch (Exception e) {
                                            sukses=false;
                                            System.out.println("Notifikasi : "+e);
                                        } finally{
                                            if(pssimpanperawat != null){
                                                pssimpanperawat.close();
                                            }
                                        }

                                        if(sukses==true){
                                            ttljmperawat=ttljmperawat+Double.parseDouble(tbTindakan.getValueAt(i,8).toString());
                                            ttlkso=ttlkso+Double.parseDouble(tbTindakan.getValueAt(i,9).toString());
                                            ttlpendapatan=ttlpendapatan+Double.parseDouble(tbTindakan.getValueAt(i,4).toString());
                                            ttljasasarana=ttljasasarana+Double.parseDouble(tbTindakan.getValueAt(i,5).toString());
                                            ttlbhp=ttlbhp+Double.parseDouble(tbTindakan.getValueAt(i,6).toString());
                                            ttlmenejemen=ttlmenejemen+Double.parseDouble(tbTindakan.getValueAt(i,10).toString());

                                            if(aktifpcare.equals("yes")){
                                                simpanTindakanPCare(
                                                    nokunjungan,Sequel.cariIsi("select kd_tindakan_pcare from maping_tindakan_pcare where kd_jenis_prw=?",tbTindakan.getValueAt(i,1).toString()),TNoRw.getText(),
                                                    Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),tbTindakan.getValueAt(i,1).toString(),
                                                    tbTindakan.getValueAt(i,5).toString(),tbTindakan.getValueAt(i,6).toString(),"0",tbTindakan.getValueAt(i,8).toString(),tbTindakan.getValueAt(i,9).toString(),
                                                    tbTindakan.getValueAt(i,10).toString(),tbTindakan.getValueAt(i,4).toString()
                                                );
                                            }
                                        }   
                                        break;
                                    case "rawat_jl_drpr":   
                                        if(Nip2.getText().trim().isEmpty()||NmPetugas2.getText().trim().isEmpty()){
                                            Valid.textKosong(TCariTindakan,"Data Petugas");
                                        }else{
                                            pssimpandokterperawat=koneksi.prepareStatement("insert into rawat_jl_drpr values(?,?,?,?,?,?,?,?,?,?,?,?,?,'Belum')");
                                            try {
                                                pssimpandokterperawat.setString(1,TNoRw.getText());
                                                pssimpandokterperawat.setString(2,tbTindakan.getValueAt(i,1).toString());
                                                pssimpandokterperawat.setString(3,kddokter.getText());
                                                pssimpandokterperawat.setString(4,Nip2.getText());
                                                pssimpandokterperawat.setString(5,Valid.SetTgl(DTPTgl.getSelectedItem()+""));
                                                pssimpandokterperawat.setString(6,cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem());
                                                pssimpandokterperawat.setString(7,tbTindakan.getValueAt(i,5).toString());  
                                                pssimpandokterperawat.setString(8,tbTindakan.getValueAt(i,6).toString()); 
                                                pssimpandokterperawat.setString(9,tbTindakan.getValueAt(i,7).toString()); 
                                                pssimpandokterperawat.setString(10,tbTindakan.getValueAt(i,8).toString()); 
                                                pssimpandokterperawat.setString(11,tbTindakan.getValueAt(i,9).toString()); 
                                                pssimpandokterperawat.setString(12,tbTindakan.getValueAt(i,10).toString()); 
                                                pssimpandokterperawat.setString(13,tbTindakan.getValueAt(i,4).toString()); 
                                                pssimpandokterperawat.executeUpdate();
                                            } catch (Exception e) {
                                                sukses=false;
                                                System.out.println("Notifikasi : "+e);
                                            } finally{
                                                if(pssimpandokterperawat != null){
                                                    pssimpandokterperawat.close();
                                                }
                                            }

                                            if(sukses==true){
                                                ttljmdokter=ttljmdokter+Double.parseDouble(tbTindakan.getValueAt(i,7).toString());
                                                ttljmperawat=ttljmperawat+Double.parseDouble(tbTindakan.getValueAt(i,8).toString());
                                                ttlkso=ttlkso+Double.parseDouble(tbTindakan.getValueAt(i,9).toString());
                                                ttlpendapatan=ttlpendapatan+Double.parseDouble(tbTindakan.getValueAt(i,4).toString());
                                                ttljasasarana=ttljasasarana+Double.parseDouble(tbTindakan.getValueAt(i,5).toString());
                                                ttlbhp=ttlbhp+Double.parseDouble(tbTindakan.getValueAt(i,6).toString());
                                                ttlmenejemen=ttlmenejemen+Double.parseDouble(tbTindakan.getValueAt(i,10).toString());

                                                if(aktifpcare.equals("yes")){
                                                    simpanTindakanPCare(
                                                        nokunjungan,Sequel.cariIsi("select kd_tindakan_pcare from maping_tindakan_pcare where kd_jenis_prw=?",tbTindakan.getValueAt(i,1).toString()),TNoRw.getText(),
                                                        Valid.SetTgl(DTPTgl.getSelectedItem()+""),cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem(),tbTindakan.getValueAt(i,1).toString(),
                                                        tbTindakan.getValueAt(i,5).toString(),tbTindakan.getValueAt(i,6).toString(),tbTindakan.getValueAt(i,7).toString(),tbTindakan.getValueAt(i,8).toString(),tbTindakan.getValueAt(i,9).toString(),
                                                        tbTindakan.getValueAt(i,10).toString(),tbTindakan.getValueAt(i,4).toString()
                                                    );
                                                }
                                            }
                                        }                                                
                                        break;
                                }                                    
                            } catch (Exception e) {
                                sukses=false;
                                System.out.println(e);
                                JOptionPane.showMessageDialog(null,"Maaf, gagal menyimpan tindakan "+tbTindakan.getValueAt(i,2).toString()+". Kemungkinan ada data yang sama dimasukkan sebelumnya...!");
                            }   
                            tbTindakan.setValueAt(false,i,0); 
                        }                           
                    }  
                    
                    if(sukses==true){
                        Sequel.queryu("delete from tampjurnal");    
                        if(ttlpendapatan>0){
                            Sequel.menyimpan("tampjurnal","'"+Suspen_Piutang_Tindakan_Ralan+"','Suspen Piutang Tindakan Ralan','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Tindakan_Ralan+"'");    
                            Sequel.menyimpan("tampjurnal","'"+Tindakan_Ralan+"','Pendapatan Tindakan Rawat Inap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Tindakan_Ralan+"'");                             
                        }
                        if(ttljmdokter>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Tindakan_Ralan+"','Beban Jasa Medik Dokter Tindakan Ralan','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Tindakan_Ralan+"'");       
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Tindakan_Ralan+"','Utang Jasa Medik Dokter Tindakan Ralan','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Tindakan_Ralan+"'");                               
                        }
                        if(ttljmperawat>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Tindakan_Ralan+"','Beban Jasa Medik Paramedis Tindakan Ralan','"+ttljmperawat+"','0'","debet=debet+'"+(ttljmperawat)+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Tindakan_Ralan+"'");       
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Tindakan_Ralan+"','Utang Jasa Medik Paramedis Tindakan Ralan','0','"+ttljmperawat+"'","kredit=kredit+'"+(ttljmperawat)+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Tindakan_Ralan+"'");                             
                        }
                        if(ttlkso>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_KSO_Tindakan_Ralan+"','Beban KSO Tindakan Ralan','"+ttlkso+"','0'","debet=debet+'"+(ttlkso)+"'","kd_rek='"+Beban_KSO_Tindakan_Ralan+"'");       
                            Sequel.menyimpan("tampjurnal","'"+Utang_KSO_Tindakan_Ralan+"','Utang KSO Tindakan Ralan','0','"+ttlkso+"'","kredit=kredit+'"+(ttlkso)+"'","kd_rek='"+Utang_KSO_Tindakan_Ralan+"'");                              
                        }
                        if(ttljasasarana>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Sarana_Tindakan_Ralan+"','Beban Jasa Sarana Tindakan Ralan','"+ttljasasarana+"','0'","debet=debet+'"+(ttljasasarana)+"'","kd_rek='"+Beban_Jasa_Sarana_Tindakan_Ralan+"'");     
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Sarana_Tindakan_Ralan+"','Utang Jasa Sarana Tindakan Ralan','0','"+ttljasasarana+"'","kredit=kredit+'"+(ttljasasarana)+"'","kd_rek='"+Utang_Jasa_Sarana_Tindakan_Ralan+"'");                              
                        }
                        if(ttlbhp>0){
                            Sequel.menyimpan("tampjurnal","'"+HPP_BHP_Tindakan_Ralan+"','HPP BHP Tindakan Ralan','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+HPP_BHP_Tindakan_Ralan+"'");      
                            Sequel.menyimpan("tampjurnal","'"+Persediaan_BHP_Tindakan_Ralan+"','Persediaan BHP Tindakan Ralan','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_BHP_Tindakan_Ralan+"'");                           
                        }
                        if(ttlmenejemen>0){
                            Sequel.menyimpan("tampjurnal","'"+Beban_Jasa_Menejemen_Tindakan_Ralan+"','Beban Jasa Menejemen Tindakan Ralan','"+ttlmenejemen+"','0'","debet=debet+'"+(ttlmenejemen)+"'","kd_rek='"+Beban_Jasa_Menejemen_Tindakan_Ralan+"'");       
                            Sequel.menyimpan("tampjurnal","'"+Utang_Jasa_Menejemen_Tindakan_Ralan+"','Utang Jasa Menejemen Tindakan Ralan','0','"+ttlmenejemen+"'","kredit=kredit+'"+(ttlmenejemen)+"'","kd_rek='"+Utang_Jasa_Menejemen_Tindakan_Ralan+"'");                            
                        }
                        sukses=jur.simpanJurnal(TNoRw.getText(),"U","TINDAKAN RAWAT JALAN PASIEN "+TNoRw.getText()+" DIPOSTING OLEH "+akses.getkode());     
                    }
                                                                   
                    if(sukses==true){
                        Sequel.Commit();
                    }else{
                        JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                        Sequel.RollBack();
                    }

                    Sequel.AutoComitTrue();
                }
                
                if(sukses==true){
                    dispose();
                }   
            } catch (Exception ex) {
                System.out.println(ex);                
            }
        }
}//GEN-LAST:event_BtnSimpanTindakanActionPerformed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
            for(i=0;i<tbTindakan.getRowCount();i++){ 
                tbTindakan.setValueAt(false,i,0);
            }
}//GEN-LAST:event_ppBersihkanActionPerformed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            switch (pilihtable) {
                case "rawat_jl_dr":
                    nmdokter.setText(dokter.tampil3(kddokter.getText()));
                    break;
                case "rawat_jl_pr":
                    nmdokter.setText(petugas.tampil3(kddokter.getText()));
                    break;
                case "rawat_jl_drpr":
                    nmdokter.setText(dokter.tampil3(kddokter.getText()));
                    break;
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokterActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            TCariTindakan.requestFocus();
        }else{            
            Valid.pindah(evt,BtnKeluar,BtnSimpanTindakan);
        }
}//GEN-LAST:event_kddokterKeyPressed

private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        switch (pilihtable) {
            case "rawat_jl_dr":
                dokter.isCek();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setAlwaysOnTop(false);
                dokter.setVisible(true);
                break;
            case "rawat_jl_pr":
                petugas.isCek();
                petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                petugas.setLocationRelativeTo(internalFrame1);
                petugas.setAlwaysOnTop(false);
                petugas.setVisible(true);
                break;
            case "rawat_jl_drpr":
                dokter.isCek();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setAlwaysOnTop(false);
                dokter.setVisible(true);
                break;      
        }
}//GEN-LAST:event_btnDokterActionPerformed

private void ppDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDokterActionPerformed
    ppBersihkanActionPerformed(evt);
    FormInput.setPreferredSize(new Dimension(WIDTH, 44));
    pilihtable="rawat_jl_dr";
    jLabel5.setText("Dokter :");
    kddokter.setText("");
    nmdokter.setText("");
    jLabel6.setVisible(false);
    Nip2.setVisible(false);
    NmPetugas2.setVisible(false);
    btnPetugas.setVisible(false);
    tampil();
        
}//GEN-LAST:event_ppDokterActionPerformed

private void ppPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPetugasActionPerformed
    ppBersihkanActionPerformed(evt);
    FormInput.setPreferredSize(new Dimension(WIDTH, 44));
    pilihtable="rawat_jl_pr";    
    jLabel5.setText("Petugas :");
    kddokter.setText("");
    nmdokter.setText(""); 
    jLabel6.setVisible(false);
    Nip2.setVisible(false);
    NmPetugas2.setVisible(false);
    btnPetugas.setVisible(false);
    tampil();
}//GEN-LAST:event_ppPetugasActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TCariTindakan.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void ppPetugasDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPetugasDokterActionPerformed
        ppBersihkanActionPerformed(evt);
        FormInput.setPreferredSize(new Dimension(WIDTH, 74));
        pilihtable="rawat_jl_drpr";
        jLabel5.setText(" Dokter :");
        kddokter.setText("");
        nmdokter.setText("");
        Nip2.setText("");
        NmPetugas2.setText("");
        
        jLabel6.setVisible(true);
        Nip2.setVisible(true);
        NmPetugas2.setVisible(true);
        btnPetugas.setVisible(true);
        tampil();
    }//GEN-LAST:event_ppPetugasDokterActionPerformed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt,TCariTindakan,cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt,DTPTgl,cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt,cmbMnt,BtnSimpanTindakan);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPerawatanRalan dialog = new DlgCariPerawatanRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllTindakan;
    private widget.Button BtnCariTindakan;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpanTindakan;
    private widget.Button BtnTambahTindakan;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.TextBox Nip2;
    private widget.TextBox NmPetugas2;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCariTindakan;
    private widget.TextBox TNoRw;
    private widget.Button btnDokter;
    private widget.Button btnPetugas;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.TextBox kddokter;
    private widget.Label label10;
    private widget.Label label9;
    private widget.TextBox nmdokter;
    private widget.panelisi panelisi3;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppDokter;
    private javax.swing.JMenuItem ppPetugas;
    private javax.swing.JMenuItem ppPetugasDokter;
    private widget.Table tbTindakan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        try{     
            Valid.tabelKosong(TabModeTindakan);
            file=new File("./cache/tarifralan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            if(aktifpcare.equals("yes")){
                sql="select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,"+
                   "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"+
                   "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen from jns_perawatan inner join kategori_perawatan "+
                   "inner join maping_tindakan_pcare on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori and maping_tindakan_pcare.kd_jenis_prw=jns_perawatan.kd_jenis_prw ";
            }else{
                sql="select jns_perawatan.kd_jenis_prw,jns_perawatan.nm_perawatan,kategori_perawatan.nm_kategori,"+
                   "jns_perawatan.total_byrdr,jns_perawatan.total_byrpr,jns_perawatan.total_byrdrpr,jns_perawatan.bhp,jns_perawatan.material,"+
                   "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.kso,jns_perawatan.menejemen from jns_perawatan inner join kategori_perawatan "+
                   "on jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori  ";
            }
            
            pstindakan=koneksi.prepareStatement(sql+
                   "where jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') order by jns_perawatan.nm_perawatan "); 
            pstindakan2=koneksi.prepareStatement(sql+
                   "where jns_perawatan.status='1' and (jns_perawatan.kd_pj=? or jns_perawatan.kd_pj='-') order by jns_perawatan.nm_perawatan ");        
            pstindakan3=koneksi.prepareStatement(sql+
                   "where jns_perawatan.status='1' and (jns_perawatan.kd_poli=? or jns_perawatan.kd_poli='-') order by jns_perawatan.nm_perawatan ");     
            pstindakan4=koneksi.prepareStatement(sql+
                   "where jns_perawatan.status='1' order by jns_perawatan.nm_perawatan "); 
            try {
                if(poli_ralan.equals("Yes")&&cara_bayar_ralan.equals("Yes")){
                    pstindakan.setString(1,kd_pj.trim());
                    pstindakan.setString(2,kd_poli.trim());
                    rstindakan=pstindakan.executeQuery();
                }else if(poli_ralan.equals("No")&&cara_bayar_ralan.equals("Yes")){
                    pstindakan2.setString(1,kd_pj.trim());
                    rstindakan=pstindakan2.executeQuery();
                }else if(poli_ralan.equals("Yes")&&cara_bayar_ralan.equals("No")){
                    pstindakan3.setString(1,kd_poli.trim());
                    rstindakan=pstindakan3.executeQuery();
                }else if(poli_ralan.equals("No")&&cara_bayar_ralan.equals("No")){
                    rstindakan=pstindakan4.executeQuery();
                }

                switch (pilihtable) {
                    case "rawat_jl_dr":
                        while(rstindakan.next()){
                            if(rstindakan.getDouble("total_byrdr")>0){
                                TabModeTindakan.addRow(new Object[] {
                                    false,rstindakan.getString(1),rstindakan.getString(2),rstindakan.getString(3),
                                    rstindakan.getDouble("total_byrdr"),rstindakan.getDouble("material"),
                                    rstindakan.getDouble("bhp"),rstindakan.getDouble("tarif_tindakandr"),
                                    rstindakan.getDouble("tarif_tindakanpr"),rstindakan.getDouble("kso"),
                                    rstindakan.getDouble("menejemen")
                                });
                                iyem=iyem+"{\"Kode\":\""+rstindakan.getString(1)+"\",\"NamaPerawatan\":\""+rstindakan.getString(2).replaceAll("\"","")+"\",\"KategoriPerawatan\":\""+rstindakan.getString(3)+"\",\"Tarif\":\""+rstindakan.getString("total_byrdr")+"\",\"BagianRS\":\""+rstindakan.getString("material")+"\",\"BHP\":\""+rstindakan.getString("bhp")+"\",\"JMDokter\":\""+rstindakan.getString("tarif_tindakandr")+"\",\"JMPerawat\":\""+rstindakan.getString("tarif_tindakanpr")+"\",\"KSO\":\""+rstindakan.getString("kso")+"\",\"Menejemen\":\""+rstindakan.getString("menejemen")+"\"},";
                            }                        
                        }   
                        break;
                    case "rawat_jl_pr":
                        while(rstindakan.next()){
                            if(rstindakan.getDouble("total_byrpr")>0){
                                TabModeTindakan.addRow(new Object[] {
                                    false,rstindakan.getString(1),rstindakan.getString(2),rstindakan.getString(3),
                                    rstindakan.getDouble("total_byrpr"),rstindakan.getDouble("material"),
                                    rstindakan.getDouble("bhp"),rstindakan.getDouble("tarif_tindakandr"),
                                    rstindakan.getDouble("tarif_tindakanpr"),rstindakan.getDouble("kso"),
                                    rstindakan.getDouble("menejemen")
                                });
                                iyem=iyem+"{\"Kode\":\""+rstindakan.getString(1)+"\",\"NamaPerawatan\":\""+rstindakan.getString(2).replaceAll("\"","")+"\",\"KategoriPerawatan\":\""+rstindakan.getString(3)+"\",\"Tarif\":\""+rstindakan.getString("total_byrpr")+"\",\"BagianRS\":\""+rstindakan.getString("material")+"\",\"BHP\":\""+rstindakan.getString("bhp")+"\",\"JMDokter\":\""+rstindakan.getString("tarif_tindakandr")+"\",\"JMPerawat\":\""+rstindakan.getString("tarif_tindakanpr")+"\",\"KSO\":\""+rstindakan.getString("kso")+"\",\"Menejemen\":\""+rstindakan.getString("menejemen")+"\"},";
                            }                            
                        }   
                        break;
                    case "rawat_jl_drpr":
                        while(rstindakan.next()){
                            if(rstindakan.getDouble("total_byrdrpr")>0){
                                TabModeTindakan.addRow(new Object[] {
                                    false,rstindakan.getString(1),rstindakan.getString(2),rstindakan.getString(3),
                                    rstindakan.getDouble("total_byrdrpr"),rstindakan.getDouble("material"),
                                    rstindakan.getDouble("bhp"),rstindakan.getDouble("tarif_tindakandr"),
                                    rstindakan.getDouble("tarif_tindakanpr"),rstindakan.getDouble("kso"),
                                    rstindakan.getDouble("menejemen")
                                });
                                iyem=iyem+"{\"Kode\":\""+rstindakan.getString(1)+"\",\"NamaPerawatan\":\""+rstindakan.getString(2).replaceAll("\"","")+"\",\"KategoriPerawatan\":\""+rstindakan.getString(3)+"\",\"Tarif\":\""+rstindakan.getString("total_byrdrpr")+"\",\"BagianRS\":\""+rstindakan.getString("material")+"\",\"BHP\":\""+rstindakan.getString("bhp")+"\",\"JMDokter\":\""+rstindakan.getString("tarif_tindakandr")+"\",\"JMPerawat\":\""+rstindakan.getString("tarif_tindakanpr")+"\",\"KSO\":\""+rstindakan.getString("kso")+"\",\"Menejemen\":\""+rstindakan.getString("menejemen")+"\"},";
                            }                        
                        }   
                        break;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rstindakan != null){
                    rstindakan.close();
                }
                if(pstindakan != null){
                    pstindakan.close();
                }
                if(pstindakan2 != null){
                    pstindakan2.close();
                }
                if(pstindakan3 != null){
                    pstindakan3.close();
                }
                if(pstindakan4 != null){
                    pstindakan4.close();
                }
            }
            fileWriter.write("{\"tarifralan\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tbTindakan.getRowCount());
    }
    
    private void tampil2() {
        try{     
            jml=0;
            for(i=0;i<tbTindakan.getRowCount();i++){
                if(tbTindakan.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml]; 
            kode=null;
            kode=new String[jml];
            nama=null;
            nama=new String[jml];
            kategori=null;
            kategori=new String[jml];
            totaltnd=null;
            totaltnd=new double[jml];  
            bagianrs=null;
            bagianrs=new double[jml];
            bhp=null;
            bhp=new double[jml];
            jmdokter=null;
            jmdokter=new double[jml];
            jmperawat=null;
            jmperawat=new double[jml];
            kso=null;
            kso=new double[jml];
            menejemen=null;
            menejemen=new double[jml];

            index=0;        
            for(i=0;i<tbTindakan.getRowCount();i++){
                if(tbTindakan.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbTindakan.getValueAt(i,1).toString();
                    nama[index]=tbTindakan.getValueAt(i,2).toString();
                    kategori[index]=tbTindakan.getValueAt(i,3).toString();
                    totaltnd[index]=Double.parseDouble(tbTindakan.getValueAt(i,4).toString());
                    bagianrs[index]=Double.parseDouble(tbTindakan.getValueAt(i,5).toString());
                    bhp[index]=Double.parseDouble(tbTindakan.getValueAt(i,6).toString());
                    jmdokter[index]=Double.parseDouble(tbTindakan.getValueAt(i,7).toString());
                    jmperawat[index]=Double.parseDouble(tbTindakan.getValueAt(i,8).toString());  
                    kso[index]=Double.parseDouble(tbTindakan.getValueAt(i,9).toString());
                    menejemen[index]=Double.parseDouble(tbTindakan.getValueAt(i,10).toString());  
                    index++;
                }
            }       

            Valid.tabelKosong(TabModeTindakan);

            for(i=0;i<jml;i++){
                TabModeTindakan.addRow(new Object[] {
                    pilih[i],kode[i],nama[i],kategori[i],totaltnd[i],bagianrs[i],bhp[i],jmdokter[i],jmperawat[i],kso[i],menejemen[i]
                });
            }
            
            myObj = new FileReader("./cache/tarifralan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("tarifralan");
            if(response.isArray()){
                if(TCariTindakan.getText().trim().isEmpty()){
                    for(JsonNode list:response){
                        TabModeTindakan.addRow(new Object[]{
                            false,list.path("Kode").asText(),list.path("NamaPerawatan").asText(),list.path("KategoriPerawatan").asText(),list.path("Tarif").asDouble(),list.path("BagianRS").asDouble(),list.path("BHP").asDouble(),list.path("JMDokter").asDouble(),list.path("JMPerawat").asDouble(),list.path("KSO").asDouble(),list.path("Menejemen").asDouble()
                        });
                    }
                }else{
                    for(JsonNode list:response){
                        if(list.path("Kode").asText().toLowerCase().contains(TCariTindakan.getText().toLowerCase())||list.path("NamaPerawatan").asText().toLowerCase().contains(TCariTindakan.getText().toLowerCase())||list.path("KategoriPerawatan").asText().toLowerCase().contains(TCariTindakan.getText().toLowerCase())){
                            TabModeTindakan.addRow(new Object[]{
                                false,list.path("Kode").asText(),list.path("NamaPerawatan").asText(),list.path("KategoriPerawatan").asText(),list.path("Tarif").asDouble(),list.path("BagianRS").asDouble(),list.path("BHP").asDouble(),list.path("JMDokter").asDouble(),list.path("JMPerawat").asDouble(),list.path("KSO").asDouble(),list.path("Menejemen").asDouble()
                            });
                        }
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tbTindakan.getRowCount());
    }

    private void emptTeks() {
        TCariTindakan.setText("");         
    }

    public JTable getTable(){
        return tbTindakan;
    }
    
    
    public void isCek(){
        BtnTambahTindakan.setEnabled(akses.gettarif_ralan());
    }
    
    public void setPoli(String KodePoli){
        this.kd_poli=KodePoli;
    }
    
    public void setNoRm(String norwt,String kdpetugas,String nmpetugas, String pilihtable,String kdpetugas2,String nmpetugas2) {
        aktifpcare="no";
        for(i=0;i<tbTindakan.getRowCount();i++){ 
            tbTindakan.setValueAt(false,i,0);
        }
        TNoRw.setText(norwt);
        kddokter.setText(kdpetugas);
        nmdokter.setText(nmpetugas);
        Nip2.setText(kdpetugas2);
        NmPetugas2.setText(nmpetugas2);
        this.pilihtable=pilihtable;
        this.kd_pj=Sequel.cariIsi("select reg_periksa.kd_pj from reg_periksa where reg_periksa.no_rawat=?",norwt);
        this.kd_poli=Sequel.cariIsi("select reg_periksa.kd_poli from reg_periksa where reg_periksa.no_rawat=?",norwt);
        switch (pilihtable) {
            case "rawat_jl_dr":
                FormInput.setPreferredSize(new Dimension(710, 44));
                jLabel5.setText("Dokter :");                
                jLabel6.setVisible(false);
                Nip2.setVisible(false);
                NmPetugas2.setVisible(false);
                btnPetugas.setVisible(false);
                break;
            case "rawat_jl_pr":
                FormInput.setPreferredSize(new Dimension(710, 44));
                
                jLabel6.setVisible(false);
                Nip2.setVisible(false);
                NmPetugas2.setVisible(false);
                btnPetugas.setVisible(false);
                jLabel5.setText("Perawat :");
                break;
            case "rawat_jl_drpr":
                FormInput.setPreferredSize(new Dimension(710, 74));
                jLabel5.setText(" Dokter :");
                
                jLabel6.setVisible(true);
                Nip2.setVisible(true);
                NmPetugas2.setVisible(true);
                btnPetugas.setVisible(true);
                break;
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =cmbJam.getSelectedIndex();
                    nilai_menit =cmbMnt.getSelectedIndex();
                    nilai_detik =cmbDtk.getSelectedIndex();
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
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    public void setPCare(String aktif,String nokunjung){
        aktifpcare=aktif;
        nokunjungan=nokunjung;
    }
    
    private void simpanTindakanPCare(String noKunjungan,String kdTindakan,String no_rawat,String tgl_perawatan,String jam,String kd_jenis_prw,String material,String bhp,String tarif_tindakandr,String tarif_tindakanpr,String kso,String menejemen,String biaya_rawat){
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.add("X-cons-id",koneksiDB.CONSIDAPIPCARE());
            utc=String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-timestamp",utc);            
            headers.add("X-signature",api.getHmac());
            headers.add("X-authorization","Basic "+Base64.encodeBase64String(otorisasi.getBytes()));
            headers.add("user_key",koneksiDB.USERKEYAPIPCARE());
            requestJson ="{" +
                "\"kdTindakanSK\": 0," +
                "\"noKunjungan\": \""+noKunjungan+"\"," +
                "\"kdTindakan\": \""+kdTindakan+"\"," +
                "\"biaya\": 0," +
                "\"keterangan\": null," +
                "\"hasil\": 0" +
            "}";
            System.out.println(requestJson);
            requestEntity = new HttpEntity(requestJson,headers);
            requestJson=api.getRest().exchange(URL+"/tindakan", HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println(requestJson);
            root = mapper.readTree(requestJson);
            nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText()); 
            if(nameNode.path("code").asText().equals("201")){
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                Sequel.menyimpan2("pcare_tindakan_ralan_diberikan","?,?,?,?,?,?,?,?,?,?,?,?,?",13,new String[]{
                    no_rawat, noKunjungan, response.path("message").asText(), tgl_perawatan, jam, kd_jenis_prw,
                    material, bhp, tarif_tindakandr, tarif_tindakanpr, kso, menejemen, biaya_rawat
                });
            }
        }catch (Exception ex) {
            System.out.println("Notifikasi Bridging : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server PCare terputus...!");
            }else if(ex.toString().contains("500")){
                JOptionPane.showMessageDialog(null,"Server PCare baru ngambek broooh...!");
            }else if(ex.toString().contains("401")){
                JOptionPane.showMessageDialog(null,"Username/Password salah. Lupa password? Wani piro...!");
            }else if(ex.toString().contains("408")){
                JOptionPane.showMessageDialog(null,"Time out, hayati lelah baaaang...!");
            }else if(ex.toString().contains("424")){
                JOptionPane.showMessageDialog(null,"Ambil data masternya yang bener dong coy...!");
            }else if(ex.toString().contains("412")){
                JOptionPane.showMessageDialog(null,"Tidak sesuai kondisi. Aku, kamu end...!");
            }else if(ex.toString().contains("204")){
                JOptionPane.showMessageDialog(null,"Data tidak ditemukan...!");
            }
        } 
    }
}
