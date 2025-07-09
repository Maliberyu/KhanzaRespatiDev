/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gemini;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public final class AudioRecord extends javax.swing.JDialog {
    
    public AudioRecord(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AudioRecord dialog = new AudioRecord(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
     
    }
    
    public void ProsesRekam(){
        try
        {
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000, 16, 2, 4, 16000, false);
            
            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            if(!AudioSystem.isLineSupported(dataInfo)){
                System.out.println("Tidak Support");
            }
            
            TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
            targetLine.open();
            
            JOptionPane.showMessageDialog(null, "Tekan Ok Untuk Memulai Record !!");
            targetLine.start();
            
            Thread audioRecorderThread = new Thread()
            {
                @Override public void run()
                {
                    AudioInputStream recordingStream = new AudioInputStream(targetLine);
                    File outputFile = new File("//172.17.115.254/media/Rekaman.wav");
                    try
                    {
                        AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
                    }
                    catch (IOException ex)
                    {
                        System.out.println(ex);
                    }
                    
                    System.out.println("Berhenti Merekam !");
                }
            };
            
            audioRecorderThread.start();
            JOptionPane.showMessageDialog(null, "Tekan Ok Untuk Berhenti Merekam !!");
            targetLine.stop();
            targetLine.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}
