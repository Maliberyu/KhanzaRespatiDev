package rekammedis;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class DlgSBAR extends javax.swing.JDialog {
    private final sekuel Sequel=new sekuel();
    private final validasi Valid=new validasi();
    private final Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private final int i=0;
    private String noRawat="";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgSBAR(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         setSize(1285,674);
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML.setDocument(doc);
    }
    private final Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        btnCari = new widget.Button();
        BtnAll = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        Scroll = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ SBAR Perawatan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 13), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi1.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi1.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(Tgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(160, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCari.setMnemonic('2');
        btnCari.setToolTipText("Alt+2");
        btnCari.setName("btnCari"); // NOI18N
        btnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        btnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariKeyPressed(evt);
            }
        });
        panelisi1.add(btnCari);

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
        panelisi1.add(BtnAll);

        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(20, 30));
        panelisi1.add(label9);

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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll.setViewportView(LoadHTML);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            
            File g = new File("file2.css");            
            try (BufferedWriter bg = new BufferedWriter(new FileWriter(g))) {
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                                ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+
                                ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
            }
            
            File f = new File("HarianKlasifikasi.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
            bw.write(LoadHTML.getText().replaceAll("<head>","<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                        "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                            "<tr class='isi2'>"+
                                "<td valign='top' align='center'>"+
                                    "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                    akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                    akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+       
                                "</td>"+
                           "</tr>"+
                        "</table>")
            );
            bw.close();                         
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }     
        
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    prosesCari();
}//GEN-LAST:event_btnCariActionPerformed

