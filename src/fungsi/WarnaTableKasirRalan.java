/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTableKasirRalan extends DefaultTableCellRenderer {
    private int kolom1 = 16, kolom2 = 17, kolom3 = 18, kolom4 = 20, kolom5 = 21, kolom6 = 24, kolom7 = 23;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        component.setForeground(Color.BLACK);

        if (row % 2 == 1) {
            component.setBackground(new Color(244, 255, 255));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        if (!table.getValueAt(row, kolom1).toString().equals("")) {
            component.setBackground(new Color(204, 255, 204));
        }

        if (!table.getValueAt(row, kolom3).toString().equals("0")) {
            component.setBackground(new Color(253, 215, 228));
        }

        if (!table.getValueAt(row, kolom2).toString().equals("")) {
            component.setBackground(new Color(204, 255, 204));
        } else if (!table.getValueAt(row, kolom2).toString().equals("")
                && !table.getValueAt(row, kolom3).toString().equals("0")) {
            component.setBackground(new Color(204, 255, 204));
        }

        if (column == 6) {
            String kodepoli = table.getValueAt(row, kolom4).toString();
            String triaseIgd = table.getValueAt(row, kolom5).toString();
            String triasePediatrik = table.getValueAt(row, kolom6).toString();
            String triasePonek = table.getValueAt(row, kolom7).toString();

            if (kodepoli.equals("IGDK") || kodepoli.equals("PON")) {
                if (triaseIgd.equalsIgnoreCase("Merah") || triasePediatrik.equalsIgnoreCase("Merah") || triasePonek.equalsIgnoreCase("Merah")) {
                    component.setBackground(Color.RED);
                    component.setForeground(Color.WHITE);
                } else if (triaseIgd.equalsIgnoreCase("Kuning") || triasePediatrik.equalsIgnoreCase("Kuning") || triasePonek.equalsIgnoreCase("Kuning")) {
                    component.setBackground(Color.YELLOW);
                    component.setForeground(Color.BLACK);
                } else if (triaseIgd.equalsIgnoreCase("Hijau") || triasePediatrik.equalsIgnoreCase("Hijau") || triasePonek.equalsIgnoreCase("Hijau")) {
                    component.setBackground(Color.GREEN);
                    component.setForeground(Color.BLACK);
                } else if (triaseIgd.equalsIgnoreCase("Hitam") || triasePonek.equalsIgnoreCase("Hitam")) {
                    component.setBackground(Color.BLACK);
                    component.setForeground(Color.WHITE);
                }
            }
        }

        if (isSelected) {
//            component.setBackground(table.getSelectionBackground());
            component.setForeground(table.getSelectionForeground());
        }

        return component;
    }
}