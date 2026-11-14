package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;

/**
 *
 * @author usu
 */
public class PasswordBox extends usu.widget.glass.PasswordBox {

    private static final int DIAMETER_SUDUT = 8;
    private static final Color WARNA_BORDER = new Color(0x14B8A6);

    public PasswordBox() {
        super();
        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setForeground(Color.BLACK);
        setBackground(new Color(255, 255, 250));
        setSelectionColor(Color.WHITE);
        setSelectedTextColor(Color.RED);
        setCaretColor(Color.RED);
        setHorizontalAlignment(LEFT);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        setSize(WIDTH, 23);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            Graphics2D latar = (Graphics2D) g.create();
            try {
                latar.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                latar.setColor(getBackground());
                latar.fillRoundRect(
                        0, 0, getWidth(), getHeight(),
                        DIAMETER_SUDUT, DIAMETER_SUDUT
                );
            } finally {
                latar.dispose();
            }
        }

        Graphics isi = g.create();
        try {
            getUI().paint(isi, this);
        } finally {
            isi.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D garis = (Graphics2D) g.create();
        try {
            garis.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            garis.setColor(WARNA_BORDER);
            garis.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                    DIAMETER_SUDUT, DIAMETER_SUDUT);
        } finally {
            garis.dispose();
        }
    }
}
