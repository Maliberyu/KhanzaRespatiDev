package aihelper;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.datatransfer.StringSelection;
//import javax.swing.SwingWorker;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//
//public class FormTanyaAI extends JDialog {
//    private JTextArea txtPertanyaan;
//    private JTextArea txtJawaban;
//    private JButton btnTanyaAI;
//    private JButton btnCopy;
//    
//    public FormTanyaAI(JFrame parent) {
//        super(parent, "Tanya AI - Mistral", true);
//        setSize(600, 400);
//        setLayout(new BorderLayout(10, 10));
//        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
//        // Panel Input
//        JPanel inputPanel = new JPanel(new BorderLayout());
//        JLabel lblPertanyaan = new JLabel("Masukkan Pertanyaan:");
//        txtPertanyaan = new JTextArea(5, 30);
//        txtPertanyaan.setLineWrap(true);
//        txtPertanyaan.setWrapStyleWord(true);
//        inputPanel.add(lblPertanyaan, BorderLayout.NORTH);
//        inputPanel.add(new JScrollPane(txtPertanyaan), BorderLayout.CENTER);
//
//        // Tombol Tanya AI
//        btnTanyaAI = new JButton("🧠 Tanya AI");
//        btnTanyaAI.addActionListener(e -> tanyaAI());
//
//        // Panel Output
//        JPanel outputPanel = new JPanel(new BorderLayout());
//        JLabel lblJawaban = new JLabel("Jawaban:");
//        txtJawaban = new JTextArea(8, 30);
//        txtJawaban.setEditable(false);
//        txtJawaban.setLineWrap(true);
//        txtJawaban.setWrapStyleWord(true);
//        outputPanel.add(lblJawaban, BorderLayout.NORTH);
//        outputPanel.add(new JScrollPane(txtJawaban), BorderLayout.CENTER);
//
//        // Tombol Salin
//        btnCopy = new JButton("📋 Salin Jawaban");
//        btnCopy.addActionListener(e -> {
//            String jawaban = txtJawaban.getText();
//            if (!jawaban.isEmpty()) {
//                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
//                    new StringSelection(jawaban), null
//                );
//                JOptionPane.showMessageDialog(this, "Jawaban telah disalin ke clipboard.");
//            } else {
//                JOptionPane.showMessageDialog(this, "Tidak ada jawaban untuk disalin.", "Perhatian", JOptionPane.WARNING_MESSAGE);
//            }
//        });
//
//        // Layout Utama
//        add(inputPanel, BorderLayout.NORTH);
//        add(btnTanyaAI, BorderLayout.CENTER);
//        add(outputPanel, BorderLayout.SOUTH);
//        add(btnCopy, BorderLayout.EAST);
//
//        setLocationRelativeTo(parent); // Tengah layar
//    }
//    
//
//    private void tanyaAI() {
//        String pertanyaan = txtPertanyaan.getText();
//        if (pertanyaan.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Silakan masukkan pertanyaan terlebih dahulu.");
//            return;
////        }
////
////        btnTanyaAI.setEnabled(false);
////        txtJawaban.setText("⏳ Menunggu jawaban...");
////
////        // Jalankan AI secara background agar GUI tidak freeze
//////        SwingWorker<String, Void> worker = new SwingWorker<>() {
////            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
////
////            @Override
////            protected String doInBackground() {
////                try {
////                    return AIHelper.generateResponse(pertanyaan);
////                } catch (Exception ex) {
////                    return "⚠️ Gagal mendapatkan jawaban:\n" + ex.getMessage();
////                }
////            }
////
////            @Override
////            protected void done() {
////                try {
////                    txtJawaban.setText(get());
////                } catch (Exception ex) {
////                    txtJawaban.setText("⚠️ Error saat mengambil hasil.");
////                } finally {
////                    btnTanyaAI.setEnabled(true);
////                }
////            }
////        };
////        worker.execute();
////    }
////}
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class FormTanyaAI extends JDialog {
//    private JTextField txtPertanyaan; // Input field untuk pertanyaan
//    private JTextArea txtHasil; // Area untuk menampilkan hasil AI
//    private JButton btnKirim; // Tombol untuk mengirim pertanyaan
//    private JButton btnSalin; // Tombol untuk menyalin hasil
//
//    public FormTanyaAI(JFrame parent) {
//        super(parent, "Tanya AI", true); // Modal dialog
//        setSize(400, 300);
//        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
//        // Komponen GUI
//        txtPertanyaan = new JTextField();
//        txtPertanyaan.setPreferredSize(new Dimension(350, 30));
//        txtPertanyaan.setText("Masukkan pertanyaan...");
//
//        txtHasil = new JTextArea();
//        txtHasil.setEditable(false); // Tidak bisa diedit oleh pengguna
//        txtHasil.setLineWrap(true);
//        txtHasil.setWrapStyleWord(true);
//        txtHasil.setPreferredSize(new Dimension(350, 150));
//
//        btnKirim = new JButton("Kirim Pertanyaan");
//        btnSalin = new JButton("Salin Jawaban");
//
//        // Layout
//        JPanel panelUtama = new JPanel();
//        panelUtama.setLayout(new BorderLayout());
//
//        // Panel input
//        JPanel panelInput = new JPanel();
//        panelInput.setLayout(new BorderLayout());
//        panelInput.add(new JLabel("Pertanyaan:"), BorderLayout.WEST);
//        panelInput.add(txtPertanyaan, BorderLayout.CENTER);
//
//        // Panel tombol
//        JPanel panelTombol = new JPanel();
//        panelTombol.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        panelTombol.add(btnKirim);
//        panelTombol.add(btnSalin);
//
//        // Mengatur layout utama
//        panelUtama.add(panelInput, BorderLayout.NORTH);
//        panelUtama.add(new JScrollPane(txtHasil), BorderLayout.CENTER);
//        panelUtama.add(panelTombol, BorderLayout.SOUTH);
//
//        add(panelUtama);
//
//        // Event listener
//        btnKirim.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String pertanyaan = txtPertanyaan.getText().trim();
//                if (!pertanyaan.isEmpty()) {
//                  String jawaban = "Jawaban dari AI: halo dunia!";
//                        txtHasil.setText(jawaban);  
//                } else {
//                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Silakan masukkan pertanyaan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//
//        btnSalin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String jawaban = txtHasil.getText();
//                if (!jawaban.isEmpty()) {
//                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
//                        new StringSelection(jawaban),
//                        null
//                    );
//                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Jawaban telah disalin ke clipboard.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Tidak ada jawaban untuk disalin.", "Peringatan", JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//
//        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame
//    }
//}
//package aihelper;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTanyaAI extends JDialog {
    private JTextField txtPertanyaan;
    private JTextArea txtHasil;
    private JButton btnKirim;
    private JButton btnSalin;

    public FormTanyaAI(JFrame parent) {
        super(parent, "Tanya AI", true); // Modal dialog
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        txtPertanyaan = new JTextField();
        txtPertanyaan.setPreferredSize(new Dimension(350, 30));
        txtPertanyaan.setText("Masukkan pertanyaan...");

        txtHasil = new JTextArea();
        txtHasil.setEditable(false);
        txtHasil.setLineWrap(true);
        txtHasil.setWrapStyleWord(true);
        txtHasil.setPreferredSize(new Dimension(350, 150));

        btnKirim = new JButton("Kirim Pertanyaan");
        btnSalin = new JButton("Salin Jawaban");

        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BorderLayout());

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new BorderLayout());
        panelInput.add(new JLabel("Pertanyaan:"), BorderLayout.WEST);
        panelInput.add(txtPertanyaan, BorderLayout.CENTER);

        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelTombol.add(btnKirim);
        panelTombol.add(btnSalin);

        panelUtama.add(panelInput, BorderLayout.NORTH);
        panelUtama.add(new JScrollPane(txtHasil), BorderLayout.CENTER);
        panelUtama.add(panelTombol, BorderLayout.SOUTH);

        add(panelUtama);

        btnKirim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pertanyaan = txtPertanyaan.getText().trim();
                if (!pertanyaan.isEmpty()) {
                    String jawaban = "Jawaban dari AI: halo dunia!";
                        txtHasil.setText(jawaban);  
                } else {
                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Silakan masukkan pertanyaan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnSalin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jawaban = txtHasil.getText();
                if (!jawaban.isEmpty()) {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new StringSelection(jawaban),
                        null
                    );
                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Jawaban telah disalin ke clipboard.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FormTanyaAI.this, "Tidak ada jawaban untuk disalin.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(parent);
    }
}