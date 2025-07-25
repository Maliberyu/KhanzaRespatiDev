/*
  by Mas Elkhanza
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public final class SatuSehatKirimAllergyIntollerance extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private String link = "", json = "", iddokter = "", idpasien = "";
    private ApiSatuSehat api = new ApiSatuSehat();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private SatuSehatCekNIK cekViaSatuSehat = new SatuSehatCekNIK();
    private DlgPasien cekflagging = new DlgPasien(null, false);

    /**
     * Creates new form DlgKamar
     *
     * @param parent
     * @param modal
     */
    public SatuSehatKirimAllergyIntollerance(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new String[]{
            "P", "No.Rawat", "No.RM", "Nama Pasien", "No.KTP Pasien", "Kode Dokter", "Nama Dokter",
            "No.KTP Dokter", "Kode Poli", "Nama Poli/Unit", "ID Lokasi Unit", "Stts Rawat", "Stts Lanjut",
            "Tanggal Entry", "ID Encounter", "KATEGORI", "KODE", "SYSTEM", "DISPLAY", "NOTE", "ID Allergy Satu Sehat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20); // P
            } else if (i == 1) {
                column.setPreferredWidth(120); // No Rawat
            } else if (i == 2) {
                column.setPreferredWidth(70); // RM Pasien
            } else if (i == 3) {
                column.setPreferredWidth(150); // Nama Pasien
            } else if (i == 4) {
                column.setPreferredWidth(120); // NIK Pasien
            } else if (i == 5) {
                column.setPreferredWidth(110); // 6.
            } else if (i == 6) {
                column.setPreferredWidth(150); // Nama Dokter
            } else if (i == 7) {
                column.setPreferredWidth(120); // NIK Dokter
            } else if (i == 8) {
                column.setPreferredWidth(70); // Kode Poli
            } else if (i == 9) {
                column.setPreferredWidth(180); // Nama Poli
            } else if (i == 10) {
                column.setPreferredWidth(215); // ID Lokasi
            } else if (i == 11) {
                column.setPreferredWidth(70); // Status
            } else if (i == 12) {
                column.setPreferredWidth(63);
            } else if (i == 13) {
                column.setPreferredWidth(150); // Tanggal Entri
            } else if (i == 14) {
                column.setPreferredWidth(215); // ID. Encounter
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(63);
            } else if (i == 17) {
                column.setPreferredWidth(63);
            } else if (i == 18) {
                column.setPreferredWidth(63);
            } else if (i == 19) {
                column.setPreferredWidth(63);
            } else if (i == 20) {
                column.setPreferredWidth(215); // 20. ID Allergy
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }

        try {
            link = koneksiDB.URLFHIRSATUSEHAT();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppPilihSemua = new javax.swing.JMenuItem();
        ppBersihkan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnAll = new widget.Button();
        BtnKirim = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        ChkBelumTerkirim = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppPilihSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihSemua.setText("Pilih Semua");
        ppPilihSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua.setName("ppPilihSemua"); // NOI18N
        ppPilihSemua.setPreferredSize(new java.awt.Dimension(150, 26));
        ppPilihSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihSemua);

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(150, 26));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBersihkan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pengiriman Data Allergy Satu Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setComponentPopupMenu(jPopupMenu1);
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

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
        jLabel7.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(LCount);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
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

        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('K');
        BtnKirim.setText("Kirim");
        BtnKirim.setToolTipText("Alt+K");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKirim);

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

        jLabel15.setText("Tgl.Registrasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d.");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel16);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        panelGlass9.add(BtnCari);

        ChkBelumTerkirim.setBorder(null);
        ChkBelumTerkirim.setText("Data belum terkirim");
        ChkBelumTerkirim.setBorderPainted(true);
        ChkBelumTerkirim.setBorderPaintedFlat(true);
        ChkBelumTerkirim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkBelumTerkirim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkBelumTerkirim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBelumTerkirim.setIconTextGap(2);
        ChkBelumTerkirim.setName("ChkBelumTerkirim"); // NOI18N
        ChkBelumTerkirim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkBelumTerkirimItemStateChanged(evt);
            }
        });
        ChkBelumTerkirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBelumTerkirimActionPerformed(evt);
            }
        });
        panelGlass9.add(ChkBelumTerkirim);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {

        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbKamar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {

        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        for (i = 0; i < tbKamar.getRowCount(); i++) {
            if (tbKamar.getValueAt(i, 0).toString().equals("true")) {
                try {
//                    true, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
//                                rs.getString("no_ktp"), rs.getString("kd_dokter"), rs.getString("nama"), rs.getString("ktpdokter"), rs.getString("kd_poli"), rs.getString("nm_poli"),
//                                rs.getString("id_lokasi_satusehat"), rs.getString("stts"), rs.getString("status_lanjut"), rs.getString("tgl_perawatan"), rs.getString("id_encounter"), rs.getString("category"),
//                                rs.getString("allergy_code"), rs.getString("system"), rs.getString("display"), rs.getString("note"), rs.getString("id_allergy")
                    iddokter = cekViaSatuSehat.tampilIDParktisi(tbKamar.getValueAt(i, 7).toString());
                    idpasien = cekViaSatuSehat.tampilIDPasien(tbKamar.getValueAt(i, 4).toString());
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer " + api.TokenSatuSehat());

                        json = "{\n"
                                + "    \"resourceType\": \"AllergyIntolerance\",\n"
                                + "    \"identifier\": [\n"
                                + "        {\n"
                                + "            \"system\": \"http://sys-ids.kemkes.go.id/allergy/" + koneksiDB.IDSATUSEHAT() + "\",\n"
                                + "            \"use\": \"official\",\n"
                                + "            \"value\": \"" + tbKamar.getValueAt(i, 1).toString().replaceAll("/", "") + "\"\n"
                                + "        }\n"
                                + "    ],\n"
                                + "    \"clinicalStatus\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical\",\n"
                                + "                \"code\": \"active\",\n"
                                + "                \"display\": \"Active\"\n"
                                + "            }\n"
                                + "        ]\n"
                                + "    },\n"
                                + "    \"verificationStatus\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"http://terminology.hl7.org/CodeSystem/allergyintolerance-verification\",\n"
                                + "                \"code\": \"confirmed\",\n"
                                + "                \"display\": \"Confirmed\"\n"
                                + "            }\n"
                                + "        ]\n"
                                + "    },\n"
                                + "    \"category\": [\n"
                                + "        \"" + tbKamar.getValueAt(i, 15).toString().replaceAll("Makanan", "food").replaceAll("Medication", "medication").replaceAll("Lingkungan", "environment").replaceAll("Biologis", "biologic") + "\"\n"
                                + "    ],\n"
                                + "    \"code\": {\n"
                                + "        \"coding\": [\n"
                                + "            {\n"
                                + "                \"system\": \"" + tbKamar.getValueAt(i, 17).toString() + "\",\n"
                                + "                \"code\": \"" + tbKamar.getValueAt(i, 16).toString() + "\",\n"
                                + "                \"display\": \"" + tbKamar.getValueAt(i, 18).toString() + "\"\n"
                                + "            }\n"
                                + "        ],\n"
                                + "        \"text\": \"" + tbKamar.getValueAt(i, 19).toString() + "\"\n"
                                + "    },\n"
                                + "    \"patient\": {\n"
                                + "        \"reference\": \"Patient/" + idpasien + "\",\n"
                                + "        \"display\": \"" + tbKamar.getValueAt(i, 3).toString() + "\"\n"
                                + "    },\n"
                                + "    \"encounter\": {\n"
                                + "        \"reference\": \"Encounter/" + tbKamar.getValueAt(i, 14).toString() + "\",\n"
                                + "        \"display\": \"Kunjungan " + tbKamar.getValueAt(i, 3).toString() + "\"\n"
                                + "    },\n"
                                + "    \"recordedDate\": \"" + tbKamar.getValueAt(i, 13).toString() + "\",\n"
                                + "    \"recorder\": {\n"
                                + "        \"reference\": \"Practitioner/" + iddokter + "\"\n"
                                + "    }\n"
                                + "}";
                        System.out.println("URL : " + link + "/AllergyIntolerance");
                        System.out.println("Request JSON : " + json);
                        requestEntity = new HttpEntity(json, headers);
//                        System.out.println(headers.toString());
                        json = api.getRest().exchange(link + "/AllergyIntolerance", HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Result JSON : " + json);
                        root = mapper.readTree(json);
                        response = root.path("id");
                        if (!response.asText().equals("")) {
                            Sequel.menyimpan("satu_sehat_allergy", "?,?", "No.Rawat", 2, new String[]{
                                tbKamar.getValueAt(i, 1).toString(), response.asText()
                            });
                            //tag update encounter

                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Bridging : " + e);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
        tampil();
    }//GEN-LAST:event_BtnKirimActionPerformed

    private void ppPilihSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemuaActionPerformed
        for (i = 0; i < tbKamar.getRowCount(); i++) {
            tbKamar.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppPilihSemuaActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbKamar.getRowCount(); i++) {
            tbKamar.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {

        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ChkBelumTerkirimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkBelumTerkirimItemStateChanged
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ChkBelumTerkirimItemStateChanged

    private void ChkBelumTerkirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBelumTerkirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkBelumTerkirimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SatuSehatKirimAllergyIntollerance dialog = new SatuSehatKirimAllergyIntollerance(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKirim;
    private widget.CekBox ChkBelumTerkirim;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppPilihSemua;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            String belumterkirim = "";
            if (ChkBelumTerkirim.isSelected() == true) {
                belumterkirim = " satu_sehat_allergy.id_allergy IS NULL and ";
            } else {
                belumterkirim = "";
            }
            ps = koneksi.prepareStatement(
                    "SELECT\n"
                    + "	reg_periksa.no_rawat, \n"
                    + "	pasien.nm_pasien, \n"
                    + "	pasien.no_ktp, \n"
                    + "	pegawai.nama, \n"
                    + "	pegawai.no_ktp AS ktpdokter, \n"
                    + "	poliklinik.nm_poli, \n"
                    + "	satu_sehat_mapping_lokasi_ralan.id_lokasi_satusehat, \n"
                    + "	reg_periksa.stts, \n"
                    + "	reg_periksa.status_lanjut, \n"
                    + "	reg_periksa.kd_dokter,"
                    + " reg_periksa.kd_poli, \n"
                    + "	reg_periksa.no_rkm_medis, \n"
                    + "	satu_sehat_encounter.id_encounter, \n"
                    + "	alergi_pasien.allergy_code, \n"
                    + "	satu_sehat_ref_allergy.system, \n"
                    + "	satu_sehat_ref_allergy.display, \n"
                    + "	alergi_pasien.note, \n"
                    + "	DATE_FORMAT(alergi_pasien.tgl_perawatan, '%Y-%m-%dT%H:%i:%s+00:00') AS tgl_perawatan, \n"
                    + "	alergi_pasien.nippetugas, \n"
                    + "	petugas.nama AS petugasallergy, \n"
                    + "	alergi_pasien.category,"
                    + " satu_sehat_allergy.id_allergy \n"
                    + "FROM\n"
                    + "	reg_periksa\n"
                    + "	INNER JOIN\n"
                    + "	pasien\n"
                    + "	ON \n"
                    + "		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n"
                    + "	INNER JOIN\n"
                    + "	pegawai\n"
                    + "	ON \n"
                    + "		pegawai.nik = reg_periksa.kd_dokter\n"
                    + "	INNER JOIN\n"
                    + "	poliklinik\n"
                    + "	ON \n"
                    + "		reg_periksa.kd_poli = poliklinik.kd_poli\n"
                    + "	INNER JOIN\n"
                    + "	satu_sehat_mapping_lokasi_ralan\n"
                    + "	ON \n"
                    + "		satu_sehat_mapping_lokasi_ralan.kd_poli = poliklinik.kd_poli\n"
                    + "	INNER JOIN\n"
                    + "	satu_sehat_encounter\n"
                    + "	ON \n"
                    + "		satu_sehat_encounter.no_rawat = reg_periksa.no_rawat\n"
                    + "	INNER JOIN\n"
                    + "	alergi_pasien\n"
                    + "	ON \n"
                    + "		reg_periksa.no_rawat = alergi_pasien.no_rawat\n"
                    + "	INNER JOIN\n"
                    + "	satu_sehat_ref_allergy\n"
                    + "	ON \n"
                    + "		alergi_pasien.allergy_code = satu_sehat_ref_allergy.kode\n"
                    + "	INNER JOIN\n"
                    + "	satu_sehat_ref_allergy_reaction\n"
                    + "	ON \n"
                    + "		alergi_pasien.reactioncode = satu_sehat_ref_allergy_reaction.kode\n"
                    + "	LEFT JOIN\n"
                    + "	satu_sehat_allergy\n"
                    + "	ON \n"
                    + "		reg_periksa.no_rawat = satu_sehat_allergy.no_rawat\n"
                    + "	INNER JOIN\n"
                    + "	pegawai AS petugas\n"
                    + "	ON \n"
                    + "		petugas.nik = alergi_pasien.nippetugas  "
                    + "where " + belumterkirim + " reg_periksa.tgl_registrasi between ? and ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or pasien.no_ktp like ? or pegawai.nama like ? or poliklinik.nm_poli like ? or "
                    + "reg_periksa.stts like ? or reg_periksa.status_lanjut like ?)") + " order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                //    if (cekflagging.GeneralConsentSatuSehat(rs.getString("no_rkm_medis")) == true) {
                        if (rs.getString("id_encounter").equals("")) {
                            tabMode.addRow(new Object[]{
                                true, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                                rs.getString("no_ktp"), rs.getString("kd_dokter"), rs.getString("nama"), rs.getString("ktpdokter"), rs.getString("kd_poli"), rs.getString("nm_poli"),
                                rs.getString("id_lokasi_satusehat"), rs.getString("stts"), rs.getString("status_lanjut"), rs.getString("tgl_perawatan"), rs.getString("id_encounter"), rs.getString("category"),
                                rs.getString("allergy_code"), rs.getString("system"), rs.getString("display"), rs.getString("note"), rs.getString("id_allergy")
                            });
                        } else {
                            tabMode.addRow(new Object[]{
                                false, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                                rs.getString("no_ktp"), rs.getString("kd_dokter"), rs.getString("nama"), rs.getString("ktpdokter"), rs.getString("kd_poli"), rs.getString("nm_poli"),
                                rs.getString("id_lokasi_satusehat"), rs.getString("stts"), rs.getString("status_lanjut"), rs.getString("tgl_perawatan"), rs.getString("id_encounter"), rs.getString("category"),
                                rs.getString("allergy_code"), rs.getString("system"), rs.getString("display"), rs.getString("note"), rs.getString("id_allergy")
                            });
                        }

                    }
            //    }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void isCek() {
        BtnKirim.setEnabled(akses.getsatu_sehat_kirim_encounter());
    }

    public JTable getTable() {
        return tbKamar;
    }
}
