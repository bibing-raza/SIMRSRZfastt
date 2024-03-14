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
public class WarnaTableKhusus2 extends DefaultTableCellRenderer {
    private int kolom = 23;
    private int kolom1 = 24;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1) {
            component.setBackground(new Color(244,255,255));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        if (!table.getValueAt(row, kolom).toString().equals("")) {
            component.setBackground(new Color(248,226,204));
        }
        
        if (akses.getcek_piutang() == true) {
            if (!table.getValueAt(row, kolom1).toString().equals("")) {
                component.setBackground(new Color(255,89,89));
            }
        }
        return component;
    }

}
