package widget; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;

/**
 *
 * @author dosen3
 */
public final class ComboBox extends JComboBox {

    private static final int DIAMETER_SUDUT = 8;
    private static final Color WARNA_BORDER = new Color(0x14B8A6);

    public ComboBox() {
        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setBackground(new Color(250, 255, 245));
        setForeground(Color.BLACK);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setSize(WIDTH, 23);
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

    public boolean getSelectedItem(String meninggal__48_Jam) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
