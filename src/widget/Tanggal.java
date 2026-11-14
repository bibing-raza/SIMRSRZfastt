package widget;

import java.awt.Color;
import java.text.SimpleDateFormat;
import uz.ncipro.calendar.JDateTimePicker;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.Locale;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JPopupMenu;
import uz.ncipro.calendar.JCalendar;

/**
 *
 * @author khanzasoft
 */
public final class Tanggal extends JDateTimePicker {

    private static final int DIAMETER_SUDUT = 8;
    private static final Color WARNA_BORDER = new Color(0x14B8A6);

    public Tanggal() {
        super();
        setLocale(Locale.forLanguageTag("id-ID"));
        setForeground(Color.BLACK);
        setBackground(new Color(248, 253, 243));
        setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        setSize(WIDTH, 23);
        pasangTombolHariIni();
    }
    
    private void pasangTombolHariIni() {
        Object popupEditor = getUI().getAccessibleChild(this, 0);

        if (!(popupEditor instanceof JPopupMenu)) {
            return;
        }

        JPopupMenu popup = (JPopupMenu) popupEditor;

        for (Component komponen : popup.getComponents()) {
            if (komponen instanceof JCalendar) {
                JButton tombolHariIni = new JButton("Hari Ini");
                tombolHariIni.setFocusable(false);
                tombolHariIni.setRequestFocusEnabled(false);

                tombolHariIni.addActionListener(e -> {
                    setPopupVisible(false);
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        setDate(new Date());
                    });
                });

                JPanel panelBawah = new JPanel(
                        new FlowLayout(FlowLayout.CENTER, 0, 1)
                );
                panelBawah.setOpaque(false);
                panelBawah.add(tombolHariIni);

                JCalendar kalender = (JCalendar) komponen;
                kalender.add(panelBawah, BorderLayout.SOUTH);
                kalender.revalidate();
                return;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        int lebar = getWidth();
        int tinggi = getHeight();

        Graphics2D isi = (Graphics2D) g.create();
        try {
            isi.clip(new RoundRectangle2D.Double(
                    0, 0, lebar, tinggi,
                    DIAMETER_SUDUT, DIAMETER_SUDUT
            ));
            super.paint(isi);
        } finally {
            isi.dispose();
        }

        Graphics2D garis = (Graphics2D) g.create();
        try {
            garis.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            garis.setColor(WARNA_BORDER);
            garis.drawRoundRect(
                    0, 0, lebar - 1, tinggi - 1,
                    DIAMETER_SUDUT, DIAMETER_SUDUT
            );
        } finally {
            garis.dispose();
        }
    }

    public void setDate(String data_b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSelectedItem(String data_b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSelectedItem(SimpleDateFormat dateformat1, String data_b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
