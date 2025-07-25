/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class WarnaTableRajal extends DefaultTableCellRenderer {
    
    public int kolom = 15;
    public int statusrawat = 10;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1){
            component.setBackground(new Color(230,240,255));
        }else{
            component.setBackground(new Color(255,255,255));
        }
        if (table.getValueAt(row, kolom).toString().equals("Sudah Bayar")) {
            component.setBackground(new Color(153, 255, 153));
        }
        if (table.getValueAt(row, statusrawat).toString().equals("Batal")) {
            component.setBackground(new Color(255, 102, 102));
        }
        if (table.getValueAt(row, statusrawat).toString().equals("Berkas Diterima")) {
            component.setBackground(new Color(204, 204, 255));
        }
        return component;
    }

}