private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            btnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, Tgl2, BtnPrint);
        }
}//GEN-LAST:event_btnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            btnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        prosesCari();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            prosesCari();
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSBAR dialog = new DlgSBAR(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.TextBox Kd2;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    // End of variables declaration//GEN-END:variables

    private  void prosesCari() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'>Tgl.Reg</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='8%'>No.Rawat</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='4%'>No.R.M</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='14%'>Nama Pasien</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='3%'>Status</td>"+
                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='66%'>S.B.A.R</td>"+
                "</tr>"
            );     
            ps=koneksi.prepareStatement(//"+noRawat+"
                "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur, "+
                "reg_periksa.status_lanjut from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat='"+noRawat+"' "+
                " "+(TCari.getText().trim().isEmpty()?"":
                "and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? or reg_periksa.status_lanjut like ?)")+" order by reg_periksa.tgl_registrasi");
            try {
                if(!TCari.getText().trim().isEmpty()){
                    ps.setString(1,"%"+TCari.getText().trim()+"%");
                    ps.setString(2,"%"+TCari.getText().trim()+"%");
                    ps.setString(3,"%"+TCari.getText().trim()+"%");
                    ps.setString(4,"%"+TCari.getText().trim()+"%");
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    htmlContent.append("<tr class='isi'><td valign='middle' align='center'>").append(rs.getString("tgl_registrasi")).append("</td><td valign='middle' align='center'>").append(rs.getString("no_rawat")).append("</td><td valign='middle' align='center'>").append(rs.getString("no_rkm_medis")).append("</td><td valign='middle' align='left'>").append(rs.getString("nm_pasien")).append(" / ").append(rs.getString("jk")).append(" / ").append(rs.getString("umur")).append("</td><td valign='middle' align='center'>").append(rs.getString("status_lanjut")).append("</td><td valign='top' align='center'><table width='100%' border='0' align='center' cellpadding='2px' cellspacing='0'>");
                    try {
                        // try {
//                        rs2=koneksi.prepareStatement(
//                                "select pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.keluhan,"+
//                                "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.rtl,pemeriksaan_ranap.penilaian,pemeriksaan_ranap.nik,pegawai.nama,departemen.nama "+
//                                "from pemeriksaan_ranap inner join pegawai on pemeriksaan_ranap.nik=pegawai.nik inner join departemen on pegawai.departemen=departemen.dep_id where pemeriksaan_ranap.no_rawat='"+rs.getString("no_rawat")+"' "+
//                                "order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                        rs2=koneksi.prepareStatement(
                                "select pemeriksaan_ranap_sbar.tgl_perawatan,pemeriksaan_ranap_sbar.jam_rawat,pemeriksaan_ranap_sbar.situation,"+
                                "pemeriksaan_ranap_sbar.background,pemeriksaan_ranap_sbar.assesment,pemeriksaan_ranap_sbar.recommendation,pemeriksaan_ranap_sbar.nip,pemeriksaan_ranap_sbar.kd_dokter,pegawai.nama,departemen.nama,"+
                                "namavalidator.nama as namavalidator,validasi_pemeriksaan_sbar.nik_validator,validasi_pemeriksaan_sbar.tgl_validasi,validasi_pemeriksaan_sbar.jam_validasi,validasi_pemeriksaan_sbar.status_validasi "+ 
                                "from pemeriksaan_ranap_sbar inner join pegawai on pemeriksaan_ranap_sbar.nip=pegawai.nik inner join departemen on pegawai.departemen=departemen.dep_id LEFT JOIN pegawai AS namavalidator ON pemeriksaan_ranap_sbar.kd_dokter=namavalidator.nik left join validasi_pemeriksaan_sbar on pemeriksaan_ranap_sbar.jam_rawat = validasi_pemeriksaan_sbar.jam_rawat AND pemeriksaan_ranap_sbar.no_rawat = validasi_pemeriksaan_sbar.no_rawat AND pemeriksaan_ranap_sbar.tgl_perawatan = validasi_pemeriksaan_sbar.tgl_perawatan "+
                                "where pemeriksaan_ranap_sbar.no_rawat='"+rs.getString("no_rawat")+"' "+
                                "order by pemeriksaan_ranap_sbar.tgl_perawatan,pemeriksaan_ranap_sbar.jam_rawat").executeQuery();
                        if(rs2.next()){
                            htmlContent.append(
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='10%'>Tanggal</td>"+
                                        "<td valign='middle' align='center'  width=40%' bgcolor='#FFFAF8'>Nama Pegawai</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='23%'>Subjek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='24%'>Objek</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='23%'>Asesmen</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='23%'>Recommendation</td>"+
                                        "<td valign='middle' bgcolor='#FFFFF8' align='center' width='23%'>Validasi</td>"+
                                    "</tr>");
                            rs2.beforeFirst();
                            while(rs2.next()){
                                String bagian="",stylee="",gbrverif="";
                                
                                bagian=Sequel.cariIsi("SELECT bidang FROM pegawai WHERE nik='"+rs2.getString("nip")+"'");
                                
                                //19960928201806045
                                if(bagian.equals("Medis")){
                                    stylee=" style=' background-color:#f7d4e8 '";
                                }else{
                                    stylee=" style=' background-color:#ccffcc '";
                                }
                                
                                if(Sequel.cariInteger("select count(validasi_pemeriksaan_sbar.nik_validator) " +
                                        "from pemeriksaan_ranap_sbar inner join pegawai on pemeriksaan_ranap_sbar.nip=pegawai.nik inner join departemen on pegawai.departemen=departemen.dep_id LEFT JOIN pegawai AS namavalidator ON pemeriksaan_ranap_sbar.kd_dokter=namavalidator.nik left join validasi_pemeriksaan_sbar on pemeriksaan_ranap_sbar.jam_rawat = validasi_pemeriksaan_sbar.jam_rawat AND pemeriksaan_ranap_sbar.no_rawat = validasi_pemeriksaan_sbar.no_rawat AND pemeriksaan_ranap_sbar.tgl_perawatan = validasi_pemeriksaan_sbar.tgl_perawatan "+
                                        "where pemeriksaan_ranap_sbar.no_rawat='"+rs.getString("no_rawat")+"' AND validasi_pemeriksaan_sbar.nik_validator='"+rs2.getString("nik_validator")+"' AND validasi_pemeriksaan_sbar.status_validasi='Validasi'" +
                                        "order by pemeriksaan_ranap_sbar.tgl_perawatan,pemeriksaan_ranap_sbar.jam_rawat")>0){
                                    gbrverif="<img src ='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/images/verif.png' align='center' width='100' height='50'/";
                                }else{
                                    gbrverif="<img src ='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/images/notverif.png' align='center' width='100' height='50'/";
                                }
//                                 System.out.println("Notif Rekening : "+stylee);
//                                 System.out.println("Notif Rekening : "+rs2.getString("nik"));
                                //2021/04/18/000056
                                htmlContent.append("<tr class='isi'  ><td align='center' ").append(stylee).append(" >").append(rs2.getString("tgl_perawatan")).append("<br>").append(rs2.getString("jam_rawat")).append("</td><td valign='top'  ").append(stylee).append(" >").append(rs2.getString("nama")).append("<br>(").append(rs2.getString("departemen.nama")).append(")</td><td align='left' ").append(stylee).append(" >").append(rs2.getString("situation")).append("</td><td align='left' ").append(stylee).append(">").append(rs2.getString("background")).append("</td><td align='left' ").append(stylee).append(">").append(rs2.getString("assesment")).append("</td><td align='left' ").append(stylee).append(">").append(rs2.getString("recommendation")).append("</td><td align='left' ").append(stylee).append(" ").append(gbrverif).append("><br>").append(rs2.getString("namavalidator")).append("<br>").append(rs2.getString("tgl_validasi")).append("<br>").append(rs2.getString("jam_validasi")).append("</td></tr>"); 
                            } 
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
                    htmlContent.append(
                                "</table>"+
                            "</td>"+
                        "</tr>"
                    );
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
            
            LoadHTML.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("laporan.DlgRL4A.prosesCari() 5 : "+e);
        } 
        this.setCursor(Cursor.getDefaultCursor());
        
    }
    
    public void isCek(){
        BtnPrint.setEnabled(akses.getharian_klasifikasi_pasien_ranap());
    }
    
    public void setNoRawat(String x,String y)
    {
        this.noRawat=x;
        TCari.setText(y);
        prosesCari();
    }

    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
