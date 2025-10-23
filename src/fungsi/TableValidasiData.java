/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class TableValidasiData extends DefaultTableCellRenderer {
    private int kolom = 4;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1) {
//            component.setBackground(new Color(248,253,243));
            component.setBackground(new Color(244,255,255));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }
        
        if (table.getValueAt(row, kolom).toString().equals("Tidak Berlaku")) {
            component.setFont(component.getFont().deriveFont(Font.BOLD));
            component.setForeground(Color.RED);
        } else {
            component.setFont(component.getFont().deriveFont(Font.PLAIN));
            component.setForeground(Color.BLACK);
        }
        return component;
    }

}
