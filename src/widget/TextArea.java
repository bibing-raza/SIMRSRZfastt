
package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author usu
 */
public class TextArea extends JTextArea {

    private static final int DIAMETER_SUDUT = 8;
    private static final Color WARNA_BORDER = new Color(0x14B8A6);

    public TextArea() {
        super();
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setBorder(new EmptyBorder(5, 6, 5, 6));
        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setSelectionColor(Color.WHITE);
        setSelectedTextColor(Color.RED);
        setForeground(Color.BLACK);
        setBackground(new Color(255, 255, 250));
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
            garis.drawRoundRect(
                    0, 0, getWidth() - 1, getHeight() - 1,
                    DIAMETER_SUDUT, DIAMETER_SUDUT
            );
        } finally {
            garis.dispose();
        }
    }
}
