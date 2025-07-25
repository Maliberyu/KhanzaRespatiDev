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

package bridging;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public class ApotekBPJSCekReferensiSettingPPK extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0;
    private ApiApotekBPJS api=new ApiApotekBPJS();
    private String URL="",link="",utc="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApotekBPJSCekReferensiFaskes faskes=new ApotekBPJSCekReferensiFaskes(null, false);

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public ApotekBPJSCekReferensiSettingPPK(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new String[]{"",""}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(200);
            }else if(i==1){
                column.setPreferredWidth(370);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        KodePPK.setDocument(new batasInput((byte)100).getKata(KodePPK));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            KodePPK.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(KodePPK.getText().length()>2){
                        tampil(KodePPK.getText());
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(KodePPK.getText().length()>2){
                        tampil(KodePPK.getText());
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(KodePPK.getText().length()>2){
                        tampil(KodePPK.getText());
                    }
                }
            });
        } 
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(faskes.getTable().getSelectedRow()!= -1){                   
                    KodePPK.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                    NamaPPK.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
                    KodePPK.requestFocus();
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
        
        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        try {
            link=koneksiDB.URLAPIAPOTEKBPJS();
        } catch (Exception e) {
            System.out.println("Notif : "+e);
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        KodePPK = new widget.TextBox();
        NamaPPK = new widget.TextBox();
        BtnFaskes = new widget.Button();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Data Referensi Setting PPK Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setText("Kode PPK Apotek Faskes :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(135, 23));
        panelGlass6.add(jLabel16);

        KodePPK.setName("KodePPK"); // NOI18N
        KodePPK.setPreferredSize(new java.awt.Dimension(100, 23));
        KodePPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodePPKKeyPressed(evt);
            }
        });
        panelGlass6.add(KodePPK);

        NamaPPK.setName("NamaPPK"); // NOI18N
        NamaPPK.setPreferredSize(new java.awt.Dimension(250, 23));
        panelGlass6.add(NamaPPK);

        BtnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnFaskes.setMnemonic('3');
        BtnFaskes.setToolTipText("ALt+3");
        BtnFaskes.setName("BtnFaskes"); // NOI18N
        BtnFaskes.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFaskesActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnFaskes);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

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
        panelGlass6.add(BtnPrint);

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
        panelGlass6.add(BtnKeluar);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            int row=tabMode.getRowCount();
            for(int r=0;r<row;r++){  
                Sequel.menyimpan("temporary","'"+r+"','"+
                                tabMode.getValueAt(r,0).toString()+"','"+
                                tabMode.getValueAt(r,1).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Rekap Harian Pengadaan Ipsrs"); 
            }
            
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            Valid.MyReportqry("rptCariApotekBPJSReferensiSettingPPK.jasper","report","[ Pencarian Referensi Setting PPK Apotek BPJS ]","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
            this.setCursor(Cursor.getDefaultCursor());
        }      
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void KodePPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodePPKKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
            BtnPrint.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_KodePPKKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if(KodePPK.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Silahkan masukkan faskes terlebih dahulu..!!");
            BtnFaskes.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            tampil(KodePPK.getText());
            this.setCursor(Cursor.getDefaultCursor());
        } 
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt,KodePPK,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFaskesActionPerformed
        faskes.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
    }//GEN-LAST:event_BtnFaskesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSCekReferensiSettingPPK dialog = new ApotekBPJSCekReferensiSettingPPK(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnFaskes;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.TextBox KodePPK;
    private widget.TextBox NamaPPK;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String keyword) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("x-cons-id",koneksiDB.CONSIDAPIAPOTEKBPJS());
	    utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("x-timestamp",utc);
	    headers.add("x-signature",api.getHmac(utc));
	    headers.add("user_key",koneksiDB.USERKEYAPIAPOTEKBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link+"/referensi/settingppk/read/"+keyword;	
            System.out.println(URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc));
                tabMode.addRow(new Object[]{
                    "Kode",": "+response.path("kode").asText()
                });
                tabMode.addRow(new Object[]{
                    "Nama Apoteker",": "+response.path("namaapoteker").asText()
                });
                tabMode.addRow(new Object[]{
                    "Nama Kepala",": "+response.path("namakepala").asText()
                });
                tabMode.addRow(new Object[]{
                    "Jabatan Kepala",": "+response.path("jabatankepala").asText()
                });
                tabMode.addRow(new Object[]{
                    "NIP Kepala",": "+response.path("nipkepala").asText()
                });
                tabMode.addRow(new Object[]{
                    "SIUP",": "+response.path("siup").asText()
                });
                tabMode.addRow(new Object[]{
                    "Alamat",": "+response.path("alamat").asText()
                });
                tabMode.addRow(new Object[]{
                    "Kota",": "+response.path("kota").asText()
                });
                tabMode.addRow(new Object[]{
                    "Nama Verifikator",": "+response.path("namaverifikator").asText()
                });
                tabMode.addRow(new Object[]{
                    "NPP Verifikator",": "+response.path("nppverifikator").asText()
                });
                tabMode.addRow(new Object[]{
                    "Nama Petugas Apotek",": "+response.path("namapetugasapotek").asText()
                });
                tabMode.addRow(new Object[]{
                    "NIP Petugas Apotek",": "+response.path("nippetugasapotek").asText()
                });
                tabMode.addRow(new Object[]{
                    "Check Stock",": "+response.path("checkstock").asText()
                });
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }    

    public JTable getTable(){
        return tbKamar;
    }
}
