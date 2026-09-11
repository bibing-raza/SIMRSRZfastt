/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTableRadiologi extends DefaultTableCellRenderer {
    private final int kolomWarna = 17;
    private final int kolomKategori = 18;

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // warna default tabel
        component.setForeground(Color.BLACK);

        if (row % 2 == 1) {
            component.setBackground(new Color(244, 255, 255));
        } else {
            component.setBackground(Color.WHITE);
        }

        // warna hanya diterapkan pada kolom 18 = Kategori Pelayanan
        if (column == kolomKategori) {
            Object objWarna = table.getValueAt(row, kolomWarna);
            if (objWarna != null) {
                String warna = objWarna.toString().trim();

                if (warna.equalsIgnoreCase("kuning")) {
                    component.setBackground(Color.YELLOW);
                    component.setForeground(Color.BLACK);

                } else if (warna.equalsIgnoreCase("hijau")) {
                    component.setBackground(Color.GREEN);
                    component.setForeground(Color.BLACK);
                }
            }
        }

        // jika baris sedang dipilih
        if (isSelected) {
            component.setForeground(table.getSelectionForeground());
        }

        return component;
    }
}