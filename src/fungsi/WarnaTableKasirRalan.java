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
public class WarnaTableKasirRalan extends DefaultTableCellRenderer {
    private int kolom1 = 16, kolom2 = 17, kolom3 = 18;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1) {
            component.setBackground(new Color(244,255,255));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }
        
        if (!table.getValueAt(row, kolom1).toString().equals("")) {
            component.setBackground(new Color(204,255,204));
        }
        
        if (!table.getValueAt(row, kolom3).toString().equals("0")) {
            component.setBackground(new Color(253, 215, 228));
        }
        
        if (!table.getValueAt(row, kolom2).toString().equals("")) {
            component.setBackground(new Color(204,255,204));
        } else if (!table.getValueAt(row, kolom2).toString().equals("") && !table.getValueAt(row, kolom3).toString().equals("0")) {
            component.setBackground(new Color(204,255,204));
        }
        return component;
    }

}
